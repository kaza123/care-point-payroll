(function () {
    //module
    angular.module("loanApproveModule", ['ui.bootstrap', 'ui-notification']);

    //http factory
    angular.module("loanApproveModule")
            .factory("loanApproveFactory", function ($http, systemConfig) {
                var factory = {};

                // find employee by epf 
                factory.findEmployeeByEpfNo = function (epfNo, callback) {
                    var url = systemConfig.apiUrl + "/api/leave/leave-request/employee/" + epfNo;

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };

                //save
                factory.saveLeaveRequestFactory = function (leave, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/leave/leave-request/save-leave";

                    $http.post(url, leave)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };


                return factory;
            });


    //controller
    angular.module("loanApproveModule")
            .controller("loanApproveController", function ($scope, $filter, loanApproveFactory, Notification, $timeout) {
                //data models 
                $scope.model = {};
                
                //ui models
                $scope.ui = {};

                //http models
                $scope.http = {};



                //------------------ validation functions ------------------------------
//                $scope.validateInput = function () {
//                    if ($scope.model.leave.year && $scope.model.leave.annual
//                            && $scope.model.leave.casual && $scope.model.leave.halfDay && $scope.model.leave.shortLeave) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                };


                //<-----------------http funtiion------------------->
                $scope.http.saveAdvanceRequest = function () {
                    var detail = $scope.model.leave;

                    var detailJSON = JSON.stringify(detail);
                    console.log(detailJSON)
                    
                };


                $scope.http.selectEpfNo = function (keyEvent, epfNo) {
                    if (keyEvent.which === 13)
                        loanApproveFactory.findEmployeeByEpfNo(epfNo
                                , function (data) {
                                    $scope.employeeName = data.name;
                                    $scope.model.leave.employee = data.indexNo;
                                });
                };

                //<-----------------ui funtiion--------------------->
                //save function
                $scope.ui.save = function () {
                    $scope.http.saveAdvanceRequest();
                };

                //focus
                $scope.ui.focus = function () {
                    $timeout(function () {
                        document.querySelectorAll("#year")[0].focus();
                    }, 10);
                };

                //new function
                $scope.ui.new = function () {
                    $scope.ui.mode = "NEW";

                    $scope.ui.focus();
                };

                $scope.ui.add = function (leaveTemp) {    
                };

                //edit funtion
                $scope.ui.edit = function (leave, index) {
                };


                $scope.ui.init = function () {
                    //set ideal mode
                    $scope.ui.mode = "IDEAL";
                };

                $scope.ui.init();
            });
}());