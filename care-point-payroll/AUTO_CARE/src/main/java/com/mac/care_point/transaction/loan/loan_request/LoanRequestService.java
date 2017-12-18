/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.loan.loan_request;

import com.mac.care_point.transaction.loan.loan_request.model.MLoanType;
import com.mac.care_point.transaction.loan.loan_request.model.TLoanRequest;
import com.mac.care_point.zutil.SecurityUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nidura Prageeth
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class LoanRequestService {

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private LoanTypeRepository loanTypeRepository;

    public int saveLoanRequest(List<TLoanRequest> loanRequest) {
        for (TLoanRequest tLoanRequest : loanRequest) {
            TLoanRequest loan = loanRequestRepository.findTop1ByBranchOrderByLoanNoDesc(SecurityUtil.getCurrentUser().getBranch());
            tLoanRequest.setLoanNo(loan.getLoanNo() + 1);
            tLoanRequest.setBranch(SecurityUtil.getCurrentUser().getBranch());
            tLoanRequest.setRequestDate(new Date());
            tLoanRequest.setApprove(Boolean.FALSE);
            tLoanRequest.setIssue(Boolean.FALSE);
            System.out.println(tLoanRequest.toString());
            loanRequestRepository.save(tLoanRequest);
        }
        return 1;
    }

    public List<MLoanType> findAllLoanType() {
        return loanTypeRepository.findAll();
    }
}
