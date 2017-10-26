/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.leave;

import com.mac.care_point.master.leave.model.MLeaveCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Nidura Prageeth
 */
public interface LeaveRepository extends JpaRepository<MLeaveCategory, Integer> {


    @Query(value = "select * from m_leave_category where m_leave_category.branch=:branch order by m_leave_category.year desc",nativeQuery = true)
    public List<MLeaveCategory> findAllOrderByYearAndBranch(@Param("branch")int branch);

    public MLeaveCategory findByYearAndTypeAndBranch(String year, String type, Integer branch);

}
