(function () {
  'use strict';

  angular
    .module('webFrontend')
    .service('loginSrv', LoginService);

  /** @ngInject */
  function LoginService($http, $state, toastr) {
    var storage = localStorage;

    this.login = function (email, password) {
      $http.post('/login', {email: email, password: password})
        .then(function (response) {
          storage.setItem("user", angular.toJson({email: email}));
          return response.data;
        })
        .catch(function (error) {
          toastr.error('Oops !', angular.toJson(error.data, true));
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
