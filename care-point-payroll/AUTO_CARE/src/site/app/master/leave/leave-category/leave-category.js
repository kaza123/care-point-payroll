(function () {
    //module
    angular.module("leaveCategoryModule", ['ui.bootstrap', 'ui-notification']);

    //http factory
    angular.module("leaveCategoryModule")
            .factory("leaveCategoryFactory", function ($http, systemConfig) {
                var factory = {};

                //load category
                factory.loadLeaveCategoryFactory = function (callback) {
                    var url = systemConfig.apiUrl + "/api/leave/leave-category";

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };

                //load employee type
                factory.loadEmpTypeFactory = function (callback) {
                    var url = systemConfig.apiUrl + "/api/leave/employee-types";

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };

                //save
                factory.saveLeaveCategoryFactory = function (leave, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/leave/save-leave-category";

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

                //delete
                factory.deleteLeaveCategoryFactory = function (indexNo, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/leave/delete-leave" + indexNo;
                    $http.delete(url)
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
    angular.module("leaveCategoryModule")
            .controller("leaveCategoryController", function ($scope, ConfirmPane, $filter, leaveCategoryFactory, Notification, $timeout) {
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
                        "indexNo": null,
                        "type": null,
                        "annual": null,
                        "casual": null,
                        "medical": null
                    };
                };

                //------------------ validation functions ------------------------------
                $scope.validateInput = function () {
                    if ($scope.model.leave.year && $scope.model.leave.type && $scope.model.leave.annual
                            && $scope.model.leave.casual && $scope.model.leave.medical) {
                        return true;
                    } else {
                        return false;
                    }
                };


                //<-----------------http funtiion------------------->
                $scope.http.saveLeaveCategory = function () {
                    ConfirmPane.primaryConfirm("DO YOU WANT TO SAVE !")
                            .confirm(function () {
                                var detail = $scope.model.leave;
                                var detailJSON = JSON.stringify(detail);
                                leaveCategoryFactory.saveLeaveCategoryFactory(
                                        detailJSON,
                                        function (data) {
                                            if (data) {
                                                $scope.model.categoryList.push(data);
                                                Notification.success(data.indexNo + " - " + "Leave setup save successfully");
                                                $scope.model.reset();
                                            } else {
                                                Notification.error("Leave setup already exists");
                                                $scope.model.reset();
                                            }
                                        },
                                        function (data) {
                                            Notification.error(data.message);
                                        }
                                );
                            });

                };


                //<-----------------ui funtiion--------------------->
                //save function
                $scope.ui.save = function () {
                    if ($scope.validateInput()) {
                        $scope.http.saveLeaveCategory();
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
                $scope.ui.edit = function (category, index) {
                    $scope.ui.mode = "EDIT";
                    $scope.model.leave = category;
                    $scope.model.categoryList.splice(index, 1);

                    $scope.ui.focus();
                };


                $scope.ui.init = function () {
                    //set ideal mode
                    $scope.ui.mode = "IDEAL";

                    //all category
                    leaveCategoryFactory.loadLeaveCategoryFactory(function (data) {
                        $scope.model.categoryList = data;
                    });

                    //all employee types
                    leaveCategoryFactory.loadEmpTypeFactory(function (data) {
                        $scope.model.empTypeList = data;
                    });

                    //set values to date picker
                    for (var j = new Date().getFullYear(); j > 2010; j--)
                    {
                        $scope.yearList.push(j);
                    }

                };

                $scope.ui.init();
            });
}());