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
import javax.persistence.Lob;
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
@Table(name = "leave_application")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeaveApplication.findAll", query = "SELECT l FROM LeaveApplication l")
    , @NamedQuery(name = "LeaveApplication.findById", query = "SELECT l FROM LeaveApplication l WHERE l.id = :id")
        , @NamedQuery(name = "LeaveApplication.findByEmp", query = "SELECT l FROM LeaveApplication l WHERE l.empid = :empid")
    , @NamedQuery(name = "LeaveApplication.findByDateApplied", query = "SELECT l FROM LeaveApplication l WHERE l.dateApplied = :dateApplied")
    , @NamedQuery(name = "LeaveApplication.findByStartDate", query = "SELECT l FROM LeaveApplication l WHERE l.startDate = :startDate")
    , @NamedQuery(name = "LeaveApplication.findByEndDate", query = "SELECT l FROM LeaveApplication l WHERE l.endDate = :endDate")})
public class LeaveApplication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_applied")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateApplied;
    @Lob
    @Size(max = 65535)
    @Column(name = "reasons")
    private String reasons;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
     @JoinColumn(name = "emp_id", referencedColumnName = "id")
    @ManyToOne
    private Employee empid;
     
      @JoinColumn(name = "leave_cat", referencedColumnName = "id")
    @ManyToOne
    private LeaveCategory leavecat;
      
      @Column(name = "duration_in_days")
    private Double durationInDays;

    public LeaveApplication() {
    }

    public Double getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(Double durationInDays) {
        this.durationInDays = durationInDays;
    }
    
    

    public LeaveApplication(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(Date dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Employee getEmpid() {
        return empid;
    }

    public void setEmpid(Employee empid) {
        this.empid = empid;
    }

    public LeaveCategory getLeavecat() {
        return leavecat;
    }

    public void setLeavecat(LeaveCategory leavecat) {
        this.leavecat = leavecat;
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
        if (!(object instanceof LeaveApplication)) {
            return false;
        }
        LeaveApplication other = (LeaveApplication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.LeaveApplication[ id=" + id + " ]";
    }
    
}
