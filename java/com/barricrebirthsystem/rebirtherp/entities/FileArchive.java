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
@Table(name = "file_archive")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FileArchive.findAll", query = "SELECT f FROM FileArchive f")
    , @NamedQuery(name = "FileArchive.findById", query = "SELECT f FROM FileArchive f WHERE f.id = :id")
    , @NamedQuery(name = "FileArchive.findByOwner", query = "SELECT f FROM FileArchive f WHERE f.owner = :owner OR f.accesslevel =2")
    , @NamedQuery(name = "FileArchive.findByOwnerdept", query = "SELECT f FROM FileArchive f WHERE f.ownerdept = :ownerdept")
    , @NamedQuery(name = "FileArchive.findByAccesslevel", query = "SELECT f FROM FileArchive f WHERE f.accesslevel = :accesslevel")
    , @NamedQuery(name = "FileArchive.findByFilename", query = "SELECT f FROM FileArchive f WHERE f.filename = :filename")
    , @NamedQuery(name = "FileArchive.findByFilepath", query = "SELECT f FROM FileArchive f WHERE f.filepath = :filepath")
    , @NamedQuery(name = "FileArchive.findByType", query = "SELECT f FROM FileArchive f WHERE f.type = :type")
    , @NamedQuery(name = "FileArchive.findByContenttype", query = "SELECT f FROM FileArchive f WHERE f.contenttype = :contenttype")
    , @NamedQuery(name = "FileArchive.findByRelatedfile", query = "SELECT f FROM FileArchive f WHERE f.relatedfile = :relatedfile")
    , @NamedQuery(name = "FileArchive.findByDescription", query = "SELECT f FROM FileArchive f WHERE f.description = :description")
    , @NamedQuery(name = "FileArchive.findByDateuploaded", query = "SELECT f FROM FileArchive f WHERE f.dateuploaded = :dateuploaded")})
public class FileArchive implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "owner")
    private Integer owner;
    @Column(name = "ownerdept")
    private Integer ownerdept;
    @Column(name = "accesslevel")
    private Integer accesslevel;
    @Size(max = 255)
    @Column(name = "filename")
    private String filename;
    @Size(max = 255)
    @Column(name = "filepath")
    private String filepath;
    @Size(max = 10)
    @Column(name = "type")
    private String type;
    @Column(name = "contenttype")
    private String contenttype;
    @Size(max = 20)
    @Column(name = "relatedfile")
    private String relatedfile;
    @Size(max = 255) 
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
   
    @Column(name = "dateuploaded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateuploaded;

    public FileArchive() {
    }

    public FileArchive(Integer id) {
        this.id = id;
    }

    public FileArchive(Integer id, String contenttype, Date dateuploaded) {
        this.id = id;
        this.contenttype = contenttype;
        this.dateuploaded = dateuploaded;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getOwnerdept() {
        return ownerdept;
    }

    public void setOwnerdept(Integer ownerdept) {
        this.ownerdept = ownerdept;
    }

    public Integer getAccesslevel() {
        return accesslevel;
    }

    public void setAccesslevel(Integer accesslevel) {
        this.accesslevel = accesslevel;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getRelatedfile() {
        return relatedfile;
    }

    public void setRelatedfile(String relatedfile) {
        this.relatedfile = relatedfile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

      @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    public Date getDateuploaded() {
        return dateuploaded;
    }

    public void setDateuploaded(Date dateuploaded) {
        this.dateuploaded = dateuploaded;
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
        if (!(object instanceof FileArchive)) {
            return false;
        }
        FileArchive other = (FileArchive) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.FileArchive[ id=" + id + " ]";
    }
    
}
