/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;
import com.barricrebirthsystem.rebirtherp.entities.FileObject;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
@Path("savefile") 
public class UploadFileService {
    
     @Context
    private HttpServletRequest servletRequest;    
    
    @Context 
    private ServletContext servletContext;
    
    
    
	@POST
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response uploadFile(@FormDataParam("upload") InputStream is, 
	                    @FormDataParam("upload") FormDataContentDisposition formData,
                            @FormDataParam("emp") String emp) {
       
            try {
                Gson json = new Gson();
                // Jsonb json = JsonbBuilder.create();
                FileObject f = json.fromJson(emp, FileObject.class);
                System.err.println("Object "+ f.getName());
            } catch (Exception jsonSyntaxException) {
                System.out.println("ERROR "+jsonSyntaxException);
            }
            
           
           
            System.out.println(emp);
		ServletContext servletContext = servletRequest.getServletContext();
		String contextPath = servletContext.getRealPath(File.separator);
            
            
            
            
            System.out.println("FORMDATA "+ emp);
		String fileLocation = contextPath+"/uploads/" + formData.getFileName();
		try {
			saveFile(is, fileLocation);
			String result = "Successfully File Uploaded on the path "+fileLocation;
			return Response.status(Status.OK).entity(result).build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	private void saveFile(InputStream is, String fileLocation) throws IOException {
	    	OutputStream os = new FileOutputStream(new File(fileLocation));
		byte[] buffer = new byte[256];
		int bytes = 0;
		while ((bytes = is.read(buffer)) != -1) {
		     os.write(buffer, 0, bytes);
		}
        }
} 