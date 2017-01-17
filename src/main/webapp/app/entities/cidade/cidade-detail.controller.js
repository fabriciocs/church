(function() {
    'use strict';

    angular
        .module('churchApp')
        .controller('CidadeDetailController', CidadeDetailController);

    CidadeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Cidade', 'Estado'];

    function CidadeDetailController($scope, $rootScope, $stateParams, previousState, entity, Cidade, Estado) {
        var vm = this;

        vm.cidade = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('churchApp:cidadeUpdate', function(event, result) {
            vm.cidade = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
