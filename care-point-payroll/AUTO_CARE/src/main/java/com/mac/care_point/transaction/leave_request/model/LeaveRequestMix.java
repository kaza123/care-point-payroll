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

    private List<TLeaveRequest> leaveRequest;

    public LeaveRequestMix() {
    }

    public LeaveRequestMix(List<TLeaveRequest> leaveRequest) {
        this.leaveRequest = leaveRequest;
    }

    public List<TLeaveRequest> getLeaveRequest() {
        return leaveRequest;
    }

    public void setLeaveRequest(List<TLeaveRequest> leaveRequest) {
        this.leaveRequest = leaveRequest;
    }

    @Override
    public String toString() {
        return "LeaveRequestMix{" + "leaveRequest=" + leaveRequest + '}';
    }

    
    
}
