/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.leave.model;

/**
 *
 * @author Nidura Prageeth
 */
public class SetupMix {
    
    private int empIndex;
    private String year;
    private int annual;
    private int casual;
    private int halfDay;
    private int shortLeave;

    public SetupMix() {
    }

    public SetupMix(int empIndex, String year, int annual, int casual, int halfDay, int shortLeave) {
        this.empIndex = empIndex;
        this.year = year;
        this.annual = annual;
        this.casual = casual;
        this.halfDay = halfDay;
        this.shortLeave = shortLeave;
    }

    public int getShortLeave() {
        return shortLeave;
    }

    public void setShortLeave(int shortLeave) {
        this.shortLeave = shortLeave;
    }

    public int getEmpIndex() {
        return empIndex;
    }

    public void setEmpIndex(int empIndex) {
        this.empIndex = empIndex;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getAnnual() {
        return annual;
    }

    public void setAnnual(int annual) {
        this.annual = annual;
    }

    public int getCasual() {
        return casual;
    }

    public void setCasual(int casual) {
        this.casual = casual;
    }

    public int getHalfDay() {
        return halfDay;
    }

    public void setHalfDay(int halfDay) {
        this.halfDay = halfDay;
    }

    @Override
    public String toString() {
        return "SetupMix{" + "empIndex=" + empIndex + ", year=" + year + ", annual=" + annual + ", casual=" + casual + ", halfDay=" + halfDay + ", shortLeave=" + shortLeave + '}';
    }
    
    
    
    
}
