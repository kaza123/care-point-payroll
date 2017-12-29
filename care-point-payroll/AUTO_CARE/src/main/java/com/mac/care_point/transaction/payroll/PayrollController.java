/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.payroll;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nidura Prageeth
 */
@CrossOrigin
@RestController
@RequestMapping(path = "api/payroll")
public class PayrollController {
    
    @Autowired
    private PayrollService payrollService;
    
    
    @RequestMapping(value = "/find-payroll/{branch}/{year}/{month}",method = RequestMethod.GET)
    public List<Object[]> findPayroll(@PathVariable int branch,@PathVariable int year,@PathVariable int month ){
        return payrollService.findPayroll(branch,year,month);
    }
}
