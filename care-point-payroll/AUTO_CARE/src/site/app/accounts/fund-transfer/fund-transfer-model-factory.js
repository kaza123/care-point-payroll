(function () {
    angular.module("appModule")
            .factory("fundTransferFactory", function () {
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
                        "description": null,
                        "fromValue": 0.00,
                        "toValue": 0.00
                    };
                    return data;
                };
                
                return factory;
            });
}());