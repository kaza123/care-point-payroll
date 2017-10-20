/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.calander;

import com.mac.care_point.master.calander.model.CalanderDefault;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author 'Kasun Chamara'
 */
public interface CalanderDefaultRepository extends JpaRepository<CalanderDefault, Integer>{

    public CalanderDefault findByYear(int i);
    
}
