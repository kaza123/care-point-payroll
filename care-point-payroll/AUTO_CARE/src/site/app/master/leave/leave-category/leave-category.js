(function () {
    //module
    angular.module("leaveCategoryModule", ['ui.bootstrap', 'ui-notification']);

    //http factory
    angular.module("leaveCategoryModule")
            .factory("leaveCategoryFactory", function ($http, systemConfig) {
                var factory = {};

                //load
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
            .controller("leaveCategoryController", function ($scope, $filter, $log, $routeParams, leaveCategoryFactory, Notification, $timeout) {
                //data models 
                $scope.model = {};

                //ui models
                $scope.ui = {};

                //http models
                $scope.http = {};

                //current ui mode IDEAL, SELECTED, NEW, EDIT
                $scope.ui.mode = null;

                //------------------ model functions ---------------------------
                //reset model
                $scope.model.reset = function () {
                    $scope.model.leave = {
                        "indexNo": null,
                        "type": null,
                        "annual": null,
                        "casual": null,
                        "halfDay": null,
                        "shortLeave": null
                    };
                };

                //------------------ validation functions ------------------------------
                $scope.validateInput = function () {
                    if ($scope.model.client.name | $scope.model.client.mobile | $scope.model.client.type !== null) {
                        return true;
                    } else {
                        return false;
                    }
                };


                //<-----------------http funtiion------------------->
                $scope.http.saveLeaveCategory = function () {
                    var detail = $scope.model.leave;

                    var detailJSON = JSON.stringify(detail);
                    console.log(detailJSON);
                    leaveCategoryFactory.saveLeaveCategoryFactory(
                            detailJSON,
                            function (data) {
                                $scope.model.categoryList.push(data);
                                Notification.success(data.indexNo + " - " + "Client Save Successfully");
                                $scope.model.reset();
                            },
                            function (data) {
                                Notification.error(data.message);
                            }
                    );
                };

                $scope.http.deleteClient = function (indexNo, index) {
                    leaveCategoryFactory.deleteClientFactory(indexNo
                            , function () {
                                $scope.model.clientList.splice(index, 1);
                                Notification.success(indexNo + " - " + "Client Delete Successfully");
                            }
                    , function (data) {
                        Notification.error(data);
                    });
                };

                //<-----------------ui funtiion--------------------->
                //save function
                $scope.ui.save = function () {
//                    if ($scope.validateInput()) {
                        $scope.http.saveLeaveCategory();
//                    } else {
//                        Notification.error("Please input detail");
//                    }
                };

                $scope.ui.editNewClient = function (client, index) {
                    $scope.ui.mode = "EDIT";
                    $scope.model.client = client;
                    $scope.model.newClientList.splice(index, 1);

                };

                $scope.ui.customerTypeLable = function (customerType) {
                    var lable = "";
                    angular.forEach($scope.model.customerTypeList, function (value) {
                        if (value.indexNo === customerType) {
                            lable = value.indexNo + " - " + value.name;
                            return;
                        }
                    });
                    return lable;
                };

                $scope.ui.changeTab = function () {
                    $scope.ui.mode = 'IDEAL';
                    $scope.model.client = {};
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
                $scope.ui.edit = function (client, index) {
                    $scope.ui.mode = "EDIT";
                    $scope.model.client = client;
                    $scope.model.clientList.splice(index, 1);

                    $scope.ui.focus();
                };

                $scope.model.findCustomer = function (client) {
                    for (var i = 0; i < $scope.model.clientList.length; i++) {
                        console.log(client + " : " + $scope.model.clientList[i].indexNo);
                        if ($scope.model.clientList[i].indexNo === parseInt(client)) {
                            return $scope.model.clientList[i];
                        }
                    }
                    $scope.ui.mode = "EDIT";
                };

                $scope.ui.init = function () {
                    //set ideal mode
                    $scope.ui.mode = "IDEAL";

                    leaveCategoryFactory.loadLeaveCategoryFactory(function (data) {
                        $scope.model.categoryList = data;
                    });

                };

                $scope.ui.init();
            });
}());