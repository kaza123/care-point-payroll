/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.loan.loan_approve;

import com.mac.care_point.transaction.loan.loan_request.LoanRequestRepository;
import com.mac.care_point.transaction.loan.loan_request.model.TLoanRequest;
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
public class LoanApproveService {

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    public List<Object[]> findAllPendingRequest() {
        return loanRequestRepository.findAllPendingRequest();
    }

    public List<TLoanRequest> findLoanRequestDetailsByBranch(int branch) {
        return loanRequestRepository.findByBranchAndApprove(branch, false);
    }

    public int approveLoanRequest(List<TLoanRequest> list) {
        for (TLoanRequest tLoanRequest : list) {
            TLoanRequest loan = loanRequestRepository.findOne(tLoanRequest.getIndexNo());

            loan.setApprove(Boolean.TRUE);
            loan.setApproveDate(new Date());
            loanRequestRepository.save(loan);
        }
        return 1;
    }

}
