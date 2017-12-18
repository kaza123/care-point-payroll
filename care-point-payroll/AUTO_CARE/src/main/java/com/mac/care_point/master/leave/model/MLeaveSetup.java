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
    private int annual;
    
    @Column(name = "casual")
    private int casual;
    
    @Column(name = "medical")
    private int medical;
    
    @Column(name = "annual_leave")
    private Integer annualLeave;
   
    @Column(name = "casual_leave")
    private Integer casualLeave;
    
    @Column(name = "medical_leave")
    private Integer medicalLeave;
    

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

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getAnnual() {
        return annual;
    }

    public void setAnnual(int annual) {
        this.annual = annual;
    }

    public int getCasual() {
        return casual;
    }

    public void setCasual(int casual) {
        this.casual = casual;
    }

    public int getMedical() {
        return medical;
    }

    public void setMedical(int medical) {
        this.medical = medical;
    }

    public Integer getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(Integer annualLeave) {
        this.annualLeave = annualLeave;
    }

    public Integer getCasualLeave() {
        return casualLeave;
    }

    public void setCasualLeave(Integer casualLeave) {
        this.casualLeave = casualLeave;
    }

    public Integer getMedicalLeave() {
        return medicalLeave;
    }

    public void setMedicalLeave(Integer medicalLeave) {
        this.medicalLeave = medicalLeave;
    }

    
    
    
}
