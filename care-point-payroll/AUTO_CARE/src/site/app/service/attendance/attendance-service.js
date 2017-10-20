(function () {
    'use strict';

    var service = function (systemConfig, $http) {

        this.loadEmolyee = function () {
            return $http.get(systemConfig.apiUrl + "/api/care-point/master/employee");
        };
        
        this.loadCurrentBranch = function () {
            return $http.get(systemConfig.apiUrl + "/api/care-point/master/branch/current-branch");
        };
        
//        this.saveFingerPrint = function (data) {
//            return $http.post(systemConfig.apiUrl + "/api/care-point-payroll/transaction/finger-print/manual-save",data);
//        };
//        
        this.loadDataByDate = function (branch,date) {
            return $http.get(systemConfig.apiUrl + "/api/care-point/transaction/attendance/get-attendance-by-date/"+branch+"/"+date);
        };
        
        this.getCalenderData = function (date) {
            return $http.get(systemConfig.apiUrl + "/api/care-point/transaction/attendance/get-calender-data-by-date/"+date);
        };
    };

    angular.module("appModule")
            .service("attendanceService", service);
}());