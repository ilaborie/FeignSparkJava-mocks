(function () {
  'use strict';

  angular
    .module('webFrontend')
    .service('wineSrv', WineService);

  /** @ngInject */
  function WineService($log, $http) {

    this.findById = function (wid) {
      $log.info('Find', wid);
      //return $http.get('wine/' + wid, user) // FIXME
      return $http.get('assets/mock/detail.json') // FIXME
        .then(function (response) {
          return response.data;
        });
    };

    this.search = function (query) {
      $log.info('Search', query);
      //return $http.get('wine/?q=' +query) // FIXME
      return $http.get('assets/mock/search.json') // FIXME
        .then(function (response) {
          return response.data;
        });
    };
  }
})();
