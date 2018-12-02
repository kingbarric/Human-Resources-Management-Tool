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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "pr_advance_salary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrAdvanceSalary.findAll", query = "SELECT p FROM PrAdvanceSalary p")
    , @NamedQuery(name = "PrAdvanceSalary.findById", query = "SELECT p FROM PrAdvanceSalary p WHERE p.id = :id")
    , @NamedQuery(name = "PrAdvanceSalary.findByAmount", query = "SELECT p FROM PrAdvanceSalary p WHERE p.amount = :amount")
 //  , @NamedQuery(name = "PrAdvanceSalary.findByDatesAndEmp", query = "SELECT p FROM PrAdvanceSalary p WHERE p.empid = :emp AND year(p.deductedMonth)= :y AND month(p.deductedMonth)= :m ")
   // , @NamedQuery(name = "PrAdvanceSalary.findByDeductedMonth", query = "SELECT p FROM PrAdvanceSalary p WHERE p.deductedMonth = :deductedMonth")
    , @NamedQuery(name = "PrAdvanceSalary.findByReasons", query = "SELECT p FROM PrAdvanceSalary p WHERE p.reasons = :reasons")
    , @NamedQuery(name = "PrAdvanceSalary.findByCreatedOn", query = "SELECT p FROM PrAdvanceSalary p WHERE p.createdOn = :createdOn")})
public class PrAdvanceSalary implements Serializable {

   @Basic(optional = false)
    @NotNull
    @Column(name = "deducted_month")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deductedMonth;

    @Size(max = 5)
    @Column(name = "ispaid")
    private String ispaid;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private BigDecimal amount;
    
    @Size(max = 255)
    @Column(name = "reasons")
    private String reasons;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne
    private Employee createdBy;
    @JoinColumn(name = "empid", referencedColumnName = "id")
    @ManyToOne
    private Employee empid;
    @Column(name="name")
    private String name;

    public PrAdvanceSalary() {
    }

    public PrAdvanceSalary(Integer id) {
        this.id = id;
    }

    public PrAdvanceSalary(Integer id, Date createdOn) {
        this.id = id;
        this.createdOn = createdOn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

   

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }

    public Employee getEmpid() {
        return empid;
    }

    public void setEmpid(Employee empid) {
        this.empid = empid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrAdvanceSalary)) {
            return false;
        }
        PrAdvanceSalary other = (PrAdvanceSalary) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PrAdvanceSalary{" + "deductedMonth=" + deductedMonth + ", ispaid=" + ispaid + ", id=" + id + ", amount=" + amount + ", reasons=" + reasons + ", createdOn=" + createdOn + ", createdBy=" + createdBy + ", empid=" + empid + '}';
    }

  

    public String getIspaid() {
        return ispaid;
    }

    public void setIspaid(String ispaid) {
        this.ispaid = ispaid;
    }

    public Date getDeductedMonth() {
        return deductedMonth;
    }

    public void setDeductedMonth(Date deductedMonth) {
        this.deductedMonth = deductedMonth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
