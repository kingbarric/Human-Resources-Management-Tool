///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.barricrebirthsystem.rebirtherp.services;
//
///**
// *
// * @author Barima
// */
//
//import com.barricrebirthsystem.rebirtherp.util.UtilHelper;
//import com.sun.jersey.core.header.FormDataContentDisposition;
//import com.sun.jersey.multipart.FormDataParam;
//import java.io.File;
//import java.io.InputStream;
//import java.util.Calendar;
//import java.util.HashMap;
//import javax.inject.Inject;
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.ResponseBuilder;
//
//
///**
// *
// * @author Sapphital-003PC
// */
//@Path("imager")
//public class ImageUploader {
//
//    @Inject
//    private HttpServletRequest servletRequest;
//
//    @Inject
//    private ServletContext servletContext;
//
//    @POST
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response uploadFile(@FormDataParam("file") InputStream is,
//            @FormDataParam("file") FormDataContentDisposition formData, String pdfData) {
//        
//        try {
//            ServletContext servletContext = servletRequest.getServletContext();
//            String contextPath = servletContext.getRealPath(File.separator);
//
//            String shortName =  Calendar.getInstance().getTimeInMillis() + formData.getFileName();
//            String fileLocation = contextPath + shortName;
//            System.out.println("File Location: " + fileLocation);
//
//            if (new UtilHelper().saveFile(is, fileLocation)) {
//                HashMap m = new HashMap();
//             //   String imageUrl = UtilHelper.API_URL_BASE+"imager/getter/"+shortName;
//                String base64 = UtilHelper.base64Encoder(fileLocation);
//                m.put("imageUrl", base64);
//                if(!"".equals(base64)){
//                    //Delete the file after conversion to base 64
//                   new File(fileLocation).delete();
//                }
//              return  Response.status(Response.Status.CREATED).entity(m).build();
//                
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//         return  Response.status(Response.Status.CREATED).entity(null).build();
//    }
//
//
//    
//    
//    
//
//    @GET
//    @Path("/getter/{id}")
//    @Produces({"image/png", "image/jpg", "image/gif"})
//    public Response downloadImageFile(@PathParam("p") String p) {
//
//         ServletContext servletContext = servletRequest.getServletContext();
//            String contextPath = servletContext.getRealPath(File.separator);
//        // set file (and path) to be download
//        File file = new File(contextPath+p);
//
//        ResponseBuilder responseBuilder = Response.ok((Object) file);
//        responseBuilder.header("Content-Disposition", "attachment; filename=\""+file.getName()+"\"");
//        return responseBuilder.build();
//    }
//
//}
//
