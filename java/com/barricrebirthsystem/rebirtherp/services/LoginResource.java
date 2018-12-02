package com.barricrebirthsystem.rebirtherp.services;




import com.barricrebirthsystem.rebirtherp.entities.DeptEmployee;
import com.barricrebirthsystem.rebirtherp.entities.Users;
import com.barricrebirthsystem.rebirtherp.entities.UsersRole;
import com.barricrebirthsystem.rebirtherp.util.UserRole;
//import com.sapphital_erp_api.response_message.StaffParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.stream.JsonGenerationException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import org.codehaus.jackson.map.JsonMappingException;
import org.jose4j.base64url.internal.apache.commons.codec.binary.Base64;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKey.Factory;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.mindrot.jbcrypt.BCrypt;
import org.netbeans.rest.application.config.StatusMessage;

/**
 *
 * @author SAPPHITAL-004
 */
@Stateless
@Path("/login")
public class LoginResource extends AbstractFacade<Users>  {
     @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;
//      @Inject
//    private SecurityContext securityContext;

    private static JsonWebKey jwKey = null;
   
    @EJB
   private UsersFacadeREST userbaen;

    static {
        // Setting up Direct Symmetric Encryption and Decryption
        String jwkJson = "{\"kty\":\"oct\",\"k\":\"9d6722d6-b45c-4dcb-bd73-2e057c44eb93-928390\"}";

        try {
            new JsonWebKey.Factory();
            jwKey = Factory.newJwk(jwkJson);
        } catch (JoseException e) {
            e.printStackTrace();
        }
    }

    public static JsonWebKey getJwKey() {
        return jwKey;
    }

    public static void setJwKey(JsonWebKey jwKey) {
        LoginResource.jwKey = jwKey;
    }

    public LoginResource() {
        super(Users.class);
    }

    /**
     * Authenticate a user and issue a token to the user if authentication is
     * successful
     *
     * @param username
     * @param password
     * @param staffParam
     * @return a response containing the a status code and and token
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    @POST
    @Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    @Consumes(value=MediaType.APPLICATION_FORM_URLENCODED )
    public Response authenticateCredentials(@FormParam("username") String username,
            @FormParam("password") String password )
            throws JsonGenerationException, JsonMappingException, IOException {
        
      //  String username = staffParam.getUsername();
        //String password = staffParam.getPassword();

        System.err.println(" PARAMS: "+username +" "+password);
        // Check if username is null
        if (username == null) {
            StatusMessage statusMessage = new StatusMessage();
            statusMessage.setStatus(Response.Status.PRECONDITION_FAILED.getStatusCode());
            statusMessage.setMessage("Username value is missing!!!");

            return Response
                    .status(Response.Status.PRECONDITION_FAILED.getStatusCode())
                    .entity(statusMessage).build();
        }

        // Check if password is null
        if (password == null) {
            StatusMessage statusMessage = new StatusMessage();
            statusMessage.setStatus(Response.Status.PRECONDITION_FAILED.getStatusCode());
            statusMessage.setMessage("Password value is missing!!!");

            return Response
                    .status(Response.Status.PRECONDITION_FAILED.getStatusCode())
                    .entity(statusMessage).build();
        }
        try {
            System.err.println("IN BLOCK: ");
            // Authenticate the user
            Users staff = this.findUser(username, password);
            System.out.println("STAFF: "+staff);

            if (staff == null) {
                StatusMessage statusMessage = new StatusMessage();
                statusMessage.setStatus(Response.Status.FORBIDDEN.getStatusCode());
                statusMessage.setMessage("Incorrect username or password!!!");

                return Response.status(Response.Status.FORBIDDEN.getStatusCode())
                        .entity(statusMessage).build();
            }
 StatusMessage statusMessage = new StatusMessage();
                  UsersRole role = em.createNamedQuery("UsersRole.findByUser", UsersRole.class)
                    .setParameter("uid", new Users(staff.getId())).getSingleResult();
            //role.setEmpId(null);
            //role.setUserid(null);
            DeptEmployee dept = em.createNamedQuery("DeptEmployee.findByEmpid", DeptEmployee.class)
                    .setParameter("id", staff.getEmpid()).getSingleResult();
            statusMessage.setRole(getRole(role));
            // Issue a token to the user
            String token = issueToken(username,staff.getEmpid().getId(),dept.getDeptID().getId(),getRole(role));
         
           
            statusMessage.setStatus(Response.Status.OK.getStatusCode());
            statusMessage.setMessage(token);
            statusMessage.setUsername(username);
      
            statusMessage.setDidd(dept.getDeptID().getId());
            statusMessage.setUidd(staff.getEmpid().getId());
                System.out.println(staff.getEmpid().getId());
                
            return Response.status(200).entity(statusMessage).build();

        } catch (Exception e) {
            System.err.println(" ERROR :"+e);
            return Response.status(UNAUTHORIZED).build();
        }
    }

    /**
     * Authenticate the user trying to log in
     *
     * @param username of the user
     * @param password of the user
     * @return
     */
  

    /**
     * Issue a token to the authenticated user
     *
     * @param username of the authenticated user
     * @return a JWT
     */
    private String issueToken(String username,int empid,int deptid,UserRole role) {
        // Create the Claims, which will be the content of the JWT
        JwtClaims claims = new JwtClaims();
        claims.setIssuer("ssrng.com");
        claims.setExpirationTimeMinutesInTheFuture(60);
        claims.setGeneratedJwtId();
        claims.setIssuedAtToNow();
        claims.setNotBeforeMinutesInThePast(2);
        claims.setSubject(username);
        claims.setClaim("EMPID", empid);
        claims.setClaim("DEPTID", deptid);
     claims.setClaim("roles", role);
//        claims.setClaim("role", "Admin");

        JsonWebSignature jws = new JsonWebSignature();

        // The payload of the JWS is JSON content of the JWT Claims
        jws.setPayload(claims.toJson());
        
        jws.setKeyIdHeaderValue(jwKey.getKeyId());
        jws.setKey(jwKey.getKey());

        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        String jwt = null;

        try {
            jwt = jws.getCompactSerialization();
        } catch (JoseException e) {
            e.printStackTrace();
        }

        JsonWebEncryption jwe = new JsonWebEncryption();
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.DIRECT);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);

        jwe.setKey(jwKey.getKey());
        jwe.setKeyIdHeaderValue(jwKey.getKeyId());
        jwe.setContentTypeHeaderValue("JWT");
        jwe.setPayload(jwt);
        String jweSerialization = null;

        try {
            jweSerialization = jwe.getCompactSerialization();
        } catch (JoseException e) {
            e.printStackTrace();
        }

        StatusMessage statusMessage = new StatusMessage();
        statusMessage.setStatus(Response.Status.OK.getStatusCode());
        statusMessage.setMessage(jweSerialization);
        System.out.println("JWT: "+jwt);
        return jwt;
    }

  
    
       public Users findUser(String username, String password){
     
        try {
           
            Users user =  em.createNamedQuery("Users.findByUsername", Users.class)
                   .setParameter("username", username)
                    .getSingleResult();
             System.err.println("Found "+user);
            
            if(this.checkPass(password, user.getPassword())){
            return user;
            }else return null;
            
        } catch (Exception e) {
            System.err.println("EXC "+e);
            return null;
        }
    }
       
       private boolean checkPass(String plainPassword, String hashedPassword) {
	return	BCrypt.checkpw(plainPassword, hashedPassword);
			
	}

    @Override
    protected EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     UserRole getRole(UsersRole u){
        UserRole user = new UserRole();
        user.setMenu1(u.getMenu1());
         user.setMenu2(u.getMenu2());
          user.setMenu3(u.getMenu3());
           user.setMenu4(u.getMenu4());
            user.setMenu5(u.getMenu5());
             user.setMenu6(u.getMenu6());
             user.setMenu7(u.getMenu7());
              user.setMenu8(u.getMenu8());
               user.setMenu9(u.getMenu9());
                user.setMenu10(u.getMenu10());
                 user.setMenu11(u.getMenu11());
                  user.setMenu12(u.getMenu12());
                   user.setMenu13(u.getMenu3());
                     user.setRoleLevel(u.getRoleLevel());
                      
                     return user;
    }
     
     
    @GET
    @Path("sec/{token}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAdmin(@PathParam("token")String token){
            Map m = new HashMap();
        try {
            byte[] byteArray = Base64.decodeBase64(token.getBytes());
            String decodedToken = new String(byteArray);
            JwtConsumer jwtConsumer = new JwtConsumerBuilder().build();
            JwtClaims jwtClaims = jwtConsumer.processToClaims(decodedToken);
            final String subject = jwtClaims.getSubject();
        int empid=(Integer)jwtClaims.getClaimValue("EMPID");
          int deptid=(Integer)jwtClaims.getClaimValue("DEPTID");
          UserRole role = (UserRole)jwtClaims.getClaimValue("roles");
          m.put("empid", empid);
          m.put("deptid", deptid);
          m.put("roles", role);
            System.err.println("JWT "+m);
          return Response.ok(m).build();
       
        } catch (MalformedClaimException |InvalidJwtException malformedClaimException) {
            
            System.err.println("Error from jwt claims: "+malformedClaimException);
           // return null;
        }
       // m.put("status","error");
     return   Response.serverError().build();
    
    }
     
}
