(function () {
  'use strict';

  angular
    .module('webFrontend')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log, $rootScope, cartSrv) {

    $log.debug('Started');
    $rootScope.shared = {
      cart: cartSrv.getMyCart()
    };
  }

})();
