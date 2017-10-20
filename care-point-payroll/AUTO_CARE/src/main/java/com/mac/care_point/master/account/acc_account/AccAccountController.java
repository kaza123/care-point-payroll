/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.account.acc_account;

import com.mac.care_point.master.account.model.MAccAccount;
import com.mac.care_point.zutil.SecurityUtil;
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
 * @author 'Kasun Chamara'
 */
@CrossOrigin
@RestController
@RequestMapping("/api/care-point/account/master/acc-account")
public class AccAccountController {

    @Autowired
    private AccAccountService accAccountService;

    @RequestMapping(method = RequestMethod.GET)
    public List<MAccAccount> findAll() {
        return accAccountService.findAll();
    }

    @RequestMapping(value = "/save-new-account", method = RequestMethod.POST)
    public MAccAccount saveNewAccount(@RequestBody MAccAccount accAccount) {
        Integer user = SecurityUtil.getCurrentUser().getIndexNo();
        accAccount.setUser(user);
        return accAccountService.saveNewAccount(accAccount);
    }

    @RequestMapping(value = "/delete-account/{index}", method = RequestMethod.DELETE)
    public Integer saveNewAccount(@PathVariable Integer index) {
        return accAccountService.deleteAccount(index);
    }

    @RequestMapping(value = "/get-account-value/{branch}/{accAccount}", method = RequestMethod.GET)
    public double getAccountValue(@PathVariable("branch") Integer branch, @PathVariable("accAccount") Integer accAccount) {
        return accAccountService.getAccountValue(branch, accAccount);
    }
}
