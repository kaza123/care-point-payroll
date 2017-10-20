(function () {
    angular.module("appModule")
            .factory("calanderFactory", function ($http, systemConfig) {
                var factory = {};
                factory.saveEventDetail = function (summary, callback, errorcallback) {
                    var url = systemConfig.apiUrl + "/api/carepoint/master/calander/save-events";
                    $http.post(url, summary)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorcallback) {
                                    errorcallback(data);
                                }
                            });
                };
                factory.saveDefaultSundaySaturday = function (summary, callback, errorcallback) {
                    var url = systemConfig.apiUrl + "/api/carepoint/master/calander/save-default-sunday-saturday";
                    $http.post(url, summary)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorcallback) {
                                    errorcallback(data);
                                }
                            });
                };
                factory.getAllMonthsData = function (month, year, callback, errorcallback) {
                    var url = systemConfig.apiUrl + "/api/carepoint/master/calander/find-by-month-and-year/" + month + "/" + year;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorcallback) {
                                    errorcallback(data);
                                }
                            });
                };
                factory.getOnlyMonthsData = function (month, year, callback, errorcallback) {
                    var url = systemConfig.apiUrl + "/api/carepoint/master/calander/find-by-month-and-year-data/" + month + "/" + year;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorcallback) {
                                    errorcallback(data);
                                }
                            });
                };
                factory.getEventList = function (callback, errorcallback) {
                    var url = systemConfig.apiUrl + "/api/carepoint/master/calander/find-event-list/";
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorcallback) {
                                    errorcallback(data);
                                }
                            });
                };
                factory.getIsSet = function (year, callback, errorcallback) {
                    var url = systemConfig.apiUrl + "/api/carepoint/master/calander/get-default-sunday-saturday-is-set/" + year;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorcallback) {
                                    errorcallback(data);
                                }
                            });
                };
                return factory;
            });

    angular.module("appModule")
            .controller("calanderController", function ($scope, calanderFactory, $filter, Notification) {
                $scope.model = {};
                $scope.ui = {};
                $scope.model.data = {
                    dateList: []
                };
                $scope.model.event = {};
                $scope.model.saveData = {
                    savelist: []
                };
                $scope.model.searchData = {
                    searchDataList: []
                };
                $scope.events = [];

                $scope.ui.addToList = function () {
                    var check = true;
                    if (!$scope.dt) {
                        Notification.error('Date is Empty !');
                        check = false;
                    }
                    if (!$scope.model.event.name) {
                        Notification.error('Event Name is empty !');
                        check = false;
                    }
                    if (check) {

                        var object = {
                            indexNo: null,
                            date: $scope.dt,
                            status: $scope.model.event.name
                        };
                        angular.forEach($scope.model.saveData.savelist, function (value, $index) {
                            if ($filter('date')(value.date, 'yyyy-MM-dd') === $filter('date')($scope.dt, 'yyyy-MM-dd')) {
                                $scope.model.saveData.savelist.splice($index, 1);
                            }
                        });
                        $scope.model.saveData.savelist.push(object);
                    }
                };

                $scope.ui.saveDetail = function () {
                    var saveList = $scope.model.saveData.savelist;
                    if (saveList.length) {
                        calanderFactory.saveEventDetail(saveList, function (data) {
                            Notification.success("save");
                            $scope.model.saveData.savelist = [];
                            $scope.ui.getAllEventData();
                        });
                    } else {
                        Notification.error("empty list");
                    }
                };

                $scope.ui.getAllEventData = function () {
                    calanderFactory.getEventList(function (data) {
                        angular.forEach(data, function (value) {
                            var object = {
                                date: null,
                                status: null
                            };
                            object.date = $filter('date')(value.date, 'yyyy-MM-dd');
                            object.status = value.status;

                            $scope.events.push(object);
                            //set currnt date
                            $scope.dt = new Date();
                        });
                    }, function (data) {
                        Notification.error(data.message);
                    });
                };

                $scope.ui.getSelectMonthsData = function () {
                    $scope.dt;
                    var year = $filter('date')($scope.dt, 'yyyy');
                    var month = $filter('date')($scope.dt, 'MM');

                    calanderFactory.getOnlyMonthsData(month, year, function (data) {
                        $scope.model.searchData.searchDataList = data;
                    }, function () {

                    });
                };

//---------------------------- calander js function ------------------------------------

                $scope.today = function () {
                    $scope.dt = new Date();
                };

                $scope.today();

                $scope.clear = function () {
                    $scope.dt = null;
                };

                $scope.options = {
                    customClass: getDayClass,
                    minDate: new Date(),
                    showWeeks: false
                };

                // Disable weekend selection
                function disabled(data) {
                    var date = data.date,
                            mode = data.mode;
                    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
                }

                $scope.toggleMin = function () {
                    $scope.options.minDate = $scope.options.minDate ? null : new Date();
                };

                $scope.toggleMin();

                $scope.setDate = function (year, month, day) {
                    $scope.dt = new Date(year, month, day);
                };

                $scope.MonthDates = function (month, year) {
                    return /8|3|5|10/.test(--month) ? 30 : month === 1 ? (!(year % 4) && year % 100) || !(year % 400) ? 29 : 28 : 31;
                };

                function getDayClass(data) {
                    var date = data.date,
                            mode = data.mode;
                    if (mode === 'day') {
                        var dayToCheck = new Date(date).setHours(0, 0, 0, 0);

                        for (var i = 0; i < $scope.events.length; i++) {
                            var currentDay = new Date($scope.events[i].date).setHours(0, 0, 0, 0);
                            if (dayToCheck === currentDay) {
                                return $scope.events[i].status;
                            }
                        }
                    }

                    return '';
                }
                ;
                $scope.ui.setSundayAndSaturday = function () {
                    //default set sunday and saturday
                    var year = $scope.year;
                    if (year) {
                        calanderFactory.getIsSet(year, function (data) {
                            console.log(data);
                            if (data.setDefault) {
                                Notification.error('already assign Sunday and Saturday');
                            } else {
                                console.log('second');
                                    for (var i = 1; i < 13; i++) {
                                        var noOfDateInMonth = $scope.MonthDates(i, year);
                                        for (var k = 1; k < noOfDateInMonth + 1; k++) {
                                            var element = {};
                                            element.date = new Date(year, i - 1, k);
                                            var number = element.date % 7;
                                            if (number === 6) {
                                                element.status = 'Saturday';
                                            } else if (number === 0) {
                                                element.status = 'Sunday';
                                            } else {
                                                element.status = 'Working_Day';
                                            }
                                            $scope.model.data.dateList.push(element);
                                        }
                                    }
                                    var saveList = $scope.model.data.dateList;
                                    console.log(saveList);
                                    calanderFactory.saveDefaultSundaySaturday(saveList, function (data) {
                                        Notification.success("save");
                                        $scope.model.data.dateList = [];
                                        $scope.ui.getAllEventData();
                                    });
                            }
                        });
                    }else{
                        Notification.error('plz input a year to set Sunday and Saturday');
                    }

                };

                $scope.ui.init = function () {
                    //set ideal mode
                    $scope.ui.mode = "IDEAL";
                    $scope.ui.getAllEventData();
//                    $scope.ui.setSundayAndSaturday();

                };
                $scope.ui.init();
            });
}());