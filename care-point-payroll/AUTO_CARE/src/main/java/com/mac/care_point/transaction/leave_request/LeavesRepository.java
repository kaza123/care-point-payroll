/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.leave_request;

import com.mac.care_point.transaction.leave_request.model.TLeave;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Nidura Prageeth
 */
public interface LeavesRepository extends JpaRepository<TLeave, Integer>{
    
}
