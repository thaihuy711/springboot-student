(function () {
    'use stricts';
    var todoApp = angular.module('todoApp');
    todoApp.factory('studentService', studentService);

    studentService.$inject = ['$log', '$http'];

    function studentService($log, $http) {
        return {
            list: list
        };

        function list(filter){
            return $http.get('/api/students').then(function(resp){
                return resp.data;
            })
        }
    }
})();