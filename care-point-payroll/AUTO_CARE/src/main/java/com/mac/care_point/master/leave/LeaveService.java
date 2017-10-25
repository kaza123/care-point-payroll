/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.leave;

import com.mac.care_point.master.leave.model.MLeaveCategory;
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
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class LeaveService {
    
    @Autowired
    private LeaveRepository leaveRepository;
    
    public List<MLeaveCategory> findAllLeaveCategory(){
        return leaveRepository.findAll();
    }

    public MLeaveCategory saveLeaveCategory(MLeaveCategory leaveCategory) {
        return leaveRepository.save(leaveCategory);
    }
    
}
