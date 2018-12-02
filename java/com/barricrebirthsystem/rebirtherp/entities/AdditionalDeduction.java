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
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "additional_deduction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdditionalDeduction.findAll", query = "SELECT a FROM AdditionalDeduction a")
    , @NamedQuery(name = "AdditionalDeduction.findById", query = "SELECT a FROM AdditionalDeduction a WHERE a.id = :id")
    , @NamedQuery(name = "AdditionalDeduction.findByAmount", query = "SELECT a FROM AdditionalDeduction a WHERE a.amount = :amount")
    , @NamedQuery(name = "AdditionalDeduction.findByDateMonth", query = "SELECT a FROM AdditionalDeduction a WHERE a.dateMonth = :dateMonth")
    , @NamedQuery(name = "AdditionalDeduction.findByDateCreated", query = "SELECT a FROM AdditionalDeduction a WHERE a.dateCreated = :dateCreated")})
public class AdditionalDeduction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_month")
    @Temporal(TemporalType.DATE)
    private Date dateMonth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    
    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee empId;

    public AdditionalDeduction() {
    }

    public AdditionalDeduction(Integer id) {
        this.id = id;
    }

    public AdditionalDeduction(Integer id, BigDecimal amount, Date dateMonth, Date dateCreated) {
        this.id = id;
        this.amount = amount;
        this.dateMonth = dateMonth;
        this.dateCreated = dateCreated;
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

    public Date getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(Date dateMonth) {
        this.dateMonth = dateMonth;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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
        if (!(object instanceof AdditionalDeduction)) {
            return false;
        }
        AdditionalDeduction other = (AdditionalDeduction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.AdditionalDeduction[ id=" + id + " ]";
    }

    public Employee getEmpId() {
        return empId;
    }

    public void setEmpId(Employee empId) {
        this.empId = empId;
    }
    
    
    
}
