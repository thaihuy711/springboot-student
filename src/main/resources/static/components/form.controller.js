(function () {
    'use strict';

    var todoApp = angular.module('todoApp');
    todoApp.controller('StudentFormController', StudentFormController);

    StudentFormController.$inject = ['$log', 'studentService',
        'id', 'form', '$uibModalInstance', 'SweetAlert'];

    function StudentFormController($log, studentService, id, form, $uibModalInstance, SweetAlert) {
        var vm = this;
        vm.data = form || {};
        vm.id = id;

        vm.submit = submit;
        vm.dismiss = dismiss;

        function submit(data) {
            $log.info(data);
            var processPromise;
            vm.errorMsg = "";
            if (id) {
                /**
                 * Update form
                 */
                processPromise = studentService.update(id, data);
            } else {
                /**
                 * Create new
                 */
                processPromise = studentService.create(data);
            }

            processPromise.then(function (resp) {
                $uibModalInstance.close(resp);
                if (id) {
                    $log.info('Update success');
                    SweetAlert.swal("Update Success!", "You clicked the button!", "success");
                } else {
                    SweetAlert.swal("Create Success!", "You clicked the button!", "success");
                }
            }, function error(err) {
                $log.info(err);
                vm.errorMsg = err.statusText;
            });
        }

        function dismiss() {
            $uibModalInstance.dismiss();
        }
    }
})();