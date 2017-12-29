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
        "leaveApproveModule",
        "shortLeaveModule",
        "clientModule",
        "vehicleModule",
        "app",
        "vehicleEntranceModule",

        //transaction
        "fingerPrintModule",
        "attendanceModule",
        "attendanceConfirmModule",
        "advanceRequestModule",
        "advanceApproveModule",
        "loanRequestModule",
        "loanApproveModule",
        "payrollModule",
        "allowanceDeductionModule"
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

                        //master
                        //finger-print-manual
                        .when("/master/finger-print-manual", {
                            templateUrl: "app/master/finger-print-manual/finger-print-manual.html",
                            controller: "fingerPrintController"
                        })

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

                        .when("/master/employee", {
                            templateUrl: "app/master/employee/employee.html",
                            controller: "employeeController"
                        })
                        .when("/master/allowance-deduction", {
                            templateUrl: "app/master/allowance-deduction/allowance-deduction.html",
                            controller: "allowanceDeductionController"
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
                        .when("/transaction/attendance-confirm", {
                            templateUrl: "app/service/attendance-confirm/attendance-confirm.html",
                            controller: "attendanceConfirmController"
                        })
                        .when("/transaction/leave-request", {
                            templateUrl: "app/service/leave/leave-request/leave-request.html",
                            controller: "leaveRequestController"
                        })
                        .when("/transaction/leave-approve", {
                            templateUrl: "app/service/leave/leave-approve/leave-approve.html",
                            controller: "leaveApproveController"
                        })
                        .when("/transaction/short-leave", {
                            templateUrl: "app/service/leave/short-leave/short-leave.html",
                            controller: "shortLeaveController"
                        })
                        .when("/transaction/loan-request", {
                            templateUrl: "app/service/loan/loan-request/loan-request.html",
                            controller: "loanRequestController"
                        })
                        .when("/transaction/loan-approve", {
                            templateUrl: "app/service/loan/loan-approve/loan-approve.html",
                            controller: "loanApproveController"
                        })
                        .when("/transaction/advance-request", {
                            templateUrl: "app/service/advance/advance-request/advance-request.html",
                            controller: "advanceRequestController"
                        })
                        .when("/transaction/advance-approve", {
                            templateUrl: "app/service/advance/advance-approve/advance-approve.html",
                            controller: "advanceApproveController"
                        })
                        .when("/transaction/payroll", {
                            templateUrl: "app/service/payroll/payroll.html",
                            controller: "payrollController"
                        })


                        //otherwise
                        .otherwise({
                            redirectTo: "/"
                        });
            });

}());
