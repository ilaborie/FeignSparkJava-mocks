(function () {
  'use strict';

  angular
    .module('webFrontend')
    .config(config);

  /** @ngInject */
  function config(config, $logProvider, toastrConfig, $authProvider) {
    // Enable log
    $logProvider.debugEnabled(true);

    // Set options third-party lib
    toastrConfig.allowHtml = true;
    toastrConfig.timeOut = 3000;
    toastrConfig.positionClass = 'toast-top-right';
    toastrConfig.preventDuplicates = true;
    toastrConfig.progressBar = true;

    // Auth
    $authProvider.loginUrl = config.loginUrl + 'api/auth/login';
  }

})();
