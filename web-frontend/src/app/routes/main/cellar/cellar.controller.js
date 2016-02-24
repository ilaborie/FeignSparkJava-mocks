(function () {
  'use strict';

  angular
    .module('webFrontend')
    .controller('CellarController', CellarController);

  /** @ngInject */
  function CellarController(cellarSrv, toastr) {
    var vm = this;

    vm.cellar = [];

    // Load data
    cellarSrv.getMyCellar()
      .then(function (cellar) {
        vm.cellar = cellar;
      })
      .catch(function (error) {
        toastr.error('Oops !', error.data);
      });
  }
})();
