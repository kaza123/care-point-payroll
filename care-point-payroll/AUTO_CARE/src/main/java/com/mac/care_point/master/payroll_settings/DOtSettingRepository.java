/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.payroll_settings;

import com.mac.care_point.master.payroll_settings.model.MDOtSettings;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Nidura Prageeth
 */
public interface DOtSettingRepository extends JpaRepository<MDOtSettings, Integer>{

    public MDOtSettings findByDateAndDoubleOt(String sunday, boolean b);
    
}
