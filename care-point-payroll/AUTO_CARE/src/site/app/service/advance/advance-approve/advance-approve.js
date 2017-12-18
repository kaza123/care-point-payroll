(function () {
    angular.module("advanceApproveModule", ['ui.bootstrap', 'ui-notification']);

    angular.module("advanceApproveModule")
            .factory("advanceApproveFactory", function ($http, systemConfig) {
                var factory = {};

                factory.findAllUnapproveAdvanceRequest = function (indexNo, callback) {
                    var url = systemConfig.apiUrl + "/api/care-point/transaction/advance-request/find-all-advance-request-unapprove-by-branch/";

                    $http.get(url + indexNo)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };
                factory.findAllUnapproveAdvanceRequestSumm = function (callback) {
                    var url = systemConfig.apiUrl + "/api/care-point/transaction/advance-request/find-summary";

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };
                factory.approveAdvance = function (approveList, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/care-point/transaction/advance-request/approve-advance";

                    $http.post(url, approveList)
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

    angular.module("advanceApproveModule")
            .controller("advanceApproveController", function ($scope, $filter, advanceApproveFactory, Notification, $timeout) {
                $scope.model = {};
                $scope.ui = {};
                $scope.model.unapproveAdvanceRequestList = [];
                $scope.model.unapproveAdvanceRequestSum = [];
                $scope.model.approveList = [];
                $scope.model.checkboxModel = {};
                $scope.model.requestTotal = {
                    requestTotal: 0.0,
                    requestAmountTotal: 0.0
                };

                $scope.ui.setChexboxValue = function (value) {
                    angular.forEach($scope.model.unapproveAdvanceRequestList, function (request) {
                        if (value) {
                            request.isCheck = true;
                        } else {
                            request.isCheck = false;
                        }
                    });
                };
                $scope.ui.findAllUnapproveAdvanceRequest = function () {
                    advanceApproveFactory.findAllUnapproveAdvanceRequestSumm(
                            function (data) {
                                $scope.model.unapproveAdvanceRequestSum = data;
                                $scope.ui.refreshAmount();
                            }, function (error) {
                        console.log(error);
                    });
                };
                $scope.ui.getSelectedList = function () {
                    angular.forEach($scope.model.unapproveAdvanceRequestList, function (value) {
                        if (value.isCheck) {
                            $scope.model.approveList.push(value);
                        }
                    });
                };
                $scope.ui.save = function () {
                    $scope.ui.getSelectedList();
                    advanceApproveFactory.approveAdvance($scope.model.approveList,
                            function (data) {
                                $scope.model.unapproveAdvanceRequestSum = [];
                                $scope.model.unapproveAdvanceRequestList = [];
                                $scope.ui.findAllUnapproveAdvanceRequest();
                                $scope.ui.setSelectBranch();
                                Notification.success("Advance Approve success !!!");
                            }, function (error) {
                    });
                };
                $scope.ui.setSelectBranch = function (indexNo) {
                    $scope.ui.mode = "IDEAL";
                    advanceApproveFactory.findAllUnapproveAdvanceRequest(indexNo,
                            function (data) {
                                $scope.model.unapproveAdvanceRequestList = data;
                            }, function () {
                    });
                };
                $scope.ui.refreshAmount = function () {
                    var requestAmountTotal = 0.0;
                    angular.forEach($scope.model.unapproveAdvanceRequestSum, function (value) {
                        requestAmountTotal += parseFloat(value[3]);
                        return;
                    });
                    $scope.model.requestTotal.requestAmountTotal = requestAmountTotal;
                    $scope.model.requestTotal.requestTotal = $scope.model.unapproveAdvanceRequestSum.length;
                    return this.requestTotal;
                };
                $scope.ui.focus = function () {
                    $timeout(function () {
                        document.querySelectorAll("#year")[0].focus();
                    }, 10);
                };
                $scope.ui.new = function () {
                    $scope.ui.mode = "NEW";
                    $scope.ui.focus();
                };
                $scope.ui.init = function () {
                    $scope.ui.mode = "NEW";
                    $scope.ui.findAllUnapproveAdvanceRequest();
//                    $scope.ui.refreshAmount();
                };

                $scope.ui.init();
            });
}());