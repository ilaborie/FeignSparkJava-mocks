(function () {
  'use strict';

  angular
    .module('webFrontend')
    .directive('winePrice', winePrice);

  /** @ngInject */
  function winePrice() {
    return {
      restrict: 'E',
      templateUrl: 'app/directives/winePrice/wine.price.html',
      scope: {
        category: '='
      }
    };
  }
})();
