(function () {
    angular.module("appModule")
            .factory("attendanceModel", function (attendanceService, attendanceFactory, $filter, $q, Notification) {
                function attendanceModel() {
                    this.constructor();
                }

                attendanceModel.prototype = {
                    data: {},
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
                    changeDate: function (date) {
                        var that = this;
                        var formatDate=$filter('date')(date, 'yyyy-MM-dd');
                        console.log(this.currentBranch.indexNo);
                        attendanceService.loadDataByDate(this.currentBranch.indexNo, formatDate)
                                .success(function (data) {
                                    that.dataList = data;
                                });
                        this.getCalanderData(formatDate);        
                    },
                    getCalanderData:function (formatDate){
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