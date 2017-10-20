/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.calander.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author 'Kasun Chamara'
 */
@Entity
@Table(name = "m_calander_default")
public class CalanderDefault implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_no")
    private Integer indexNo;
    
    
    @Column(name = "year")
    private Integer year;
    
    @Column(name = "set_default")
    private boolean setDefault;

    public CalanderDefault() {
    }

    public CalanderDefault(Integer indexNo, Integer year, boolean setDefault) {
        this.indexNo = indexNo;
        this.year = year;
        this.setDefault = setDefault;
    }

    public boolean isSetDefault() {
        return setDefault;
    }

    public void setSetDefault(boolean setDefault) {
        this.setDefault = setDefault;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    
}
