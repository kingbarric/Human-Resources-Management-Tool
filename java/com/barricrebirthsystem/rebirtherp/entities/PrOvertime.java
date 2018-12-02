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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "pr_overtime")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrOvertime.findAll", query = "SELECT p FROM PrOvertime p")
    , @NamedQuery(name = "PrOvertime.findById", query = "SELECT p FROM PrOvertime p WHERE p.id = :id")
    , @NamedQuery(name = "PrOvertime.findByForDate", query = "SELECT p FROM PrOvertime p WHERE p.forDate = :forDate")
    , @NamedQuery(name = "PrOvertime.findByOvertimeHours", query = "SELECT p FROM PrOvertime p WHERE p.overtimeHours = :overtimeHours")
    , @NamedQuery(name = "PrOvertime.findByNote", query = "SELECT p FROM PrOvertime p WHERE p.note = :note")
    , @NamedQuery(name = "PrOvertime.findByDateCreated", query = "SELECT p FROM PrOvertime p WHERE p.dateCreated = :dateCreated")})
public class PrOvertime implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "for_date")
    @Temporal(TemporalType.DATE)
    private Date forDate;
    @Column(name = "overtime_hours")
    private Integer overtimeHours;
    @Size(max = 255)
    @Column(name = "note")
    private String note;
    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne
    private Employee createdBy;
    @JoinColumn(name = "empid", referencedColumnName = "id")
    @ManyToOne
    private Employee empid;

    public PrOvertime() {
    }

    public PrOvertime(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getForDate() {
        return forDate;
    }

    public void setForDate(Date forDate) {
        this.forDate = forDate;
    }

    public Integer getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(Integer overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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
        if (!(object instanceof PrOvertime)) {
            return false;
        }
        PrOvertime other = (PrOvertime) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.PrOvertime[ id=" + id + " ]";
    }
    
}
