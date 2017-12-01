/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.attendance_confirm;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nidura Prageeth
 */
@CrossOrigin
@RestController
@RequestMapping(path = "api/care-point/transaction/attendance-confirm")
public class AttendanceConfirmController {

    @Autowired
    private AttendanceConfirmService attendanceConfirmService;

    @RequestMapping(path = "/all-attendance/{date}", method = RequestMethod.GET)
    public List<Object[]> allEmployeeeAttendanceTemp(@PathVariable Date date) {
        List<Object[]> returnList = new ArrayList<>();
        try {
            returnList = attendanceConfirmService.allEmployeeAttendance(date);
        } catch (ParseException ex) {
            Logger.getLogger(AttendanceConfirmController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnList;

    }

    @RequestMapping(path = "/save/{date}", method = RequestMethod.POST)
    public int save(@PathVariable Date date,@RequestBody List<Object[]> list) {
        return attendanceConfirmService.save(date,list);
    }

}
