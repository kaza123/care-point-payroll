/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.leave.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Nidura Prageeth
 */
@Entity
@Table(name = "m_leave_category")
public class MLeaveCategory implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_no")
    private Integer indexNo;

    @Column(name = "year")
    private Date year;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "annual")
    private Integer annual;
    
    @Column(name = "casual")
    private Integer casual;
    
    @Column(name = "half_day")
    private Integer halfDay;
    
    @Column(name = "short_leave")
    private Integer shortLeave;

    public MLeaveCategory() {
    }

    public MLeaveCategory(Integer indexNo, Date year, String type, Integer annual, Integer casual, Integer halfDay, Integer shortLeave) {
        this.indexNo = indexNo;
        this.year = year;
        this.type = type;
        this.annual = annual;
        this.casual = casual;
        this.halfDay = halfDay;
        this.shortLeave = shortLeave;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAnnual() {
        return annual;
    }

    public void setAnnual(Integer annual) {
        this.annual = annual;
    }

    public Integer getCasual() {
        return casual;
    }

    public void setCasual(Integer casual) {
        this.casual = casual;
    }

    public Integer getHalfDay() {
        return halfDay;
    }

    public void setHalfDay(Integer halfDay) {
        this.halfDay = halfDay;
    }

    public Integer getShortLeave() {
        return shortLeave;
    }

    public void setShortLeave(Integer shortLeave) {
        this.shortLeave = shortLeave;
    }

    
    
    
    
}
