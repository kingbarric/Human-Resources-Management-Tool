/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import com.barricrebirthsystem.rebirtherp.entities.Department;
import com.barricrebirthsystem.rebirtherp.entities.DeptEmployee;
import com.barricrebirthsystem.rebirtherp.entities.Employee;
import com.barricrebirthsystem.rebirtherp.util.DeptEmp;
import com.barricrebirthsystem.rebirtherp.util.UtilHelper;
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
@Path("deptemployee")
public class DeptEmployeeFacadeREST extends AbstractFacade<DeptEmployee> {

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public DeptEmployeeFacadeREST() {
        super(DeptEmployee.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(DeptEmp entity) {
        try {
          //  System.err.println("ENTITY " + entity.getDeptID() + " EMP: " + entity.getEmployeeID());
            DeptEmployee e = new DeptEmployee();
            e.setEmployeeID(em.find(Employee.class, entity.getEmployeeID()));
            e.setDeptID(em.find(Department.class, entity.getDeptID()));
            
            super.create(e);
            return Response.status(Response.Status.OK).entity(UtilHelper.publishSuccessMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.OK).entity(UtilHelper.publishErrorMessage()).build();
        }
    }

     @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(DeptEmp entity) {
        try {
           
            
         int i =    em.createNativeQuery("UPDATE deptemployee SET DeptID = ? WHERE EmployeeID = ?")
                    .setParameter(1, entity.getDeptID())
                    .setParameter(2, entity.getEmployeeID())
                    .executeUpdate();
           if(i>0){
            return Response.status(Response.Status.OK).entity(UtilHelper.publishSuccessMessage()).build();
           }else{
            return Response.status(Response.Status.OK).entity(UtilHelper.publishErrorMessage()).build();
           }
           
        } catch (Exception e) {
            System.err.println("ERROR"+e);
            return Response.status(Response.Status.OK).entity(UtilHelper.publishErrorMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public DeptEmployee find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    
    @GET
    @Path("byemployee/{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public DeptEmployee findbyEmpID(@PathParam("id") Integer id){
        
        DeptEmployee de = null;
        try {
            de = (DeptEmployee) em.createNamedQuery("DeptEmployee.findByEmpid").
                    setParameter("id", new Employee(id)).getSingleResult();
        } catch (Exception e) {
            System.err.println("Error "+e.getMessage());
            
        }
      return de;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    @GET
    @Path("bydepartment/{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<DeptEmployee> findbyDeptID(@PathParam("id") Integer id){
        
        List<DeptEmployee> de = null;
        try {
            de =  em.createNamedQuery("DeptEmployee.findByDeptId").
                    setParameter("id", new Department(id)).getResultList();
        } catch (Exception e) {
            System.err.println("Error "+e.getMessage());
            
        }
      return de;
    }

    @GET
    @Override
    @Produces({ MediaType.APPLICATION_JSON})
    public List<DeptEmployee> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<DeptEmployee> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
