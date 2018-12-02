/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import com.barricrebirthsystem.rebirtherp.entities.Attendance;
import com.barricrebirthsystem.rebirtherp.entities.Employee;
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
@Path("attendance")
public class AttendanceFacadeREST extends AbstractFacade<Attendance> {

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public AttendanceFacadeREST() {
        super(Attendance.class);
    }
    
    
       @POST
    @Consumes({MediaType.APPLICATION_JSON})
        @Produces({MediaType.APPLICATION_JSON})
    public boolean clockIn(Attendance att) {
           if (!isClockedIn()) {
               try {
                   att.setAttendanceDate(new Date());
                   att.setClockIntime(new Date());
                   
                   this.create(att);
                   return true;
                   // return Response.status(Response.Status.CREATED).entity(UtilHelper.SUCCESS_MESSAGE).build();
               } catch (Exception e) {
                   return false;
                   // return Response.status(Response.Status.BAD_REQUEST).entity(UtilHelper.ERROR_MESSAGE).build();
               }
           } else {
               return false;
           }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Boolean clockOut(Attendance entity) {
        if (isClockedIn()) {
            try {
                
                Attendance atten = em.createNamedQuery("Attendance.findByAttendanceDate", Attendance.class)
                        .setParameter("attendanceDate", new Date()).getSingleResult();
                // att.setAttendanceDate(new Date());
                atten.setClockoutTime(new Date());
                atten.setReason(entity.getReason());
                super.edit(atten);
                return true;
               // return Response.status(Response.Status.CREATED).entity("Success").build();
            } catch (Exception e) {
                return false;
               // return Response.status(Response.Status.BAD_REQUEST).entity("Error occured").build();
            }
        }
        
        return false;
         // return Response.status(Response.Status.BAD_REQUEST).entity("Please clock in first").build();
        
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("isclockedin")
    public Boolean isClockedIn() {
        try {
            Attendance atten = em.createNamedQuery("Attendance.findByAttendanceDate", Attendance.class)
                    .setParameter("attendanceDate", new Date()).getSingleResult();
            return atten != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("isclockedout")
    public Boolean isClockedOut() {
        if (isClockedIn()) {
            try {
                Attendance atten = em.createNamedQuery("Attendance.findByAttendanceDate", Attendance.class)
                        .setParameter("attendanceDate", new Date()).getSingleResult();
                return atten.getClockoutTime() != null;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Attendance find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Attendance> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Attendance> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
     @GET
    @Path("emp/{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Attendance> findByEmp(@PathParam("id") Integer id) {
    return    em.createNamedQuery("Attendance.findByEmployeeId", Attendance.class)
            .setParameter("employeeId", new Employee(id))
                .getResultList();
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
