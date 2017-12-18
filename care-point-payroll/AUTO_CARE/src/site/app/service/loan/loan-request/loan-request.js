(function () {
    //module
    angular.module("loanRequestModule", ['ui.bootstrap', 'ui-notification']);

    //http factory
    angular.module("loanRequestModule")
            .factory("loanRequestFactory", function ($http, systemConfig) {
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

                // find employee by index
                factory.findEmployeeByIndex = function (index, callback) {
                    var url = systemConfig.apiUrl + "/api/care-point/master/employee/find-one/" + index;

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };

                //all loan type
                factory.findAllLoanType = function (callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/loan/loan-request/all-loan-type";

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                //save
                factory.saveLoanRequestFactory = function (loan, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/loan/loan-request/save-loan";

                    $http.post(url, loan)
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
    angular.module("loanRequestModule")
            .controller("loanRequestController", function ($scope, $filter, loanRequestFactory, Notification, $timeout) {
                //data models 
                $scope.model = {};
                $scope.model.loan = {};
                $scope.model.loanTemp = [];
                $scope.model.loanRequest = [];
                $scope.model.loanTypeList = [];

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
                    var detail = $scope.model.loanRequest;

                    var detailJSON = JSON.stringify(detail);
                    console.log(detailJSON)
                    loanRequestFactory.saveLoanRequestFactory(
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
                        loanRequestFactory.findEmployeeByEpfNo(epfNo
                                , function (data) {
                                    $scope.model.empName = data.name;
                                    $scope.model.employee = data.indexNo;
                                    $scope.model.loan.employee = data;
                                });
                };

                $scope.http.selectIndexNo = function (keyEvent, index) {
                    if (keyEvent.which === 13)
                        loanRequestFactory.findEmployeeByIndex(index
                                , function (data) {
                                    $scope.model.empName = data.name;
                                    $scope.model.loan.epfNo = data.epfNo;
                                    $scope.model.loan.employee = data;
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

                $scope.ui.add = function (loan) {
                    $scope.model.loanRequest.push(loan);
                    console.log($scope.model.loanRequest)
                    $scope.model.loan = {};
                };



                //edit funtion
                $scope.ui.edit = function (leave, index) {
                };

                $scope.ui.upload = function () {
                    $scope.model.loanRequest = $scope.model.loanTemp;
                };

                $scope.ui.getMonthlyDeduction = function ($event, interestRate1) {
                    if ($event.which === 13) {
                        var interestRate = $scope.model.loan.amount / 100 * interestRate1;
                        //interest rate for insatalment count
                        var interestForInstallment = interestRate / $scope.model.loan.installmentCount;
                        console.log(interestForInstallment)
                        //console.log(14400/24)

                        var TotalInterest = interestForInstallment * $scope.model.loan.installmentCount;
                        console.log(TotalInterest)

                        var TotalForAmount = $scope.model.loan.amount + TotalInterest;
                        console.log(TotalForAmount)
                        $scope.model.loan.totaAmount = TotalForAmount;

                        //monthly installment
                        var monthlyDeduction = TotalForAmount / $scope.model.loan.installmentCount;
                        console.log(monthlyDeduction)

                        $scope.model.loan.monthlyDeduction = monthlyDeduction;
                    }

                };

                $scope.ui.excelExport = function (event) {
                    var input = event.target;
                    var reader = new FileReader();
                    reader.onload = function () {
                        var fileData = reader.result;
                        var wb = XLSX.read(fileData, {type: 'binary'});

                        wb.SheetNames.forEach(function (sheetName) {
                            var rowObj = XLSX.utils.sheet_to_row_object_array(wb.Sheets[sheetName]);
//                            var jsonObj = JSON.stringify(rowObj);
                            if ($scope.model.loanTemp.length === 0) {
                                $scope.model.loanTemp = rowObj;
                                console.log($scope.model.loanTemp)
                            }
                        });
                    };
                    reader.readAsBinaryString(input.files[0]);
                };


                $scope.ui.init = function () {
                    //set ideal mode
                    $scope.ui.mode = "IDEAL";

                    loanRequestFactory.findAllLoanType(
                            function (data) {
                                $scope.model.loanTypeList = data;
                                console.log(data)
                            });
                };

                $scope.ui.init();
            });
}());