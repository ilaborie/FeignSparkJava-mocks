(function () {
  'use strict';

  angular
    .module('webFrontend')
    .directive('wineNavbar', mainNavbar);

  /** @ngInject */
  function mainNavbar() {
    return {
      restrict: 'E',
      templateUrl: 'app/directives/navbar/navbar.html',
      scope: {},
      controller: NavbarController,
      controllerAs: 'vm',
      bindToController: true
    };

    /** @ngInject */
    function NavbarController() {
     // var vm = this;

    }
  }
})();
