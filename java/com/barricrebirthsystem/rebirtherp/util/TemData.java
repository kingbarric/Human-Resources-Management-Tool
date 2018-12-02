/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.util;

/**
 *
 * @author Barima
 */
public class TemData {
    private Integer templateId;
    private Integer employeeId;

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

  
    
    @Override
    public String toString(){
    return this.templateId +" EMP: "+this.employeeId;
    }
}
