(function () {
  'use strict';

  angular
    .module('webFrontend')
    .directive('wineImg', wineImg);

  /** @ngInject */
  function wineImg() {
    return {
      restrict: 'E',
      templateUrl: 'app/directives/wineImg/wine.image.html',
      scope: {
        wine: '='
      }
    };
  }
})();
