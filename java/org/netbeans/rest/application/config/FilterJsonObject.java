/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

/**
 *
 * @author Barima
 */


import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Provider
@Produces(MediaType.APPLICATION_JSON)
public class FilterJsonObject extends JacksonJaxbJsonProvider {

    /** the object mapper */
    private static ObjectMapper m_mapper = null;

    /** Default constructor */
    public FilterJsonObject() {
        if(m_mapper == null){
            initialize();
        }

        super.setMapper(m_mapper);
    }


    /** Returns the global copy of the object mapper 
     * @return the global object mapper */
    public synchronized static ObjectMapper getParser() {

        if (m_mapper == null) {
            initialize();
        }

        return m_mapper;
    }


    /** Initialize the mapper. */
    private synchronized static void initialize() {
        m_mapper = new ObjectMapper();

        m_mapper.setSerializationInclusion(Include.NON_NULL);
        m_mapper.setSerializationInclusion(Include.NON_DEFAULT);
        m_mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        m_mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

}