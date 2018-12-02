/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/**
 *
 * @author Barima
 */
public final class UtilHelper {
      @Context
    private static HttpServletRequest servletRequest;  
   public static final String SUCCESS_MESSAGE = "Proccess completed successfully"  ;
   public static final String ERROR_MESSAGE = "Sorry, Something not right, please try again";
   private static int errorCode = 0;
   private static int successCode = 1;
   public static HashMap<String,String> msg;
    public static String getSUCCESS_MESSAGE() {
        return SUCCESS_MESSAGE;
    }

    public static String getERROR_MESSAGE() {
        return ERROR_MESSAGE;
    }
   
   public static HashMap<String,String> ErrorMessage(){
   
       msg = new HashMap<>();
       msg.put("CODE", "0");
       msg.put("MSG", ERROR_MESSAGE);
       return msg;
   }
   public static HashMap<String,String> SuccessMessage(){
   
       msg = new HashMap<>();
       msg.put("CODE", "1");
       msg.put("MSG", SUCCESS_MESSAGE);
       return msg;
   }
   
   
   public static Messager publishErrorMessage(){
   return new Messager(errorCode,ERROR_MESSAGE);
   }
    
   public static Messager publishSuccessMessage(){
   return new Messager(successCode,SUCCESS_MESSAGE);
   }
   
   public static boolean saveFile(InputStream is, String fileLocation) {
	      try {
                
               
           OutputStream os = new FileOutputStream(new File(fileLocation));
           byte[] buffer = new byte[256];
           int bytes = 0;
           while ((bytes = is.read(buffer)) != -1) {
               os.write(buffer, 0, bytes);
           }
       } catch (IOException iOException) {
                  System.err.println("ERROR IO: "+iOException);
           return false;
       }
              return true;
        }
   
    /**
     *
     * @param is
     * @param fileLocation
     * @return
     */
    boolean saveStream(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return true;
    }



    public static String base64Encoder(String imagePath) {
        String any ="data:image/jpg;base64,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";
        String base64Image = "";
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            //no worries just return default image
            return any;
       
        } catch (IOException ioe) {
            //same thing goes here
            return any;
        }
        return base64Image;
    }

    public static boolean writeString(String text, String path) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(path), "utf-8"))) {
            writer.write(text);
            return true;
        }catch(IOException e){
            return false;
        }
    }
}


