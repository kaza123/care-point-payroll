(function () {
    'use strict';

    var service = function (systemConfig, $http) {

        this.loadEmolyeeAttendnce = function (date) {
            return $http.get(systemConfig.apiUrl + "/api/care-point/transaction/attendance-confirm/all-attendance/" + date);
        };

        this.save = function (data, date) {
            return $http.post(systemConfig.apiUrl + "/api/care-point/transaction/attendance-confirm/save/"+date,data);
        };

        this.getCalenderData = function (date) {
            return $http.get(systemConfig.apiUrl + "/api/care-point/transaction/attendance/get-calender-data-by-date/" + date);
        };
    };

    angular.module("appModule")
            .service("attendanceConfirmService", service);
}());