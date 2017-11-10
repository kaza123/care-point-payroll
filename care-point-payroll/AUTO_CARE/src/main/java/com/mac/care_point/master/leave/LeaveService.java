/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.leave;

import com.mac.care_point.master.employee.EmployeeRepository;
import com.mac.care_point.master.employee.model.Employee;
import com.mac.care_point.master.leave.model.MLeaveCategory;
import com.mac.care_point.master.leave.model.MLeaveSetup;
import com.mac.care_point.master.leave.model.SetupMix;
import com.mac.care_point.zutil.SecurityUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private LeaveSetupRepository leaveSetupRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<MLeaveCategory> findAllLeaveCategory() {
        return leaveRepository.findAllOrderByYearAndBranch(SecurityUtil.getCurrentUser().getBranch());
    }

    public List<String> findAllEmployeeTypes() {
        return employeeRepository.allEmpTypesByBranch(SecurityUtil.getCurrentUser().getBranch());
    }

    @Transactional
    public MLeaveCategory saveLeaveCategory(MLeaveCategory leaveCategory) {
        MLeaveCategory category = leaveRepository.findByYearAndTypeAndBranch(leaveCategory.getYear(), leaveCategory.getType(), SecurityUtil.getCurrentUser().getBranch());
        if (category == null) {
            List<Employee> employees = employeeRepository.findByTypeAndBranch(leaveCategory.getType(), SecurityUtil.getCurrentUser().getBranch());
            for (Employee employee : employees) {
                MLeaveSetup leaveSetup = new MLeaveSetup();
                leaveSetup.setEmployee(employee);
                leaveSetup.setBranch(employee.getBranch());
                leaveSetup.setYear(leaveCategory.getYear());
                leaveSetup.setAnnual(leaveCategory.getAnnual());
                leaveSetup.setCasual(leaveCategory.getCasual());
                leaveSetup.setMedical(leaveCategory.getMedical());
                leaveSetupRepository.save(leaveSetup);
            }
            leaveCategory.setBranch(SecurityUtil.getCurrentUser().getBranch());
            return leaveRepository.save(leaveCategory);
        } else {
            return null;
        }
    }

    //leave setup funtions
    public List<Object> findAllLeaveSetupByYear(String year) {
        return leaveSetupRepository.findAllLeaveSetupByYear(year, SecurityUtil.getCurrentUser().getBranch());
    }

    public List<Object> findAllLeaveSetupByYearAndEpfNo(String year, String epfNo) {
        return leaveSetupRepository.findAllLeaveSetupByYearAndEpfNo(year, epfNo, SecurityUtil.getCurrentUser().getBranch());
    }

    public int updateEmployeeLeaveSetup(SetupMix setupMix) {
        Employee employee = employeeRepository.findOne(setupMix.getEmpIndex());
        MLeaveSetup leaveSetup = leaveSetupRepository.findByYearAndEmployee(setupMix.getYear(), employee);

        leaveSetup.setAnnual(setupMix.getAnnual());
        leaveSetup.setCasual(setupMix.getCasual());
        leaveSetup.setMedical(setupMix.getMedical());

        leaveSetupRepository.save(leaveSetup);
        return 1;
    }

    // Leave Approve funtions
    
    public MLeaveSetup findEmployeeLeave(int empIndex, String year) {
        Employee employee = employeeRepository.findOne(empIndex);
        //parse date to simple date format
        
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String year1 = null;
        try {
            year1 = yearFormat.format(yearFormat.parse(year));
        } catch (ParseException ex) {
            Logger.getLogger(LeaveService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return leaveSetupRepository.findByYearAndEmployee(year1, employee);
    }

}
