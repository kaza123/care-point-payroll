(function () {
    angular.module("appModule")
            .factory("attendanceModel", function (attendanceService, attendanceFactory, $filter, $q, Notification) {
                function attendanceModel() {
                    this.constructor();
                }

                attendanceModel.prototype = {
                    data: {},
                    fingerPrint: {},
                    calenderData: {},
                    employeeList: [],
                    currentBranch: {},
                    dataList: [],

                    constructor: function () {
                        var that = this;

                        that.data = attendanceFactory.newData();

                        attendanceService.loadEmolyee()
                                .success(function (data) {
                                    that.employeeList = data;
                                });
                        attendanceService.loadCurrentBranch()
                                .success(function (data) {
                                    that.currentBranch = data;
                                });


                    },
                    clear: function () {
                        this.fingerPrint.clock = null;
                        this.fingerPrint.time = null;
                        this.fingerPrint.type = null;
                        this.fingerPrint.isIn = null;
                        this.fingerPrint.isOut = null;
                    },

                    saveFingerPrint: function () {
                        var defer = $q.defer();
                        var that = this;

                        var date = $filter('date')(this.fingerPrint.date, 'yyyy-MM-dd');
                        var time = $filter('date')(this.fingerPrint.time, 'HH:mm:ss');
                        this.fingerPrint.clock = date + ' ' + time;
                        this.fingerPrint.type = "MANUAL";
                        this.fingerPrint.isIn = true;
                        this.fingerPrint.isOut = false;

                        console.log(this.fingerPrint)

                        attendanceService.saveFingerPrint(JSON.stringify(this.fingerPrint))
                                .success(function (data) {
                                    that.clear();
                                    Notification.success('Fingerprint Save Success ! ');
                                    defer.resolve(data);
                                })
                                .error(function (data) {
                                    Notification.error(data.message);
                                    defer.reject(data);
                                });
                        return  defer.promise;
                    },

                    inConfirm: function (date) {
                        var formatDate = $filter('date')(date, 'yyyy-MM-dd');
                        attendanceService.inConfirm(this.currentBranch.indexNo, formatDate)
                                .success(function (data) {
                                    Notification.success("In Time Confirm Success");
                                });
                    },

                    outConfirm: function (date) {
                        var that = this;

                        var isNull = false;
                        angular.forEach(that.dataList, function (val) {
                            if (val[9] === null) {
                                isNull = true;
                                return;
                            }
                        });

                        if (!isNull) {
                            var formatDate = $filter('date')(date, 'yyyy-MM-dd');
                            attendanceService.outConfirm(this.currentBranch.indexNo, formatDate)
                                    .success(function (data) {
                                        Notification.success("Out Time Confirm Success");
                                    });
                        } else {
                            Notification.error("Some Employees Not Completed Their Out Times");
                        }
                    },

                    changeDate: function (date) {
                        var that = this;
                        var formatDate = $filter('date')(date, 'yyyy-MM-dd');
                        attendanceService.loadDataByDate(this.currentBranch.indexNo, formatDate)
                                .success(function (data) {
                                    that.dataList = [];
                                    that.dataList = data;
                                });
                        this.getCalanderData(formatDate);
                    },

                    changeTab: function (date, val) {
                        var that = this;
                        var formatDate = $filter('date')(date, 'yyyy-MM-dd');
                        attendanceService.loadDataByDateAndStatus(this.currentBranch.indexNo, formatDate, val)
                                .success(function (data) {
                                    that.dataList = [];
                                    that.dataList = data;
                                });
                        this.getCalanderData(formatDate);
                    },

                    getCalanderData: function (formatDate) {
                        var that = this;
                        attendanceService.getCalenderData(formatDate)
                                .success(function (data) {
                                    that.calenderData = data;
                                });
                    }

                };
                return attendanceModel;
            });
}());