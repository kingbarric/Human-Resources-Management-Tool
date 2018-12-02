/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import com.barricrebirthsystem.rebirtherp.entities.Employee;
import com.barricrebirthsystem.rebirtherp.entities.LeaveApplication;
import com.barricrebirthsystem.rebirtherp.entities.LeaveApprovals;
import com.barricrebirthsystem.rebirtherp.entities.Notification;
import com.barricrebirthsystem.rebirtherp.util.ApprovalData;
import com.barricrebirthsystem.rebirtherp.util.UtilHelper;
import java.util.Date;
import java.util.HashMap;
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

/**
 *
 * @author Barima
 */
@Stateless
@Path("leaveapprovals")
public class LeaveApprovalsFacadeREST extends AbstractFacade<LeaveApprovals> {

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public LeaveApprovalsFacadeREST() {
        super(LeaveApprovals.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(LeaveApprovals entity) {
        super.create(entity);
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public HashMap<String, String> editE(ApprovalData data) {
        try {

            LeaveApprovals leave = em.find(LeaveApprovals.class,data.getId());
            
         //   leave.setLeaveId(leave.getLeaveId());
            
           // System.err.println("Sttatus "+leave.getApproval1Status()+" LEAVE  "+leave);
            String notiMsg = "";
            
            switch (data.getApproval_level()) {
                case 1:
                    leave.setApproval1Status(data.getStatus());
                    leave.setApproval1Date(new Date());
                    leave.setApprovedby1Id(new Employee(data.getApprovedby()));
                    leave.setComment1(data.getComment());
                    notiMsg = "Department head responded to your Leave Application "+leave.getLeaveId().getLeavecat().getName();
                
                    
                    break;
                case 2:
                     leave.setApproval2Status(data.getStatus());
                    leave.setApproval2Date(new Date());
                    leave.setApprovedby2Id(new Employee(data.getApprovedby()));
                    leave.setComment2(data.getComment());
                      //now for others
                     notiMsg = "HR responded to your Leave Application "+leave.getLeaveId().getLeavecat().getName();
                    break;
                case 3:
                     leave.setApproval3Status(data.getStatus());
                    leave.setApproval3Date(new Date());
                    leave.setApprovedby3Id(new Employee(data.getApprovedby()));
                    leave.setComment3(data.getComment());
                     notiMsg = "Manager responded to your Leave Application "+leave.getLeaveId().getLeavecat().getName();
                   
                    break;
                default:
                    break;
            }
            
            super.edit(leave);
            //Set notification
            Notification noti = new Notification();
            noti.setMessage(notiMsg);
            noti.setEmpid(leave.getLeaveId().getEmpid().getId());
            noti.setForAll("NO");
            noti.setIsSeen(false);
            noti.setDateCreated(new Date());
            em.persist(noti);
            
            
        } catch (Exception e) {
            System.err.println("ERr" + e);
            return UtilHelper.ErrorMessage();
        }

        return UtilHelper.SuccessMessage();
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public LeaveApprovals find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("leave/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public LeaveApprovals findByLeaveID(@PathParam("id") Integer id) {

        try {
            LeaveApprovals lv = (LeaveApprovals) em.createNamedQuery("LeaveApprovals.findByLeaveId")
                    .setParameter("leaveId", new LeaveApplication(id))
                    .getSingleResult();
            return lv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<LeaveApprovals> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<LeaveApprovals> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

}
