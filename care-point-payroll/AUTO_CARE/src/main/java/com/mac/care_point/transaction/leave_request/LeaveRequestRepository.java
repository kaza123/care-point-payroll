/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.leave_request;

import com.mac.care_point.transaction.leave_request.model.TLeaveDetails;
import com.mac.care_point.transaction.leave_request.model.TLeaveRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Nidura Prageeth
 */
public interface LeaveRequestRepository extends JpaRepository<TLeaveRequest, Integer> {

    public List<TLeaveRequest> findByLeave(int indexNo);
    
    //public List<TLeaveRequest> findByLeave(int indexNo);

    @Query(value = "select m_employee.name,m_employee.epf_no,m_leave_setup.branch,m_leave_setup.year,m_leave_setup.annual,m_leave_setup.casual,m_leave_setup.medical,\n"
            + "(select count(t_leave_request.index_no) \n"
            + "from t_leave_request \n"
            + "left join t_leave on t_leave_request.`leave`=t_leave.index_no \n"
            + "left join t_leave_details on t_leave_details.leave_request=t_leave_request.index_no \n"
            + "where t_leave.employee=:employee and year(t_leave_request.from_date)=:year and month(t_leave_request.from_date)=:month and t_leave_request.leave_type='half_day' and t_leave_details.real_leave=1) as half_day,\n"
            + "m_leave_setup.annual_leave,m_leave_setup.medical_leave,m_leave_setup.casual_leave\n"
            + "from\n"
            + "m_leave_setup \n"
            + "left join\n"
            + "m_employee on m_employee.index_no= m_leave_setup.employee\n"
            + "where m_leave_setup.year=:year\n"
            + "and m_leave_setup.employee =:employee\n"
            + "and m_leave_setup.branch =:branch", nativeQuery = true)
    public Object findLeaveHistory(@Param("branch") int branch,@Param("employee") int employee,@Param("year") String year,@Param("month") String month);

}
