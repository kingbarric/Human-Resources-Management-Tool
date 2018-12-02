/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "pr_salary_payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrSalaryPayment.findAll", query = "SELECT p FROM PrSalaryPayment p")
    , @NamedQuery(name = "PrSalaryPayment.findById", query = "SELECT p FROM PrSalaryPayment p WHERE p.id = :id")
    , @NamedQuery(name = "PrSalaryPayment.findByForMonth", query = "SELECT p FROM PrSalaryPayment p WHERE p.forMonth = :forMonth")
    , @NamedQuery(name = "PrSalaryPayment.findByPaymentDate", query = "SELECT p FROM PrSalaryPayment p WHERE p.paymentDate = :paymentDate")})
public class PrSalaryPayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "for_month")
    @Temporal(TemporalType.DATE)
    private Date forMonth;
    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
    @JoinColumn(name = "managed_salary_id", referencedColumnName = "id")
    @ManyToOne
    private PrManagedSalary managedSalaryId;
    @JoinColumn(name = "payment_by", referencedColumnName = "id")
    @ManyToOne
    private Employee paymentBy;

    public PrSalaryPayment() {
    }

    public PrSalaryPayment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getForMonth() {
        return forMonth;
    }

    public void setForMonth(Date forMonth) {
        this.forMonth = forMonth;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PrManagedSalary getManagedSalaryId() {
        return managedSalaryId;
    }

    public void setManagedSalaryId(PrManagedSalary managedSalaryId) {
        this.managedSalaryId = managedSalaryId;
    }

    public Employee getPaymentBy() {
        return paymentBy;
    }

    public void setPaymentBy(Employee paymentBy) {
        this.paymentBy = paymentBy;
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
        if (!(object instanceof PrSalaryPayment)) {
            return false;
        }
        PrSalaryPayment other = (PrSalaryPayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.PrSalaryPayment[ id=" + id + " ]";
    }
    
}
