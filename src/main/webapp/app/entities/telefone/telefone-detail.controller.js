(function() {
    'use strict';

    angular
        .module('churchApp')
        .controller('TelefoneDetailController', TelefoneDetailController);

    TelefoneDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Telefone'];

    function TelefoneDetailController($scope, $rootScope, $stateParams, previousState, entity, Telefone) {
        var vm = this;

        vm.telefone = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('churchApp:telefoneUpdate', function(event, result) {
            vm.telefone = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
