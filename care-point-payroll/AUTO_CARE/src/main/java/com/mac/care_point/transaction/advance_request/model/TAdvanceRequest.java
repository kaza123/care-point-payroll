/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.advance_request.model;

import com.mac.care_point.master.employee.model.Employee;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Thilina Kalum
 */
@Entity
@Table(name = "t_advance_request")
public class TAdvanceRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_no", nullable = false)
    private Integer indexNo;
    
    @Basic(optional = false)
    @Column(name = "advance_no", nullable = false)
    private Integer advanceNo;
    
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date month;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private Boolean approve;
    
    @Basic(optional = false)
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private int branch;
    
    @Basic(optional = false)
    @Column(name = "request_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date requestDate;
    
    @Column(name = "approve_date")
    @Temporal(TemporalType.DATE)
    private Date approveDate;
    
    @Column(name = "issue_date")
    @Temporal(TemporalType.DATE)
    private Date issueDate;
    
    @JoinColumn(name = "employee", referencedColumnName = "index_no", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employee;

    public TAdvanceRequest() {
    }

    public TAdvanceRequest(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public int getAdvanceNo() {
        return advanceNo;
    }

    public void setAdvanceNo(int advanceNo) {
        this.advanceNo = advanceNo;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    @Override
    public String toString() {
        return "TAdvanceRequest{" + "indexNo=" + indexNo + ", advanceNo=" + advanceNo + ", month=" + month + ", approve=" + approve + ", amount=" + amount + ", branch=" + branch + ", requestDate=" + requestDate + ", approveDate=" + approveDate + ", issueDate=" + issueDate + ", employee=" + employee + '}';
    }
    
}
