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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "issuetracking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Issuetracking.findAll", query = "SELECT i FROM Issuetracking i")
    , @NamedQuery(name = "Issuetracking.findById", query = "SELECT i FROM Issuetracking i WHERE i.id = :id")
    , @NamedQuery(name = "Issuetracking.findByRelatingdeptid", query = "SELECT i FROM Issuetracking i WHERE i.relatingdeptid = :relatingdeptid")
    , @NamedQuery(name = "Issuetracking.findByDescription", query = "SELECT i FROM Issuetracking i WHERE i.description = :description")
    , @NamedQuery(name = "Issuetracking.findByDatereported", query = "SELECT i FROM Issuetracking i WHERE i.datereported = :datereported")
    , @NamedQuery(name = "Issuetracking.findByPriorityvote", query = "SELECT i FROM Issuetracking i WHERE i.priorityvote = :priorityvote")
    , @NamedQuery(name = "Issuetracking.findByStatus", query = "SELECT i FROM Issuetracking i WHERE i.status = :status")
    , @NamedQuery(name = "Issuetracking.findByEmpId", query = "SELECT i FROM Issuetracking i WHERE i.empId = :empId")})
public class Issuetracking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
     @JoinColumn(name = "relatingdeptid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department relatingdeptid;
    
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "datereported")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datereported;
    @Column(name = "priorityvote")
    private Integer priorityvote;
    @Size(max = 50)
    @Column(name = "status")
    private String status;
    
    
    @JoinColumn(name = "empId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee empId;

    public Issuetracking() {
    }

    public Issuetracking(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getRelatingdeptid() {
        return relatingdeptid;
    }

    public void setRelatingdeptid(Department relatingdeptid) {
        this.relatingdeptid = relatingdeptid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

      @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
    public Date getDatereported() {
        return datereported;
    }

    public void setDatereported(Date datereported) {
        this.datereported = datereported;
    }

    public Integer getPriorityvote() {
        return priorityvote;
    }

    public void setPriorityvote(Integer priorityvote) {
        this.priorityvote = priorityvote;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getEmpId() {
        return empId;
    }

    public void setEmpId(Employee empId) {
        this.empId = empId;
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
        if (!(object instanceof Issuetracking)) {
            return false;
        }
        Issuetracking other = (Issuetracking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.Issuetracking[ id=" + id + " ]";
    }
    
}
