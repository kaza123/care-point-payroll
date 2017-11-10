/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.employee;

import com.mac.care_point.master.employee.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author kavish manjitha
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public List<Employee> findByName(String name);

    public List<Employee> findByType(String employeeType);

    public List<Employee> findByTypeAndBranch(String type, Integer branch);

    @Query(value = "SELECT m_employee.`type` FROM m_employee WHERE m_employee.branch=:branch GROUP BY m_employee.`type`", nativeQuery = true)
    public List<String> allEmpTypesByBranch(@Param("branch")int branch);

    public Employee findByEpfNoAndBranch(int epfNo, Integer branch);

}
