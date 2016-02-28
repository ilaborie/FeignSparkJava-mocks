(function () {
  'use strict';

  angular
    .module('webFrontend')
    .controller('CatalogController', CatalogController);

  /** @ngInject */
  function CatalogController(wineSrv, toastr) {
    var vm = this;
    vm.query = null;
    vm.wines = [];
    vm.wine = null;

    vm.search = function () {
      wineSrv.search(vm.query)
        .then(function (wines) {
          vm.wines = wines;
          vm.wine = vm.wines[0];
        })
        .catch(function (error) {
          toastr.error('Oops !', error.data);
        });
    }
  }
})();
