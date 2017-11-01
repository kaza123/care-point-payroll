/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.leave_request;

import com.mac.care_point.master.employee.model.Employee;
import com.mac.care_point.transaction.leave_request.model.LeaveRequestMix;
import com.mac.care_point.transaction.leave_request.model.TLeave;
import com.mac.care_point.transaction.leave_request.model.TLeaveDetails;
import java.text.ParseException;
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
@RequestMapping(path = "api/leave/leave-request")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @RequestMapping(method = RequestMethod.GET)
    public List<TLeaveDetails> findAll() {
        return leaveRequestService.findAll();
    }

    @RequestMapping(path = "/save-leave", method = RequestMethod.POST)
    public int saveLeave(@RequestBody LeaveRequestMix leaveRequestMix) {
        int i = 0;
        try {
            i = leaveRequestService.saveLeave(leaveRequestMix);
        } catch (ParseException ex) {
            Logger.getLogger(LeaveRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
    @RequestMapping(path = "/employee/{epfNo}",method = RequestMethod.GET)
    public Employee findEmployeeByEpfNo(@PathVariable String epfNo){
        return leaveRequestService.findEmployeeByEpfNo(epfNo);
    }

}
