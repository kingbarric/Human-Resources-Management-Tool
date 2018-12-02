/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import com.barricrebirthsystem.rebirtherp.entities.Employee;
import com.barricrebirthsystem.rebirtherp.entities.Users;
import com.barricrebirthsystem.rebirtherp.util.UserProfile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Barima
 */
@Stateless
@Path("users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Override
    public void create(Users entity) {
        String defaultPassword = "Password1";
        entity.setPassword(this.hashPassword(defaultPassword));
        super.create(entity);
    }

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Override
    public void edit(Users entity) {
        super.edit(entity);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updatep")
    public Response updatePassword(UserProfile entity) {
        System.err.println("USER: "+entity);
        Map m = new HashMap();
      Users u =  em.createNamedQuery("Users.findByEmpid", Users.class)
              .setParameter("empid", new Employee(entity.getId()))
              .getSingleResult();
        
        System.out.println("USER: "+u);
        if (BCrypt.checkpw(entity.getOldPassword(), u.getPassword())) {
            u.setPassword(this.hashPassword(entity.getNewPassword()));
            super.edit(u);
            m.put("status", " Password Updated Successfully");
            m.put("code", 0);
            return Response.status(Response.Status.CREATED).entity(m).build();
        } else {
             m.put("code", 1);
            m.put("status", "Error => Current password do not match password provided");
            return Response.status(Response.Status.CREATED).entity(m).build();
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
    public Users find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Users> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

//    @POST
//    @Consumes({ MediaType.APPLICATION_JSON})
//    @Path("login")
    public Users findUser(
            //  @HeaderParam("username")
            String username,
            //   @HeaderParam("password")
            String password) {
        System.out.println(" Before return");
        try {

            return em.createNamedQuery("Users.findByUsernameAndPassword", Users.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();

        } catch (Exception e) {
            System.err.println("EXC " + e);
            return null;
        }
    }

}
