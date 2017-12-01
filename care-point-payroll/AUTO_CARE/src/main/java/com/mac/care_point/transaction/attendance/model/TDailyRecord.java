/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.attendance.model;

import com.mac.care_point.master.employee.model.Employee;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Nidura Prageeth
 */
@Entity
@Table(name = "t_daily_record")
public class TDailyRecord implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_no")
    private Integer indexNo;

//    @JoinColumn(name = "employee")
//    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Column(name = "employee")
    private int employee;

    @Column(name = "branch")
    private int branch;
    
    @Column(name = "date")
    private String date;
    
    @Column(name = "emp_epf")
    private String empEpf;
    
    @Column(name = "emp_type")
    private String empType;
    
    @Column(name = "in_final")
    private String inTime;
    
    @Column(name = "out_final")
    private String outTime;

    public TDailyRecord() {
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    public String getEmpEpf() {
        return empEpf;
    }

    public void setEmpEpf(String empEpf) {
        this.empEpf = empEpf;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TDailyRecord{" + "indexNo=" + indexNo + ", employee=" + employee + ", branch=" + branch + ", date=" + date + ", empEpf=" + empEpf + ", empType=" + empType + ", inTime=" + inTime + ", outTime=" + outTime + '}';
    }

    
    
    
    
}
