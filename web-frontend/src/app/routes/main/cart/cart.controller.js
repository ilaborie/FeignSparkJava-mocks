(function () {
  'use strict';

  angular
    .module('webFrontend')
    .controller('CartController', CartController);

  /** @ngInject */
  function CartController($rootScope, $state, cartSrv, toastr) {
    var vm = this;
    vm.cart = $rootScope.shared.cart;

    vm.computeTotal = function () {
      return vm.cart
          .map(function (order) {
            return order.quantity * order.stock.price;
          })
          .reduce(function (sum, elt) {
            return sum + elt;
          }, 0) + vm.computeTva();
    };

    vm.computeTva = function () {
      return 0.2 * vm.cart
          .map(function (order) {
            return order.quantity * order.stock.price;
          })
          .reduce(function (sum, elt) {
            return sum + elt;
          }, 0);
    };

    vm.clear = function () {
      cartSrv.clear();
      vm.total = 0;
    };
    vm.remove = function (stock) {
      cartSrv.remove(stock);
    };

    vm.order = function () {
      cartSrv.order()
        .then(function () {
          toastr.info('Order complete !');
          $state.go('cellar');
        })
        .catch(function (error) {
          toastr.error('Oops !', error.data);
        });
    }
  }
})();
