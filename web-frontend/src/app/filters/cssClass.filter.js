//cssClass
(function () {
  'use strict';

  angular
    .module('webFrontend')
    .filter('cssClass', CssClassFilter);

  /** @ngInject */
  function CssClassFilter() {
    return function (value) {
      return value
        ? value.replace(/\W/g, '').replace(/[èéêë]/g, 'e')
        : 'label-default';
    };
  }
})();
