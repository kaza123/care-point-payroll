/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.leave_request.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nidura Prageeth
 */
public class LeaveRequestMix implements Serializable {

    private int employee;
    private int branch;
    private String reason;
    private Boolean approve;
    private Boolean view;
    private List<TLeaveRequest> leaveRequest;

    public LeaveRequestMix() {
    }

    public LeaveRequestMix(int employee, int branch, String reason, Boolean approve, Boolean view, List<TLeaveRequest> leaveRequest) {
        this.employee = employee;
        this.branch = branch;
        this.reason = reason;
        this.approve = approve;
        this.view = view;
        this.leaveRequest = leaveRequest;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public Boolean getView() {
        return view;
    }

    public void setView(Boolean view) {
        this.view = view;
    }

    public List<TLeaveRequest> getLeaveRequest() {
        return leaveRequest;
    }

    public void setLeaveRequest(List<TLeaveRequest> leaveRequest) {
        this.leaveRequest = leaveRequest;
    }

    @Override
    public String toString() {
        return "LeaveRequestMix{" + "employee=" + employee + ", branch=" + branch + ", reason=" + reason + ", approve=" + approve + ", view=" + view + ", leaveRequest=" + leaveRequest + '}';
    }

    
    
    
}
