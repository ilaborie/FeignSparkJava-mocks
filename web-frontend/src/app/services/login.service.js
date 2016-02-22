(function () {
  'use strict';

  angular
    .module('webFrontend')
    .service('LoginSrv', LoginService);

  /** @ngInject */
  function LoginService($http, toastr) {
    this.login = function (user, password) {
      $http.post('/login', {user: user, password: password})
        .then(function (response) {
          return response.data;
        })
        .catch(function (error) {
          toastr.error('Oops !', angular.toJson(error.data, null, 2));
        });
    };

  }
})();
