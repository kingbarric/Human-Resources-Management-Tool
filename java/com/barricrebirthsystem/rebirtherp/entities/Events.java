/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "events")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Events.findAll", query = "SELECT e FROM Events e")
    , @NamedQuery(name = "Events.findById", query = "SELECT e FROM Events e WHERE e.id = :id")
    , @NamedQuery(name = "Events.findByEmpid", query = "SELECT e FROM Events e WHERE e.empid = :empid")
    , @NamedQuery(name = "Events.findByTitle", query = "SELECT e FROM Events e WHERE e.title = :title")
    , @NamedQuery(name = "Events.findByDescription", query = "SELECT e FROM Events e WHERE e.description = :description")
    , @NamedQuery(name = "Events.findByStartdate", query = "SELECT e FROM Events e WHERE e.startdate = :startdate")
    , @NamedQuery(name = "Events.findByEnddate", query = "SELECT e FROM Events e WHERE e.enddate = :enddate")
    , @NamedQuery(name = "Events.findByDatecreated", query = "SELECT e FROM Events e WHERE e.datecreated = :datecreated")})
public class Events implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @JoinColumn(name = "empid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee empid;
    @Size(max = 150)
    @Column(name = "title")
    private String title;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "startdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;
    @Column(name = "enddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;

    public Events() {
    }

    public Events(Integer id) {
        this.id = id;
    }

    public Events(Integer id, Employee empid) {
        this.id = id;
        this.empid = empid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmpid() {
        return empid;
    }

    public void setEmpid(Employee empid) {
        this.empid = empid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
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
        if (!(object instanceof Events)) {
            return false;
        }
        Events other = (Events) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.Events[ id=" + id + " ]";
    }
    
}
