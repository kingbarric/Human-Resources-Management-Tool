package org.netbeans.rest.application.config;

import javax.ws.rs.FormParam;

/**
 *
 * @author SAPPHITAL-004
 */
public class StaffParam {

    private @FormParam("username")
    String username;
    private @FormParam("password")
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
