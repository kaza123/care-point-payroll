/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.attendance;

import com.mac.care_point.transaction.attendance.model.TFingerPrint;
import com.mac.care_point.transaction.attendance.model.attendanceMixModel;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author 'Kasun Chamara'
 */
public interface AttendanceRepocitory extends JpaRepository<TFingerPrint, Integer> {

    @Query(value = "select \n"
            + "	m_employee.index_no as em_no,\n"
            + "	m_employee.name as employee_name,\n"
            + "	m_employee.epf_no,\n"
            + "	m_employee.`type` as em_type,\n"
            + "	ras_attrecord.Clock as in_time,\n"
            + "	(select m_branch.name from m_branch where m_branch.index_no = m_employee.branch )as payroll_branch,\n"
            + "	(select m_branch.name from m_branch where m_branch.index_no = t_finger_print_mashine.branch )as current_branch,\n"
            + "ras_attrecord.type as fp_type\n"
            + "from \n"
            + "	ras_attrecord\n"
            + "	left JOIN m_employee on m_employee.index_no=ras_attrecord.DIN\n"
            + "	left JOIN t_finger_print_mashine on t_finger_print_mashine.index_no=ras_attrecord.DN\n"
            + "where t_finger_print_mashine.branch=:branch and DATE(ras_attrecord.Clock) = :date\n"
            + "group by m_employee.index_no\n"
            + "order by ras_attrecord.Clock", nativeQuery = true)
    public List<Object> getAttendanceByDateAndBranch(@Param("branch") Integer branch, @Param("date") String date);

}
