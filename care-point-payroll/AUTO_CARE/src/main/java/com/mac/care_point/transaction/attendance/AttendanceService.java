/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.attendance;

import com.mac.care_point.master.calander.CalanderRepository;
import com.mac.care_point.master.calander.model.Calander;
import com.mac.care_point.transaction.attendance.model.TDailyRecord;
import com.mac.care_point.transaction.finger_print.FingerPrintRepository;
import com.mac.care_point.transaction.finger_print.model.TFingerPrint;
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
 * @author 'Kasun Chamara'
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepocitory;

    @Autowired
    private CalanderRepository calanderRepository;

    @Autowired
    private FingerPrintRepository fingerPrintRepository;
    
    @Autowired
    private DailyRecordRepository dailyRecordRepository;

    public List<Object[]> getAttendanceByDateAndBranch(Integer branch, String date) {
        return attendanceRepocitory.getAttendanceByDateAndBranch(branch, date);

    }

    public Calander getCalenderDataByDate(String date) {
        List<Calander> calList = calanderRepository.findByDate(date);
        if (!calList.isEmpty()) {
            return calList.get(0);
        }
        return null;
    }

    List<Object[]> getAttendanceByDateAndStatusAndBranch(Integer branch, String date, int status) {
        return attendanceRepocitory.getAttendanceByDateAndStatusBranch(branch, date, status);
    }

    public int inConfirm(Integer branch, String date) {
        int status = 0;
        List<TFingerPrint> fingerPrint = attendanceRepocitory.findRasRecordsByDateAndBranch(branch, date, status);

        for (TFingerPrint tFingerPrint : fingerPrint) {
            tFingerPrint.setIsIn(Boolean.TRUE);
            fingerPrintRepository.save(tFingerPrint);
        }

        return status;
    }

    public int outConfirm(Integer branch, String date) throws ParseException {
        int status = 1;
        List<TFingerPrint> fingerPrint = attendanceRepocitory.findRasRecordsByDateAndBranch(branch, date, status);
        
       List<Object[]> list = attendanceRepocitory.getAttendanceByDateAndBranch(branch, date);
        for (Object[] object : list) {
            TDailyRecord dailyRecord=new TDailyRecord();
            dailyRecord.setBranch(branch);
             //parse date to simple date format
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date fromDate = sdf.parse(date);
            dailyRecord.setDate(date);
            dailyRecord.setEmpEpf(object[2].toString());
            dailyRecord.setEmpType(object[3].toString());
            dailyRecord.setEmployee((int)object[0]);
            dailyRecord.setInTime(object[6].toString());
            dailyRecord.setOutTime(object[8].toString());
            dailyRecordRepository.save(dailyRecord);
        }
 
       
        for (TFingerPrint tFingerPrint : fingerPrint) {
            tFingerPrint.setIsOut(Boolean.TRUE);
            fingerPrintRepository.save(tFingerPrint);
        }

        return status;
    }

}
