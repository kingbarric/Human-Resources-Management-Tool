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
@Table(name = "audit_userlogin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuditUserlogin.findAll", query = "SELECT a FROM AuditUserlogin a")
    , @NamedQuery(name = "AuditUserlogin.findById", query = "SELECT a FROM AuditUserlogin a WHERE a.id = :id")
    , @NamedQuery(name = "AuditUserlogin.findByUserid", query = "SELECT a FROM AuditUserlogin a WHERE a.userid = :userid")
    , @NamedQuery(name = "AuditUserlogin.findByUsername", query = "SELECT a FROM AuditUserlogin a WHERE a.username = :username")
    , @NamedQuery(name = "AuditUserlogin.findByEmpid", query = "SELECT a FROM AuditUserlogin a WHERE a.empid = :empid")
    , @NamedQuery(name = "AuditUserlogin.findByDatetime", query = "SELECT a FROM AuditUserlogin a WHERE a.datetime = :datetime")})
public class AuditUserlogin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "userid")
    private int userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Column(name = "empid")
    private int empid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;

    public AuditUserlogin() {
    }

    public AuditUserlogin(Integer id) {
        this.id = id;
    }

    public AuditUserlogin(Integer id, int userid, String username, int empid, Date datetime) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.empid = empid;
        this.datetime = datetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
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
        if (!(object instanceof AuditUserlogin)) {
            return false;
        }
        AuditUserlogin other = (AuditUserlogin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.AuditUserlogin[ id=" + id + " ]";
    }
    
}
