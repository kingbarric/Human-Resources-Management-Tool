/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import com.barricrebirthsystem.rebirtherp.entities.PrSalaryAllowance;
import com.barricrebirthsystem.rebirtherp.entities.PrSalarytemplate;
import com.barricrebirthsystem.rebirtherp.services.AbstractFacade;
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
@Path("prsalaryallowance")
public class PrSalaryAllowanceFacadeREST extends AbstractFacade<PrSalaryAllowance> {

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PrSalaryAllowanceFacadeREST() {
        super(PrSalaryAllowance.class);
    }

    @POST
    @Override
    @Consumes({ MediaType.APPLICATION_JSON})
    public void create(PrSalaryAllowance entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, PrSalaryAllowance entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public PrSalaryAllowance find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
      @GET
    @Path("temp/{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<PrSalaryAllowance> findByTemplate(@PathParam("id") Integer id) {
      List<PrSalaryAllowance> l =  em.createNamedQuery("PrSalaryAllowance.findByTemplate").setParameter("tempid", new PrSalarytemplate(id)).getResultList();
        return l;
    }

    @GET
    @Override
    @Produces({ MediaType.APPLICATION_JSON})
    public List<PrSalaryAllowance> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PrSalaryAllowance> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
