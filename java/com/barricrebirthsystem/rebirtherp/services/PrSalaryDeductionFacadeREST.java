/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import com.barricrebirthsystem.rebirtherp.entities.PrSalaryDeduction;
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
@Path("prsalarydeductions")
public class PrSalaryDeductionFacadeREST extends AbstractFacade<PrSalaryDeduction> {

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PrSalaryDeductionFacadeREST() {
        super(PrSalaryDeduction.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(PrSalaryDeduction entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, PrSalaryDeduction entity) {
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
    public PrSalaryDeduction find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    
    @GET
    @Path("temp/{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<PrSalaryDeduction> findByTemp(@PathParam("id") Integer id) {
        List<PrSalaryDeduction> l =  em.createNamedQuery("PrSalaryDeduction.findByTemp").setParameter("temp", new PrSalarytemplate(id)).getResultList();
        return l;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<PrSalaryDeduction> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PrSalaryDeduction> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
