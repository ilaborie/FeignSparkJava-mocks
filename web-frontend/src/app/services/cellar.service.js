(function () {
  'use strict';

  angular
    .module('webFrontend')
    .service('cellarSrv', CellarService);

  /** @ngInject */
  function CellarService($http) {

    this.getMyCellar = function () {
      //return $http.get('/cellar')
      return $http.get('assets/mock/cellar.json')
        .then(function (response) {
          return response.data;
        });
    };

    this.drink = function (wid, qty) {
      return $http.post('cellar/drink/' + wid, qty)
        .then(function (response) {
          return response.data;
        });
    };
  }
})();
