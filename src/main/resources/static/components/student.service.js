(function () {
    'use stricts';
    var todoApp = angular.module('todoApp');
    todoApp.factory('studentService', studentService);

    studentService.$inject = ['$log', '$http'];

    function studentService($log, $http) {
        var URL = '/api/students';
        return {
            list: list,
            create: create,
            update: update,
            delete: _delete
        };

        function create(form) {
            return $http.post(URL, form).then(function (resp) {
                return resp.data;
            });
        }

        function update(id, form) {
            return $http.post(URL + '/' + id, form).then(function (resp) {
                return resp.data;
            });
        }

        function _delete(id) {
            return $http.delete(URL + '/' + id);
        }

        function list(filter) {
            var _SEARCH_URL = URL + '?page=' + (filter.page - 1) + '&size=' + filter.size;

            if (filter.search && filter.search.length) {
                _SEARCH_URL = _SEARCH_URL + '&name=' + filter.search + '&course=' + filter.search;
            }
            return $http.get(_SEARCH_URL).then(function (resp) {
                return resp.data;
            })
        }
    }
})();