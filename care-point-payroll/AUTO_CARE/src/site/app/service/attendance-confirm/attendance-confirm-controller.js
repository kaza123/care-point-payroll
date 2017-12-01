(function () {
//module
    angular.module("attendanceConfirmModule", ['ui.bootstrap']);
    //controller
    angular.module("attendanceConfirmModule")
            .controller("attendanceConfirmController", function ($scope, attendanceConfirmModel, $filter, $uibModal, $uibModalStack) {
                $scope.model = new attendanceConfirmModel();
                $scope.ui = {};

                $scope.ui.dateChange = function () {
                    $scope.ui.loading = true;
                    $scope.model.changeDate($scope.model.date)
                            .then(function (data) {
                                $scope.ui.loading = false;
                            });
                };

                $scope.ui.save = function () {
                    console.log($scope.model.attendanceList)
                    $scope.model.save($scope.model.attendanceList)
                            .then(function (data) {
                                console.log(data)
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

                    console.log("start")
                    $scope.ui.mode = 'SAVE';
                };

                $scope.ui.init();
            });
}());
