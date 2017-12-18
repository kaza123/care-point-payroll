/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.advance_request;

import com.mac.care_point.transaction.advance_request.model.TAdvanceRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Thilina Kalum
 */
public interface AdvanceRequestRepository extends JpaRepository<TAdvanceRequest, Integer> {

    public TAdvanceRequest findTop1ByBranchOrderByAdvanceNoDesc(Integer branch);

    @Query(value = "SELECT MAX(advance_no) FROM t_advance_request WHERE branch = :branch", nativeQuery = true)
    public Integer getMaximumAdvanceNoByBranch(@Param("branch") Integer branch);

    public List<TAdvanceRequest> findByBranchAndApprove(Integer branch, boolean b);

    public List<TAdvanceRequest> findByApprove(boolean b);

    @Query(value = "select m_branch.index_no as indexNo, \n"
            + "m_branch.name as branchname, \n"
            + "COUNT(t_advance_request.index_no) as noOfCount, \n"
            + "SUM(t_advance_request.amount) as amountSum  \n"
            + "from  t_advance_request \n"
            + "left join m_branch  \n"
            + "on t_advance_request.branch = m_branch.index_no \n"
            + "where t_advance_request.approve = 0 \n"
            + "group by t_advance_request.branch ", nativeQuery = true)
    public List<Object[]> findAllRequestByBranch();

}
