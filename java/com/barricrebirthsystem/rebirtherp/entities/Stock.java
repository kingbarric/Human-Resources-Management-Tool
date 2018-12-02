/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s")
    , @NamedQuery(name = "Stock.findById", query = "SELECT s FROM Stock s WHERE s.id = :id")
    , @NamedQuery(name = "Stock.findByCategoryId", query = "SELECT s FROM Stock s WHERE s.categoryId = :categoryId")
    , @NamedQuery(name = "Stock.findByDescription", query = "SELECT s FROM Stock s WHERE s.description = :description")
    , @NamedQuery(name = "Stock.findByTotalStock", query = "SELECT s FROM Stock s WHERE s.totalStock = :totalStock")
    , @NamedQuery(name = "Stock.findByAmount", query = "SELECT s FROM Stock s WHERE s.amount = :amount")
    , @NamedQuery(name = "Stock.findByCreatedOn", query = "SELECT s FROM Stock s WHERE s.createdOn = :createdOn")})
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
     @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StockCategory categoryId;
    
    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_stock")
    private int totalStock;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    
     @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee createdby;

    public Stock() {
    }

    public Stock(Integer id) {
        this.id = id;
    }

    public Stock(Integer id, StockCategory categoryId, String description, int totalStock, BigDecimal amount, Date createdOn) {
        this.id = id;
        this.categoryId = categoryId;
        this.description = description;
        this.totalStock = totalStock;
        this.amount = amount;
        this.createdOn = createdOn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StockCategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(StockCategory categoryId) {
        this.categoryId = categoryId;
    }

    public Employee getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Employee createdby) {
        this.createdby = createdby;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
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
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.Stock[ id=" + id + " ]";
    }
    
}
