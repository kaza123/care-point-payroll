(function () {
    //index module
    angular.module("appModule", [
        "ngRoute",
        "ngCookies",
        "ui.bootstrap",
        //master
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
                        .when("/master/create-account", {
                            templateUrl: "app/master/create-account/create-account.html",
                            controller: "createAccountController"
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
                        
                        
                        //otherwise
                        .otherwise({
                            redirectTo: "/"
                        });
            });

}());
