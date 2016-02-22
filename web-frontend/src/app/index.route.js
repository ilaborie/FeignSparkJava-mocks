(function () {
  'use strict';

  angular
    .module('webFrontend')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
    // Login
      .state('login', {
        url: '/login',
        templateUrl: 'app/routes/login/login.html'
      })
      // Main
      .state('main', {
        url: '/main',
        abstract: true,
        templateUrl: 'app/routes/main/main.html'
      })
      // Cellar
      .state('cellar', {
        url: '/cellar',
        parent: 'main',
        views: {
          'body@main': {templateUrl: 'app/routes/main/cellar/cellar.html'}
        }
      })
      // Catalog List
      .state('catalog', {
        url: '/catalog',
        parent: 'main',
        views: {
          'body@main': {templateUrl: 'app/routes/main/catalog/catalog.list.html'}
        }
      })
      // Wine
      .state('wine', {
        url: '/wine/:wid',
        parent: 'main',
        views: {
          'body@main': {templateUrl: 'app/routes/main/wine/wine.html'}
        }
      })
      // Cart
      .state('cart', {
        url: '/cart',
        parent: 'main',
        views: {
          'body@main': {templateUrl: 'app/routes/main/cart/cart.html'}
        }
      })
      // End
    ;

    $urlRouterProvider.otherwise('/login');
  }

})();
