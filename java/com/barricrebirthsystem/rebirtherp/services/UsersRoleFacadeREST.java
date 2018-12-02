/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import com.barricrebirthsystem.rebirtherp.entities.Users;
import com.barricrebirthsystem.rebirtherp.entities.UsersRole;
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
@Path("usersrole")
public class UsersRoleFacadeREST extends AbstractFacade<UsersRole> {

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public UsersRoleFacadeREST() {
        super(UsersRole.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(UsersRole entity) {
        super.create(entity);
    }

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON})
    @Override
    public void edit(UsersRole entity) {
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
    public UsersRole find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
     @GET
    @Path("user/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public UsersRole findByUserid(@PathParam("id") Integer id) {
    try{ return    em.createNamedQuery("UsersRole.findByUser", UsersRole.class)
                .setParameter("uid", em.find(Users.class,id)).getSingleResult();
    }catch(Exception e){System.err.println("ERROR: "+e.getMessage()); return null;
    }
        //return super.find(id);
    }

    @GET
    @Override
    @Produces({ MediaType.APPLICATION_JSON})
    public List<UsersRole> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<UsersRole> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
