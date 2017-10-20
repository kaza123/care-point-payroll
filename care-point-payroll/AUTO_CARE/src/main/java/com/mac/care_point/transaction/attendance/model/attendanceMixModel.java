/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.attendance.model;

import java.util.Date;

/**
 *
 * @author 'Kasun Chamara'
 */
public class attendanceMixModel {

    private Integer em_no;
    private String epf_no;
    private String employee_name;
    private String type;
    private Date in_time;
    private Date out_time;
    private Integer payroll_branch;
    private Integer current_branch;

    public attendanceMixModel() {
    }

    public attendanceMixModel(Integer em_no, String epf_no, String employee_name, String type, Date in_time, Date out_time, Integer payroll_branch, Integer current_branch) {
        this.em_no = em_no;
        this.epf_no = epf_no;
        this.employee_name = employee_name;
        this.type = type;
        this.in_time = in_time;
        this.out_time = out_time;
        this.payroll_branch = payroll_branch;
        this.current_branch = current_branch;
    }

    public Integer getEm_no() {
        return em_no;
    }

    public void setEm_no(Integer em_no) {
        this.em_no = em_no;
    }

    public String getEpf_no() {
        return epf_no;
    }

    public void setEpf_no(String epf_no) {
        this.epf_no = epf_no;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getIn_time() {
        return in_time;
    }

    public void setIn_time(Date in_time) {
        this.in_time = in_time;
    }

    public Date getOut_time() {
        return out_time;
    }

    public void setOut_time(Date out_time) {
        this.out_time = out_time;
    }

    public Integer getPayroll_branch() {
        return payroll_branch;
    }

    public void setPayroll_branch(Integer payroll_branch) {
        this.payroll_branch = payroll_branch;
    }

    public Integer getCurrent_branch() {
        return current_branch;
    }

    public void setCurrent_branch(Integer current_branch) {
        this.current_branch = current_branch;
    }

    
}
