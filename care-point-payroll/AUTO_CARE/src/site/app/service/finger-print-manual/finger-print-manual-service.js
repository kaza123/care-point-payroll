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
            return $http.post(systemConfig.apiUrl + "/api/care-point-payroll/transaction/finger-print/manual-save",data);
        };
        
        this.loadFingerPrintMashine = function () {
            return $http.get(systemConfig.apiUrl + "/api/care-point-payroll/transaction/finger-print/load-mashine");
        };
      

    };

    angular.module("appModule")
            .service("fingerPrintService", service);
}());