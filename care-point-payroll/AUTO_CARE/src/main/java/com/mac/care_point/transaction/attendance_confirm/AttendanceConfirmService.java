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
import com.mac.care_point.zutil.SecurityUtil;

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
    public List<Object[]> allEmployeeAttendance(Date date, int branch) throws ParseException {
        List<Object[]> list = new ArrayList<>();

        List<TRecordDetails> recordDetails = attendanceConfirmRepository.findByDate(date);
        if (recordDetails.size() > 0) {
            list = attendanceConfirmRepository.allEmployeeeAttendance(date, branch);
        } else {
            list = attendanceConfirmRepository.allEmployeeeAttendanceTemp(branch);
            for (Object[] object : list) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formatDate = sdf.format(date);
                TDailyRecord dayRecord = dailyRecordRepository.findByEmployeeAndDate((int) object[0], formatDate);
                if (dayRecord != null) {
                    object[11] = dayRecord.getInTime();
                    object[12] = dayRecord.getOutTime();
                    object[8] = "1";

                    //OT calculation
                    MPayrollSettings otSetting = payrollSettingsRepository.findByNameAndEmpType("OT", dayRecord.getEmpType());
                    SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
                    Date outTime = time.parse(dayRecord.getOutTime());
                    Date otStartTime = time.parse(otSetting.getStartTime());
                    Date otEndtTime = time.parse(otSetting.getEndTime());

                    String otHours = null;
                    if (outTime.compareTo(otStartTime) > 0) {
                        otHours = attendanceConfirmRepository.getTimeDiff(dayRecord.getOutTime(), otSetting.getStartTime());
                        object[13] = otHours;
                        object[14] = 0;
                        object[16] = 0;
                    } else {
                        object[13] = 0;
                        object[14] = 0;
                        object[16] = 0;
                    }

                    //employee present but half day
                    MPayrollSettings startSetting = payrollSettingsRepository.findByNameAndEmpType("START_TIME", dayRecord.getEmpType());
                    String timedef = attendanceConfirmRepository.getTimeDiff(dayRecord.getOutTime(), startSetting.getStartTime());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                    dateFormat.format(time.parse(timedef));

                    //no pay for less than 6 hours
                    if (dateFormat.parse(dateFormat.format(time.parse(timedef))).before(dateFormat.parse("06:00"))) {
                        object[8] = "0.5";
                        object[10] = "0.5";
                        object[16] = 0;
                    } else {
                        //Early off calculation
                        MPayrollSettings earlyOffSetting = payrollSettingsRepository.findByNameAndEmpType("START_TIME", dayRecord.getEmpType());
                        Date earlyOffEndTime = time.parse(earlyOffSetting.getEndTime());
                        if (earlyOffEndTime.compareTo(outTime) > 0) {
                            String earlyOff = attendanceConfirmRepository.getTimeDiff(earlyOffSetting.getEndTime(), dayRecord.getOutTime());
                            TLeaveDetails leaveDetails = leaveRequestDetailRepository.findByEmployeeAndDateAndRealLeave((int) object[0], formatDate, true);
                            if (leaveDetails == null) {
                                object[16] = earlyOff;
                            }
                        }
                    }

                    //no pay for less than 2 hours
                    if (dateFormat.parse(dateFormat.format(time.parse(timedef))).before(dateFormat.parse("02:00"))) {
                        object[8] = "0";
                        object[10] = "1";
                        object[16] = 0;
                    }

                    //Extra hours with OT calc
                    if (outTime.compareTo(otEndtTime) > 0) {
                        String extraHours = attendanceConfirmRepository.getTimeDiff(dayRecord.getOutTime(), otSetting.getEndTime());
                        object[17] = extraHours;
                        object[13] = getOtTime(otHours, extraHours);
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
                            Date saturdayOtStartTime = time.parse(saturdayOtSetting.getStartTime());
                            Date saturdayOtEndTime = time.parse(saturdayOtSetting.getEndTime());
                            String saturdayOTHours = null;
                            if (outTime.compareTo(saturdayOtStartTime) > 0) {
                                saturdayOTHours = attendanceConfirmRepository.getTimeDiff(dayRecord.getOutTime(), saturdayOtSetting.getStartTime());
                                object[13] = saturdayOTHours;
                                object[16] = 0;
                            }
                            MDOtSettings otSettings = dotSettingRepository.findByDateAndDoubleOt(calender.getStatus(), true);
                            if (otSettings != null) {
                                //Extra hours calculation
                                if (outTime.compareTo(saturdayOtEndTime) > 0) {
                                    String extraHours = attendanceConfirmRepository.getTimeDiff(dayRecord.getOutTime(), saturdayOtSetting.getEndTime());
                                    object[17] = extraHours;
                                    object[13] = getOtTime(saturdayOTHours, extraHours);
                                }
                            }

                        } else {
                            MPayrollSettings doubleOtSetting = payrollSettingsRepository.findByNameAndEmpType("DOUBLE_OT", dayRecord.getEmpType());
                            Date doubleOtStartTime = time.parse(doubleOtSetting.getStartTime());
                            Date doubleOtEndtTime = time.parse(doubleOtSetting.getEndTime());
                            String holidayOtHours = null;
                            if (outTime.compareTo(doubleOtStartTime) > 0) {
                                holidayOtHours = attendanceConfirmRepository.getTimeDiff(dayRecord.getOutTime(), doubleOtSetting.getStartTime());
                                object[14] = holidayOtHours;
                                object[13] = 0;
                                object[16] = 0;
                            }

                            MDOtSettings otSettings = dotSettingRepository.findByDateAndDoubleOt(calender.getStatus(), true);
                            if (otSettings != null) {
                                //Extra hours calculation
                                if (outTime.compareTo(doubleOtEndtTime) > 0) {
                                    String extraHours = attendanceConfirmRepository.getTimeDiff(dayRecord.getOutTime(), doubleOtSetting.getEndTime());
                                    object[17] = extraHours;
                                    object[14] = getOtTime(holidayOtHours, extraHours);
                                }
                            }
                        }
                    }

                } else {
                    object[8] = "0"; //absent
                    object[13] = 0; //OT
                    object[14] = 0; //DOT
                    object[15] = 0; //Late Come
                    object[16] = 0; //Early Off
                    object[17] = 0; //Extra Hours
                }

                //employee have leave
                TLeaveDetails leaveDetails = leaveRequestDetailRepository.findByEmployeeAndDateAndRealLeave((int) object[0], formatDate, true);
                if (leaveDetails != null) {
                    object[18] = leaveDetails.getLeaveRequest().getLeaveCategory();
                    //set leave 1
                    if (leaveDetails.getLeaveType().equals("full_day")) {
                        object[9] = "1";
                        //set no_pay
                        object[10] = "0";
                    } else {
                        //set half day
                        object[10] = "0";
                        object[9] = "0.5";
                        object[8] = "0.5";
                    }
                } else {
                    object[9] = "0";
                }

                //employee absent  
                if (object[10] == null && object[8].equals("0")) {
//                    //set no_pay 1
                    object[10] = "1";
                } else if (object[9].equals("0") && object[8].equals("1")) {
                    object[10] = "0";
                }
            }
        }

        return list;
    }

    public String getOtTime(String otHours, String extraHours) throws ParseException {
        java.text.DateFormat df = new java.text.SimpleDateFormat("hh:mm:ss");
        Date date1 = df.parse(extraHours);
        Date date2 = df.parse(otHours);
        long diff = date2.getTime() - date1.getTime();

        int timeInSeconds = (int) (diff / 1000);
        int hours, minutes, seconds;
        hours = timeInSeconds / 3600;
        timeInSeconds = timeInSeconds - (hours * 3600);
        minutes = timeInSeconds / 60;
        timeInSeconds = timeInSeconds - (minutes * 60);
        seconds = timeInSeconds;
        String diffTime = (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds);
        return diffTime; //OT hours
    }

    public int save(Date date, List<Object[]> list) {
        for (Object[] object : list) {
            TRecordDetails recordDetails = new TRecordDetails();
            Employee employee = employeeRepository.findOne((int) object[0]);
            recordDetails.setEmployee(employee);
            recordDetails.setBranch(employee.getBranch());
            recordDetails.setDate(date);
            recordDetails.setEmpEpf((String) object[1]);
            recordDetails.setEmpType(employee.getType());
            recordDetails.setPresent((String) object[8]);
            recordDetails.setLeave((String) object[9]);
            recordDetails.setNoPay((String) object[10]);
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
