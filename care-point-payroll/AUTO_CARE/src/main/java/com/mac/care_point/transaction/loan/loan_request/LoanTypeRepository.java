/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.loan.loan_request;

import com.mac.care_point.transaction.loan.loan_request.model.MLoanType;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Nidura Prageeth
 */
public interface LoanTypeRepository extends JpaRepository<MLoanType, Integer>{
    
}
