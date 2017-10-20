
(function () {
    var factory = function (fundTransferFactory, fundTransferService, $q, $filter, Notification) {
        function employeeModel() {
            this.constructor();
        }
        //prototype functions
        employeeModel.prototype = {
            data: {},
            currentBranch: {},

            accAccountList: [],
            branchList: [],
            //constructor
            constructor: function () {
                var that = this;
                that.data = fundTransferFactory.Data();
//                that.tempData = fundTransferFactory.tempData();

//                fundTransferService.loadAccCategory1()
//                        .success(function (data) {
//                            that.accCategory1List = data;
//                        });
//                fundTransferService.loadAccCategory2()
//                        .success(function (data) {
//                            that.accCategory2List = data;
//                        });
//                fundTransferService.loadAccCategory3()
//                        .success(function (data) {
//                            that.accCategory3List = data;
//                        });
//                fundTransferService.loadAccCategoryMain()
//                        .success(function (data) {
//                            that.accCategoryMainList = data;
//                        });
                fundTransferService.loadAccAccounts()
                        .success(function (data) {
                            that.accAccountList = data;
                            console.log(data);
                        });
                fundTransferService.loadBranch()
                        .success(function (data) {
                            that.branchList = data;
                        });
                fundTransferService.currentBranch()
                        .success(function (data) {
                            that.currentBranch = data;
                        });
                
            }
            , branchLable: function (model) {
                var label;
                angular.forEach(this.branchList, function (value) {
                    if (value.indexNo === model) {
                        label = value.branchCode + ' - ' + value.name;
                        return;
                    }
                });
                return label;
            }
            ,
            new : function () {
                this.data = {};
                this.data.transactionDate = new Date();
                this.data.fromBranch = this.currentBranch.indexNo;
                this.data.toBranch = this.currentBranch.indexNo;
                this.dataList = [];

            }
            , accountLable: function (model) {
                var label;
                angular.forEach(this.accAccountList, function (value) {
                    if (value.indexNo === model) {
                        label = value.indexNo + ' - ' + value.name;
                        return;
                    }
                });
                return label;
            }
            ,
            getAccountValueFrom:function (branch,accAccount){
                var that = this;
                fundTransferService.getAccountValue(branch,accAccount)
                        .success(function (data) {
                            that.data.fromValue=data;
                        });
            },
            getAccountValueTo:function (branch,accAccount){
                var that = this;
                fundTransferService.getAccountValue(branch,accAccount)
                        .success(function (data) {
                           that.data.toValue=data;
                        });
            }
//            , accMainLable: function (model) {
//                var label;
//                angular.forEach(this.accCategoryMainList, function (value) {
//                    if (value.indexNo === model) {
//                        label = value.indexNo + ' - ' + value.name;
//                        return;
//                    }
//                });
//                return label;
//            }
//            , accountLable: function (model) {
//                var label;
//                angular.forEach(this.accAccountList, function (value) {
//                    if (value.indexNo === model) {
//                        label = value.indexNo + ' - ' + value.name;
//                        return;
//                    }
//                });
//                return label;
//            }
//            , accCategory1Lable: function (model) {
//                var label;
//                angular.forEach(this.accCategory1List, function (value) {
//                    if (value.indexNo === model) {
//                        label = value.indexNo + ' - ' + value.name;
//                        return;
//                    }
//                });
//                return label;
//            }
//            , accCategory2Lable: function (model) {
//                var label;
//                angular.forEach(this.accCategory2List, function (value) {
//                    if (value.indexNo === model) {
//                        label = value.indexNo + ' - ' + value.name;
//                        return;
//                    }
//                });
//                return label;
//            }
//            , branchLable: function (model) {
//                var label;
//                angular.forEach(this.branchList, function (value) {
//                    if (value.indexNo === model) {
//                        label = value.branchCode + ' - ' + value.name;
//                        return;
//                    }
//                });
//                return label;
//            },
//            setMainAccount: function (accIndexNo) {
//                var that = this;
//                angular.forEach(this.accAccountList, function (value) {
//                    if (value.indexNo === parseInt(accIndexNo)) {
//                        that.tempData.accMain = value.accMainIndex;
//                        that.tempData.category1 = value.accCategory1.indexNo;
//                        that.tempData.category2 = value.accCategory2.indexNo;
//                        that.tempData.cop = value.cop;
//                        console.log(that.tempData.accMain);
//                        return;
//                    }
//                });
//            },
//            add: function () {
//                var saveData = {};
//                saveData.indexNo = null;
//                saveData.transactionDate = $filter('date')(new Date(this.data.transactionDate), 'yyyy-MM-dd');
//                saveData.currentDate = null;
//                saveData.time = null;
//                saveData.branch = this.tempData.branch;
//                saveData.currentBranch = null;
//                saveData.user = null;
//                saveData.debit = this.tempData.debit;
//                saveData.credit = this.tempData.credit;
//                saveData.accAccount = this.tempData.accAccount;
//                saveData.formName = null;
//                saveData.refNumber = null;
//                saveData.type = null;
//                saveData.typeIndexNo = null;
//                saveData.description = this.data.description;
//                this.dataList.unshift(saveData);
//                this.tempData = fundTransferFactory.tempData();
//                this.totalCalculated();
//                this.tempData.branch = this.currentBranch.indexNo;
//            },
//            totalCalculated: function () {
//                var debit = 0.00;
//                var credit = 0.00;
//                angular.forEach(this.dataList, function (value) {
//                    debit += value.debit;
//                    credit += value.credit;
//                });
//                this.data.totalDebit = debit;
//                this.data.totalCredit = credit;
//            }
//            ,
//            save: function () {
//                var defer = $q.defer();
//                var that = this;
//                fundTransferService.saveJournal(JSON.stringify(that.dataList))
//                        .success(function (data) {
//                            that.refresh();
//                            defer.resolve(data);
//                        })
//                        .error(function (data) {
//                            defer.reject(data);
//                        });
//                return defer.promise;
//            },
//            refresh: function () {
//                this.data = fundTransferFactory.Data();
//                this.tempData = fundTransferFactory.tempData();
//                this.dataList = [];
//            },
//            searchNumber: function () {
//                var defer = $q.defer();
//                var that = this;
//                fundTransferService.numberList(this.data.number)
//                        .success(function (data) {
//                            Notification.success('find ' + data.length + ' record from this number !');
//                            if (data) {
//                                that.dataList = data;
//                                console.log(data[0].transactionDate);
//                                that.data.transactionDate = new Date(data[0].transactionDate);
//                                that.data.description = data[0].description;
//                            }
//                            defer.resolve(data);
//                        })
//                        .error(function (data) {
//                            defer.reject(data);
//                        });
//                return defer.promise;
//            }
//            ,
//            edit: function (index, data) {
//                this.dataList.splice(index, 1);
//                this.tempData = data;
//                this.totalCalculated();
//                this.setMainAccount(data.accAccount);
//
//            }
//            ,
//            delete: function (index) {
//                this.dataList.splice(index, 1);
//                this.totalCalculated();
//            }
        };
        return employeeModel;
    };
    angular.module("appModule")
            .factory("fundTransferModel", factory);
}());


