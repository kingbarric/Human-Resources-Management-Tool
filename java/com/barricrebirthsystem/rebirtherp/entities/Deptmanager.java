/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "deptmanager")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deptmanager.findAll", query = "SELECT d FROM Deptmanager d")
    , @NamedQuery(name = "Deptmanager.findById", query = "SELECT d FROM Deptmanager d WHERE d.id = :id")})
public class Deptmanager implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "DeptID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department deptID;
    @JoinColumn(name = "EmployeeID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee employeeID;

    public Deptmanager() {
    }

    public Deptmanager(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getDeptID() {
        return deptID;
    }

    public void setDeptID(Department deptID) {
        this.deptID = deptID;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
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
        if (!(object instanceof Deptmanager)) {
            return false;
        }
        Deptmanager other = (Deptmanager) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.Deptmanager[ id=" + id + " ]";
    }
    
}
