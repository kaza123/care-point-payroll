///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mac.care_point.transaction.employee_attendance;
//
//import com.mac.care_point.transaction.employee_attendance.model.TEmployeeAttendance;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author L T430
// */
//@Service
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//public class TEmployeeAttendanceService {
//
//    @Autowired
//    private TEmployeeAttendanceRepository employeeAttendanceRepository;
//
//    List<TEmployeeAttendance> findAll() {
//        return employeeAttendanceRepository.findAll();
//    }
//
//}
