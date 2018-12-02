
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import com.barricrebirthsystem.rebirtherp.entities.Employee;
import com.barricrebirthsystem.rebirtherp.entities.LeaveApplication;
import com.barricrebirthsystem.rebirtherp.entities.LeaveApprovals;
import com.barricrebirthsystem.rebirtherp.util.LeaveAppJsonObject;
import com.barricrebirthsystem.rebirtherp.util.UtilHelper;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
@Path("leaveapplication")
public class LeaveApplicationFacadeREST extends AbstractFacade<LeaveApplication> {

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public LeaveApplicationFacadeREST() {
        super(LeaveApplication.class);
    }

    @POST
    //@Override
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public HashMap<String, String> createE(LeaveApplication entity) {
        try {

            long diff = entity.getEndDate().getTime() - entity.getStartDate().getTime();
            Long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            entity.setDurationInDays(days.doubleValue());
            super.create(entity);

        } catch (Exception e) {
            return UtilHelper.ErrorMessage();
        }

        return UtilHelper.SuccessMessage();
    }

    @PUT
    //@Override
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public HashMap<String, String> editE(LeaveApplication entity) {
        try {
            super.edit(entity);
        } catch (Exception e) {
            System.err.println("ERr" + e);
            return UtilHelper.ErrorMessage();
        }

        return UtilHelper.SuccessMessage();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public HashMap<String, String> remove(@PathParam("id") Integer id) {
        try {
            super.remove(super.find(id));
        } catch (Exception e) {
            return UtilHelper.ErrorMessage();
        }

        return UtilHelper.SuccessMessage();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public LeaveApplication find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<LeaveApplication> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<LeaveApplication> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("emp/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<LeaveApplication> findByEmp(@PathParam("id") Integer id) {
        return em.createNamedQuery("LeaveApplication.findByEmp", LeaveApplication.class)
                .setParameter("empid", new Employee(id))
                .getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @GET
    @Path("leaveid/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public LeaveAppJsonObject findLeave(@PathParam("id") Integer id) {
        try {
            LeaveApprovals lv = (LeaveApprovals) em.createNamedQuery("LeaveApprovals.findByLeaveId")
                    .setParameter("leaveId", new LeaveApplication(id))
                    .getSingleResult();

            String sql = "SELECT SUM(la.duration_in_days) as din FROM leave_approvals l  JOIN leave_application la ON(l.leave_id=la.id) \n"
                    + " WHERE la.leave_cat =" + lv.getLeaveId().getLeavecat().getId() + " AND l.approval3_status ='APPROVED' group by la.leave_cat ";

            Double daysTaken = 0.0;

            try {
                daysTaken = (Double) em.createNativeQuery(sql).setMaxResults(1).getSingleResult();
            } catch (Exception e) {
            }
            int daysAllowed = lv.getLeaveId().getLeavecat().getDurationInDays();
            Double daysAvai = daysAllowed - daysTaken;
            LeaveAppJsonObject obj = new LeaveAppJsonObject();
            obj.setLeaveApp(lv);
            obj.setDaysTaken(daysTaken.intValue());
            obj.setDaysRemain(daysAvai.intValue());
            boolean flag = daysAvai > 0;
            obj.setShouldApprove(flag);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR: " + e.getMessage());
            return null;
        }
    }
    
    


    @GET
    @Path("depthead/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<LeaveApplication> findByDeptHead(@PathParam("id") Integer id) {
        String sql = "SELECT e.* from deptemployee d  join department de on(d.DeptID = de.id) join leave_application e on(d.EmployeeID= e.emp_id) where de.department_head =" + id;
        return em.createNativeQuery(sql, LeaveApplication.class).getResultList();

    }

    @GET
    @Path("manager")
    @Produces({MediaType.APPLICATION_JSON})
    public List<LeaveApplication> findByManager() {
      String sql ="SELECT l.* FROM leave_application l join leave_approvals a on (l.id=a.leave_id) WHERE a.approval1_status='APPROVED'  AND a.approval2_status ='APPROVED'";
   return em.createNativeQuery(sql, LeaveApplication.class).getResultList();
    }

    @GET
    @Path("hr")
    @Produces({MediaType.APPLICATION_JSON})
    public List<LeaveApplication> findByHr() {
      String sql ="SELECT l.* FROM leave_application l join leave_approvals a on (l.id=a.leave_id) WHERE a.approval1_status='APPROVED' ";
   return em.createNativeQuery(sql, LeaveApplication.class).getResultList();
    }

}
