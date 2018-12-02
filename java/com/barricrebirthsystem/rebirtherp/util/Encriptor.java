/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.util;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author Barima
 */
public class Encriptor {
     private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;
    
    public Encriptor (){
        try {
            myEncryptionKey = "sdjkjkjkjkjkj";
            myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
            arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
            ks = new DESedeKeySpec(arrayBytes);
            skf = SecretKeyFactory.getInstance(myEncryptionScheme);
            cipher = Cipher.getInstance(myEncryptionScheme);
            key = skf.generateSecret(ks);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
        } catch (InvalidKeyException invalidKeyException) {
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
        } catch (NoSuchPaddingException noSuchPaddingException) {
        } catch (InvalidKeySpecException invalidKeySpecException) {
        }
    
    }
      public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }
      
      public static void main(String[] args) {
        String text = new Encriptor().hashPassword("pass");
          Encriptor en = new Encriptor();
          String p1 ="$2a$10$O1y11morViOsbEWZXmJKleWqBktNB.1aIEJ5UiNQS1rmptpWNA2Eq";
          String p2 ="$2a$10$VxEGLfIS8y2KEJmhPZdgT.M4xH0ziV9SHeiIriBKBR8.yLTNxROMm";
          en.checkPass("pass4", p2);
    }
      
      
       public String encrypt2(String x) {
         try {
             java.security.MessageDigest d = null;
             d = java.security.MessageDigest.getInstance("SHA-1");
             d.reset();
             d.update(x.getBytes());
             String s = new String(d.digest());
             return s;
         } catch (NoSuchAlgorithmException ex) {
             Logger.getLogger(Encriptor.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
  }
       
       private String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}
       
       private void checkPass(String plainPassword, String hashedPassword) {
		if (BCrypt.checkpw(plainPassword, hashedPassword))
			System.out.println("The password matches.");
		else
			System.out.println("The password does not match.");
	}

}
