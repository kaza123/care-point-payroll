/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.attendance;

import com.mac.care_point.master.calander.CalanderRepository;
import com.mac.care_point.master.calander.model.Calander;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 'Kasun Chamara'
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AttendanceService {

    @Autowired
    private AttendanceRepocitory attendanceRepocitory;
    
    @Autowired
    private CalanderRepository calanderRepository;
            
    public List<Object> getAttendanceByDateAndBranch(Integer branch, String date) {
       return attendanceRepocitory.getAttendanceByDateAndBranch(branch,date);
          
    }
    public Calander getCalenderDataByDate(String date) {
        List<Calander> calList = calanderRepository.findByDate(date);
        if (!calList.isEmpty()) {
            return calList.get(0);
        }
        return null;
    }
    
}
