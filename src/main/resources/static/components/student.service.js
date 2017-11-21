(function () {
    'use stricts';
    var todoApp = angular.module('todoApp');
    todoApp.factory('studentService', studentService);

    studentService.$inject = ['$log', '$http'];

    function studentService($log, $http) {
        return {
            list: list
        };

        function list(filter) {
            var _SEARCH_URL = '/api/students?page=' + (filter.page - 1) + '&size=' + filter.size;

            if (filter.search && filter.search.length) {
                _SEARCH_URL = _SEARCH_URL + '&name=' + filter.search + '&course=' + filter.search;
            }
            return $http.get(_SEARCH_URL).then(function (resp) {
                return resp.data;
            })
        }
    }
})();