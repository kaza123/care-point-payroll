(function () {
    //module
    angular.module("leaveSetupModule", ['ui.bootstrap', 'ui-notification']);

    //http factory
    angular.module("leaveSetupModule")
            .factory("leaveSetupFactory", function ($http, systemConfig) {
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

                factory.findByYearAndEpfLeaveSetupFactory = function (year, epfNo, callback) {
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
                factory.updateLeaveSetupFactory = function (leave, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/leave/leave-setup-update";

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
    angular.module("leaveSetupModule")
            .controller("leaveSetupController", function ($scope, $filter, leaveSetupFactory, Notification, $timeout) {
                //data models 
                $scope.model = {};
                $scope.model.leave = {};
                //ui models
                $scope.ui = {};

                //http models
                $scope.http = {};

                //current ui mode IDEAL, SELECTED, NEW, EDIT
                $scope.ui.mode = null;
                $scope.yearList = [];

                //------------------ model functions ---------------------------
                //reset model
                $scope.model.reset = function () {
                    $scope.model.leave = {
                        "empIndex": null,
                        "year": null,
                        "EpfNo": null,
                        "annual": null,
                        "casual": null,
                        "medical": null
                    };
                };

                //------------------ validation functions ------------------------------
                $scope.validateInput = function () {
                    if ($scope.model.leave.year && $scope.model.leave.annual
                            && $scope.model.leave.casual && $scope.model.leave.medical) {
                        return true;
                    } else {
                        return false;
                    }
                };


                //<-----------------http funtiion------------------->
                $scope.http.saveLeaveSetup = function () {
                    var detail = $scope.model.leave;
                    console.log(detail)
                    var detailJSON = JSON.stringify(detail);
                    leaveSetupFactory.updateLeaveSetupFactory(
                            detailJSON,
                            function (data) {
                                Notification.success("Leave Setup Save Successfully");
                                $scope.model.reset();
                            },
                            function (data) {
                                Notification.error(data.message);
                            }
                    );
                };


                $scope.http.selectYear = function (year) {
                    leaveSetupFactory.findByYearLeaveSetupFactory(year
                            , function (data) {
                                $scope.model.leaveList = data;
                            });
                };

                $scope.http.selectEpfNo = function (keyEvent, epfNo) {
                    if ($scope.model.leave.year) {
                        if (keyEvent.which === 13)
                            leaveSetupFactory.findByYearAndEpfLeaveSetupFactory($scope.model.leave.year, epfNo
                                    , function (data) {
                                        $scope.model.leaveList = data;
                                    });
                    } else {
                        Notification.error("please select year");
                    }
                };

                //<-----------------ui funtiion--------------------->
                //save function
                $scope.ui.save = function () {
                    if ($scope.validateInput()) {
                        console.log("ssssss")
                        $scope.ui.mode = "IDEAL";
                        $scope.http.saveLeaveSetup();
                    } else {
                        Notification.error("Please input detail");
                    }
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

                //edit funtion
                $scope.ui.edit = function (leave, index) {
                    console.log(leave)
                    $scope.model.leave.year = leave[0];
                    $scope.model.leave.EpfNo = leave[1];
                    $scope.model.leave.annual = leave[3];
                    $scope.model.leave.casual = leave[4];
                    $scope.model.leave.medical = leave[5];
                    $scope.model.leave.empIndex = leave[6];
                    $scope.model.leaveList.splice(index, 1);
                    $scope.ui.focus();
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