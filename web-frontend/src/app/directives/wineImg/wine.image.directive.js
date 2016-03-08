(function () {
  'use strict';

  angular
    .module('webFrontend')
    .directive('wineImg', wineImg);

  /** @ngInject */
  function wineImg(config) {
    return {
      restrict: 'E',
      templateUrl: 'app/directives/wineImg/wine.image.html',
      scope: {
        wine: '='
      }, link: function (scope) {
        scope.url = config.imgUrl;
      }
    };
  }
})();
