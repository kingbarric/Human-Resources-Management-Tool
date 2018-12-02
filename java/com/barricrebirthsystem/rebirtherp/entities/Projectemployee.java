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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "projectemployee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projectemployee.findAll", query = "SELECT p FROM Projectemployee p")
    , @NamedQuery(name = "Projectemployee.findByNote", query = "SELECT p FROM Projectemployee p WHERE p.note = :note")
    , @NamedQuery(name = "Projectemployee.findByDaysWork", query = "SELECT p FROM Projectemployee p WHERE p.daysWork = :daysWork")
    , @NamedQuery(name = "Projectemployee.findById", query = "SELECT p FROM Projectemployee p WHERE p.id = :id")})
public class Projectemployee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "Note")
    private String note;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DaysWork")
    private int daysWork;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "EmployeeID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee employeeID;
    @JoinColumn(name = "ProjectID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Project projectID;

    public Projectemployee() {
    }

    public Projectemployee(Integer id) {
        this.id = id;
    }

    public Projectemployee(Integer id, int daysWork) {
        this.id = id;
        this.daysWork = daysWork;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getDaysWork() {
        return daysWork;
    }

    public void setDaysWork(int daysWork) {
        this.daysWork = daysWork;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
    }

    public Project getProjectID() {
        return projectID;
    }

    public void setProjectID(Project projectID) {
        this.projectID = projectID;
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
        if (!(object instanceof Projectemployee)) {
            return false;
        }
        Projectemployee other = (Projectemployee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.Projectemployee[ id=" + id + " ]";
    }
    
}
