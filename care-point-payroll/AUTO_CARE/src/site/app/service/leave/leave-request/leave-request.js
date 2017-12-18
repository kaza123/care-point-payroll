(function () {
    //module
    angular.module("leaveRequestModule", ['ui.bootstrap', 'ui-notification']);

    //http factory
    angular.module("leaveRequestModule")
            .factory("leaveRequestFactory", function ($http, systemConfig) {
                var factory = {};

                //load leave setup
                factory.findByYearLeaveSetupFactory = function (year, callback) {
                    var url = systemConfig.apiUrl + "/api/leave/leave-setup-year/" + year;

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };

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
                // find leave history
                factory.findLeaveHistory = function (date,empIndex, callback) {
                    var url = systemConfig.apiUrl + "/api/leave/leave-request/find-history/" + date +"/"+empIndex;

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };

                factory.findEmployeeByEpfNoFactory = function (year, epfNo, callback) {
                    var url = systemConfig.apiUrl + "/api/leave/leave-setup-year-epf/" + year + "/" + epfNo;

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
    angular.module("leaveRequestModule")
            .controller("leaveRequestController", function ($scope, $filter, leaveRequestFactory, Notification, $timeout) {
                //data models 
                $scope.model = {};
                $scope.model.leaveHistory = {};
                
                $scope.model.leave = {
                    employee: null,
                    branch: null,
                    reason: null,
                    approve: null,
                    view: null,
                    leaveRequest: [
//                        {
//                            indexNo : null,
//                            leave: null,
//                            fromDate: null,
//                            toDate: null,
//                            approve: null,
//                            realApprove: null,
//                            leaveType: null
//                        }
                    ]
                };
                $scope.leaveTemp = {
                    leave: null,
                    fromDate: null,
                    toDate: null,
                    approve: null,
                    leaveType: null,
                    leaveCategory : null
                };
                //ui models
                $scope.ui = {};

                //http models
                $scope.http = {};

                //current ui mode IDEAL, SELECTED, NEW, EDIT
                $scope.ui.mode = null;
                $scope.yearList = [];
                $scope.model.employees = [];

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
                    leaveRequestFactory.saveLeaveRequestFactory(
                            detailJSON,
                            function (data) {
                                Notification.success("Save Successfully");
                            },
                            function (data) {
                                Notification.error(data.message);
                            }
                    );
                };


                $scope.http.selectEpfNo = function (keyEvent, epfNo) {
                    if (keyEvent.which === 13)
                        leaveRequestFactory.findEmployeeByEpfNo(epfNo
                                , function (data) {
                                    $scope.employeeName = data.name;
                                    $scope.model.leave.employee = data.indexNo;
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
                    leaveTemp.fromDate = $filter('date')(leaveTemp.fromDate, 'yyyy-MM-dd');
                    leaveTemp.toDate = $filter('date')(leaveTemp.toDate, 'yyyy-MM-dd');
                    if (leaveTemp.toDate === null || angular.isUndefined(leaveTemp.toDate)) {
                        leaveTemp.toDate = leaveTemp.fromDate;
                    }
                    leaveTemp.approve = 0;
                    $scope.model.leave.leaveRequest.push(leaveTemp);
                    $scope.leaveTemp = {};
                };

                //edit funtion
                $scope.ui.edit = function (leave, index) {
                    $scope.model.leave.year = leave[0];
                    $scope.model.leave.EpfNo = leave[1];
                    $scope.model.leave.annual = leave[3];
                    $scope.model.leave.casual = leave[4];
                    $scope.model.leave.halfDay = leave[5];
                    $scope.model.leave.shortLeave = leave[6];
                    $scope.model.leave.empIndex = leave[7];
                    $scope.model.leaveList.splice(index, 1);
                    $scope.ui.focus();
                };

                $scope.ui.changeLeaveType = function (type) {
                    if (type === 'half_day') {
                        $scope.ui.leaveMode = 'half_day';
                    } else {
                        $scope.ui.leaveMode = 'full_day';
                    }
                };
                
                $scope.ui.changeDate = function (date){
                    leaveRequestFactory.findLeaveHistory(date,$scope.model.leave.employee
                                , function (data) {
                                    $scope.model.leaveHistory = data;
                                    console.log(data)
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

                };

                $scope.ui.init();
            });
}());