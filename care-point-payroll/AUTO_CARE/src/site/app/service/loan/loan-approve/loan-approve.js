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

                // find all pending loan request
                factory.findAllPendingLoanRequest = function (callback) {
                    var url = systemConfig.apiUrl + "/api/loan/loan-approve/find-all-pending";

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };

                // find loan request details by branch
                factory.findLoanRequestByBranch = function (branch, callback) {
                    var url = systemConfig.apiUrl + "/api/loan/loan-approve/find-by-branch/" + branch;

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };

                //approve loan request
                factory.approveLoanRequest = function (list, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/loan/loan-approve/approve-loan";

                    $http.post(url, list)
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
                $scope.model.pendingRequestList = [];
                $scope.model.loanRequestDetails = [];

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
                $scope.http.saveLoanRequest = function () {
                    var saveList = [];
                    angular.forEach($scope.model.loanRequestDetails, function (val) {
                        if (val.isCheck === true) {
                            saveList.push(val);
                        }
                    });
                    var detail = saveList;
                    var detailJSON = JSON.stringify(detail);
                    loanApproveFactory.approveLoanRequest(detailJSON,
                            function (data) {
                                Notification.success("Approve Success.!");
                                loanApproveFactory.findLoanRequestByBranch($scope.selectedBranch
                                        , function (data) {
                                            $scope.model.loanRequestDetails = [];
                                            $scope.loadDetailist = data;
                                            angular.forEach($scope.loadDetailist, function (val) {
                                                val.isCheck = false;
                                                $scope.model.loanRequestDetails.push(val);
                                            });

                                            console.log($scope.model.loanRequestDetails)

                                        });
                            });

                };


                $scope.http.selectEpfNo = function (keyEvent, epfNo) {
                    if (keyEvent.which === 13)
                        loanApproveFactory.findEmployeeByEpfNo(epfNo
                                , function (data) {
                                    $scope.employeeName = data.name;
                                    $scope.model.leave.employee = data.indexNo;
                                });
                };

                $scope.http.selectLoanRequest = function (branch, indexNo) {
                    $scope.selectedBranch = branch;
                    $scope.ui.selectedIndex = indexNo;
                    loanApproveFactory.findLoanRequestByBranch(branch
                            , function (data) {
                                $scope.model.loanRequestDetails = [];
                                $scope.loadDetailist = data;
                                angular.forEach($scope.loadDetailist, function (val) {
                                    val.isCheck = false;
                                    $scope.model.loanRequestDetails.push(val);
                                });

                                console.log($scope.model.loanRequestDetails)

                            });
                };

                //<-----------------ui funtiion--------------------->
                //save function
                $scope.ui.save = function () {
                    $scope.http.saveLoanRequest();
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

                $scope.ui.selectAll = function () {
                    angular.forEach($scope.model.loanRequestDetails, function (val) {
                        if ($scope.ui.isAllSelect === true) {
                            val.isCheck = true;
                        }
                        if ($scope.ui.isAllSelect === false) {
                            val.isCheck = false;
                        }
                    });
                    console.log($scope.model.loanRequestDetails)
                };

//                $scope.ui.getRequestTotal = function () {
//                    angular.forEach($scope.model.pendingRequestList, function (val){
//                        console.log(val)
//                    });
//                    if ($scope.totalAmount) {
//                        $scope.totalAmount += amount;
//                    } else {
//                        $scope.totalAmount = amount;
//                    }
//                    console.log($scope.totalAmount)
//                    $scope.model.loanTotal = toamountamount;
//                };


                //edit funtion
                $scope.ui.edit = function (leave, index) {
                };


                $scope.ui.init = function () {
                    //set ideal mode
                    $scope.ui.mode = "IDEAL";

                    loanApproveFactory.findAllPendingLoanRequest(
                            function (data) {
                                $scope.model.pendingRequestList = data;
                                console.log(data)
                            });
                };

                $scope.ui.init();
            });
}());