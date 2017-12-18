/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.loan.loan_request;

import com.mac.care_point.transaction.loan.loan_request.model.MLoanType;
import com.mac.care_point.transaction.loan.loan_request.model.TLoanRequest;
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
@RequestMapping(path = "api/loan/loan-request")
public class LoanRequestController {
    
    @Autowired
    private LoanRequestService loanRequestService;
    
    
    @RequestMapping(path = "/save-loan",method = RequestMethod.POST)
    public int saveLoanRequest(@RequestBody List<TLoanRequest> loanRequest){
      return loanRequestService.saveLoanRequest(loanRequest);
    }
    
    @RequestMapping(path = "/all-loan-type",method = RequestMethod.GET)
    public List<MLoanType> findAllLoanType(){
      return loanRequestService.findAllLoanType();
    }
    
}
