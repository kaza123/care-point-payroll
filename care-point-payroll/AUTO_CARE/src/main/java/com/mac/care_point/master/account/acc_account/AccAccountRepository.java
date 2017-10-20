/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.account.acc_account;

import com.mac.care_point.master.account.model.MAccAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author 'Kasun Chamara'
 */
public interface AccAccountRepository extends JpaRepository<MAccAccount, Integer>{
    
    @Query(value = "select ifnull(sum(t_acc_ledger.debit) - sum(t_acc_ledger.credit),0.00) as credit\n"
            + "from t_acc_ledger where t_acc_ledger.branch=:branch and t_acc_ledger.acc_account=:accAccount", nativeQuery = true)
    public double getAccountValue(@Param("branch") Integer branch, @Param("accAccount") Integer accAccount);
}
