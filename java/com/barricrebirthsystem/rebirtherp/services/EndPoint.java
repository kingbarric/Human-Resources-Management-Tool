/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.SecurityContext;
import org.netbeans.rest.application.config.TokenValidation;

/**
 *
 * @author Barima
 */
@TokenValidation
@Path("/endpoint")
public class EndPoint {
     @Inject
    private SecurityContext secureContext;
     
     @GET
     public void callSecure(){
         
         String username = secureContext.getUserPrincipal().getName();
         System.out.println("SECURE: "+username);
         
     }
    
}
