(function () {
    'use strict';

    var service = function (systemConfig, $http) {

        this.loadEmolyee = function () {
            return $http.get(systemConfig.apiUrl + "/api/care-point/master/employee");
        };
        
        this.loadCurrentBranch = function () {
            return $http.get(systemConfig.apiUrl + "/api/care-point/master/branch/current-branch");
        };
        
        this.saveFingerPrint = function (data) {
            return $http.post(systemConfig.apiUrl + "/api/care-point/transaction/finger-print/manual-save-outtime",data);
        };
//        
        this.loadDataByDate = function (branch,date) {
            return $http.get(systemConfig.apiUrl + "/api/care-point/transaction/attendance/get-attendance-by-date/"+branch+"/"+date);
        };
        
        this.inConfirm = function (branch,date) {
            return $http.get(systemConfig.apiUrl + "/api/care-point/transaction/attendance/in-confirm/"+branch+"/"+date);
        };
        
        this.outConfirm = function (branch,date) {
            return $http.get(systemConfig.apiUrl + "/api/care-point/transaction/attendance/out-confirm/"+branch+"/"+date);
        };
       
        this.loadDataByDateAndStatus = function (branch,date,val) {
            return $http.get(systemConfig.apiUrl + "/api/care-point/transaction/attendance/get-attendance-by-date-status/"+branch+"/"+date+"/"+val);
        };
        
        this.getCalenderData = function (date) {
            return $http.get(systemConfig.apiUrl + "/api/care-point/transaction/attendance/get-calender-data-by-date/"+date);
        };
    };

    angular.module("appModule")
            .service("attendanceService", service);
}());