/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.payroll;

import java.util.List;
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
public class PayrollService {
    
    @Autowired
    private PayrollRepository payrollRepository;

    public List<Object[]> findPayroll(int branch, int year, int month) {
        System.out.println(branch);
        System.out.println(year);
        System.out.println(month);
       return payrollRepository.findPayroll(branch,year,month);
    }
    
    
    
}
