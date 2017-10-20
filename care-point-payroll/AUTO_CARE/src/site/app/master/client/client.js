(function () {
    //module
    angular.module("clientModule", ['ui.bootstrap', 'ui-notification']);

    //http factory
    angular.module("clientModule")
            .factory("clientFactory", function ($http, systemConfig) {
                var factory = {};

                factory.lordClientFactory = function (callback) {
                    var url = systemConfig.apiUrl + "/api/care-point/master/client";

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };
                factory.getNewClientList = function (callback) {
                    var url = systemConfig.apiUrl + "/api/care-point/master/client/get-new-client-list";

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };
                factory.getCustomerTypes = function (callback) {
                    var url = systemConfig.apiUrl + "/api/care-point/service/zmaster/customer-type";

                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                callback(data);
                            });
                };

                factory.saveClientFactory = function (summary, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/care-point/master/client/insert-client";

                    $http.post(url, summary)
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
                factory.deleteClientFactory = function (indexNo, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/care-point/master/client/delete-client/" + indexNo;
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
    angular.module("clientModule")
            .controller("clientController", function ($scope, $filter, $log, $routeParams, clientFactory, Notification, $timeout) {
                //data models 
                $scope.model = {};
                $scope.model.newClientList = [];

                //ui models
                $scope.ui = {};

                //http models
                $scope.http = {};

                //current ui mode IDEAL, SELECTED, NEW, EDIT
                $scope.ui.mode = null;

                //------------------ model functions ---------------------------
                //reset model
                $scope.model.reset = function () {
                    $scope.model.client = {
                        "indexNo": null,
                        "name": null,
                        "address1": null,
                        "address2": null,
                        "address3": null,
                        "mobile": null,
                        "branch": null,
                        "type": null,
                        "nic": null,
                        "resident": null,
                        "date": null,
                        "isNew": false

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
                $scope.http.saveClient = function () {
                    $scope.model.client.branch = 1;
                    if (!$scope.model.client.date) {
                        $scope.model.client.date = $filter('date')(new Date(), 'yyyy-MM-dd');
                    }
                    $scope.model.client.isNew = false;

                    var detail = $scope.model.client;

                    var detailJSON = JSON.stringify(detail);
                    console.log(detailJSON);
                    clientFactory.saveClientFactory(
                            detailJSON,
                            function (data) {
                                $scope.model.clientList.push(data);
                                Notification.success(data.indexNo + " - " + "Client Save Successfully");
                                $scope.model.reset();
                            },
                            function (data) {
                                Notification.error(data.message);
                            }
                    );


                };

                $scope.http.deleteClient = function (indexNo, index) {
                    clientFactory.deleteClientFactory(indexNo
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

                    if ($scope.validateInput()) {
                        $scope.http.saveClient();
                    } else {
                        Notification.error("Please input detail");
                    }
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
                        document.querySelectorAll("#nicText")[0].focus();
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

                    //reset mdel
                    $scope.model.reset();

                    clientFactory.lordClientFactory(function (data) {
                        $scope.model.clientList = data;
                    });

                    clientFactory.getCustomerTypes(function (data) {
                        $scope.model.customerTypeList = data;
                    });

                    clientFactory.getNewClientList(function (data) {
                        $scope.model.newClientList = data;
                    });

                    var client = parseInt($routeParams.client);
                    if (client) {
                        console.log(client);

                        $timeout(function () {
                            $scope.model.client = $scope.model.findCustomer(client);
                            console.log($scope.model.client);
                        }, 2000);


                    }
                };

                $scope.ui.init();
            });
}());