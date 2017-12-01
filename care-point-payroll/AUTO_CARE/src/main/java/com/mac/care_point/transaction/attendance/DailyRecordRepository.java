/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.attendance;

import com.mac.care_point.transaction.attendance.model.TDailyRecord;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Nidura Prageeth
 */
public interface DailyRecordRepository extends JpaRepository<TDailyRecord, Integer>{

    public TDailyRecord findByEmployeeAndDate(int i, String date);

    public TDailyRecord findByEmployee(int i);
    
}
