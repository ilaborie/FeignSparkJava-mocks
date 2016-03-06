(function () {
  'use strict';

  angular
    .module('webFrontend')
    .service('wineSrv', WineService);

  /** @ngInject */
  function WineService($log, $http) {

    this.findById = function (wid) {
      $log.info('Find', wid);
      return $http.get('api/wine/' + wid)
        .then(function (response) {
          return response.data;
        });
    };

    this.search = function (query) {
      $log.info('Search', query);
      return $http.get('api/wine' + (query ? '?q=' + query : ''))
        .then(function (response) {
          return response.data;
        });
    };

    this.addComment = function (wid, message) {
      $log.info('Add comment', message);
      return $http.post('api/wine/' + wid + '/comment', message)
        .then(function (response) {
          return response.data;
        });
    }
  }
})();
