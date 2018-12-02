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
@Table(name = "notification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
    , @NamedQuery(name = "Notification.findById", query = "SELECT n FROM Notification n WHERE n.id = :id")
    , @NamedQuery(name = "Notification.findByEmpid", query = "SELECT n FROM Notification n WHERE n.empid = :empid")
    , @NamedQuery(name = "Notification.findByDateCreated", query = "SELECT n FROM Notification n WHERE n.dateCreated = :dateCreated")
    , @NamedQuery(name = "Notification.findByDepartmentId", query = "SELECT n FROM Notification n WHERE n.departmentId = :departmentId")
    , @NamedQuery(name = "Notification.findByIsSeen", query = "SELECT n FROM Notification n WHERE n.isSeen = :isSeen")
    , @NamedQuery(name = "Notification.findByForAll", query = "SELECT n FROM Notification n WHERE n.forAll = :forAll")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "empid")
    private Integer empid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "department_id")
    private Integer departmentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_seen")
    private boolean isSeen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "for_all")
    private String forAll;
    @Column(name="message")
    @Size(min=1,max=255)
    private String message;

    public Notification() {
    }

    public Notification(Integer id) {
        this.id = id;
    }

    public Notification(Integer id, Date dateCreated, boolean isSeen, String forAll) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.isSeen = isSeen;
        this.forAll = forAll;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public boolean getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(boolean isSeen) {
        this.isSeen = isSeen;
    }

    public String getForAll() {
        return forAll;
    }

    public void setForAll(String forAll) {
        this.forAll = forAll;
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
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.Notification[ id=" + id + " ]";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
