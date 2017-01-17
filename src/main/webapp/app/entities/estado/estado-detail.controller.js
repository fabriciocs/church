(function() {
    'use strict';

    angular
        .module('churchApp')
        .controller('EstadoDetailController', EstadoDetailController);

    EstadoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Estado'];

    function EstadoDetailController($scope, $rootScope, $stateParams, previousState, entity, Estado) {
        var vm = this;

        vm.estado = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('churchApp:estadoUpdate', function(event, result) {
            vm.estado = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
