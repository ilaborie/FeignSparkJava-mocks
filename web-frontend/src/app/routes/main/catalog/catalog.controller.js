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
    vm.comment = null;

    vm.search = function () {
      wineSrv.search(vm.query)
        .then(function (wines) {
          vm.wines = wines;
          vm.select(wines[0].id);
        })
        .catch(function (error) {
          toastr.error('Oops !', error.data);
        });
    };
    vm.search();

    vm.select = function (wid) {
      wineSrv.findById(wid)
        .then(function (wine) {
          vm.wine = wine;
        })
        .catch(function (error) {
          toastr.error('Oops !', error.data);
        });
    };

  }
})();
