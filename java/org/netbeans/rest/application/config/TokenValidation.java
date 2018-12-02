package org.netbeans.rest.application.config;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 *
 * @author SAPPHITAL-004
 */
@javax.ws.rs.NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface TokenValidation {
    
}
