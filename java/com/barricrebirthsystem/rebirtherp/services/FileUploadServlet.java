/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;

import java.io.IOException;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.commons.fileupload.FileItem;

import org.apache.commons.fileupload.FileItemFactory;

import org.apache.commons.fileupload.FileUploadException;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Barima
 */
@WebServlet(name = "FileUploadServlet", urlPatterns = {"/FileUploadServlet"})
//@Path("serveupload")
public class FileUploadServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // @POST
    //@Path("/upload")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String CONTENT_TYPE = "multipart-formdata";

        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();

        request.setAttribute("Content-type",CONTENT_TYPE);

        //Simply getting the user's home directory so we have a place to write the files.
        String sUserDir = System.getProperty("user.home") + System.getProperty("file.separator");
       // String sUserDir ="c:/images/";
       
        
        System.err.println("DIRs: "+sUserDir);
        //Create a new instance of the file factory.
        FileItemFactory factory = new DiskFileItemFactory();

        //Initialize your file upload parser
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {

            //Parse the request, it returns a List, containing FileItem objects
            List<FileItem> fileItems = upload.parseRequest(request);
            
            String dir = new File(sUserDir).getParentFile().getParentFile().getName();
            
            //process the files
            for (FileItem fileItem : fileItems) {

                //This is a simple way to save them to disk, but you
                //could also just process the files by getting the input stream right
                //from FileItem
                File persistedFile = new File(dir+"/"+fileItem.getName());
                System.err.println("PAR "+persistedFile);

                fileItem.write(persistedFile);

                out.println("<pre><b>Recieved file:</b> " + fileItem.getName());

                out.println("Size: " + fileItem.getSize());

                out.println("Saved To: " + persistedFile.getPath() + "\n</pre>");

            }

        } catch (FileUploadException fileEx) {

            fileEx.printStackTrace();

            throw new ServletException("Error parsing uploaded files", fileEx);

        } catch (Exception ex) {

            ex.printStackTrace();

            throw new ServletException("Error saving files on server", ex);

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public String getPath() throws UnsupportedEncodingException {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String fullPath = URLDecoder.decode(path, "UTF-8");
        String pathArr[] = fullPath.split("/WEB-INF/classes/");
        System.out.println(fullPath);
        System.out.println(pathArr[0]);
        fullPath = pathArr[0];
        String reponsePath = "";
// to read a file from webcontent
        reponsePath = new File(fullPath).getPath();
        return reponsePath;
    }

}
