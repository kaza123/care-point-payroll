/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.leave;

import com.mac.care_point.master.leave.model.MLeaveCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Nidura Prageeth
 */
public interface LeaveRepository extends JpaRepository<MLeaveCategory, Integer>{
    
}
