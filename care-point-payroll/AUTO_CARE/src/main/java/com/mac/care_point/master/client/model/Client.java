/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.client.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Kalum
 */
@Entity
@Table(name = "m_client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_no")
    private Integer indexNo;

    @Basic(optional = false)
    @Size(min = 1, max = 50)
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
    @Column(name = "nic")
    private String nic;
    
    @Column(name = "resident")
    private String resident;

    @Column(name = "customer_type")
    private Integer customerType;
    
    @Column(name = "is_new")
    private boolean isNew;
    
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public Client() {
    }

    public Client(Integer indexNo, String name, String addressLine1, String addressLine2, String addressLine3, String mobile, int branch, String nic, String resident, Integer customerType, boolean isNew, Date date) {
        this.indexNo = indexNo;
        this.name = name;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.mobile = mobile;
        this.branch = branch;
        this.nic = nic;
        this.resident = resident;
        this.customerType = customerType;
        this.isNew = isNew;
        this.date = date;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getResident() {
        return resident;
    }

    public void setResident(String resident) {
        this.resident = resident;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public boolean isIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}