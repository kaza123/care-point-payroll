/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.leave_approve;

import com.mac.care_point.master.leave.LeaveSetupRepository;
import com.mac.care_point.master.leave.model.MLeaveSetup;
import com.mac.care_point.transaction.leave_request.LeaveRequestDetailRepository;
import com.mac.care_point.transaction.leave_request.LeaveRequestRepository;
import com.mac.care_point.transaction.leave_request.LeavesRepository;
import com.mac.care_point.transaction.leave_request.model.TLeave;
import com.mac.care_point.transaction.leave_request.model.TLeaveDetails;
import com.mac.care_point.transaction.leave_request.model.TLeaveRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nidura Prageeth
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class LeaveApproveService {

    @Autowired
    private LeaveRequestDetailRepository leaveRequestDetailRepository;

    @Autowired
    private LeaveApproveRepository leaveApproveRepository;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private LeaveSetupRepository leaveSetupRepository;

    public List<TLeave> findAllByBranch(int branch) {
        return leaveApproveRepository.findByBranchAndApproveFalse(branch);
    }

    public List<TLeaveRequest> findLeaveDetail(int indexNo) {
        return leaveRequestRepository.findByLeave(indexNo);
    }

    //TODO
    @Transactional
    public int approveLeave(int indexNo, int empIndex) {
        System.out.println("start");
        TLeave leave = leaveApproveRepository.findOne(indexNo);
        leave.setApprove(Boolean.TRUE);
        leaveApproveRepository.save(leave);

        List<TLeaveRequest> tLeaveRequests = leaveRequestRepository.findByLeave(leave.getIndexNo());
        for (TLeaveRequest tLeaveRequest : tLeaveRequests) {
            tLeaveRequest.setApprove(Boolean.TRUE);
            TLeaveRequest leaveRequest = leaveRequestRepository.save(tLeaveRequest);

            //parse date to simple date format
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            String year = null;
            try {
                year = yearFormat.format(yearFormat.parse(leaveRequest.getFromDate()));
            } catch (ParseException ex) {
                Logger.getLogger(LeaveApproveService.class.getName()).log(Level.SEVERE, null, ex);
            }

            //get date deff
            if (!leaveRequest.getLeaveCategory().equals(null)) {
                int count = leaveRequestDetailRepository.getDateCount(leaveRequest.getToDate(), leaveRequest.getFromDate());

                MLeaveSetup leaveSetup = leaveSetupRepository.findByYearAndEmployee(year, leave.getEmployee());

                if (leaveRequest.getLeaveCategory().equals("annual")) {
//                    leaveSetup.setAnnual(leaveSetup.getAnnual() - (count + 1));
                    if (leaveSetup.getAnnualLeave() == null) {
                        leaveSetup.setAnnualLeave(count + 1);
                    } else {
                        leaveSetup.setAnnualLeave(leaveSetup.getAnnualLeave() + (count + 1));
                    }
                }
                if (leaveRequest.getLeaveCategory().equals("casual")) {
                    if (leaveSetup.getCasualLeave() == null) {
//                        leaveSetup.setCasual(leaveSetup.getCasual() - 1);
                        leaveSetup.setCasualLeave(count + 1);
                    } else {
//                        leaveSetup.setCasual(leaveSetup.getCasual() - count);
                          leaveSetup.setCasualLeave(leaveSetup.getCasualLeave() + (count + 1));
                    }
                }
                if (leaveRequest.getLeaveCategory().equals("medical")) {
                    if (leaveSetup.getMedicalLeave() == null) {
//                        leaveSetup.setMedical(leaveSetup.getMedical() - 1);
                        leaveSetup.setMedicalLeave(count + 1);
                    } else {
//                        leaveSetup.setMedical(leaveSetup.getMedical() - count);
                        leaveSetup.setMedicalLeave(leaveSetup.getMedicalLeave()+ (count + 1));
                    }
                }

                leaveSetupRepository.save(leaveSetup);
            } else {
                System.out.println("ddddddddddddddddddddddddd");
            }

            List<TLeaveDetails> leaveDetailses = leaveRequestDetailRepository.findByLeaveRequest(leaveRequest);
            for (TLeaveDetails leaveDetail : leaveDetailses) {
                leaveDetail.setApprove(Boolean.TRUE);
                leaveDetail.setRealLeave(Boolean.TRUE);
                leaveRequestDetailRepository.save(leaveDetail);
            }
        }
        return 1;
    }
}
