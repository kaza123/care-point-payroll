/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.payroll;

import com.mac.care_point.transaction.payroll.model.MEpfRate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Nidura Prageeth
 */
public interface PayrollRepository extends JpaRepository<MEpfRate, Integer> {

    @Query(value = "select emp.index_no, emp.epf_no,emp.name,emp.basic_salary,\n"
            + "	(select COUNT(t_daily_record.index_no) from t_daily_record \n"
            + "	left join m_employee on m_employee.index_no = t_daily_record.employee\n"
            + "	where MONTH(t_daily_record.date)=:month and YEAR(t_daily_record.date)=:year and emp.index_no=m_employee.index_no) as working_days,\n"
            + "	(select ifnull(SUM(m_allowance_deduction_details.value),0.00) from m_allowance_deduction_details\n"
            + "	left join m_allowance_deduction on m_allowance_deduction_details.allowance_deduction=m_allowance_deduction.index_no\n"
            + "	left join m_employee on m_allowance_deduction_details.employee=m_employee.index_no\n"
            + "	where m_allowance_deduction.`type`='deduction' and emp.index_no=m_employee.index_no) as dedution,\n"
            + "	(select ifnull(SUM(m_allowance_deduction_details.value),0.00) from m_allowance_deduction_details\n"
            + "	left join m_allowance_deduction on m_allowance_deduction_details.allowance_deduction=m_allowance_deduction.index_no\n"
            + "	left join m_employee on m_allowance_deduction_details.employee=m_employee.index_no\n"
            + "	where m_allowance_deduction.`type`='allowance' and emp.index_no=m_employee.index_no) as allowance,\n"
            + "	(select sum(t_record_details.ot_hours) from t_record_details\n"
            + "	left join m_employee on m_employee.index_no = t_record_details.employee\n"
            + "	where MONTH(t_record_details.date)=:month and YEAR(t_record_details.date)=:year and m_employee.index_no= emp.index_no) as ot_hours,\n"
            + "	(select sum(t_record_details.extra_hours) from t_record_details\n"
            + "	left join m_employee on m_employee.index_no = t_record_details.employee\n"
            + "	where MONTH(t_record_details.date)=:month and YEAR(t_record_details.date)=:year and m_employee.index_no= emp.index_no) as extra_hours,\n"
            + "	(select ifnull(sum(t_advance_request.amount),0.00) from t_advance_request\n"
            + "	left join m_employee on m_employee.index_no=t_advance_request.employee\n"
            + "	where MONTH(t_advance_request.month)=:month and YEAR(t_advance_request.request_date)=:year and t_advance_request.approve=1 and m_employee.index_no= emp.index_no) as advance,\n"
            + "	(select m_epf_rate.rate from m_epf_rate where m_epf_rate.name='epf_company') as epf_rate_company,\n"
            + "	(select m_epf_rate.rate from m_epf_rate where m_epf_rate.name='epf_employee') as epf_rate_employee,\n"
            + "	(select m_epf_rate.rate from m_epf_rate where m_epf_rate.name='etf') as etf_rate,\n"
            + "	TRUNCATE(((select m_employee.basic_salary from m_employee where m_employee.index_no=emp.index_no)/100 * \n"
            + "	(select m_epf_rate.rate from m_epf_rate where m_epf_rate.name='epf_employee')),2) as epf_amount,\n"
            + "	((select sum(t_record_details.ot_hours) from t_record_details\n"
            + "	left join m_employee on m_employee.index_no = t_record_details.employee\n"
            + "	where MONTH(t_record_details.date)=:month and YEAR(t_record_details.date)=:year and m_employee.index_no = emp.index_no)  * \n"
            + "	(select m_ot_rate.rate from m_ot_rate where m_ot_rate.name='ot')) as ot_price,\n"
            + "	((select sum(t_record_details.extra_hours) from t_record_details\n"
            + "	left join m_employee on m_employee.index_no = t_record_details.employee\n"
            + "	where MONTH(t_record_details.date)=:month and YEAR(t_record_details.date)=:year and m_employee.index_no= emp.index_no)  * \n"
            + "	(select m_ot_rate.rate from m_ot_rate where m_ot_rate.name='extra')) as extra_price,\n"
            + "	(emp.basic_salary + ifnull((select SUM(m_allowance_deduction_details.value) from m_allowance_deduction_details\n"
            + "	left join m_allowance_deduction on m_allowance_deduction_details.allowance_deduction=m_allowance_deduction.index_no\n"
            + "	left join m_employee on m_allowance_deduction_details.employee=m_employee.index_no\n"
            + "	where m_allowance_deduction.`type`='allowance' and emp.index_no=m_employee.index_no),0) + \n"
            + "	(select sum(t_record_details.ot_hours) from t_record_details\n"
            + "	left join m_employee on m_employee.index_no = t_record_details.employee\n"
            + "	where MONTH(t_record_details.date)=:month and YEAR(t_record_details.date)=:year and m_employee.index_no = emp.index_no)  * \n"
            + "	(select m_ot_rate.rate from m_ot_rate where m_ot_rate.name='ot') +\n"
            + "	(select sum(t_record_details.extra_hours) from t_record_details\n"
            + "	left join m_employee on m_employee.index_no = t_record_details.employee\n"
            + "	where MONTH(t_record_details.date)=:month and YEAR(t_record_details.date)=:year and m_employee.index_no= emp.index_no)  * \n"
            + "	(select m_ot_rate.rate from m_ot_rate where m_ot_rate.name='extra')) as gross_pay,\n"
            + "	(ifnull((select SUM(m_allowance_deduction_details.value) from m_allowance_deduction_details\n"
            + "	left join m_allowance_deduction on m_allowance_deduction_details.allowance_deduction=m_allowance_deduction.index_no\n"
            + "	left join m_employee on m_allowance_deduction_details.employee=m_employee.index_no\n"
            + "	where m_allowance_deduction.`type`='deduction' and emp.index_no=m_employee.index_no),0) +\n"
            + "	ifnull((select sum(t_advance_request.amount) from t_advance_request\n"
            + "	left join m_employee on m_employee.index_no=t_advance_request.employee\n"
            + "	where MONTH(t_advance_request.month)=:month and YEAR(t_advance_request.request_date)=:year and t_advance_request.approve=1 and m_employee.index_no= emp.index_no),0) +\n"
            + "	TRUNCATE(((select m_employee.basic_salary from m_employee where m_employee.index_no=emp.index_no)/100 * \n"
            + "	(select m_epf_rate.rate from m_epf_rate where m_epf_rate.name='epf_employee')),2)) as total_deduction,\n"
            + "	((emp.basic_salary + ifnull((select SUM(m_allowance_deduction_details.value) from m_allowance_deduction_details\n"
            + "	left join m_allowance_deduction on m_allowance_deduction_details.allowance_deduction=m_allowance_deduction.index_no\n"
            + "	left join m_employee on m_allowance_deduction_details.employee=m_employee.index_no\n"
            + "	where m_allowance_deduction.`type`='allowance' and emp.index_no=m_employee.index_no),0) + \n"
            + "	(select sum(t_record_details.ot_hours) from t_record_details\n"
            + "	left join m_employee on m_employee.index_no = t_record_details.employee\n"
            + "	where MONTH(t_record_details.date)=:month and YEAR(t_record_details.date)=:year and m_employee.index_no = emp.index_no)  * \n"
            + "	(select m_ot_rate.rate from m_ot_rate where m_ot_rate.name='ot') +\n"
            + "	(select sum(t_record_details.extra_hours) from t_record_details\n"
            + "	left join m_employee on m_employee.index_no = t_record_details.employee\n"
            + "	where MONTH(t_record_details.date)=:month and YEAR(t_record_details.date)=:year and m_employee.index_no= emp.index_no)  * \n"
            + "	(select m_ot_rate.rate from m_ot_rate where m_ot_rate.name='extra')) - \n"
            + "	(ifnull((select SUM(m_allowance_deduction_details.value) from m_allowance_deduction_details\n"
            + "	left join m_allowance_deduction on m_allowance_deduction_details.allowance_deduction=m_allowance_deduction.index_no\n"
            + "	left join m_employee on m_allowance_deduction_details.employee=m_employee.index_no\n"
            + "	where m_allowance_deduction.`type`='deduction' and emp.index_no=m_employee.index_no),0) +\n"
            + "	ifnull((select sum(t_advance_request.amount) from t_advance_request\n"
            + "	left join m_employee on m_employee.index_no=t_advance_request.employee\n"
            + "	where MONTH(t_advance_request.month)=:month and YEAR(t_advance_request.request_date)=:year and t_advance_request.approve=1 and m_employee.index_no= emp.index_no),0) +\n"
            + "	TRUNCATE(((select m_employee.basic_salary from m_employee where m_employee.index_no=emp.index_no)/100 * \n"
            + "	(select m_epf_rate.rate from m_epf_rate where m_epf_rate.name='epf_employee')),2))) as net_salary\n"
            + "from \n"
            + "m_employee emp\n"
            + "LEFT JOIN\n"
            + "m_allowance_deduction_details on m_allowance_deduction_details.employee = emp.index_no\n"
            + "LEFT JOIN\n"
            + "m_allowance_deduction on m_allowance_deduction.index_no = m_allowance_deduction.index_no\n"
            + "LEFT JOIN\n"
            + "t_daily_record on t_daily_record.employee = emp.index_no\n"
            + "LEFT JOIN\n"
            + "t_record_details on t_record_details.employee=emp.index_no\n"
            + "LEFT JOIN\n"
            + "t_advance_request on t_advance_request.employee=emp.index_no\n"
            + "LEFT JOIN\n"
            + "m_branch on m_branch.index_no=emp.branch\n"
            + "where m_branch.index_no=:branch and emp.active = 1\n"
            + "group by emp.index_no", nativeQuery = true)
    public List<Object[]> findPayroll(@Param("branch")int branch,@Param("year") int year,@Param("month") int month);

}
