(function () {
    //module
    angular.module("payrollModule", ['ui.bootstrap', 'ui-notification']);

    //http factory
    angular.module("payrollModule")
            .factory("payrollFactory", function ($http, systemConfig) {
                var factory = {};

                //load all Branch
                factory.findAllBranch = function (callback) {
                    var url = systemConfig.apiUrl + "/api/care-point/master/branch";

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };


                // find payroll
                factory.findPayroll = function (branch, year, month, callback) {
                    var url = systemConfig.apiUrl + "/api/payroll/find-payroll/" + branch + "/" + year + "/" + month;

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
    angular.module("payrollModule")
            .controller("payrollController", function ($scope, $filter, payrollFactory, Notification, $timeout) {
                //data models 
                $scope.model = {};
                //ui models
                $scope.ui = {};
                //http models
                $scope.http = {};

                //current ui mode IDEAL, SELECTED, NEW, EDIT
                $scope.ui.mode = null;
                $scope.yearList = [];
                $scope.model.payroll = [];
                $scope.totaldeduction = null;
                $scope.totalForPayroll = null;

                //------------------ model functions ---------------------------
                //reset model
//                $scope.model.reset = function () {
//                    $scope.model.leave = {
//                        "empIndex": null,
//                        "year": null,
//                        "EpfNo": null,
//                        "annual": null,
//                        "casual": null,
//                        "halfDay": null,
//                        "shortLeave": null
//                    };
//                };

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
                $scope.http.saveLeaveRequest = function () {
                    var detail = $scope.model.leave;

                    var detailJSON = JSON.stringify(detail);
                    console.log(detailJSON)
                    payrollFactory.saveLeaveRequestFactory(
                            detailJSON,
                            function (data) {
                                Notification.success("Save Successfully");
                            },
                            function (data) {
                                Notification.error(data.message);
                            }
                    );
                };


                $scope.http.generatePayroll = function () {
                    payrollFactory.findPayroll($scope.model.selectbranch, parseInt($scope.model.year), parseInt($scope.model.month)
                            , function (data) {
                                $scope.model.payroll = data;
//                                console.log($scope.model.payroll)
                                $scope.ui.getTotalDeduction(data);
                                $scope.ui.getTotalForPayroll(data);
                            });
                };

                //<-----------------ui funtiion--------------------->
                //save function
                $scope.ui.save = function () {
//                    if ($scope.validateInput()) {
//                        $scope.ui.mode = "IDEAL";
                    $scope.http.saveLeaveRequest();
//                    } else {
//                        Notification.error("Please input detail");
//                    }
                };
//             


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

                $scope.ui.getTotalDeduction = function (list) {
                    angular.forEach(list, function (val) {
                        $scope.totaldeduction += val[17];
                    });
                };
                $scope.ui.getTotalForPayroll = function (list) {
                    angular.forEach(list, function (val) {
                        $scope.totalForPayroll += val[18];
                    });
                };

                $scope.ui.init = function () {
                    //set ideal mode
                    $scope.ui.mode = "IDEAL";

                    //set values to date picker
                    for (var j = new Date().getFullYear(); j > 2010; j--)
                    {
                        $scope.yearList.push(j);
                    }

                    //all branch
                    payrollFactory.findAllBranch(function (data) {
                        $scope.model.branch = data;
                    });
                };

                $scope.ui.init();
            });
}());







