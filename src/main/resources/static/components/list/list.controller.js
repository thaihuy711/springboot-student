(function () {
    'use stricts';
    var todoApp = angular.module('todoApp');
    todoApp.controller('MainController', MainController);

    MainController.$inject = ['$log', 'studentService'];

    function MainController($log, studentService) {
        var vm = this;
        vm.create = create;
        vm.search = search;

        studentService.list().then(function(resp){
            vm.students = resp;
            $log.info(vm.students);
        });


        function create(){
            $log.info('Create students');
        }

        function search(filter){
            $log.info(filter);
        }
    }
})();