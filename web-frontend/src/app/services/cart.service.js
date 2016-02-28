(function () {
  'use strict';

  angular
    .module('webFrontend')
    .service('cartSrv', CartService);

  /** @ngInject */
  function CartService($log, $http, storage) {
    var key = "cart";
    var cart = JSON.parse(storage.getItem(key) || "[]");
    var that = this;
    this.getMyCart = function () {
      return cart;
    };

    this.add = function (wid, quantity) {
      var qty = quantity || 1;
      $log.info('Add', qty, 'for', wid);
      var current = cart.filter(function (order) {
        return order.wid === wid;
      });
      if (current.length) {
        current[0].qty = current[0].qty + qty;
      } else {
        cart.push({wid: wid, qty: qty});
      }
      storage.setItem(key, JSON.stringify(cart));
    };

    this.clear = function () {
      cart.splice(0, cart.length);
      storage.setItem(key, JSON.stringify(cart));
    };

    this.order = function () {
      $log.info('Order', cart);
      return $http.post('cart/order', cart)
        .then(function () {
          that.clear();
          return cart;
        });
    };
  }
})();
