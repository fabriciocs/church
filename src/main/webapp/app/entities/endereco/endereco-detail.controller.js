(function() {
    'use strict';

    angular
        .module('churchApp')
        .controller('EnderecoDetailController', EnderecoDetailController);

    EnderecoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Endereco', 'Cidade', 'Telefone'];

    function EnderecoDetailController($scope, $rootScope, $stateParams, previousState, entity, Endereco, Cidade, Telefone) {
        var vm = this;

        vm.endereco = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('churchApp:enderecoUpdate', function(event, result) {
            vm.endereco = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
