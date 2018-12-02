/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import com.barricrebirthsystem.rebirtherp.entities.Notification;
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
@Path("notification")
public class NotificationFacadeREST extends AbstractFacade<Notification> {

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public NotificationFacadeREST() {
        super(Notification.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Notification entity) {
        super.create(entity);
    }

    @GET
    @Path("update/{id}")
    public void edit(@PathParam("id") Integer id) {
        Notification n = em.find(Notification.class, id);
        n.setIsSeen(true);
        super.edit(n);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Notification find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Notification> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Notification> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("recent/{eid}/{did}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Notification> findRecent(@PathParam(value = "eid") Integer eid, @PathParam(value = "did") Integer did) {
        return em.createNativeQuery("SELECT * FROM notification where empid=? OR department_id =? OR for_all = 'YES' order by id DESC limit 5", Notification.class)
                .setParameter(1, eid)
                .setParameter(2, did)
                .getResultList();
    }

    @GET
    @Path("all/{eid}/{did}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Notification> findAllByEmp(@PathParam(value = "eid") Integer eid, @PathParam(value = "did") Integer did) {
        return em.createNativeQuery("SELECT * FROM notification where empid=? OR department_id =? OR for_all = 'YES' order by id DESC", Notification.class)
                .setParameter(1, eid)
                .setParameter(2, did)
                .getResultList();
    }
    
       @GET
    @Path("notseen/{eid}/{did}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Notification> findAllByEmpNOTSeen(@PathParam(value = "eid") Integer eid, @PathParam(value = "did") Integer did) {
        return em.createNativeQuery("SELECT * FROM notification where ( empid=? OR department_id =? OR for_all = 'YES' ) AND is_seen = 0  order by id DESC ", Notification.class)
                .setParameter(1, eid)
                .setParameter(2, did)
                .getResultList();
    }

}
