/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.loan.loan_approve;

import com.mac.care_point.transaction.loan.loan_request.model.TLoanRequest;
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
 * @author Nidura Prageeth
 */
@CrossOrigin
@RestController
@RequestMapping(path = "api/loan/loan-approve")
public class LoanApproveController {
    
    @Autowired
    private LoanApproveService loanApproveService;
    
    @RequestMapping(path = "/find-all-pending",method = RequestMethod.GET)
    public List<Object[]> findAllPendingRequest(){
        return loanApproveService.findAllPendingRequest();
    }
   
    @RequestMapping(path = "/find-by-branch/{branch}",method = RequestMethod.GET)
    public List<TLoanRequest> findLoanRequestDetailsByBranch(@PathVariable int branch){
        return loanApproveService.findLoanRequestDetailsByBranch(branch);
    }
    
    @RequestMapping(path = "/approve-loan",method = RequestMethod.POST)
    public int approveLoanRequest(@RequestBody List<TLoanRequest>list){
        return loanApproveService.approveLoanRequest(list);
    }
    
}
