/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.leave_request;

import com.mac.care_point.transaction.leave_request.model.LeaveRequestMix;
import com.mac.care_point.transaction.leave_request.model.TLeaveDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public TLeaveDetails saveLeave(@RequestBody LeaveRequestMix leaveRequestMix) {
        return leaveRequestService.saveLeave(leaveRequestMix);
    }

}
