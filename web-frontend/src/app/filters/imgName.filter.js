//cssClass
(function () {
  'use strict';

  angular
    .module('webFrontend')
    .filter('imgName', ImgNameFilter);

  /** @ngInject */
  function ImgNameFilter() {
    return function (value) {
      return value
        ? value.replace(/[oöô]/g, 'o').replace(/[èéêë]/g, 'e')
        : null;
    };
  }
})();
