/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "expenses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Expenses.findAll", query = "SELECT e FROM Expenses e")
    , @NamedQuery(name = "Expenses.findById", query = "SELECT e FROM Expenses e WHERE e.id = :id")
         , @NamedQuery(name = "Expenses.findByInitiatedBy", query = "SELECT e FROM Expenses e WHERE e.initiatedby = :empid")
    , @NamedQuery(name = "Expenses.findByPayeename", query = "SELECT e FROM Expenses e WHERE e.payeename = :payeename")
    , @NamedQuery(name = "Expenses.findByPhonenumber", query = "SELECT e FROM Expenses e WHERE e.phonenumber = :phonenumber")
    , @NamedQuery(name = "Expenses.findByInitiatedby", query = "SELECT e FROM Expenses e WHERE e.initiatedby = :initiatedby")
    , @NamedQuery(name = "Expenses.findByDescription", query = "SELECT e FROM Expenses e WHERE e.description = :description")
    , @NamedQuery(name = "Expenses.findByBusinessjustification", query = "SELECT e FROM Expenses e WHERE e.businessjustification = :businessjustification")
    , @NamedQuery(name = "Expenses.findByTotalamount", query = "SELECT e FROM Expenses e WHERE e.totalamount = :totalamount")
    , @NamedQuery(name = "Expenses.findByApprovedby1", query = "SELECT e FROM Expenses e WHERE e.approvedby1 = :approvedby1")
    , @NamedQuery(name = "Expenses.findByApprovedby2", query = "SELECT e FROM Expenses e WHERE e.approvedby2 = :approvedby2")
    , @NamedQuery(name = "Expenses.findByApprovedby3", query = "SELECT e FROM Expenses e WHERE e.approvedby3 = :approvedby3")
    , @NamedQuery(name = "Expenses.findByApproval1", query = "SELECT e FROM Expenses e WHERE e.approval1 = :approval1"),
         @NamedQuery(name = "Expenses.findByApproval1AND2", query = "SELECT e FROM Expenses e WHERE e.approval1 = :approval1 AND e.approval2 = :approval2")
    , @NamedQuery(name = "Expenses.findByApproval1Date", query = "SELECT e FROM Expenses e WHERE e.approval1Date = :approval1Date")
    , @NamedQuery(name = "Expenses.findByApproval2", query = "SELECT e FROM Expenses e WHERE e.approval2 = :approval2")
    , @NamedQuery(name = "Expenses.findByApproval2Date", query = "SELECT e FROM Expenses e WHERE e.approval2Date = :approval2Date")
    , @NamedQuery(name = "Expenses.findByApproval3", query = "SELECT e FROM Expenses e WHERE e.approval3 = :approval3")
    , @NamedQuery(name = "Expenses.findByApproval3Date", query = "SELECT e FROM Expenses e WHERE e.approval3Date = :approval3Date")
    , @NamedQuery(name = "Expenses.findByComment1", query = "SELECT e FROM Expenses e WHERE e.comment1 = :comment1")
    , @NamedQuery(name = "Expenses.findByComment2", query = "SELECT e FROM Expenses e WHERE e.comment2 = :comment2")
    , @NamedQuery(name = "Expenses.findByComment3", query = "SELECT e FROM Expenses e WHERE e.comment3 = :comment3")
    , @NamedQuery(name = "Expenses.findByCashcollectedby", query = "SELECT e FROM Expenses e WHERE e.cashcollectedby = :cashcollectedby")
    , @NamedQuery(name = "Expenses.findByDatecollected", query = "SELECT e FROM Expenses e WHERE e.datecollected = :datecollected")
    , @NamedQuery(name = "Expenses.findByCreditaccountno", query = "SELECT e FROM Expenses e WHERE e.creditaccountno = :creditaccountno")
    , @NamedQuery(name = "Expenses.findByDebitaccountno", query = "SELECT e FROM Expenses e WHERE e.debitaccountno = :debitaccountno")
    , @NamedQuery(name = "Expenses.findByDateCreated", query = "SELECT e FROM Expenses e WHERE e.dateCreated = :dateCreated")})
public class Expenses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "payeename")
    private String payeename;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "phonenumber")
    private String phonenumber;
    
     @JoinColumn(name = "initiatedby", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee initiatedby;
    
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "businessjustification")
    private String businessjustification;
    @Column(name = "totalamount")
    private BigInteger totalamount;
    
     @JoinColumn(name = "approvedby1", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee approvedby1;
     
   @JoinColumn(name = "approvedby2", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee approvedby2;
    @JoinColumn(name = "approvedby3", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee approvedby3;
    @Size(max = 45)
    @Column(name = "approval1")
    private String approval1;
    @Column(name = "approval1_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approval1Date;
    @Size(max = 45)
    @Column(name = "approval2")
    private String approval2;
    @Column(name = "approval2_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approval2Date;
    @Size(max = 45)
    @Column(name = "approval3")
    private String approval3;
    @Column(name = "approval3_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approval3Date;
    @Size(max = 255)
    @Column(name = "comment1")
    private String comment1;
    @Size(max = 255)
    @Column(name = "comment2")
    private String comment2;
    @Size(max = 255)
    @Column(name = "comment3")
    private String comment3;
    @Size(max = 50)
    @Column(name = "cashcollectedby")
    private String cashcollectedby;
    @Column(name = "datecollected")
    @Temporal(TemporalType.DATE)
    private Date datecollected;

    @Size(max = 16)
    @Column(name = "creditaccountno")
    private String creditaccountno;
    @Size(max = 16)
    @Column(name = "debitaccountno")
    private String debitaccountno;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public Expenses() {
    }

    public Expenses(Integer id) {
        this.id = id;
    }

    public Expenses(Integer id, String phonenumber, Date dateCreated) {
        this.id = id;
        this.phonenumber = phonenumber;
        this.dateCreated = dateCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayeename() {
        return payeename;
    }

    public void setPayeename(String payeename) {
        this.payeename = payeename;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Employee getInitiatedby() {
        return initiatedby;
    }

    public void setInitiatedby(Employee initiatedby) {
        this.initiatedby = initiatedby;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusinessjustification() {
        return businessjustification;
    }

    public void setBusinessjustification(String businessjustification) {
        this.businessjustification = businessjustification;
    }

    public BigInteger getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigInteger totalamount) {
        this.totalamount = totalamount;
    }

    public Employee getApprovedby1() {
        return approvedby1;
    }

    public void setApprovedby1(Employee approvedby1) {
        this.approvedby1 = approvedby1;
    }

    public Employee getApprovedby2() {
        return approvedby2;
    }

    public void setApprovedby2(Employee approvedby2) {
        this.approvedby2 = approvedby2;
    }

    public Employee getApprovedby3() {
        return approvedby3;
    }

    public void setApprovedby3(Employee approvedby3) {
        this.approvedby3 = approvedby3;
    }

    public String getApproval1() {
        return approval1;
    }

    public void setApproval1(String approval1) {
        this.approval1 = approval1;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
    public Date getApproval1Date() {
        return approval1Date;
    }

    public void setApproval1Date(Date approval1Date) {
        this.approval1Date = approval1Date;
    }

    public String getApproval2() {
        return approval2;
    }

    public void setApproval2(String approval2) {
        this.approval2 = approval2;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
    public Date getApproval2Date() {
        return approval2Date;
    }

    public void setApproval2Date(Date approval2Date) {
        this.approval2Date = approval2Date;
    }

    public String getApproval3() {
        return approval3;
    }

    public void setApproval3(String approval3) {
        this.approval3 = approval3;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
    public Date getApproval3Date() {
        return approval3Date;
    }

    public void setApproval3Date(Date approval3Date) {
        this.approval3Date = approval3Date;
    }

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public String getComment3() {
        return comment3;
    }

    public void setComment3(String comment3) {
        this.comment3 = comment3;
    }

    public String getCashcollectedby() {
        return cashcollectedby;
    }

    public void setCashcollectedby(String cashcollectedby) {
        this.cashcollectedby = cashcollectedby;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
    public Date getDatecollected() {
        return datecollected;
    }

    public void setDatecollected(Date datecollected) {
        this.datecollected = datecollected;
    }



    public String getCreditaccountno() {
        return creditaccountno;
    }

    public void setCreditaccountno(String creditaccountno) {
        this.creditaccountno = creditaccountno;
    }

    public String getDebitaccountno() {
        return debitaccountno;
    }

    public void setDebitaccountno(String debitaccountno) {
        this.debitaccountno = debitaccountno;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
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
        if (!(object instanceof Expenses)) {
            return false;
        }
        Expenses other = (Expenses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.Expenses[ id=" + id + " ]";
    }
    
}
