/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.attendance_confirm;

import com.mac.care_point.transaction.attendance_confirm.model.TRecordDetails;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Nidura Prageeth
 */
public interface AttendanceConfirmRepository extends JpaRepository<TRecordDetails, Integer> {

    @Query(value = "select  m_employee.index_no as emp_id,m_employee.epf_no,m_employee.name , t_record_details_temp.*\n"
            + "from\n"
            + "m_employee\n"
            + "left join\n"
            + "t_record_details_temp on t_record_details_temp.employee = m_employee.index_no\n"
            + "group by m_employee.index_no", nativeQuery = true)
    public List<Object[]> allEmployeeeAttendanceTemp();

    public List<TRecordDetails> findByDate(Date date);

    @Query(value = "select  m_employee.index_no as emp_id,m_employee.epf_no,m_employee.name , t_record_details.*\n"
            + "from\n"
            + "m_employee\n"
            + "left join\n"
            + "t_record_details on t_record_details.employee = m_employee.index_no\n"
            + "where t_record_details.date=:date\n"
            + "group by m_employee.index_no", nativeQuery = true)
    public List<Object[]> allEmployeeeAttendance(@Param("date") Date date);

    @Query(value = "SELECT TIMEDIFF(:outTime, :endTime);",nativeQuery = true)
    public String getTimeDiff(@Param("outTime")String outTime,@Param("endTime") String endTime);

}
