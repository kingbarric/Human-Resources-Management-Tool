/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import com.barricrebirthsystem.rebirtherp.entities.Employee;
import com.barricrebirthsystem.rebirtherp.entities.Expenses;
import com.barricrebirthsystem.rebirtherp.entities.LeaveApprovals;
import com.barricrebirthsystem.rebirtherp.entities.Notification;
import com.barricrebirthsystem.rebirtherp.util.ApprovalData;
import com.barricrebirthsystem.rebirtherp.util.UtilHelper;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Barima
 */
@Stateless
@Path("expenses")
public class ExpensesFacadeREST extends AbstractFacade<Expenses> {

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ExpensesFacadeREST() {
        super(Expenses.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Expenses entity) {
        entity.setApproval1("PENDING");
        entity.setApproval2("PENDING");
        entity.setApproval3("PENDING");
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Expenses entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Expenses find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Expenses> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Expenses> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editE(ApprovalData data) {
        try {

            Expenses leave = em.find(Expenses.class, data.getId());
            String notiMsg = "";

            switch (data.getApproval_level()) {
                case 1:
                    leave.setApproval1(data.getStatus());
                    leave.setApproval1Date(new Date());
                    leave.setApprovedby1(new Employee(data.getApprovedby()));
                    leave.setComment1(data.getComment());
                    notiMsg = "Department head responded to your Expenses raised ' => "+leave.getDescription();

                    break;
                case 2:
                    leave.setApproval2(data.getStatus());
                    leave.setApproval2Date(new Date());
                    leave.setApprovedby2(new Employee(data.getApprovedby()));
                    leave.setComment2(data.getComment());
                    //now for others
                    notiMsg = " HR responded to your Expenses raised => "+leave.getDescription();
                    break;
                case 3:
                    leave.setApproval3(data.getStatus());
                    leave.setApproval3Date(new Date());
                    leave.setApprovedby3(new Employee(data.getApprovedby()));
                    leave.setComment3(data.getComment());
                    notiMsg = " Manager responded to your Expenses raised => "+leave.getDescription();
                    break;
                default:
                    break;
            }

            super.edit(leave);

            Notification noti = new Notification();
            noti.setMessage(notiMsg);
            noti.setEmpid(leave.getInitiatedby().getId());
            noti.setDateCreated(new Date());
            noti.setForAll("NO");
            noti.setIsSeen(false);
            em.persist(noti);

        } catch (Exception e) {
            System.err.println("ERr" + e);
            return Response.status(Response.Status.BAD_REQUEST).entity(UtilHelper.ERROR_MESSAGE).build();
        }

        return Response.status(Response.Status.CREATED).entity(UtilHelper.SUCCESS_MESSAGE).build();
    }

    @GET
    @Path("emp/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Expenses> findByEmp(@PathParam("id") Integer id) {
        System.err.println("IDS: "+id);
        return em.createNamedQuery("Expenses.findByInitiatedBy", Expenses.class)
                .setParameter("empid", new Employee(id))
                .getResultList();
    }

    @GET
    @Path("depthead/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Expenses> findByDeptHead(@PathParam("id") Integer id) {
        String sql = "SELECT e.* from deptemployee d  join department de on(d.DeptID = de.id) join expenses e on(d.EmployeeID= e.initiatedby) where de.department_head = " + id;
        return em.createNativeQuery(sql, Expenses.class).getResultList();

    }

    @GET
    @Path("hr")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Expenses> findByHR() {
        return em.createNamedQuery("Expenses.findByApproval1", Expenses.class)
                .setParameter("approval1", "APPROVED")
                .getResultList();
    }

    @GET
    @Path("manager")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Expenses> findByManager() {
        return em.createNamedQuery("Expenses.findByApproval1AND2", Expenses.class)
                .setParameter("approval1", "APPROVED")
                .setParameter("approval2", "APPROVED")
                .getResultList();
    }

}
