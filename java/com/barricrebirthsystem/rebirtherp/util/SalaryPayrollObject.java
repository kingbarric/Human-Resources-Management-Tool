/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.util;

import com.barricrebirthsystem.rebirtherp.entities.PrAdvanceSalary;
import com.barricrebirthsystem.rebirtherp.entities.PrSalaryAllowance;
import com.barricrebirthsystem.rebirtherp.entities.PrSalaryDeduction;
import java.util.List;

/**
 *
 * @author Barima
 */
public class SalaryPayrollObject {

    private Integer empId;
    private String name;
    private String staffId;
    private String department;
    private String month;
    private String SalaryGrade;
    private float basicSalary;
    private float totalDeduction;
    private float totalAllowance;
    private float netSalary;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    
    private List<PrSalaryDeduction> deductions;
    private List<PrSalaryAllowance> allowances;
    private List<PrAdvanceSalary> advances;
    
    public float getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(float basicSalary) {
        this.basicSalary = basicSalary;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSalaryGrade() {
        return SalaryGrade;
    }

    public void setSalaryGrade(String SalaryGrade) {
        this.SalaryGrade = SalaryGrade;
    }

    public float getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(float totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    public float getTotalAllowance() {
        return totalAllowance;
    }

    public void setTotalAllowance(float totalAllowance) {
        this.totalAllowance = totalAllowance;
    }

    public float getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(float netSalary) {
        this.netSalary = netSalary;
    }

    public List<PrSalaryDeduction> getDeductions() {
        return deductions;
    }

    public void setDeductions(List<PrSalaryDeduction> deductions) {
        this.deductions = deductions;
    }

    public List<PrSalaryAllowance> getAllowances() {
        return allowances;
    }

    public void setAllowances(List<PrSalaryAllowance> allowances) {
        this.allowances = allowances;
    }

    public List<PrAdvanceSalary> getAdvances() {
        return advances;
    }

    public void setAdvances(List<PrAdvanceSalary> advances) {
        this.advances = advances;
    }

    
}
