(function () {
    angular.module("appModule")
            .factory("journalFactory", function () {
                var factory = {};
                factory.Data = function () {
                    var data = {
                        "indexNo": null,
                        "number": null,
                        "transactionDate": null,
                        "currentDate": null,
                        "time": null,
                        "branch": null,
                        "currentDranch": null,
                        "user": null,
                        "debit": 0.00,
                        "credit": 0.00,
                        "accAccount": null,
                        "formName": null,
                        "refNumber": null,
                        "type": null,
                        "typeIndexNo": null,
                        "description": null
                    };
                    return data;
                };
                factory.tempData = function () {
                    var data = {
                        "accMain": null,
                        "accountName": null,
                        "branch": null,
                        "debit": 0.00,
                        "credit": 0.00,
                        "category1": {
                            "indexNo": null,
                            "name": null,
                            "orderNo": 1
                        },
                        "category2": {
                            "indexNo": null,
                            "name": null,
                            "orderNo": 1
                        },
                        "cop": false,
                        "description": null,
                        "date": null
                    };
                    return data;
                };
                return factory;
            });
}());