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
@Table(name = "users_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersRole.findAll", query = "SELECT u FROM UsersRole u")
    , @NamedQuery(name = "UsersRole.findById", query = "SELECT u FROM UsersRole u WHERE u.id = :id")
    , @NamedQuery(name = "UsersRole.findByUser", query = "SELECT u FROM UsersRole u WHERE u.userid = :uid")})
public class UsersRole implements Serializable {

    
    @Size(min = 1, max = 45)
    @Column(name = "role_level")
    private String roleLevel;
   

    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_1")
    private int menu1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_2")
    private int menu2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_3")
    private int menu3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_4")
    private int menu4;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_5")
    private int menu5;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_6")
    private int menu6;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_7")
    private int menu7;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_8")
    private int menu8;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_9")
    private int menu9;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_10")
    private int menu10;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_11")
    private int menu11;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_12")
    private int menu12;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_13")
    private int menu13;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userid;
    
    
    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Employee empId;

    public UsersRole() {
    }

    public UsersRole(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
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
        if (!(object instanceof UsersRole)) {
            return false;
        }
        UsersRole other = (UsersRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barricrebirthsystem.rebirtherp.entities.UsersRole[ id=" + id + " ]";
    }

    public int getMenu1() {
        return menu1;
    }

    public void setMenu1(int menu1) {
        this.menu1 = menu1;
    }

    public int getMenu2() {
        return menu2;
    }

    public void setMenu2(int menu2) {
        this.menu2 = menu2;
    }

    public int getMenu3() {
        return menu3;
    }

    public void setMenu3(int menu3) {
        this.menu3 = menu3;
    }

    public int getMenu4() {
        return menu4;
    }

    public void setMenu4(int menu4) {
        this.menu4 = menu4;
    }

    public int getMenu5() {
        return menu5;
    }

    public void setMenu5(int menu5) {
        this.menu5 = menu5;
    }

    public int getMenu6() {
        return menu6;
    }

    public void setMenu6(int menu6) {
        this.menu6 = menu6;
    }

    public int getMenu7() {
        return menu7;
    }

    public void setMenu7(int menu7) {
        this.menu7 = menu7;
    }

    public int getMenu8() {
        return menu8;
    }

    public void setMenu8(int menu8) {
        this.menu8 = menu8;
    }

    public int getMenu9() {
        return menu9;
    }

    public void setMenu9(int menu9) {
        this.menu9 = menu9;
    }

    public int getMenu10() {
        return menu10;
    }

    public void setMenu10(int menu10) {
        this.menu10 = menu10;
    }

    public int getMenu11() {
        return menu11;
    }

    public void setMenu11(int menu11) {
        this.menu11 = menu11;
    }

    public int getMenu12() {
        return menu12;
    }

    public void setMenu12(int menu12) {
        this.menu12 = menu12;
    }

    public int getMenu13() {
        return menu13;
    }

    public void setMenu13(int menu13) {
        this.menu13 = menu13;
    }

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Employee getEmpId() {
        return empId;
    }

    public void setEmpId(Employee empId) {
        this.empId = empId;
    }

}
