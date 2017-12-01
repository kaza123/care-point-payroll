(function () {
    angular.module("appModule")
            .factory("employeeFactory", function () {
                var factory = {};
                factory.newEmployeeData = function () {
                    var data = {
                        "indexNo": null,
                        "name": null,
                        "addressLine1": null,
                        "addressLine2": null,
                        "addressLine3": null,
                        "mobile": null,
                        "telNo": null,
                        "email": null,
                        "nic": null,
                        "branch": null,
                        "civilStatus": null,
                        "type": null,
                        "department": null,
                        "joinedDate": null,
                        "active": null,
                        "confirm": null,
                        "resigned": null,
                        "image": null,
                        "epfNo": null,
                        "fingerPrintNo": null,
                        "basicSalary": null,
                        "basicForEpf": null,
                        "payrollType": null,
                        "epfStatus": null,
                        "epfGrade": null,
                        "epfApplicable": null,
                        "otApplicable": null,
                        "morningOt": null,
                        "attendanceAllowance": null,
                        "taxApplicable": null
                    };
                    return data;
                };
                return factory;
            });
}());