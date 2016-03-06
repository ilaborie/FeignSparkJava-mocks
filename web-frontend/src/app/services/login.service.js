(function () {
  'use strict';

  angular
    .module('webFrontend')
    .service('loginSrv', LoginService);

  /** @ngInject */
  function LoginService($state, $auth) {

    this.login = function (user) {
      return $auth.login(user);
      // FIXME implements backend
      // api/auth/login should send { token: createJWT(user) }
    };

    this.getUser = function () {
      return $auth.getPayload();
    };

    this.logout = function () {
      $auth.logout();
      $state.go('login');
    };
  }
})();
