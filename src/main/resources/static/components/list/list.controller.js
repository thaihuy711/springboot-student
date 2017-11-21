(function () {
    'use stricts';
    var todoApp = angular.module('todoApp');
    todoApp.controller('MainController', MainController);

    MainController.$inject = ['$log', 'studentService', '$state', '$stateParams', '$uibModal', 'SweetAlert'];

    function MainController($log, studentService, $state, $stateParams, $uibModal, SweetAlert) {
        var vm = this;
        vm.error = "";
        vm.filter = {
            search: $stateParams.search || '',
            page: $stateParams.page || 1,
            size: $stateParams.size || 10
        };

        vm.create = create;
        vm.edit = edit;
        vm.delete = _delete;
        vm.search = search;
        vm.pageChange = pageChange;

        getData(vm.filter);

        function create() {
            $log.info('Create students');
            openFormModal();
        }

        function edit(student) {
            var id = student.id;
            var form = angular.copy(student);
            openFormModal(id, form);
        }

        function _delete(student) {
            SweetAlert.swal({
                    title: "Are you sure?",
                    text: "Your will not be able to recover student " + student.name,
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55", confirmButtonText: "Yes, delete it!",
                    cancelButtonText: "No, cancel plx!",
                    closeOnConfirm: false,
                    closeOnCancel: true
                },
                function (isConfirm) {
                    $log.info(isConfirm);
                    if (isConfirm) {
                        studentService.delete(student.id).then(function (resp) {
                            getData(vm.filter);
                            SweetAlert.swal("Deleted!", 'Student ' + student.name + ' file has been deleted.', "success");
                        });
                    }
                });
        }

        function search() {
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
            }, function error(errResp) {
                vm.error = "Error loading data";
            });
        }

        function openFormModal(id, form) {
            var modalInstance = $uibModal.open({
                animation: false,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'components/form.html',
                controller: 'StudentFormController',
                controllerAs: '$ctrl',
                size: 'modal-md',
                resolve: {
                    id: function () {
                        return id;
                    },
                    form: function () {
                        return form;
                    }
                }
            });

            modalInstance.result.then(function (resp) {
                $log.info('Success');
                getData(vm.filter);
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        }
    }
})();