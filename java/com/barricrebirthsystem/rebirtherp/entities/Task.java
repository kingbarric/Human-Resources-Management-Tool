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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t")
    , @NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.id = :id")
    , @NamedQuery(name = "Task.findByCreatedby", query = "SELECT t FROM Task t WHERE t.createdby = :createdby")
    , @NamedQuery(name = "Task.findByName", query = "SELECT t FROM Task t WHERE t.name = :name")
    , @NamedQuery(name = "Task.findByAssignto", query = "SELECT t FROM Task t WHERE t.assignto = :assignto")
    , @NamedQuery(name = "Task.findByStartdate", query = "SELECT t FROM Task t WHERE t.startdate = :startdate")
    , @NamedQuery(name = "Task.findByDuedate", query = "SELECT t FROM Task t WHERE t.duedate = :duedate")
    , @NamedQuery(name = "Task.findByDescription", query = "SELECT t FROM Task t WHERE t.description = :description")
    , @NamedQuery(name = "Task.findByStatus", query = "SELECT t FROM Task t WHERE t.status = :status")
    , @NamedQuery(name = "Task.findByCompletiondate", query = "SELECT t FROM Task t WHERE t.completiondate = :completiondate")})
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    
     @JoinColumn(name = "createdby", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee createdby;
   
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    
    
    @JoinColumn(name = "assignto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee assignto;
    
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "startdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date duedate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "status")
    private String status;
    @Column(name = "completiondate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completiondate;
    

    public Task() {
    }

    public Task(Integer id) {
        this.id = id;
    }

    public Task(Integer id, Employee createdby, String name, Employee assignto, Date startdate, Date duedate, String description, String status) {
        this.id = id;
        this.createdby = createdby;
        this.name = name;
        this.assignto = assignto;
        this.startdate = startdate;
        this.duedate = duedate;
        this.description = description;
        this.status = status;
     
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Employee createdby) {
        this.createdby = createdby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getAssignto() {
        return assignto;
    }

    public void setAssignto(Employee assignto) {
        this.assignto = assignto;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCompletiondate() {
        return completiondate;
    }

    public void setCompletiondate(Date completiondate) {
        this.completiondate = completiondate;
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
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.Task[ id=" + id + " ]";
    }
    
}
