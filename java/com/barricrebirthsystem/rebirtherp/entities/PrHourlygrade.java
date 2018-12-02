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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "pr_hourlygrade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrHourlygrade.findAll", query = "SELECT p FROM PrHourlygrade p")
    , @NamedQuery(name = "PrHourlygrade.findById", query = "SELECT p FROM PrHourlygrade p WHERE p.id = :id")
    , @NamedQuery(name = "PrHourlygrade.findByName", query = "SELECT p FROM PrHourlygrade p WHERE p.name = :name")
    , @NamedQuery(name = "PrHourlygrade.findByRate", query = "SELECT p FROM PrHourlygrade p WHERE p.rate = :rate")})
public class PrHourlygrade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "rate")
    private String rate;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne
    private Employee createdBy;

    public PrHourlygrade() {
    }

    public PrHourlygrade(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
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
        if (!(object instanceof PrHourlygrade)) {
            return false;
        }
        PrHourlygrade other = (PrHourlygrade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.PrHourlygrade[ id=" + id + " ]";
    }
    
}
