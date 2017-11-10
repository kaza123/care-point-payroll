/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.leave_request.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nidura Prageeth
 */
@Entity
@Table(name = "t_leave_details")
public class TLeaveDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_no")
    private Integer indexNo;   

    @JoinColumn(name = "leave_request", referencedColumnName = "index_no")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TLeaveRequest leaveRequest;

    @Column(name = "date")
    private String date;

    @Column(name = "leave_type")
    private String leaveType;

    @Column(name = "real_leave")
    private Boolean realLeave;
   
    @Column(name = "approve")
    private Boolean approve;
   
    @Column(name = "employee")
    private int employee;

    public TLeaveDetails() {
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Boolean getRealLeave() {
        return realLeave;
    }

    public void setRealLeave(Boolean realLeave) {
        this.realLeave = realLeave;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public TLeaveRequest getLeaveRequest() {
        return leaveRequest;
    }

    public void setLeaveRequest(TLeaveRequest leaveRequest) {
        this.leaveRequest = leaveRequest;
    }
    
    
    
    

}
