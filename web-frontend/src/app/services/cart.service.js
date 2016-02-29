(function () {
  'use strict';

  angular
    .module('webFrontend')
    .service('cartSrv', CartService);

  /** @ngInject */
  function CartService($log, $http, storage, wineSrv) {
    var key = "cart";
    var cart = angular.fromJson(storage.getItem(key) || "[]");
    var that = this;
    this.getMyCart = function () {
      return cart;
    };

    this.add = function (wid, quantity) {
      var qty = quantity || 1;
      $log.info('Cart, add', qty, 'for', wid);
      var current = cart.find(function (order) {
        return order.wine.id === wid;
      });
      if (current) {
        current.quantity += qty;
        storage.setItem(key, angular.toJson(cart));
      } else {
        wineSrv.findById(wid)
          .then(function (detail) {
            cart.push({
              wine: detail.wine,
              stock: detail.stock,
              quantity: qty
            });
            storage.setItem(key, angular.toJson(cart));
          });
      }
    };

    this.remove = function(stock) {
      $log.info('Clear cart');
      var idx = cart.indexOf(stock);
      if (idx >= 0) {
        cart.splice(idx, 1);
      }
    };

    this.clear = function () {
      $log.info('Clear cart');
      cart.splice(0, cart.length);
      storage.setItem(key, angular.toJson(cart));
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
