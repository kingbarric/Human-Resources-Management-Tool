/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("filer")
public class FileUploader {

	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public Response uploadFile(@MultipartForm FileUploadForm form) {

		String fileName = "c:\\zuploader";
		
            try {
                writeFile(form.getData(), fileName);
            } catch (IOException ex) {
                Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
            }

		System.out.println("Done");

		return Response.status(200)
		    .entity("uploadFile is called, Uploaded file name : " + fileName).build();

	}

	// save to somewhere
	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}
}