/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.attendance_confirm;

import com.mac.care_point.master.calander.CalanderRepository;
import com.mac.care_point.master.calander.model.Calander;
import com.mac.care_point.master.employee.EmployeeRepository;
import com.mac.care_point.master.employee.model.Employee;
import com.mac.care_point.transaction.attendance.DailyRecordRepository;
import com.mac.care_point.transaction.attendance.model.TDailyRecord;
import com.mac.care_point.transaction.attendance_confirm.model.TRecordDetails;
import com.mac.care_point.transaction.leave_request.LeaveRequestDetailRepository;
import com.mac.care_point.transaction.leave_request.model.TLeaveDetails;
import com.mac.care_point.master.payroll_settings.PayrollSettingsRepository;
import com.mac.care_point.master.payroll_settings.model.MDOtSettings;
import com.mac.care_point.master.payroll_settings.model.MPayrollSettings;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.mac.care_point.master.payroll_settings.DOtSettingRepository;
import com.mac.care_point.master.payroll_settings.OTsettingRepository;

/**
 *
 * @author Nidura Prageeth
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AttendanceConfirmService {

    @Autowired
    private AttendanceConfirmRepository attendanceConfirmRepository;

    @Autowired
    private DailyRecordRepository dailyRecordRepository;

    @Autowired
    private LeaveRequestDetailRepository leaveRequestDetailRepository;

    @Autowired
    private PayrollSettingsRepository payrollSettingsRepository;

    @Autowired
    private CalanderRepository calanderRepository;

    @Autowired
    private DOtSettingRepository dotSettingRepository;
    
    @Autowired
    private OTsettingRepository oTsettingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    //fukXXX transation TODO
    public List<Object[]> allEmployeeAttendance(Date date) throws ParseException {
        List<Object[]> list = new ArrayList<>();

        List<TRecordDetails> recordDetails = attendanceConfirmRepository.findByDate(date);
        if (recordDetails.size() > 0) {
            list = attendanceConfirmRepository.allEmployeeeAttendance(date);
        } else {
            list = attendanceConfirmRepository.allEmployeeeAttendanceTemp();
            for (Object[] object : list) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formatDate = sdf.format(date);
                TDailyRecord dayRecord = dailyRecordRepository.findByEmployeeAndDate((int) object[0], formatDate);
                if (dayRecord != null) {
                    object[11] = dayRecord.getInTime();
                    object[12] = dayRecord.getOutTime();
                    object[8] = "P";

                    //OT calculation
                    MPayrollSettings otSetting = payrollSettingsRepository.findByNameAndEmpType("OT", dayRecord.getEmpType());
                    SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
                    Date outTime = time.parse(dayRecord.getOutTime());
                    Date otStartTime = time.parse(otSetting.getStartTime());
                    Date otEndtTime = time.parse(otSetting.getEndTime());

                    if (outTime.compareTo(otStartTime) > 0) {
                        String timeDiff = attendanceConfirmRepository.getTimeDiff(dayRecord.getOutTime(), otSetting.getStartTime());
                        object[13] = timeDiff;
                        object[14] = 0;
                        object[16] = 0;
                    } else {
                        object[13] = 0;
                        object[14] = 0;
                        object[16] = 0;
                    }
                    //Extra hours calc
                    if (outTime.compareTo(otEndtTime) > 0) {
                        String extraHours = attendanceConfirmRepository.getTimeDiff(dayRecord.getOutTime(), otSetting.getEndTime());
                        object[17] = extraHours;
                    } else {
                        object[17] = 0;
                    }

                    //Late come calculation
                    MPayrollSettings lateComeSetting = payrollSettingsRepository.findByNameAndEmpType("LATE_COME", dayRecord.getEmpType());
                    Date inTime = time.parse(dayRecord.getInTime());
                    Date lateStartTime = time.parse(lateComeSetting.getStartTime());

                    if (inTime.compareTo(lateStartTime) > 0) {
                        String timeDiff = attendanceConfirmRepository.getTimeDiff(dayRecord.getInTime(), lateComeSetting.getStartTime());
                        object[15] = timeDiff;
                    } else {
                        object[15] = 0;
                    }

                    //Saturday, Sunday,holiday OT/DOT calculation
                    Calander calender = calanderRepository.findByStatusNotInAndDate("Working_Day", date);
                    if (calender != null) {
                        if (calender.getStatus().equals("Saturday")) {
                            MPayrollSettings saturdayOtSetting = payrollSettingsRepository.findByNameAndEmpType("SATURDAY_OT", dayRecord.getEmpType());
                            Date outTime4 = time.parse(dayRecord.getOutTime());
                            Date saturdayOtStartTime = time.parse(saturdayOtSetting.getStartTime());
                            Date saturdayOtEndTime = time.parse(saturdayOtSetting.getEndTime());

                            if (outTime4.compareTo(saturdayOtStartTime) > 0) {
                                String timeDiff = attendanceConfirmRepository.getTimeDiff(dayRecord.getOutTime(), saturdayOtSetting.getStartTime());
                                object[13] = timeDiff;
                                object[16] = 0;
                            }
                            MDOtSettings otSettings = dotSettingRepository.findByDateAndDoubleOt(calender.getStatus(), true);
                            if (otSettings != null) {
                                //Extra hours calculation
                                if (outTime4.compareTo(saturdayOtEndTime) > 0) {
                                    String extraHours = attendanceConfirmRepository.getTimeDiff(dayRecord.getOutTime(), saturdayOtSetting.getEndTime());
                                    object[17] = extraHours;
                                }
                            }

                        } else {
                            MPayrollSettings doubleOtSetting = payrollSettingsRepository.findByNameAndEmpType("DOUBLE_OT", dayRecord.getEmpType());
                            Date outTime2 = time.parse(dayRecord.getOutTime());
                            Date doubleOtStartTime = time.parse(doubleOtSetting.getStartTime());
                            Date doubleOtEndtTime = time.parse(doubleOtSetting.getEndTime());

                            if (outTime2.compareTo(doubleOtStartTime) > 0) {
                                String timeDiff = attendanceConfirmRepository.getTimeDiff(dayRecord.getOutTime(), doubleOtSetting.getStartTime());
                                object[14] = timeDiff;
                                object[13] = 0;
                                object[16] = 0;
                            }

                            MDOtSettings otSettings = dotSettingRepository.findByDateAndDoubleOt(calender.getStatus(), true);
                            if (otSettings != null) {
                                //Extra hours calculation
                                if (outTime2.compareTo(doubleOtEndtTime) > 0) {
                                    String extraHours = attendanceConfirmRepository.getTimeDiff(dayRecord.getOutTime(), doubleOtSetting.getEndTime());
                                    object[17] = extraHours;
                                }
                            }
                        }

                    }

                    //Early off calculation
                    MPayrollSettings earlyOffSetting = payrollSettingsRepository.findByNameAndEmpType("START_TIME", dayRecord.getEmpType());
                    Date outTime3 = time.parse(dayRecord.getOutTime());
                    Date earlyOffEndTime = time.parse(earlyOffSetting.getEndTime());
                    if (earlyOffEndTime.compareTo(outTime3) > 0) {
                        String earlyOff = attendanceConfirmRepository.getTimeDiff(earlyOffSetting.getEndTime(), dayRecord.getOutTime());
                        TLeaveDetails leaveDetails = leaveRequestDetailRepository.findByEmployeeAndDateAndRealLeave((int) object[0], formatDate, true);
                        if (leaveDetails == null) {
                            object[16] = earlyOff;
                        }
                    }
                } else {
                    object[8] = "A";
                    object[13] = 0;
                    object[14] = 0;
                    object[15] = 0;
                    object[16] = 0;
                    object[17] = 0;
                }

                TLeaveDetails leaveDetails = leaveRequestDetailRepository.findByEmployeeAndDateAndRealLeave((int) object[0], formatDate, true);
                if (leaveDetails != null) {
                    //set leave 1
                    object[18] = leaveDetails.getLeaveType();
//                    if (leaveDetails.getLeaveType().equals("full_day")) {
                    object[9] = 1;
//                    } else {
//                        object[9] = 0.5;
//                    }
                } else {
                    object[9] = 0;
                }

                if (object[10] == null && object[8] == "A") {
                    //set no_pay 1
                    object[10] = 1;
                } else {
                    object[10] = 0;
                }

            }
        }

        return list;
    }

    public int save(Date date,List<Object[]> list) {
        for (Object[] object : list) {
            TRecordDetails recordDetails = new TRecordDetails();
            Employee employee = employeeRepository.findOne((int) object[0]);
            recordDetails.setEmployee(employee);
            recordDetails.setDate(date);
            recordDetails.setEmpEpf((String) object[1]);
            recordDetails.setEmpType(employee.getType());
            recordDetails.setPresent((String) object[8]);
            recordDetails.setLeave((int) object[9]);
            recordDetails.setNoPay((int) object[10]);
            recordDetails.setInTime((String) object[11]);
            recordDetails.setOutTime((String) object[12]);
//            recordDetails.setOtHours((int) object[13]);
//            recordDetails.setDoubleOt((int) object[14]);
//            recordDetails.setLateCome((int) object[15]);
//            recordDetails.setEarlyOff((int) object[16]);
//            recordDetails.setExtraHours((int) object[17]);
            recordDetails.setLeaveType((String) object[18]);

            attendanceConfirmRepository.save(recordDetails);
        }
        return 1;
    }
}
