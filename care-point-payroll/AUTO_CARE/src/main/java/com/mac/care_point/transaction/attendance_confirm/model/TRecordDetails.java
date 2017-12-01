/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.attendance_confirm.model;

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
@Table(name = "t_record_details")
public class TRecordDetails implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_no")
    private Integer indexNo;

    @JoinColumn(name = "employee")
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Employee employee;

    @Column(name = "date")
    private Date date;
  
    @Column(name = "emp_type")
    private String empType;
    
    @Column(name = "emp_epf")
    private String empEpf;
    
    @Column(name = "present")
    private String present;
    
    @Column(name = "`leave`")
    private int leave;
    
    @Column(name = "no_pay")
    private int noPay;
    
    @Column(name = "in_time")
    private String inTime;
    
    @Column(name = "out_time")
    private String outTime;
    
    @Column(name = "ot_hours")
    private int otHours;
    
    @Column(name = "double_ot")
    private int doubleOt;
    
    @Column(name = "late_come")
    private int lateCome;
    
    @Column(name = "early_off")
    private int earlyOff;
    
    @Column(name = "extra_hours")
    private int extraHours;
    
    @Column(name = "leave_type")
    private String leaveType ;

    public TRecordDetails() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getEmpEpf() {
        return empEpf;
    }

    public void setEmpEpf(String empEpf) {
        this.empEpf = empEpf;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }

    public int getNoPay() {
        return noPay;
    }

    public void setNoPay(int noPay) {
        this.noPay = noPay;
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

    public int getOtHours() {
        return otHours;
    }

    public void setOtHours(int otHours) {
        this.otHours = otHours;
    }

    public int getDoubleOt() {
        return doubleOt;
    }

    public void setDoubleOt(int doubleOt) {
        this.doubleOt = doubleOt;
    }

    public int getLateCome() {
        return lateCome;
    }

    public void setLateCome(int lateCome) {
        this.lateCome = lateCome;
    }

    public int getEarlyOff() {
        return earlyOff;
    }

    public void setEarlyOff(int earlyOff) {
        this.earlyOff = earlyOff;
    }

    public int getExtraHours() {
        return extraHours;
    }

    public void setExtraHours(int extraHours) {
        this.extraHours = extraHours;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

  

    
    
    
}
