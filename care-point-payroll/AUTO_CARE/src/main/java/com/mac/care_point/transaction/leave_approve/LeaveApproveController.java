/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.leave_approve;

import com.mac.care_point.transaction.leave_request.model.TLeave;
import com.mac.care_point.transaction.leave_request.model.TLeaveRequest;
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
 * @author Nidura Prageeth
 */
@CrossOrigin
@RestController
@RequestMapping(path = "api/leave/leave-approve")
public class LeaveApproveController {

    @Autowired
    private LeaveApproveService leaveApproveService;

    @RequestMapping(value = "/pending-leave/{branch}", method = RequestMethod.GET)
    public List<TLeave> findAllByBranch(@PathVariable int branch) {
        return leaveApproveService.findAllByBranch(branch);
    }

    @RequestMapping(value = "/leave-detail/{indexNo}", method = RequestMethod.GET)
    public List<TLeaveRequest> findLeaveDetail(@PathVariable int indexNo) {
        return leaveApproveService.findLeaveDetail(indexNo);
    }

    @RequestMapping(value = "/approve/{indexNo}/{empIndex}", method = RequestMethod.PUT)
    public int findLeaveDetail(@PathVariable int indexNo, @PathVariable int empIndex) {
        return leaveApproveService.approveLeave(indexNo, empIndex);

    }

}
