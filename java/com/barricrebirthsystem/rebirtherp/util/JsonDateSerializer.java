/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.util;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;
/**
 *
 * @author Barima
 */
@Component
public class JsonDateSerializer extends JsonSerializer<Date> {
    
 public void serialize(Date dt, JsonGenerator jsonGen, SerializerProvider serProv) 
                                            throws IOException, JsonProcessingException {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(dt);
        jsonGen.writeString(formattedDate);
}
}
