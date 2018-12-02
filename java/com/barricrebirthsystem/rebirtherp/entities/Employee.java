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
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
    , @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id")
    , @NamedQuery(name = "Employee.findByStaffid", query = "SELECT e FROM Employee e WHERE e.staffid = :staffid")
    , @NamedQuery(name = "Employee.findByFirstname", query = "SELECT e FROM Employee e WHERE e.firstname = :firstname")
    , @NamedQuery(name = "Employee.findByLastname", query = "SELECT e FROM Employee e WHERE e.lastname = :lastname")
    , @NamedQuery(name = "Employee.findByDob", query = "SELECT e FROM Employee e WHERE e.dob = :dob")
    , @NamedQuery(name = "Employee.findByGender", query = "SELECT e FROM Employee e WHERE e.gender = :gender")
    , @NamedQuery(name = "Employee.findByMaritalstatus", query = "SELECT e FROM Employee e WHERE e.maritalstatus = :maritalstatus")
    , @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email")
    , @NamedQuery(name = "Employee.findByPhone", query = "SELECT e FROM Employee e WHERE e.phone = :phone")
    , @NamedQuery(name = "Employee.findByAddress", query = "SELECT e FROM Employee e WHERE e.address = :address")
    , @NamedQuery(name = "Employee.findByJoinningdate", query = "SELECT e FROM Employee e WHERE e.joinningdate = :joinningdate")
    , @NamedQuery(name = "Employee.findByImagename", query = "SELECT e FROM Employee e WHERE e.imagename = :imagename")
    , @NamedQuery(name = "Employee.findByAccountNo", query = "SELECT e FROM Employee e WHERE e.accountNo = :accountNo")
    , @NamedQuery(name = "Employee.findByBankName", query = "SELECT e FROM Employee e WHERE e.bankName = :bankName")
    , @NamedQuery(name = "Employee.findByGla", query = "SELECT e FROM Employee e WHERE e.gla = :gla")
    , @NamedQuery(name = "Employee.findByGrossSalary", query = "SELECT e FROM Employee e WHERE e.grossSalary = :grossSalary")
    , @NamedQuery(name = "Employee.findByNoLeaveDays", query = "SELECT e FROM Employee e WHERE e.noLeaveDays = :noLeaveDays")
    , @NamedQuery(name = "Employee.findByPension", query = "SELECT e FROM Employee e WHERE e.pension = :pension")
    , @NamedQuery(name = "Employee.findByNhis", query = "SELECT e FROM Employee e WHERE e.nhis = :nhis")
    , @NamedQuery(name = "Employee.findByNhf", query = "SELECT e FROM Employee e WHERE e.nhf = :nhf")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 10)
    @Column(name = "staffid")
    private String staffid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "maritalstatus")
    private String maritalstatus;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "phone")
    private String phone;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "joinningdate")
    @Temporal(TemporalType.DATE)
    private Date joinningdate;
    @Size(max = 150)
    @Column(name = "imagename")
    private String imagename;
    @Size(max = 10)
    @Column(name = "account_no")
    private String accountNo;
    @Size(max = 45)
    @Column(name = "bank_name")
    private String bankName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GLA")
    private BigDecimal gla;
   
    @Column(name = "gross_salary")
    private BigDecimal grossSalary;
    
    @Column(name = "no_leave_days")
    private int noLeaveDays;
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "pension")
    private boolean pension;
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "nhis")
    private boolean nhis;
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "nhf")
    private boolean nhf;

    public Employee() {
    }

    public Employee(Integer id) {
        this.id = id;
    }

    public Employee(Integer id, String firstname, String lastname, String gender, String maritalstatus, String email, String phone, Date joinningdate, BigDecimal grossSalary, int noLeaveDays, boolean pension, boolean nhis, boolean nhf) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.maritalstatus = maritalstatus;
        this.email = email;
        this.phone = phone;
        this.joinningdate = joinningdate;
        this.grossSalary = grossSalary;
        this.noLeaveDays = noLeaveDays;
        this.pension = pension;
        this.nhis = nhis;
        this.nhf = nhf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getJoinningdate() {
        return joinningdate;
    }

    public void setJoinningdate(Date joinningdate) {
        this.joinningdate = joinningdate;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BigDecimal getGla() {
        return gla;
    }

    public void setGla(BigDecimal gla) {
        this.gla = gla;
    }

    public BigDecimal getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(BigDecimal grossSalary) {
        this.grossSalary = grossSalary;
    }

    public int getNoLeaveDays() {
        return noLeaveDays;
    }

    public void setNoLeaveDays(int noLeaveDays) {
        this.noLeaveDays = noLeaveDays;
    }

    public boolean getPension() {
        return pension;
    }

    public void setPension(boolean pension) {
        this.pension = pension;
    }

    public boolean getNhis() {
        return nhis;
    }

    public void setNhis(boolean nhis) {
        this.nhis = nhis;
    }

    public boolean getNhf() {
        return nhf;
    }

    public void setNhf(boolean nhf) {
        this.nhf = nhf;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.Employee[ id=" + id + " ]";
    }
    
}
