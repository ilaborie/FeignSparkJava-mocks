(function () {
  'use strict';

  angular
    .module('webFrontend')
    .directive('wineDetail', wineDetail);

  /** @ngInject */
  function wineDetail() {
    return {
      restrict: 'E',
      templateUrl: 'app/directives/wine/wine.html',
      scope: {
        wine: '='
      },
      controller: WineController,
      controllerAs: 'vm'
    };

    /** @ngInject */
    function WineController(cartSrv) {
      var vm = this;

      vm.buy = function (wid) {
        cartSrv.add(wid);
      }
    }
  }
})();
