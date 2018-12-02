/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import com.barricrebirthsystem.rebirtherp.entities.Employee;
import com.barricrebirthsystem.rebirtherp.util.EmpAccount;
import com.barricrebirthsystem.rebirtherp.util.Messager;
import com.barricrebirthsystem.rebirtherp.util.UtilHelper;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.netbeans.rest.application.config.TokenValidation;

/**
 *
 * @author Barima
 */
@Stateless
@TokenValidation
@Path("/employee")
public class EmployeeFacadeREST extends AbstractFacade<Employee> {

    
//    @Context
//    private SecurityContext secureContext;
     
    @Context
    private static HttpServletRequest servletRequest;
    @Context
    private ServletContext servletContext;

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    

    public EmployeeFacadeREST() {
        super(Employee.class);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createEmp(Employee entity) {
        try {
            System.err.println(entity);
            super.create(entity);
            return Response.status(Response.Status.ACCEPTED).entity(new Messager(0, Messager.success)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.ACCEPTED).entity(new Messager(1, Messager.error)).build();
        }
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editE(Employee entity) {
        try {
            super.edit(entity);
            return Response.status(Response.Status.ACCEPTED).entity(new Messager(0, Messager.success)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.ACCEPTED).entity(new Messager(1, Messager.error)).build();
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
    public Employee find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Employee> findAll() {
        return super.findAll();
    }

    @GET
    @Path("justemployee")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Employee> findJustEmployee() {
        //List<Employee> e =  em.createNamedQuery("Employee.findAll").getResultList();
        List<Employee> e = em.createNativeQuery("SELECT * FROM employee", Employee.class).getResultList();
        return e;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Employee> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("updateaccount")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editAccount(EmpAccount emp) {

        System.err.println("ERRRRRR "+emp);
        try {
            Employee entity = super.find(emp.getId());
            entity.setAccountNo(emp.getAccountNo());
            entity.setBankName(emp.getBankName());
            entity.setGrossSalary(emp.getGrossSalary());
            entity.setGla(emp.getGla());
            entity.setNoLeaveDays(emp.getNoLeaveDays());
            entity.setPension(emp.isPension());
            entity.setNhf(emp.isNhf());
            entity.setNhis(emp.isNhis());
            super.edit(entity);
            return Response.status(Response.Status.ACCEPTED).entity(new Messager(0, Messager.success)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.ACCEPTED).entity(new Messager(1, Messager.error)).build();
        }
    }

    @POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("uploadimage")
    public Response uploadFile(@FormDataParam("upload") InputStream is,
            @FormDataParam("upload") FormDataContentDisposition formData,
            @FormDataParam("data") String data) {
        System.err.println("DATA: " + data);
 HashMap m = new HashMap();
  m.put("status", "Unsuccessful");
        servletContext = servletRequest.getServletContext();
        String contextPath = servletContext.getRealPath(File.separator);
        String fileName = Calendar.getInstance().getTimeInMillis() + formData.getFileName();

        String location = contextPath + "uploads\\" + fileName;
        boolean flag;
        if (!new File(contextPath + "uploads").exists()) {
            flag = new File(contextPath + "uploads").mkdir();
        } else {
            flag = true;
        }

        if (flag) {
            if (UtilHelper.saveFile(is, location)) {

                try {

                    Employee e = em.find(Employee.class, Integer.parseInt(data));
                    String oldImg = contextPath + "uploads\\" + e.getImagename();
                    
                    
                    e.setImagename(fileName);
                    super.edit(e);
                    // super.create(f);
                    //Delete old image
                    File olF = new File(oldImg);
                    if(olF.exists()){
                      new File(oldImg).delete();  
                    }
                    
                    
            m.put("status", "Successful");
                    return Response.status(Response.Status.OK).entity(m).build();
                } catch (Exception exp) {
                    System.err.println("Err"+exp);
                   
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(m).build();
                }

            } else {
              
                return Response.status(Response.Status.BAD_REQUEST).entity(m).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(m).build();
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("image/{empid}")
    public Response getImage64(@PathParam("empid") Integer empid) {

        try {
            
   //  JwtClaims jwt =       jwtClaims.getClaimValue("", String.class);
//           String token = headers.getRequestHeader("Authorization").get(0);
//           System.out.println("token: "+token);

// String username = secureContext.getUserPrincipal().getName();
//            System.err.println("USERNAME: "+username);
// int empid = 1;
            
            ServletContext servletContext = servletRequest.getServletContext();
            String contextPath = servletContext.getRealPath(File.separator);

            Employee e = super.find(empid);
            String fileLocation = contextPath + "uploads\\" + e.getImagename();
            HashMap m = new HashMap();
            String base64 = UtilHelper.base64Encoder(fileLocation);
            m.put("imager", base64);
            return Response.status(Response.Status.CREATED).entity(m).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.CREATED).entity(null).build();
        }

    }

}
