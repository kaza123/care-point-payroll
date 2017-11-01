(function () {
    //index module
    angular.module("appModule", [
        "ngRoute",
        "ngCookies",
        "ui.bootstrap",
        //master
        "leaveCategoryModule",
        "leaveSetupModule",
        "leaveRequestModule",
        "clientModule",
        "vehicleModule",
        "app",
        "vehicleEntranceModule",

        //transaction
        "fingerPrintModule",
        "attendanceModule"
    ]);

    //constants
    angular.module("appModule")
            .constant("systemConfig", {
                apiUrl:
                        location.hostname === 'localhost'
                        ? "http://localhost:8090"
                        : location.protocol + "//" + location.hostname + (location.port ? ":" + location.port : "")
            });

    //route config
    angular.module("appModule")
            .config(function ($routeProvider) {
                $routeProvider
                        //system
                        .when("/", {
                            redirectTo: "/payroll/home"
                        })
                        .when("/login", {
                            templateUrl: "app/system/login/login.html",
                            controller: "LoginController"
                        })
                        //home
                        .when("/payroll/home", {
                            templateUrl: "app/service/vehicle-entrance/vehicle-entrance.html",
                            controller: "vehicleEntranceController"
                        })

                        //reports
                        .when("/reports/general/report-viewer", {
                            templateUrl: "app/reports/report-viewer/report-viewer.html",
                            controller: "ReportViewerController"
                        })

                        //registration
                        .when("/master/leave-setup", {
                            templateUrl: "app/master/leave/leave-setup/leave-setup.html",
                            controller: "leaveSetupController"
                        })
                        .when("/master/leave-category", {
                            templateUrl: "app/master/leave/leave-category/leave-category.html",
                            controller: "leaveCategoryController"
                        })
                        .when("/master/calander", {
                            templateUrl: "app/master/calander/calander.html",
                            controller: "calanderController"
                        })
                        //setting
                        .when("/setting/account-settings", {
                            templateUrl: "app/master/account-settings/account-settings.html"
//                            controller: "reOrderLevelController"
                        })
                        //transaction
                        .when("/transaction/finger-print-manual", {
                            templateUrl: "app/service/finger-print-manual/finger-print-manual.html",
                            controller: "fingerPrintController"
                        })
                        .when("/transaction/attendance", {
                            templateUrl: "app/service/attendance/attendance.html",
                            controller: "attendanceController"
                        })
                        .when("/transaction/leave-request", {
                            templateUrl: "app/service/leave/leave-request/leave-request.html",
                            controller: "leaveRequestController"
                        })
                        .when("/transaction/leave-approve", {
                            templateUrl: "app/service/leave/leave-approve/leave-approve.html"
//                            controller: "attendanceController"
                        })


                        //otherwise
                        .otherwise({
                            redirectTo: "/"
                        });
            });

}());
