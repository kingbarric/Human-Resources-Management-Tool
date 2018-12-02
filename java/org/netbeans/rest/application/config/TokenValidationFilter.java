package org.netbeans.rest.application.config;


import com.barricrebirthsystem.rebirtherp.services.LoginResource;
import java.io.IOException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import org.jose4j.base64url.internal.apache.commons.codec.binary.Base64;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

/**
 *
 * @author 
 */
@Provider
@TokenValidation
@Priority(Priorities.AUTHENTICATION)
public class TokenValidationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String token = requestContext.getHeaderString("Authorization");
        System.out.println(token);
        
        byte[] byteArray = Base64.decodeBase64(token.getBytes());
        String decodedToken = new String(byteArray);
        
        //if (token == null) {
            // Response for unauthorized user
            Response unauthoizedStatus = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User cannot access this resource")
                    .build();
            requestContext.abortWith(unauthoizedStatus);
      //  }

        // Validate Token's authenticity and check claims
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds(30)
                .setRequireSubject()
                .setExpectedIssuer("ssrng.com")
                .setDecryptionKey(LoginResource.getJwKey().getKey())
                .setVerificationKey(LoginResource.getJwKey().getKey()).build();

        try {
            // Validate the JWT and process it to the Claims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(decodedToken);
            final String subject = jwtClaims.getSubject();
//            int empID = jwtClaims.getClaimValue("EMPID", Integer.class);
//            int deptID = jwtClaims.getClaimValue("DEPTID", Integer.class);

            System.err.println("JWT: "+subject);
            if (subject != null) {
                final SecurityContext securityContext = requestContext.getSecurityContext();
                requestContext.setSecurityContext(new SecurityContext() {
                    @Override
                    public Principal getUserPrincipal() {
                        return new Principal() {
                           // @Override
                            public String equals() {
                                return subject;
                            }

                            @Override
                            public String getName() {
                              return  subject; //To change body of generated methods, choose Tools | Templates.
                            }
                          
                        };
                    }

                    @Override
                    public boolean isUserInRole(String role) {
                        return securityContext.isUserInRole(role);
                    }

                    @Override
                    public boolean isSecure() {
                        return securityContext.isSecure();
                    }

                    @Override
                    public String getAuthenticationScheme() {
                        return securityContext.getAuthenticationScheme();
                    }
                });
            }

        } catch (InvalidJwtException e) {
            // Response for unauthorized user
             unauthoizedStatus = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User cannot access the resource")
                    .build();
            requestContext.abortWith(unauthoizedStatus);
        } catch (MalformedClaimException ex) {
            Logger.getLogger(TokenValidationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}

