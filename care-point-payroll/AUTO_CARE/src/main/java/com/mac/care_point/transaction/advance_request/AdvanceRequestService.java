/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.advance_request;

import com.mac.care_point.master.employee.EmployeeRepository;
import com.mac.care_point.master.employee.model.Employee;
import com.mac.care_point.transaction.advance_request.model.TAdvanceRequest;
import com.mac.care_point.zutil.SecurityUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Thilina Kalum
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AdvanceRequestService {

    @Autowired
    private AdvanceRequestRepository advanceRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Transactional
    public List<TAdvanceRequest> saveAdvanceRequest(List<TAdvanceRequest> tAdvanceRequest) {

        for (TAdvanceRequest getRequest : tAdvanceRequest) {
            if (getRequest.getIndexNo() == null) {
                Integer maxNumber = advanceRequestRepository.getMaximumAdvanceNoByBranch(SecurityUtil.getCurrentUser().getBranch());
                if (maxNumber == null) {
                    maxNumber = 0;
                }
                getRequest.setAdvanceNo(maxNumber + 1);
            }
            getRequest.setRequestDate(new Date());
            getRequest.setBranch(SecurityUtil.getCurrentUser().getBranch());
            getRequest.setApprove(Boolean.FALSE);
            advanceRequestRepository.save(getRequest);
        }
        return tAdvanceRequest;
    }

    @Transactional
    public List<TAdvanceRequest> approveAdvanceRequest(List<TAdvanceRequest> tAdvanceRequest) {

        for (TAdvanceRequest getRequest : tAdvanceRequest) {
            getRequest.setRequestDate(new Date());
            getRequest.setApprove(Boolean.TRUE);
            advanceRequestRepository.save(getRequest);
        }
        return tAdvanceRequest;
    }

    public List<TAdvanceRequest> findAllAdvanceRequest(Integer branch) {
        return advanceRequestRepository.findByBranchAndApprove(branch, false);
    }

    public void deleteAdvanceRequest(Integer indexNo) {
        try {
            advanceRequestRepository.delete(indexNo);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete this Advance Request because there are details in other transaction");
        }
    }

    public List<Object[]> findAllRequestByBranch() {
        return advanceRequestRepository.findAllRequestByBranch();
    }
}
