(function () {
    angular.module("appModule")
            .factory("pettyCashFactory", function () {
                var factory = {};
                factory.Data = function () {
                    var data = {
                        "indexNo": null,
                        "number": null
                    };
                    return data;
                };
                
                return factory;
            });
}());