(function () {
  'use strict';

  angular
    .module('webFrontend')
    .service('loginSrv', LoginService);

  /** @ngInject */
  function LoginService($http, $state, storage) {

    this.login = function (user) {
      //return $http.post('login', user) // FIXME
      return $http.get('assets/mock/login.json', user) // FIXME
        .then(function (response) {
          var result = {
            email: user.email,
            token: response.data
          };
          storage.setItem("user", angular.toJson(result));
          return result;
        });
    };

    this.getUser = function () {
      return angular.fromJson(storage.getItem("user"));
    };

    this.logout = function () {
      storage.removeItem("user");
      $state.go('login');
    };
  }
})();
