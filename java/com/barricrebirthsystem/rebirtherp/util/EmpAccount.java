/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.util;

import java.math.BigDecimal;

/**
 *
 * @author Barima
 */
public class EmpAccount {
    private Integer id;
    
    private String accountNo;
  
    private String bankName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
   
    private BigDecimal gla;
   
    private BigDecimal grossSalary;
    
    private boolean nhf;
    private boolean nhis;
    private boolean pension;

    public String getAccountNo() {
        return accountNo;
    }
    
    private Integer noLeaveDays;

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BigDecimal getGla() {
        return gla;
    }

    public void setGla(BigDecimal gla) {
        this.gla = gla;
    }

    public BigDecimal getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(BigDecimal grossSalary) {
        this.grossSalary = grossSalary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoLeaveDays() {
        return noLeaveDays;
    }

    public void setNoLeaveDays(Integer noLeaveDays) {
        this.noLeaveDays = noLeaveDays;
    }

    public boolean isNhf() {
        return nhf;
    }

    public void setNhf(boolean nhf) {
        this.nhf = nhf;
    }

    public boolean isNhis() {
        return nhis;
    }

    public void setNhis(boolean nhis) {
        this.nhis = nhis;
    }

    public boolean isPension() {
        return pension;
    }

    public void setPension(boolean pension) {
        this.pension = pension;
    }

    @Override
    public String toString() {
        return "EmpAccount{" + "id=" + id + ", accountNo=" + accountNo + ", bankName=" + bankName + ", gla=" + gla + ", grossSalary=" + grossSalary + ", nhf=" + nhf + ", nhis=" + nhis + ", pension=" + pension + ", noLeaveDays=" + noLeaveDays + '}';
    }

    
    
   
    
}
