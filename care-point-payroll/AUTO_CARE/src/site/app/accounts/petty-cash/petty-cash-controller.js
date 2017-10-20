(function () {
    angular.module("appModule")
            .controller("pettyCashController", function ($scope, pettyCashModel, $timeout, Notification, ConfirmPane) {
                $scope.model = new pettyCashModel();
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

                $scope.ui.init = function () {
                    $scope.ui.mode = "IDEAL";
                };
                $scope.ui.init();
            });
}());