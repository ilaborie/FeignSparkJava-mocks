(function () {
  'use strict';

  angular
    .module('webFrontend')
    .controller('WineController', WineController);

  /** @ngInject */
  function WineController($stateParams, wineSrv, toastr) {
    var vm = this;
    vm.wine = null;
    vm.wid = $stateParams.wid;

    wineSrv.findById(vm.wid)
      .then(function (wine) {
        vm.wine = wine;
      })
      .catch(function (error) {
        toastr.error('Oops !', error.data);
      });
  }
})();
