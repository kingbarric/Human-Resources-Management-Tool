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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "leave_approvals")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeaveApprovals.findAll", query = "SELECT l FROM LeaveApprovals l")
    , @NamedQuery(name = "LeaveApprovals.findById", query = "SELECT l FROM LeaveApprovals l WHERE l.id = :id")
    , @NamedQuery(name = "LeaveApprovals.findByLeaveId", query = "SELECT l FROM LeaveApprovals l WHERE l.leaveId = :leaveId")
    , @NamedQuery(name = "LeaveApprovals.findByApproval1Status", query = "SELECT l FROM LeaveApprovals l WHERE l.approval1Status = :approval1Status")
    , @NamedQuery(name = "LeaveApprovals.findByApproval2Status", query = "SELECT l FROM LeaveApprovals l WHERE l.approval2Status = :approval2Status")
    , @NamedQuery(name = "LeaveApprovals.findByApproval3Status", query = "SELECT l FROM LeaveApprovals l WHERE l.approval3Status = :approval3Status")
    , @NamedQuery(name = "LeaveApprovals.findByApproval1Date", query = "SELECT l FROM LeaveApprovals l WHERE l.approval1Date = :approval1Date")
    , @NamedQuery(name = "LeaveApprovals.findByApproval2Date", query = "SELECT l FROM LeaveApprovals l WHERE l.approval2Date = :approval2Date")
    , @NamedQuery(name = "LeaveApprovals.findByApproval3Date", query = "SELECT l FROM LeaveApprovals l WHERE l.approval3Date = :approval3Date")})
public class LeaveApprovals implements Serializable {

    @JoinColumn(name = "leave_id", referencedColumnName = "id")
    @ManyToOne
    private LeaveApplication leaveId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;
   
    @Size(max = 45)
    @Column(name = "approval1_status")
    private String approval1Status;
    @Size(max = 45)
    @Column(name = "approval2_status")
    private String approval2Status;
    @Size(max = 45)
    @Column(name = "approval3_status")
    private String approval3Status;
    @Column(name = "approval1_date")
    @Temporal(TemporalType.DATE)
    private Date approval1Date;
    @Column(name = "approval2_date")
    @Temporal(TemporalType.DATE)
    private Date approval2Date;
    @Column(name = "approval3_date")
    @Temporal(TemporalType.DATE)
    private Date approval3Date;
    @JoinColumn(name = "approvedby1_id", referencedColumnName = "id")
    @ManyToOne
    private Employee approvedby1Id;
    @JoinColumn(name = "approvedby2_id", referencedColumnName = "id")
    @ManyToOne
    private Employee approvedby2Id;
    @JoinColumn(name = "approvedby3_id", referencedColumnName = "id")
    @ManyToOne
    private Employee approvedby3Id;

    @Size(max = 255)
    @Column(name = "comment1")
    private String comment1;
    
    @Size(max = 255)
    @Column(name = "comment2")
    private String comment2;
    
    @Size(max = 255)
    @Column(name = "comment3")
    private String comment3;
    
    public LeaveApprovals() {
    }

    public LeaveApprovals(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   

    public String getApproval1Status() {
        return approval1Status;
    }

    public void setApproval1Status(String approval1Status) {
        this.approval1Status = approval1Status;
    }

    public String getApproval2Status() {
        return approval2Status;
    }

    public void setApproval2Status(String approval2Status) {
        this.approval2Status = approval2Status;
    }

    public String getApproval3Status() {
        return approval3Status;
    }

    public void setApproval3Status(String approval3Status) {
        this.approval3Status = approval3Status;
    }

    public Date getApproval1Date() {
        return approval1Date;
    }

    public void setApproval1Date(Date approval1Date) {
        this.approval1Date = approval1Date;
    }

    public Date getApproval2Date() {
        return approval2Date;
    }

    public void setApproval2Date(Date approval2Date) {
        this.approval2Date = approval2Date;
    }

    public Date getApproval3Date() {
        return approval3Date;
    }

    public void setApproval3Date(Date approval3Date) {
        this.approval3Date = approval3Date;
    }

    public Employee getApprovedby1Id() {
        return approvedby1Id;
    }

    public void setApprovedby1Id(Employee approvedby1Id) {
        this.approvedby1Id = approvedby1Id;
    }

    public Employee getApprovedby2Id() {
        return approvedby2Id;
    }

    public void setApprovedby2Id(Employee approvedby2Id) {
        this.approvedby2Id = approvedby2Id;
    }

    public Employee getApprovedby3Id() {
        return approvedby3Id;
    }

    public void setApprovedby3Id(Employee approvedby3Id) {
        this.approvedby3Id = approvedby3Id;
    }

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public String getComment3() {
        return comment3;
    }

    public void setComment3(String comment3) {
        this.comment3 = comment3;
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
        if (!(object instanceof LeaveApprovals)) {
            return false;
        }
        LeaveApprovals other = (LeaveApprovals) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.LeaveApprovals[ id=" + id + " ]";
    }

    public LeaveApplication getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(LeaveApplication leaveId) {
        this.leaveId = leaveId;
    }
    
}
