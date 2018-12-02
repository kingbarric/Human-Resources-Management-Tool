package org.netbeans.rest.application.config;

import com.barricrebirthsystem.rebirtherp.util.UserRole;

/**
 *
 * @author SAPPHITAL-004
 */
public class StatusMessage {
    
    private int status;
    private String message;
    private String username;
    private UserRole role;
    private String attendanceCheckInValue;
    private String markCheckOut;
    private String markCheckIn;
    private Integer uidd;
    private Integer didd;

    public StatusMessage(int status, String message, String username, UserRole role, String attendanceCheckInValue, String markCheckOut, String markCheckIn, Integer uidd, Integer didd) {
        this.status = status;
        this.message = message;
        this.username = username;
        this.role = role;
        this.attendanceCheckInValue = attendanceCheckInValue;
        this.markCheckOut = markCheckOut;
        this.markCheckIn = markCheckIn;
        this.uidd = uidd;
        this.didd = didd;
    }

    
    
    public Integer getUidd() {
        return uidd;
    }

    public void setUidd(Integer uidd) {
        this.uidd = uidd;
    }

    public Integer getDidd() {
        return didd;
    }

    public void setDidd(Integer didd) {
        this.didd = didd;
    }

   

    public StatusMessage() {
        
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String isAttendanceCheckInValue() {
        return attendanceCheckInValue;
    }

    public void setAttendanceCheckInValue(String attendanceCheckInValue) {
        this.attendanceCheckInValue = attendanceCheckInValue;
    }

    public String getMarkCheckOut() {
        return markCheckOut;
    }

    public void setMarkCheckOut(String markCheckOut) {
        this.markCheckOut = markCheckOut;
    }

    public String getMarkCheckIn() {
        return markCheckIn;
    }

    public void setMarkCheckIn(String markCheckIn) {
        this.markCheckIn = markCheckIn;
    }
    
    
    
    
    
}
