/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import com.barricrebirthsystem.rebirtherp.entities.DeptEmployee;
import com.barricrebirthsystem.rebirtherp.entities.Employee;
import com.barricrebirthsystem.rebirtherp.entities.FileArchive;
import com.barricrebirthsystem.rebirtherp.entities.FileObject;
import com.barricrebirthsystem.rebirtherp.util.UtilHelper;
import com.google.gson.Gson;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
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

/**
 *
 * @author Barima
 */
@Stateless
@Path("filearchive")
public class FileArchiveFacadeREST extends AbstractFacade<FileArchive> {

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @Context
    private static HttpServletRequest servletRequest; 
    @Context 
    private ServletContext servletContext;

    public FileArchiveFacadeREST() {
        super(FileArchive.class);
    }

    @POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
   public Response uploadFile(@FormDataParam("upload") InputStream is, 
	                    @FormDataParam("upload") FormDataContentDisposition formData,
                            @FormDataParam("data") String data) {
       
          servletContext = servletRequest.getServletContext();
		String contextPath = servletContext.getRealPath(File.separator);
       
         String filename = contextPath+"uploads\\"+Calendar.getInstance().getTimeInMillis()+formData.getFileName();
         String fileP = Calendar.getInstance().getTimeInMillis()+formData.getFileName();
         boolean flag = false;
         if(!new File(contextPath+"uploads\\").exists()){
      flag =   new File(contextPath+"uploads\\").mkdir();
         }else{
         flag = true;
         }
         
         if(flag){
            if(UtilHelper.saveFile(is, filename))
            {
            
                   try {
                Gson json = new Gson();
              
                FileArchive f = json.fromJson(data, FileArchive.class);
                f.setContenttype(formData.getName());
                f.setFilepath(fileP);
                super.create(f);
               
                return Response.status(Response.Status.OK).entity(UtilHelper.publishSuccessMessage()).build();
            } catch (Exception exp) {
               return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(UtilHelper.publishErrorMessage()).build();
            }
                
            }else{
            return Response.status(Response.Status.BAD_REQUEST).entity(UtilHelper.publishErrorMessage()).build();
            }
         }else{
             return Response.status(Response.Status.BAD_REQUEST).entity(UtilHelper.publishErrorMessage()).build();
         }
		
	}

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, FileArchive entity) {
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
    public List<FileArchive> find(@PathParam("id") Integer id) {
        DeptEmployee de = null;
        List<FileArchive> att = null;
        try {
            de = (DeptEmployee) em.createNamedQuery("DeptEmployee.findByEmpid").
                    setParameter("id", new Employee(id)).getSingleResult();

            att = em.createNativeQuery("SELECT * FROM file_archive WHERE owner = ? OR accesslevel=3 OR accesslevel=2 AND ownerdept = ? ORDER BY dateuploaded DESC ", FileArchive.class).
                    setParameter(1, de.getId()).setParameter(2, de.getDeptID().getId()).getResultList();
            return att;

        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());

        }
        return att;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<FileArchive> findAll() {

        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<FileArchive> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    
    @GET
    @Path("download/{id}")
    public Response downloadFile(@PathParam("id") int id){
          servletContext = servletRequest.getServletContext();
		String contextPath = servletContext.getRealPath(File.separator);
                FileArchive ar = em.find(FileArchive.class, id);
                if(ar!=null){
                    File file = new File(contextPath+ar.getFilepath());
                    Response.ResponseBuilder response = Response.ok((Object) file);
                    response.header("Content-Disposition", "attatchment; filename=\""+file.getName()+"\"");
                    return response.build();
                }else{
                    return Response.noContent().build();
                }
    }
}
