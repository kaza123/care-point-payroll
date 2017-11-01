/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.leave_request;

import com.mac.care_point.transaction.leave_request.model.LeaveRequestMix;
import com.mac.care_point.transaction.leave_request.model.TLeaveDetails;
import com.mac.care_point.transaction.leave_request.model.TLeaveRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class LeaveRequestService {

    @Autowired
    private LeaveRequestDetailRepository leaveRequestDetailRepository;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public List<TLeaveDetails> findAll() {
        return leaveRequestDetailRepository.findAll();
    }

    @Transactional
    public TLeaveDetails saveLeave(LeaveRequestMix leaveRequestMix) throws ParseException {
        List<TLeaveRequest> leaveRequests = leaveRequestMix.getLeaveRequest();
        TLeaveDetails savedetail = new TLeaveDetails();
        for (TLeaveRequest leaveRequest : leaveRequests) {
            //save leave request
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
                    leaveDetails.setDate(sdf.format(fromDate));
                    leaveDetails.setLeaveType(leaveRequest.getLeaveType());
                    leaveDetails.setRealLeave(false);
                    leaveDetails.setTLeaveRequest(saveLeaveRequest);
                    leaveRequestDetailRepository.save(leaveDetails);
                    fromDate.setDate(fromDate.getDate() + 1);
                }
            }
            //save leave details to date
            TLeaveDetails leaveDetails = new TLeaveDetails();
            leaveDetails.setDate(sdf.format(toDate));
            leaveDetails.setLeaveType(leaveRequest.getLeaveType());
            leaveDetails.setRealLeave(false);
            leaveDetails.setTLeaveRequest(saveLeaveRequest);
            savedetail = leaveRequestDetailRepository.save(leaveDetails);
        }
        return savedetail;
    }

}
