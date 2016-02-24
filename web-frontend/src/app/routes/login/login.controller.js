(function () {
  'use strict';

  angular
    .module('webFrontend')
    .controller('LoginController', LoginController);

  /** @ngInject */
  function LoginController($state, loginSrv) {
    var vm = this;

    vm.error = null;
    vm.user = {
      email: null,
      password: null
    };

    vm.login = function () {
      return loginSrv.login(vm.user)
        .then(function () {
          $state.go('cellar');
        })

        .catch(function () {
          vm.error = "Invalid login/passowrd :(";
        });
    }
  }
})();
