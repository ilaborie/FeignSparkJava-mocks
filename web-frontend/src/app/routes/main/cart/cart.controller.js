(function () {
  'use strict';

  angular
    .module('webFrontend')
    .controller('CartController', CartController);

  /** @ngInject */
  function CartController($rootScope, $state, cartSrv, toastr) {
    var vm = this;
    vm.cart = $rootScope.shared.cart;
    vm.total = 0;

    vm.computeTotal = function () {
      return vm.cart
        .map(function (order) {
          return order.quantity * order.stock.price;
        })
        .reduce(function (sum, elt) {
          return sum + elt;
        }, 0);
    };
    //var unregister = $rootScope.$watch('shared.cart', computeTotal);
    //$rootScope.$on('$destroy', unregister);

    vm.clear = function () {
      cartSrv.clear();
      vm.total = 0;
    };
    vm.remove = function(stock){
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
