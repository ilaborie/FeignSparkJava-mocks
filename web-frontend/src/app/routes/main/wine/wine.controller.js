(function () {
  'use strict';

  angular
    .module('webFrontend')
    .controller('WineController', WineController);

  /** @ngInject */
  function WineController($stateParams, wineSrv, toastr) {
    var vm = this;

    vm.wid = $stateParams.wid;
    vm.wine = null;
    vm.comment = null;

    wineSrv.findById(vm.wid)
      .then(function (wine) {
        vm.wine = wine;
      })
      .catch(function (error) {
        toastr.error('Oops !', error.data);
      });

    vm.addComment = function () {
      wineSrv.addComment(vm.comment)
        .then(function (comment) {
          vm.comment = null;
          vm.wine.comments.push(comment);
        })
        .catch(function (error) {
          toastr.error('Oops !', error.data);
        });
    };
  }
})();
