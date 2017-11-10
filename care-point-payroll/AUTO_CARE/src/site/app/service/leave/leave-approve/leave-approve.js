(function () {
    //module
    angular.module("leaveApproveModule", ['ui.bootstrap', 'ui-notification']);

    //http factory
    angular.module("leaveApproveModule")
            .factory("leaveApproveFactory", function ($http, systemConfig) {
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

                //find employee leave details
                factory.findEmployeeLeave = function (empIndex, year, callback) {
                    var url = systemConfig.apiUrl + "/api/leave/employee-leave/" + empIndex + "/" + year;

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };

                // all leave
                factory.findAllLeaveByBranch = function (branch, callback) {
                    var url = systemConfig.apiUrl + "/api/leave/leave-approve/pending-leave/" + branch;

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };

                // all leave details
                factory.findAllLeaveDetailByleave = function (indexNo, callback) {
                    var url = systemConfig.apiUrl + "/api/leave/leave-approve/leave-detail/" + indexNo;

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };


                //save
                factory.approveLeaveFactory = function (indexNo, empIndex, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/leave/leave-approve/approve/" + indexNo + "/" + empIndex;

                    $http.put(url)
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
    angular.module("leaveApproveModule")
            .controller("leaveApproveController", function ($scope, $filter, leaveApproveFactory, Notification, $timeout) {
                //data models 
                $scope.model = {};
                $scope.model.leave = {};

                $scope.employee = {};
                //ui models
                $scope.ui = {};

                //http models
                $scope.http = {};

                //current ui mode IDEAL, SELECTED, NEW, EDIT
                $scope.ui.mode = null;


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
                $scope.http.saveLeaveApprove = function () {
                    leaveApproveFactory.approveLeaveFactory($scope.leaveIndex, $scope.empIndex,
                            function (data) {
                                Notification.success("Save Successfully");
                            },
                            function (data) {
                                Notification.error(data.message);
                            }
                    );
                };


                $scope.http.selectBranch = function (branch) {
                    leaveApproveFactory.findAllLeaveByBranch(branch
                            , function (data) {
                                $scope.model.leaveList = data;
                            });
                };

                $scope.http.selectleave = function (indexNo, leave) {
                    $scope.leaveIndex = indexNo;
                    $scope.empIndex = leave.employee.indexNo;
                    $scope.reason = leave.reason;
                    var year;
                    leaveApproveFactory.findAllLeaveDetailByleave(indexNo
                            , function (data) {
                                $scope.model.leaveDetailList = data;
                                angular.forEach(data, function (value) {
                                    if (!year) {
                                        year = value.fromDate;
                                        return;
                                    }
                                });
                                leaveApproveFactory.findEmployeeLeave(leave.employee.indexNo, year, function (data1) {
                                    $scope.employee = data1;
                                });
                            });
                };

                //<-----------------ui funtiion--------------------->
                //save function
                $scope.ui.save = function () {
//                    if ($scope.validateInput()) {
//                        $scope.ui.mode = "IDEAL";
                    $scope.http.saveLeaveApprove();
//                    } else {
//                        Notification.error("Please input detail");
//                    }
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

                $scope.ui.init = function () {
                    //set ideal mode
                    $scope.ui.mode = "IDEAL";

                    //all branch
                    leaveApproveFactory.findAllBranch(function (data) {
                        $scope.model.branch = data;
                    });
                };

                $scope.ui.init();
            });
}());