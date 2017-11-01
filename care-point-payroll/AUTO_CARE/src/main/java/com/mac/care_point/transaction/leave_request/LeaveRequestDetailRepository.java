/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.leave_request;

import com.mac.care_point.transaction.leave_request.model.TLeaveDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Nidura Prageeth
 */
public interface LeaveRequestDetailRepository extends JpaRepository<TLeaveDetails, Integer>{

    @Query(value = "SELECT DATEDIFF(:toDate,:fromDate)",nativeQuery = true)
    public int getDateCount(@Param("toDate")String toDate,@Param("fromDate") String fromDate);
    
}
