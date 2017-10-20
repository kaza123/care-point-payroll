(function () {
    angular.module("appModule")
            .controller("journalController", function ($scope, journalModel, $timeout, Notification, ConfirmPane) {
                $scope.model = new journalModel();
                $scope.ui = {};


                //focus
                $scope.ui.focus = function (id) {
                    $timeout(function () {
                        document.querySelectorAll(id)[0].focus();
                    }, 10);
                };
                //new
                $scope.ui.new = function () {
                    $scope.ui.mode = "NEW";
                    $scope.ui.focus('#date');
                    $scope.model.new();
                };

                //save
                $scope.ui.save = function () {
                    var check = true;
                    if (!$scope.model.data.transactionDate) {
                        check = false;
                        Notification.error('insert date to save !');
                    }
                    if (!$scope.model.data.description) {
                        check = false;
                        Notification.error('insert description to save !');
                    }
                    if (!$scope.model.dataList.length) {
                        check = false;
                        Notification.error('insert data to save !');
                    }
                    if ($scope.model.data.totalDebit !== $scope.model.data.totalCredit && $scope.model.data.totalCredit !== 0) {
                        check = false;
                        Notification.error('total debit and credit amount is deference!');
                    }
                    if (check) {
                        ConfirmPane.successConfirm("DO YOU WANT TO SAVE !")
                                .confirm(function () {
                                    $scope.model.save()
                                            .then(function (data) {
                                                Notification.success('Journal Number '+data + ' save success !');
                                                $scope.ui.mode = "IDEAL";
                                            });

                                });
                    }
                };

                //add data
                $scope.ui.addData = function () {
                    var check = true;
                    if (!$scope.model.data.transactionDate) {
                        check = false;
                        Notification.error('insert date to add !');
                    }
                    if (!$scope.model.data.description) {
                        check = false;
                        Notification.error('insert description to add !');
                    }
                    if (!$scope.model.tempData.debit && !$scope.model.tempData.credit) {
                        check = false;
                        Notification.error('insert debit or credit amount to add !');
                    }
                    if ($scope.model.tempData.debit && $scope.model.tempData.credit) {
                        check = false;
                        Notification.error('invalied debit and credit amount !');
                    }
                    if (!$scope.model.tempData.accAccount) {
                        check = false;
                        Notification.error('select a account name to add !');
                    }
                    if (!$scope.model.tempData.branch) {
                        check = false;
                        Notification.error('select a branch name to add !');
                    }
                    if (check) {
                        $scope.model.add();
                        $scope.ui.focus('#accMain');
                    }
                };
                $scope.ui.focusAddFunction = function (e) {
                    var code = e ? e.keyCode || e.which : 13;
                    if (code === 13) {
                        $scope.ui.addData();
                    }
                };
                $scope.ui.searchNumber = function (e) {
                    var code = e ? e.keyCode || e.which : 13;
                    if (code === 13) {
                        if ($scope.model.data.number) {
                            $scope.model.searchNumber();
                        }else{
                            Notification.error('number empty !');
                        }
                    }
                };
                $scope.ui.edit=function (index,data){
                    $scope.model.edit(index,data);
                     $scope.ui.focus('#accMain');
                };
                $scope.ui.delete=function (index){
                    $scope.model.delete(index);
                     $scope.ui.focus('#accMain');
                };

                $scope.ui.init = function () {
                    $scope.ui.mode = "IDEAL";
                    $scope.ui.focus('#number');
                };
                $scope.ui.init();
            });
}());