/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.leave;

import com.mac.care_point.master.leave.model.MLeaveCategory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    
    
    @RequestMapping(path = "/leave-category",method = RequestMethod.GET)
    public List<MLeaveCategory> findAllLeaveCategory(){
        return leaveService.findAllLeaveCategory();
    }
    
    @RequestMapping(path = "/save-leave-category",method = RequestMethod.POST)
    public MLeaveCategory saveLeaveCategory(@RequestBody MLeaveCategory leaveCategory){
        return leaveService.saveLeaveCategory(leaveCategory);
    }
    
}
