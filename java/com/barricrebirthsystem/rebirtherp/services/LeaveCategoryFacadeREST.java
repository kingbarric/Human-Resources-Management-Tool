/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;


import com.barricrebirthsystem.rebirtherp.entities.LeaveCategory;
import com.barricrebirthsystem.rebirtherp.util.UtilHelper;
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
@Path("leavecategory")
public class LeaveCategoryFacadeREST extends AbstractFacade<LeaveCategory> {

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public LeaveCategoryFacadeREST() {
        super(LeaveCategory.class);
    }

     @POST
    //@Override
    @Produces({ MediaType.APPLICATION_JSON})
    @Consumes({ MediaType.APPLICATION_JSON})
    public HashMap<String, String> createE(LeaveCategory entity) {
       try{super.create(entity);}catch(Exception e){
        return UtilHelper.ErrorMessage();
        }
        
        return UtilHelper.SuccessMessage();
    }

    @PUT
    //@Override
     @Produces({ MediaType.APPLICATION_JSON})
    @Consumes({ MediaType.APPLICATION_JSON})
    public HashMap<String, String> editE( LeaveCategory entity) {
        try{
            super.edit(entity);
        }catch(Exception e){
        return UtilHelper.ErrorMessage();
        }
        
        return UtilHelper.SuccessMessage();
    }

    @DELETE
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public HashMap<String, String> remove(@PathParam("id") Integer id) {
        try{
        super.remove(super.find(id));
         }catch(Exception e){
        return UtilHelper.ErrorMessage();
        }
        
        return UtilHelper.SuccessMessage();
    }
    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public LeaveCategory find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({ MediaType.APPLICATION_JSON})
    public List<LeaveCategory> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<LeaveCategory> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
