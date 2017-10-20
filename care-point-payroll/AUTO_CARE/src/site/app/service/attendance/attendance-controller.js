(function () {
//module
    angular.module("attendanceModule", ['ui.bootstrap']);
    //controller
    angular.module("attendanceModule")
            .controller("attendanceController", function ($scope, attendanceModel) {
                $scope.model = new attendanceModel();
                $scope.ui = {};

                $scope.ui.save = function () {
                    $scope.model.save();
                };
                $scope.ui.new = function () {
                    $scope.ui.mode = 'NEW';

                };
                $scope.ui.init = function () {
                    $scope.ui.mode = 'IDEAL';
                };
                $scope.ui.dateChange = function () {
                    $scope.model.changeDate($scope.model.date);
                };
                $scope.ui.init();
            });
}());
