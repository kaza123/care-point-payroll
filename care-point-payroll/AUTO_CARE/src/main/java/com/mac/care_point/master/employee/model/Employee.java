/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.employee.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author nidura prageeth
 */
@Entity
@Table(name = "m_employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_no")
    private Integer indexNo;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "address_line1")
    private String addressLine1;

    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "address_line2")
    private String addressLine2;

    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "address_line3")
    private String addressLine3;

    @Basic(optional = false)
    @Size(min = 1, max = 25)
    @Column(name = "mobile")
    private String mobile;

    @Basic(optional = false)
    @Column(name = "branch")
    private int branch;

    @Basic(optional = false)
    @Size(min = 1, max = 25)
    @Column(name = "type")
    private String type;

    @Basic(optional = false)
    @Size(min = 1, max = 25)
    @Column(name = "rol")
    private String rol;

    @Size(max = 45)
    @Column(name = "image")
    private String image;

    @Column(name = "bay")
    private Integer bay;
   
    @Column(name = "epf_no")
    private Integer epfNo;
    
    @Column(name = "tel_no")
    private Integer telNo;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "nic")
    private String nic;
    
    @Column(name = "civil_status")
    private String civilStatus;
    
    @Column(name = "joined_date")
    private Date joinedDate;
    
    @Column(name = "active")
    private Boolean active;
    
    @Column(name = "confirm")
    private Boolean confirm;
    
    @Column(name = "resigned")
    private Boolean resigned;
    
    @Column(name = "basic_salary")
    private BigDecimal basicSalary;
    
    @Column(name = "basic_for_epf")
    private BigDecimal basicForEpf;
    
    @Column(name = "payroll_type")
    private String payrollType;
    
    @Column(name = "epf_status")
    private String epfStatus;
    
    @Column(name = "epf_grade")
    private String epfGrade;
    
    @Column(name = "epf_applicable")
    private Boolean epfApplicable;
    
    @Column(name = "ot_applicable")
    private Boolean OtApplicable;
    
    @Column(name = "morning_ot")
    private Boolean morningOt;
    
    @Column(name = "attendance_allowance")
    private Boolean attendanceAllowance;
    
    @Column(name = "tax_applicable")
    private Boolean taxApplicable;
    

    public Employee() {
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getBay() {
        return bay;
    }

    public void setBay(Integer bay) {
        this.bay = bay;
    }

    public Integer getEpfNo() {
        return epfNo;
    }

    public void setEpfNo(Integer epfNo) {
        this.epfNo = epfNo;
    }

    public Integer getTelNo() {
        return telNo;
    }

    public void setTelNo(Integer telNo) {
        this.telNo = telNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public Boolean getResigned() {
        return resigned;
    }

    public void setResigned(Boolean resigned) {
        this.resigned = resigned;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getBasicForEpf() {
        return basicForEpf;
    }

    public void setBasicForEpf(BigDecimal basicForEpf) {
        this.basicForEpf = basicForEpf;
    }

    public String getPayrollType() {
        return payrollType;
    }

    public void setPayrollType(String payrollType) {
        this.payrollType = payrollType;
    }

    public String getEpfStatus() {
        return epfStatus;
    }

    public void setEpfStatus(String epfStatus) {
        this.epfStatus = epfStatus;
    }

    public String getEpfGrade() {
        return epfGrade;
    }

    public void setEpfGrade(String epfGrade) {
        this.epfGrade = epfGrade;
    }

    public Boolean getEpfApplicable() {
        return epfApplicable;
    }

    public void setEpfApplicable(Boolean epfApplicable) {
        this.epfApplicable = epfApplicable;
    }

    public Boolean getOtApplicable() {
        return OtApplicable;
    }

    public void setOtApplicable(Boolean OtApplicable) {
        this.OtApplicable = OtApplicable;
    }

    public Boolean getMorningOt() {
        return morningOt;
    }

    public void setMorningOt(Boolean morningOt) {
        this.morningOt = morningOt;
    }

    public Boolean getAttendanceAllowance() {
        return attendanceAllowance;
    }

    public void setAttendanceAllowance(Boolean attendanceAllowance) {
        this.attendanceAllowance = attendanceAllowance;
    }

    public Boolean getTaxApplicable() {
        return taxApplicable;
    }

    public void setTaxApplicable(Boolean taxApplicable) {
        this.taxApplicable = taxApplicable;
    }
    
    
    
    
   
    
}
