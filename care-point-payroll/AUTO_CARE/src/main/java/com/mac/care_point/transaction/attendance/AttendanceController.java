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
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public List<Object[]> getAttendanceByDateAndBranch(@PathVariable("branch") Integer branch, @PathVariable("date") String date) {
        return attendanceService.getAttendanceByDateAndBranch(branch, date);
    }

    @RequestMapping(value = "/in-confirm/{branch}/{date}", method = RequestMethod.GET)
    public int inConfirm(@PathVariable("branch") Integer branch, @PathVariable("date") String date) {
        return attendanceService.inConfirm(branch, date);
    }

    @RequestMapping(value = "/out-confirm/{branch}/{date}", method = RequestMethod.GET)
    public int outConfirm(@PathVariable("branch") Integer branch, @PathVariable("date") String date) {
        int i = 0;
        try {
            i = attendanceService.outConfirm(branch, date);
        } catch (ParseException ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @RequestMapping(value = "/get-attendance-by-date-status/{branch}/{date}/{status}", method = RequestMethod.GET)
    public List<Object[]> getAttendanceByDateAndStatusAndBranch(@PathVariable("branch") Integer branch, @PathVariable("date") String date, @PathVariable("status") int status) {
        return attendanceService.getAttendanceByDateAndStatusAndBranch(branch, date, status);
    }

    @RequestMapping(value = "/get-calender-data-by-date/{date}", method = RequestMethod.GET)
    public Calander getCalenderDataByDate(@PathVariable("date") String date) {
        return attendanceService.getCalenderDataByDate(date);
    }
}
