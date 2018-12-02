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
@Table(name = "pr_salary_config")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrSalaryConfig.findAll", query = "SELECT p FROM PrSalaryConfig p")
    , @NamedQuery(name = "PrSalaryConfig.findById", query = "SELECT p FROM PrSalaryConfig p WHERE p.id = :id")
    , @NamedQuery(name = "PrSalaryConfig.findByName", query = "SELECT p FROM PrSalaryConfig p WHERE p.name = :name")
    , @NamedQuery(name = "PrSalaryConfig.findByPercentage", query = "SELECT p FROM PrSalaryConfig p WHERE p.percentage = :percentage")
    , @NamedQuery(name = "PrSalaryConfig.findByDescription", query = "SELECT p FROM PrSalaryConfig p WHERE p.description = :description")})
public class PrSalaryConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Column(name = "percentage")
    private Integer percentage;
    @Size(max = 255)
    @Column(name = "description")
    private String description;

    public PrSalaryConfig() {
    }

    public PrSalaryConfig(Integer id) {
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

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof PrSalaryConfig)) {
            return false;
        }
        PrSalaryConfig other = (PrSalaryConfig) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.PrSalaryConfig[ id=" + id + " ]";
    }
    
}
