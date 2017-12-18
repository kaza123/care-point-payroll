/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.loan.loan_request.model;

import com.mac.care_point.master.employee.model.Employee;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "t_loan_request")
public class TLoanRequest implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_no")
    private Integer indexNo;

    @Column(name = "loan_no")
    private int loanNo;
    
    @JoinColumn(name = "employee")
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private Employee employee;
    
    @JoinColumn(name = "loan_type")
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private MLoanType loanType;
    
    @Column(name = "request_date")
    private Date requestDate;
    
    @Column(name = "start_date")
    private Date startDate;
   
    @Column(name = "approve_date")
    private Date approveDate;
    
    @Column(name = "issue_date")
    private Date issueDate;
    
    @Column(name = "remark")
    private String remark;
    
    @Column(name = "approve")
    private Boolean approve;
    
    @Column(name = "issue")
    private Boolean issue;
    
    @Column(name = "amount")
    private BigDecimal amount;
    
    @Column(name = "monthly_deduction")
    private BigDecimal monthlyDeduction;
    
    @Column(name = "interest_rate")
    private Integer interestRate;
   
    @Column(name = "installment_count")
    private Integer installmentCount;
    
    @Column(name = "branch")
    private int branch;

    public TLoanRequest() {
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public int getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(int loanNo) {
        this.loanNo = loanNo;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public MLoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(MLoanType loanType) {
        this.loanType = loanType;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public Boolean getIssue() {
        return issue;
    }

    public void setIssue(Boolean issue) {
        this.issue = issue;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getMonthlyDeduction() {
        return monthlyDeduction;
    }

    public void setMonthlyDeduction(BigDecimal monthlyDeduction) {
        this.monthlyDeduction = monthlyDeduction;
    }

    public Integer getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Integer interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(Integer installmentCount) {
        this.installmentCount = installmentCount;
    }

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "TLoanRequest{" + "indexNo=" + indexNo + ", loanNo=" + loanNo + ", employee=" + employee + ", loanType=" + loanType + ", requestDate=" + requestDate + ", startDate=" + startDate + ", approveDate=" + approveDate + ", issueDate=" + issueDate + ", remark=" + remark + ", approve=" + approve + ", issue=" + issue + ", amount=" + amount + ", monthlyDeduction=" + monthlyDeduction + ", interestRate=" + interestRate + ", installmentCount=" + installmentCount + ", branch=" + branch + '}';
    }

    
    
    
    
    
    
    
}
