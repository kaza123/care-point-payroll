/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.leave;

import com.mac.care_point.master.employee.model.Employee;
import com.mac.care_point.master.leave.model.MLeaveSetup;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Nidura Prageeth
 */
public interface LeaveSetupRepository extends JpaRepository<MLeaveSetup, Integer> {

    @Query(value = "SELECT  m_leave_setup.year,m_employee.epf_no, m_employee.name,\n"
            + "m_leave_setup.annual,m_leave_setup.casual,m_leave_setup.half_day,\n"
            + "m_leave_setup.short_leave,m_employee.index_no as empIndex\n"
            + "FROM\n"
            + "m_leave_setup\n"
            + "INNER JOIN\n"
            + "m_employee ON m_leave_setup.employee = m_employee.index_no\n"
            + "AND  m_leave_setup.year=:year AND m_leave_setup.branch=:branch", nativeQuery = true)
    public List<Object> findAllLeaveSetupByYear(@Param("year") String year, @Param("branch") int branch);

    @Query(value = "SELECT  m_leave_setup.year,m_employee.epf_no, m_employee.name,\n"
            + "m_leave_setup.annual,m_leave_setup.casual,m_leave_setup.half_day,\n"
            + "m_leave_setup.short_leave,m_employee.index_no as empIndex\n"
            + "FROM\n"
            + "m_leave_setup\n"
            + "INNER JOIN\n"
            + "m_employee ON m_leave_setup.employee = m_employee.index_no\n"
            + "AND  m_leave_setup.year=:year \n"
            + "AND m_leave_setup.branch=:branch\n"
            + "AND m_employee.epf_no=:epfNo", nativeQuery = true)
    public List<Object> findAllLeaveSetupByYearAndEpfNo(@Param("year") String year, @Param("epfNo") String epfNo,@Param("branch") int branch);

    public MLeaveSetup findByYearAndEmployee(String year, Employee employee);

}
