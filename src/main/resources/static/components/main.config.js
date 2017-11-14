var todoApp = angular.module('todoApp');

todoApp.config(function($stateProvider) {
    var mainState = {
        name: 'main',
        url: '/',
        templateUrl: 'components/list/list.html',
        controller: 'MainController',
        controllerAs: '$ctrl'
    };

    var formState = {
        name: 'form',
        url: '/form',
        template: '<h3>This is student form for update/create!</h3>'
    };

    var deleteStudent = {
        name: 'delete',
        url: '/delete',
        template: '<h3>Delete student!</h3>'
    };

    $stateProvider.state(mainState);
    $stateProvider.state(formState);
    $stateProvider.state(deleteStudent);
});