(function () {
//module
    angular.module("attendanceModule", ['ui.bootstrap']);
    //controller
    angular.module("attendanceModule")
            .controller("attendanceController", function ($scope, attendanceModel, $filter, $uibModal, $uibModalStack) {
                $scope.model = new attendanceModel();
                $scope.ui = {};

                $scope.ui.inConfirm = function () {
                    $scope.model.inConfirm($scope.model.date);
                };

                $scope.ui.outConfirm = function () {
                    $scope.model.outConfirm($scope.model.date);
                };

                $scope.ui.new = function () {
                    $scope.ui.mode = 'NEW';

                };
                
                $scope.ui.cancelPopUp = function (){
                  $uibModalStack.dismissAll();  
                };

                $scope.ui.dateChange = function () {
                    $scope.model.changeDate($scope.model.date);
                };

                $scope.ui.tabAttendance = function (val) {
                    if ($scope.model.date) {
                        if (val === 0) {
                            $scope.ui.mode = 'IN';
                        } else {
                            $scope.ui.mode = 'OUT';
                        }
                        $scope.model.changeTab($scope.model.date, val);
                    }
                };
                $scope.ui.edit = function (data) {
                    $scope.empName = data[1];
                    $scope.model.fingerPrint.din = data[0];
                    $scope.model.fingerPrint.dn = data[10];
                    $scope.model.fingerPrint.date = $filter('date')(new Date(data[6]), 'yyyy-MM-dd');
                    console.log(data)
                    $uibModal.open({
                        animation: true,
                        ariaLabelledBy: 'modal-title',
                        ariaDescribedBy: 'modal-body',
                        templateUrl: 'attendance_popup.html',
                        scope: $scope,
                        size: 'xs'
                    });
                };

                $scope.ui.updateOutTime = function () {
                    $scope.model.saveFingerPrint()
                            .then(function (data) {
                                $uibModalStack.dismissAll();
                                $scope.model.changeTab($scope.model.date, 1);
                            });
                };

                $scope.ui.tabAllAttendance = function () {
                    $scope.ui.mode = 'IDEAL';
                    if ($scope.model.date) {
                        $scope.model.changeDate($scope.model.date);
                    }
                };

                $scope.ui.getTimeFormat = function (time) {
                    if (time) {
                        return $filter('date')(new Date(time), "HH:mm");
                    } else {
                        return "----";
                    }
                };

                $scope.ui.init = function () {
                    $scope.ui.mode = 'IDEAL';
                };

                $scope.ui.init();
            });
}());
