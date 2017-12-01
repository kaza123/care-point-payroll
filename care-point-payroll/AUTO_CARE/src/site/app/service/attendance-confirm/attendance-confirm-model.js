(function () {
    angular.module("appModule")
            .factory("attendanceConfirmModel", function (attendanceConfirmService, attendanceConfirmFactory, $filter, $q, Notification) {
                function attendanceConfirmModel() {
                    this.constructor();
                }

                attendanceConfirmModel.prototype = {
                    data: {},
                    fingerPrint: {},
                    calenderData: {},
                    currentBranch: {},
                    attendanceList: [],

                    constructor: function () {
                        var that = this;


                        that.data = attendanceConfirmFactory.newData();

                    },
                    clear: function () {
                        this.fingerPrint.clock = null;
                        this.fingerPrint.time = null;
                        this.fingerPrint.type = null;
                        this.fingerPrint.isIn = null;
                        this.fingerPrint.isOut = null;
                    },

                    save: function (list) {
                        var defer = $q.defer();
                        var that = this;
                        attendanceConfirmService.save(JSON.stringify(list),this.date)
                                .success(function (data) {
                                    that.clear();
                                    Notification.success('Save Success ! ');
                                    defer.resolve(data);
                                })
                                .error(function (data) {
                                    Notification.error(data.message);
                                    defer.reject(data);
                                });
                        return  defer.promise;
                    },

                 

                    
                    changeDate: function (date) {
                        var defer = $q.defer();
                        var that = this;
                        var formatDate = $filter('date')(date, 'yyyy-MM-dd');
                        attendanceConfirmService.loadEmolyeeAttendnce(date)
                                .success(function (data) {
                                    that.attendanceList = data;
                                    defer.resolve(data);
                                })
                                .error(function (data) {
                                    Notification.error(data.message);
                                    defer.reject(data);
                                });
                        this.getCalanderData(formatDate);
                        return  defer.promise;
                    },

                    
                    getCalanderData: function (formatDate) {
                        var that = this;
                        attendanceConfirmService.getCalenderData(formatDate)
                                .success(function (data) {
                                    that.calenderData = data;
                                });
                    }

                };
                return attendanceConfirmModel;
            });
}());