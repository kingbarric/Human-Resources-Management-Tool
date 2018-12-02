/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.util;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
//@XmlRootElement
public class ApprovalData {
    private int id;
    private String status;
    private String comment;
    private int approvedby;
    private int approval_level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getApprovedby() {
        return approvedby;
    }

    public void setApprovedby(int approvedby) {
        this.approvedby = approvedby;
    }

    public int getApproval_level() {
        return approval_level;
    }

    public void setApproval_level(int approval_level) {
        this.approval_level = approval_level;
    }
    
    @Override
    public String toString(){
    
        return "Status: "+this.status+" ID: "+this.id;
    }
}
