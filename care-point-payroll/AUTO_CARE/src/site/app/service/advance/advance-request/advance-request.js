(function () {
    angular.module("advanceRequestModule", ['ui.bootstrap', 'ui-notification']);

    angular.module("advanceRequestModule")
            .factory("advanceRequestFactory", function ($http, systemConfig) {
                var factory = {};
                factory.findEmployee = function (callback) {
                    var url = systemConfig.apiUrl + "/api/care-point/transaction/advance-request/find-all-employee";

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };
                factory.findAdvanceRequest = function (callback) {
                    var url = systemConfig.apiUrl + "/api/care-point/transaction/advance-request/find-all-advance-request";

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };
                factory.saveAdavanceRequest = function (data, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/care-point/transaction/advance-request/save-advance-request";

                    $http.post(url, data)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.deleteAdavanceRequest = function (indexNo, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/care-point/transaction/advance-request/delete-advance-request/";

                    $http.delete(url + indexNo)
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
    angular.module("advanceRequestModule")
            .controller("advanceRequestController", function ($scope, advanceRequestFactory, $timeout, Notification, $q) {
                $scope.model = {};
                $scope.ui = {};
                $scope.http = {};
                $scope.model.advance = {};
                $scope.model.emp = {};

                $scope.model.requestTotal = {
                    requestTotal: 0.0,
                    requestAmountTotal: 0.0
                };
                $scope.model.tempList = [];
                $scope.model.advanceRequestList = [];

                $scope.ui.getAllEmployee = function () {
                    advanceRequestFactory.findEmployee(
                            function (data) {
                                $scope.model.employeeList = data;
                                console.log($scope.model.employeeList);
                            }, function (error) {
                        console.log(error);
                    });
                };
//                $scope.ui.findAdvanceRequest = function () {
//                    advanceRequestFactory.findAdvanceRequest(
//                            function (data) {
//                                $scope.model.advanceRequestList = data;
//                                $scope.ui.refreshAmount();
//                            }, function (error) {
//                        console.log(error);
//                    });
//                };

                $scope.ui.employeeIndexLabel = function (indexNo) {
                    var index;
                    angular.forEach($scope.model.employeeList, function (value) {
                        if (value.indexNo === parseInt(indexNo)) {
                            index = value.indexNo;
                            $scope.model.advance.employee.epfNo = value.epfNo;
                            $scope.model.advance.employee.name = value.name;
                            $scope.ui.focus("#month");
                            return;
                        }
                    });
                    return index;
                };
                $scope.ui.employeeEpfLabel = function (epfNoo) {
                    var epfNo;
                    angular.forEach($scope.model.employeeList, function (value) {
                        if (value.epfNo === parseInt(epfNoo)) {
                            epfNo = value.epfNo;
                            $scope.model.advance.employee.indexNo = value.indexNo;
                            $scope.model.advance.employee.name = value.name;
                            $scope.ui.focus("#month");
                            return;
                        }
                    });
                    return epfNo;
                };
                $scope.ui.validateData = function (event) {
                    var key = event ? event.keyCode || event.which : 13;
                    if (key === 13) {
                        $scope.ui.add();
                    }
                };
                $scope.ui.add = function () {
                    $scope.model.advanceRequestList.push($scope.model.advance);
                    $scope.ui.refreshAmount();
                    $scope.model.advance = {};
                    $scope.ui.focus("#etfNo");
                };
                $scope.ui.save = function () {
                    var defer = $q.defer();
                    var detail = JSON.stringify($scope.model.advanceRequestList);
                    advanceRequestFactory.saveAdavanceRequest(detail, function () {
                        Notification.success("All Loan request save success !!!");
                        $scope.model.advanceRequestList = [];
                        $scope.model.requestTotal.requestTotal= 0.0;
                        $scope.model.requestTotal. requestAmountTotal= 0.0;
                        defer.resolve();
                    }, function (data) {
                        Notification.error("Loan request save Fail !!!");
                        defer.reject();
                    });
                    return defer.promise;
                };
                $scope.ui.editRequest = function (index, AdavanceRequest) {
                    $scope.model.advance = AdavanceRequest;
                    $scope.model.advanceRequestList.splice(index, 1);
                    $scope.ui.new();
                    $scope.ui.refreshAmount();
                };
                $scope.ui.deleteRequest = function (index) {
                    Notification.success("Loan request delete success !!!");
                    $scope.model.advanceRequestList.splice(index, 1);
                    $scope.ui.refreshAmount();
                };
                $scope.ui.refreshAmount = function () {
                    var requestAmountTotal = 0.0;
                    angular.forEach($scope.model.advanceRequestList, function (value) {
                        requestAmountTotal += parseFloat(value.amount);
                        return;
                    });
                    $scope.model.requestTotal.requestAmountTotal = requestAmountTotal;
                    $scope.model.requestTotal.requestTotal = $scope.model.advanceRequestList.length;
                    return this.requestTotal;
                };
                $scope.ui.excelExport = function (event) {
                    var input = event.target;
                    var reader = new FileReader();
                    reader.onload = function () {
                        var fileData = reader.result;
                        var wb = XLSX.read(fileData, {type: 'binary'});

                        wb.SheetNames.forEach(function (sheetName) {
                            var rowObj = XLSX.utils.sheet_to_row_object_array(wb.Sheets[sheetName]);

                            if ($scope.model.advanceRequestList.length === 0) {
                                $scope.model.tempList = $scope.ui.setEmployeeToExcelUpload(rowObj);
                            }
                        });
                    };
                    reader.readAsBinaryString(input.files[0]);
                };
                
                //--------- note---------//
                //    create xl file, colomn name are 
                //          epfNo , amount , month (2017-12-20 date) 
                //     
                $scope.ui.setEmployeeToExcelUpload = function (rowObj) {
                    var advanceList = [];
                    angular.forEach(rowObj, function (value) {
                        var employee = {};
                        var employeeDetailList = {};
                        var epfNo = value.epfNo;
                        var amount = value.amount;
                        var month = value.month;
                        angular.forEach($scope.model.employeeList, function (value1) {
                            if (parseInt(epfNo) === parseInt(value1.epfNo)) {
                                employee.indexNo = value1.indexNo;
                                employee.epfNo = value1.epfNo;
                                employee.name = value1.name;
                                return;
                            }
                        });
                        employeeDetailList.employee = employee;
                        employeeDetailList.amount = amount;
                        employeeDetailList.month = month;
                        advanceList.push(employeeDetailList);
                        return employeeDetailList;
                    });
                    return advanceList;
                };


                $scope.ui.upload = function () {
                    $scope.model.advanceRequestList = $scope.model.tempList;
                    $scope.ui.refreshAmount();
                };

                $scope.ui.focus = function (val) {
                    $timeout(function () {
                        document.querySelectorAll(val)[0].focus();
                    }, 10);
                };
                $scope.ui.new = function () {
                    $scope.ui.mode = "NEW";
                    $scope.ui.focus("#etfNo");
                };
                $scope.ui.init = function () {
                    $scope.ui.mode = "IDEAL";
                    $scope.ui.getAllEmployee();
                    $scope.model.advance.month = new Date();
                };
                $scope.ui.init();
            });
}());


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
             