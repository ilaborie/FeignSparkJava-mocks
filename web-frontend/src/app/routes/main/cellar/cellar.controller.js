(function () {
  'use strict';

  angular
    .module('webFrontend')
    .controller('CellarController', CellarController);

  /** @ngInject */
  function CellarController(cellarSrv, cartSrv, toastr) {
    var vm = this;

    vm.cellar = [];

    var findById = function (wid) {
      return vm.cellar.find(function (stock) {
        return wid === stock.wine.id;
      });
    };

    // Load data
    cellarSrv.getMyCellar()
      .then(function (cellar) {
        vm.cellar = cellar;
      })
      .catch(function (error) {
        toastr.error('Oops !', error.data);
      });

    vm.drink = function (wid) {
      cellarSrv.drink(wid)
        .then(function (quantity) {
          var stock = findById(wid);
          stock.qty = quantity;
        })
        .catch(function (err) {
          toastr.error(err.data, "Oops!");
        });
    };
    vm.buy = function (wid) {
      cartSrv.add(wid);
    }
  }
})();
