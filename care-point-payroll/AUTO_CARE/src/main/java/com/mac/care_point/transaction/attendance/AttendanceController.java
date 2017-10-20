/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.attendance;

import com.mac.care_point.master.calander.model.Calander;
import com.mac.care_point.transaction.attendance.model.attendanceMixModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 'Kasun Chamara'
 */
@RestController
@CrossOrigin
@RequestMapping("/api/care-point/transaction/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping(value = "/get-attendance-by-date/{branch}/{date}", method = RequestMethod.GET)
    public List<Object> getAttendanceByDateAndBranch(@PathVariable("branch") Integer branch, @PathVariable("date") String date) {
        return attendanceService.getAttendanceByDateAndBranch(branch, date);
    }
    @RequestMapping(value = "/get-calender-data-by-date/{date}", method = RequestMethod.GET)
    public Calander getCalenderDataByDate(@PathVariable("date") String date) {
        return attendanceService.getCalenderDataByDate(date);
    }
}
