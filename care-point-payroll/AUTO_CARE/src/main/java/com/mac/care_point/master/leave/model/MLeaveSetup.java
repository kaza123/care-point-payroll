/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.leave.model;

import com.mac.care_point.master.employee.model.Employee;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Nidura Prageeth
 */
@Entity
@Table(name = "m_leave_setup")
public class MLeaveSetup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_no")
    private Integer indexNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee",referencedColumnName = "index_no")
    private Employee employee;
    
    @Column(name = "branch")
    private int branch;

    @Column(name = "year")
    private String year;
    
    @Column(name = "annual")
    private Integer annual;
    
    @Column(name = "casual")
    private Integer casual;
    
    @Column(name = "half_day")
    private Integer halfDay;
    
    @Column(name = "short_leave")
    private Integer shortLeave;

    public MLeaveSetup() {
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getAnnual() {
        return annual;
    }

    public void setAnnual(Integer annual) {
        this.annual = annual;
    }

    public Integer getCasual() {
        return casual;
    }

    public void setCasual(Integer casual) {
        this.casual = casual;
    }

    public Integer getHalfDay() {
        return halfDay;
    }

    public void setHalfDay(Integer halfDay) {
        this.halfDay = halfDay;
    }

    public Integer getShortLeave() {
        return shortLeave;
    }

    public void setShortLeave(Integer shortLeave) {
        this.shortLeave = shortLeave;
    }

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }
    
    
  
    
}
