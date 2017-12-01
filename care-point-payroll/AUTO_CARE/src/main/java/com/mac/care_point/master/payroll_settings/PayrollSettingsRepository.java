/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.payroll_settings;

import com.mac.care_point.master.payroll_settings.model.MPayrollSettings;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Nidura Prageeth
 */
public interface PayrollSettingsRepository extends JpaRepository<MPayrollSettings, Integer>{

    public MPayrollSettings findByNameAndEmpType(String start_time, String worker);
    
}
