/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.advance_request;

import com.mac.care_point.master.employee.model.Employee;
import com.mac.care_point.transaction.advance_request.model.TAdvanceRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Thilina Kalum
 */

@RestController
@CrossOrigin
@RequestMapping(path = "api/care-point/transaction/advance-request")
public class AdvanceRequestController {
    
    @Autowired
    private  AdvanceRequestService advanceRequestService;
    
    @RequestMapping(value = "/find-all-employee" , method = RequestMethod.GET)
    public List<Employee> findAllEmployee(){
        return advanceRequestService.findAllEmployee();
    }
    @RequestMapping(value = "/save-advance-request" , method = RequestMethod.POST)
    public List<TAdvanceRequest> saveAdvanceRequest(@RequestBody List<TAdvanceRequest> tAdvanceRequest){
        return advanceRequestService.saveAdvanceRequest(tAdvanceRequest);
    }
    @RequestMapping(value = "/approve-advance" , method = RequestMethod.POST)
    public List<TAdvanceRequest> approveAdvanceRequest(@RequestBody List<TAdvanceRequest> tAdvanceRequest){
        return advanceRequestService.approveAdvanceRequest(tAdvanceRequest);
    }
    @RequestMapping(value = "/find-all-advance-request-unapprove-by-branch/{branch}" , method = RequestMethod.GET)
    public List<TAdvanceRequest> findAllAdvanceRequest(@PathVariable ("branch") Integer branch){
        return advanceRequestService.findAllAdvanceRequest(branch);
    }
    @RequestMapping(value = "/delete-advance-request/{indexNo}" , method = RequestMethod.DELETE)
    public void deleteAdvanceRequest(@PathVariable ("indexNo") Integer indexNo){
        advanceRequestService.deleteAdvanceRequest(indexNo);
    }
    @RequestMapping(value = "/find-summary" , method = RequestMethod.GET)
    public List<Object[]> findAllRequestByBranch(){
        return advanceRequestService.findAllRequestByBranch();
    }
}
