/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.leave;

import com.mac.care_point.master.leave.model.MLeaveCategory;
import com.mac.care_point.master.leave.model.MLeaveSetup;
import com.mac.care_point.master.leave.model.SetupMix;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nidura Prageeth
 */
@CrossOrigin
@RestController
@RequestMapping(path = "api/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    //leave category funtions
    @RequestMapping(path = "/leave-category", method = RequestMethod.GET)
    public List<MLeaveCategory> findAllLeaveCategory() {
        return leaveService.findAllLeaveCategory();
    }

    @RequestMapping(path = "/employee-types", method = RequestMethod.GET)
    public List<String> findAllEmpTypes() {
        return leaveService.findAllEmployeeTypes();
    }

    @RequestMapping(path = "/save-leave-category", method = RequestMethod.POST)
    public MLeaveCategory saveLeaveCategory(@RequestBody MLeaveCategory leaveCategory) {
        return leaveService.saveLeaveCategory(leaveCategory);
    }

    //leave setup funtions
    
    @RequestMapping(path = "/leave-setup-year/{year}", method = RequestMethod.GET)
    public List<Object> findAllLeaveSetupByYear(@PathVariable String year) {
        return leaveService.findAllLeaveSetupByYear(year);
    }
    
    @RequestMapping(path = "/leave-setup-year-epf/{year}/{epfNo}", method = RequestMethod.GET)
    public List<Object> findAllLeaveSetupByYearAndEpfNo(@PathVariable String year,@PathVariable String epfNo) {
        return leaveService.findAllLeaveSetupByYearAndEpfNo(year,epfNo);
    }
    
    @RequestMapping(path = "/leave-setup-update", method = RequestMethod.POST)
    public int updateEmployeeLeaveSetup(@RequestBody SetupMix setupMix) {
        return leaveService.updateEmployeeLeaveSetup(setupMix);
    }
    
    //leave approve funtions
   
    @RequestMapping(path = "/employee-leave/{empIndex}/{year}", method = RequestMethod.GET)
    public MLeaveSetup findEmployeeLeave(@PathVariable int empIndex, @PathVariable String year) {
        return leaveService.findEmployeeLeave(empIndex, year);    
    }

}
