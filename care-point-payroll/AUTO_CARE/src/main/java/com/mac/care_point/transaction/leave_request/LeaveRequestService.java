/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.leave_request;

import com.mac.care_point.master.employee.EmployeeRepository;
import com.mac.care_point.master.employee.model.Employee;
import com.mac.care_point.master.leave.LeaveSetupRepository;
import com.mac.care_point.master.leave.model.MLeaveSetup;
import com.mac.care_point.transaction.leave_request.model.LeaveRequestMix;
import com.mac.care_point.transaction.leave_request.model.TLeave;
import com.mac.care_point.transaction.leave_request.model.TLeaveDetails;
import com.mac.care_point.transaction.leave_request.model.TLeaveRequest;
import com.mac.care_point.zutil.SecurityUtil;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nidura Prageeth
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class LeaveRequestService {

    @Autowired
    private LeaveRequestDetailRepository leaveRequestDetailRepository;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private LeavesRepository leavesRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<TLeaveDetails> findAll() {
        return leaveRequestDetailRepository.findAll();
    }

    @Transactional
    public int saveLeave(LeaveRequestMix leaveRequestMix) throws ParseException {
        //save leave
        TLeave leave = new TLeave();
        leave.setBranch(SecurityUtil.getCurrentUser().getBranch());
        Employee employee = employeeRepository.findOne(leaveRequestMix.getEmployee());
        leave.setEmployee(employee);
        leave.setDate(new Date());
        leave.setReason(leaveRequestMix.getReason());
        leave.setApprove(Boolean.FALSE);
        TLeave tleave = leavesRepository.save(leave);

        List<TLeaveRequest> leaveRequests = leaveRequestMix.getLeaveRequest();
        for (TLeaveRequest leaveRequest : leaveRequests) {
            //save leave request
            leaveRequest.setLeave(tleave.getIndexNo());
            TLeaveRequest saveLeaveRequest = leaveRequestRepository.save(leaveRequest);

            //parse date to simple date format
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fromDate = sdf.parse(leaveRequest.getFromDate());
            Date toDate = sdf.parse(leaveRequest.getToDate());

            int count = leaveRequestDetailRepository.getDateCount(leaveRequest.getToDate(), leaveRequest.getFromDate());
            for (int i = 0; i < count; i++) {
                if (!fromDate.equals(toDate)) {
                    //save leave details from date
                    TLeaveDetails leaveDetails = new TLeaveDetails();
                    leaveDetails.setEmployee(leaveRequestMix.getEmployee());
                    leaveDetails.setDate(sdf.format(fromDate));
                    leaveDetails.setLeaveType(leaveRequest.getLeaveType());
                    leaveDetails.setRealLeave(Boolean.FALSE);
                    leaveDetails.setApprove(Boolean.FALSE);
                    leaveDetails.setLeaveRequest(saveLeaveRequest);
                    leaveRequestDetailRepository.save(leaveDetails);
                    fromDate.setDate(fromDate.getDate() + 1);
                }
            }
            //save leave details to date
            TLeaveDetails leaveDetails = new TLeaveDetails();
            leaveDetails.setEmployee(leaveRequestMix.getEmployee());
            leaveDetails.setDate(sdf.format(toDate));
            leaveDetails.setLeaveType(leaveRequest.getLeaveType());
            leaveDetails.setRealLeave(Boolean.FALSE);
            leaveDetails.setApprove(Boolean.FALSE);
            leaveDetails.setLeaveRequest(saveLeaveRequest);
            leaveRequestDetailRepository.save(leaveDetails);
        }
        return 1;
    }

    public Employee findEmployeeByEpfNo(int epfNo) {
        return employeeRepository.findByEpfNoAndBranch(epfNo, SecurityUtil.getCurrentUser().getBranch());
    }

    public Object findLeaveHistory(Date date, int empIndex) {
        System.out.println(date + "ddddd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        String year = sdf.format(date);
        String month = sdf2.format(date);
        //Date fromDate = sdf.parse(leaveRequest.getFromDate());
        return leaveRequestRepository.findLeaveHistory(SecurityUtil.getCurrentUser().getBranch(),empIndex,year,month);
        
    }

    public Object findLeaveHistoryByBranch(String date, int empIndex, int branch) throws ParseException {
        System.out.println(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
        Format sdf2 = new SimpleDateFormat("MM");
        String month = sdf2.format(sdf3.parse(date));
        String year = sdf.format(sdf.parse(date));
        System.out.println(month);
        System.out.println(year);
        //Date fromDate = sdf.parse(leaveRequest.getFromDate());
        return leaveRequestRepository.findLeaveHistory(branch,empIndex,year,month); 
    }

}
