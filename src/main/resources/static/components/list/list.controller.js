(function () {
    'use stricts';
    var todoApp = angular.module('todoApp');
    todoApp.controller('MainController', MainController);

    MainController.$inject = ['$log', 'studentService', '$state', '$stateParams'];

    function MainController($log, studentService, $state, $stateParams) {
        var vm = this;
        vm.error = "";
        vm.filter = {
            search: $stateParams.search || '',
            page: $stateParams.page || 1,
            size: $stateParams.size || 10
        };

        vm.create = create;
        vm.search = search;
        vm.pageChange = pageChange;

        getData(vm.filter);

        function create() {
            $log.info('Create students');
        }

        function search(){
            vm.filter.page = 1;
            getData(vm.filter);
        }

        function pageChange() {
            getData(vm.filter);
        }

        function getData(filter) {
            vm.students = [];
            $log.info(filter);
            $state.go($state.current, filter, {notify: false, reload: false, location: 'replace'});
            vm.error = "";
            studentService.list(filter).then(function (resp) {
                vm.students = resp;
                $log.info(vm.students);
                vm.filter.totalItems = resp.total;
            }, function error(errResp){
                vm.error = "Error loading data";
            });
        }

    }
})();