/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import com.barricrebirthsystem.rebirtherp.entities.PrAdvanceSalary;
import com.barricrebirthsystem.rebirtherp.entities.PrManagedSalary;
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

/**
 *
 * @author Barima
 */
@Stateless
@Path("pradvancesalary")
public class PrAdvanceSalaryFacadeREST extends AbstractFacade<PrAdvanceSalary> {

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PrAdvanceSalaryFacadeREST() {
        super(PrAdvanceSalary.class);
    }

    @POST
    @Override
    @Consumes({ MediaType.APPLICATION_JSON})
    public void create(PrAdvanceSalary entity) {
        entity.setCreatedOn(new Date());
        entity.setIspaid("NO");
        
       
        super.create(entity);
    }

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON})
    @Override
    public void edit( PrAdvanceSalary entity) {
         System.err.println(entity.getId());
         PrAdvanceSalary pp = em.createNamedQuery("PrAdvanceSalary.findById", PrAdvanceSalary.class)
                 .setParameter("id", entity.getId()).getSingleResult();
        // PrManagedSalary ppp = em.find(PrManagedSalary.class, 2);
         pp.setDeductedMonth(entity.getDeductedMonth());
         pp.setIspaid(entity.getIspaid());
         
      //   System.out.println("NEW "+pp);
        super.edit(pp);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public PrAdvanceSalary find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({ MediaType.APPLICATION_JSON})
    public List<PrAdvanceSalary> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PrAdvanceSalary> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
