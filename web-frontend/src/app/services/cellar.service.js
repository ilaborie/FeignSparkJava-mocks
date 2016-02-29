(function () {
  'use strict';

  angular
    .module('webFrontend')
    .service('cellarSrv', CellarService);

  /** @ngInject */
  function CellarService($log, $http) {

    this.getMyCellar = function () {
      //return $http.get('/cellar')
      return $http.get('assets/mock/cellar.json')
        .then(function (response) {
          return response.data;
        });
    };

    this.drink = function (wid, quantity) {
      var qty = quantity | 1;
      $log.info('Drink', qty, 'for', wid);
      return $http.post('cellar/drink/' + wid, qty)
        .then(function (response) {
          return response.data;
        });
    };

    this.setFavorite = function (wid, favorite) {
      $log.info('Favorite', wid, favorite);
      return $http.post('cellar/favorite/' + wid, favorite)
        .then(function (response) {
          return response.data;
        });
    };
  }
})();
