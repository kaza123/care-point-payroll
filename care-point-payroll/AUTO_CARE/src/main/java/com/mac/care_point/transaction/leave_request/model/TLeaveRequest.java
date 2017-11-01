/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.leave_request.model;

import java.io.Serializable;
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
@Table(name = "t_leave_request")
public class TLeaveRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_no")
    private Integer indexNo;

    @Column(name = "from_date")
    private String fromDate;

    @Column(name = "to_date")
    private String toDate;

    @Column(name = "approve")
    private Boolean approve;

    @Column(name = "leave_type")
    private String leaveType;

    @JoinColumn(name = "`leave`", referencedColumnName = "index_no")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TLeave leave;

    public TLeaveRequest() {
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public TLeave getLeave() {
        return leave;
    }

    public void setLeave(TLeave leave) {
        this.leave = leave;
    }

    @Override
    public String toString() {
        return "TLeaveRequest{" + "indexNo=" + indexNo + ", fromDate=" + fromDate + ", toDate=" + toDate + ", approve=" + approve + ", leaveType=" + leaveType + ", leave=" + leave + '}';
    }

 

    

}
