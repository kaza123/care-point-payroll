(function () {
    angular.module("appModule")
            .factory("attendanceFactory", function () {
                var factory = {};

                factory.newData = function () {
                    var data = {
                        "index_no": null
                    };
                    return data;
                };
                return factory;
            });
}());