/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.loan.loan_request;

import com.mac.care_point.transaction.loan.loan_request.model.TLoanRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Nidura Prageeth
 */
public interface LoanRequestRepository extends JpaRepository<TLoanRequest, Integer> {

    public TLoanRequest findTop1ByBranchOrderByLoanNoDesc(Integer branch);

    @Query(value = "select t_loan_request.branch,m_branch.name , count(t_loan_request.index_no) as request_count,\n"
            + "SUM(t_loan_request.amount) as amount\n"
            + "from \n"
            + "t_loan_request \n"
            + "left join\n"
            + "m_branch on t_loan_request.branch = m_branch.index_no\n"
            + "where \n"
            + "t_loan_request.approve = 0 \n"
            + "group by t_loan_request.branch", nativeQuery = true)
    public List<Object[]> findAllPendingRequest();

    public List<TLoanRequest> findByBranchAndApprove(int branch, boolean b);

}
