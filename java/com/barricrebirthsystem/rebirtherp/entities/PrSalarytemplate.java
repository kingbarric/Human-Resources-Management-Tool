/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "pr_salarytemplate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrSalarytemplate.findAll", query = "SELECT p FROM PrSalarytemplate p")
    , @NamedQuery(name = "PrSalarytemplate.findById", query = "SELECT p FROM PrSalarytemplate p WHERE p.id = :id")
    , @NamedQuery(name = "PrSalarytemplate.findBySalaryGrade", query = "SELECT p FROM PrSalarytemplate p WHERE p.salaryGrade = :salaryGrade")
    , @NamedQuery(name = "PrSalarytemplate.findByBasicSalary", query = "SELECT p FROM PrSalarytemplate p WHERE p.basicSalary = :basicSalary")
    , @NamedQuery(name = "PrSalarytemplate.findByOvertimePerhour", query = "SELECT p FROM PrSalarytemplate p WHERE p.overtimePerhour = :overtimePerhour")
 })
public class PrSalarytemplate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "salary_grade")
    private String salaryGrade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "basic_salary")
    private BigDecimal basicSalary;
    
    @Column(name = "total_allowance")
    private BigDecimal totalAllowance;
    
    @Column(name = "total_deductions")
    private BigDecimal totalDeductions;
    
    @Column(name = "overtime_perhour")
    private BigDecimal overtimePerhour;
    
    @Column(name = "created_on")
    @Temporal(TemporalType.DATE)
    private Date createdOn;
  
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "templateId")
//    private List<PrSalaryAllowance> prSalaryAllowanceList;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne
    private Employee createdBy;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "templateId")
//    private List<PrSalaryDeduction> prSalaryDeductionList;

    public PrSalarytemplate() {
    }

    public PrSalarytemplate(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSalaryGrade() {
        return salaryGrade;
    }

    public void setSalaryGrade(String salaryGrade) {
        this.salaryGrade = salaryGrade;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getOvertimePerhour() {
        return overtimePerhour;
    }

    public void setOvertimePerhour(BigDecimal overtimePerhour) {
        this.overtimePerhour = overtimePerhour;
    }


//
//    @XmlTransient
//    @JsonIgnore
//    public List<PrSalaryAllowance> getPrSalaryAllowanceList() {
//        return prSalaryAllowanceList;
//    }
//
//    public void setPrSalaryAllowanceList(List<PrSalaryAllowance> prSalaryAllowanceList) {
//        this.prSalaryAllowanceList = prSalaryAllowanceList;
//    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }

    public BigDecimal getTotalAllowance() {
        return totalAllowance;
    }

    public void setTotalAllowance(BigDecimal totalAllowance) {
        this.totalAllowance = totalAllowance;
    }

    public BigDecimal getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(BigDecimal totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    
//    @XmlTransient
//    @JsonbTransient
//    public List<PrSalaryDeduction> getPrSalaryDeductionList() {
//        return prSalaryDeductionList;
//    }
//
//    public void setPrSalaryDeductionList(List<PrSalaryDeduction> prSalaryDeductionList) {
//        this.prSalaryDeductionList = prSalaryDeductionList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrSalarytemplate)) {
            return false;
        }
        PrSalarytemplate other = (PrSalarytemplate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.PrSalarytemplate[ id=" + id + " ]";
    }
    
}
