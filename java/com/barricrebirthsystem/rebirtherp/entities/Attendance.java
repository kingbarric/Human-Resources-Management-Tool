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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "attendance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attendance.findAll", query = "SELECT a FROM Attendance a")
    , @NamedQuery(name = "Attendance.findById", query = "SELECT a FROM Attendance a WHERE a.id = :id")
    , @NamedQuery(name = "Attendance.findByAttendanceDate", query = "SELECT a FROM Attendance a WHERE a.attendanceDate = :attendanceDate")
    , @NamedQuery(name = "Attendance.findByClockIntime", query = "SELECT a FROM Attendance a WHERE a.clockIntime = :clockIntime")
    , @NamedQuery(name = "Attendance.findByClockoutTime", query = "SELECT a FROM Attendance a WHERE a.clockoutTime = :clockoutTime")
    , @NamedQuery(name = "Attendance.findByReason", query = "SELECT a FROM Attendance a WHERE a.reason = :reason")
    , @NamedQuery(name = "Attendance.findByEmployeeId", query = "SELECT a FROM Attendance a WHERE a.employeeId = :employeeId")})
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "attendance_date")
    @Temporal(TemporalType.DATE)
    private Date attendanceDate;
    @Column(name = "clock_intime")
    @Temporal(TemporalType.TIME)
    private Date clockIntime;
    @Column(name = "clockout_time")
    @Temporal(TemporalType.TIME)
    private Date clockoutTime;
    @Size(max = 255)
    @Column(name = "reason")
    private String reason;
     @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee employeeId;

    public Attendance() {
    }

    public Attendance(Integer id) {
        this.id = id;
    }

    public Attendance(Integer id, Employee employeeId) {
        this.id = id;
        this.employeeId = employeeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="HH:mm:ss")
    public Date getClockIntime() {
        return clockIntime;
    }

    public void setClockIntime(Date clockIntime) {
        this.clockIntime = clockIntime;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="HH:mm:ss")
    public Date getClockoutTime() {
        return clockoutTime;
    }

     @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="hh-MM-ss")
    public void setClockoutTime(Date clockoutTime) {
        this.clockoutTime = clockoutTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
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
        if (!(object instanceof Attendance)) {
            return false;
        }
        Attendance other = (Attendance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.Attendance[ id=" + id + " ]";
    }
    
}
