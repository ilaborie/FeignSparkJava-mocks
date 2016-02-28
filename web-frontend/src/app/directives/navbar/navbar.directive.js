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
    function NavbarController($rootScope,loginSrv) {
      var vm = this;
      vm.user = loginSrv.getUser();
      vm.signOut = loginSrv.logout;
      vm.cart = $rootScope.shared.cart;
    }
  }
})();
