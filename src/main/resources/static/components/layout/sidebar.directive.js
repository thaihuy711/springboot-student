(function () {
    'use strict';
    var todoApp = angular.module('todoApp');
    todoApp.directive('citSidebar', citSidebar);

    citSidebar.$inject = ['$log'];

    function citSidebar($log) {
        return {
            restrict: 'E',
            templateUrl: 'components/layout/sidebar.html',
            link: link
        };

        function link(scope, element, attrs) {
            /**
             * Link function, manipulation ui
             */
            $log.info(element);
            // offcanvas mobile menu
            $('.page-sidebar-mobile-offcanvas .responsive-toggler').click(function(e) {
                $('body').toggleClass('page-sidebar-mobile-offcanvas-open');
                e.preventDefault();
                e.stopPropagation();
            });

            if ($('body').hasClass('page-sidebar-mobile-offcanvas')) {
                $(document).on('click', function(e) {
                    if ($('body').hasClass('page-sidebar-mobile-offcanvas-open')) {
                        if ($(e.target).closest('.page-sidebar-mobile-offcanvas .responsive-toggler').length === 0 &&
                            $(e.target).closest('.page-sidebar-wrapper').length === 0) {
                            $('body').removeClass('page-sidebar-mobile-offcanvas-open');
                            e.preventDefault();
                            e.stopPropagation();
                        }
                    }
                });
            }


            // handle the search bar close
            $('.page-sidebar').on('click', '.sidebar-search .remove', function (e) {
                e.preventDefault();
                $('.sidebar-search').removeClass("open");
            });


            // handle close on body click
            if ($('.sidebar-search').length) {
                $('.sidebar-search .input-group').on('click', function(e){
                    e.stopPropagation();
                });

                $('body').on('click', function() {
                    if ($('.sidebar-search').hasClass('open')) {
                        $('.sidebar-search').removeClass("open");
                    }
                });
            }
        }
    }
})();