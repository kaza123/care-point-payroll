/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.supplier;

import com.mac.care_point.master.supplier.model.MSupplier;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author L T430
 */
public interface SupplierRepository extends JpaRepository<MSupplier, Integer>{
    
}
