(function() {
    'use strict';

    angular
        .module('churchApp')
        .controller('InstituicaoDetailController', InstituicaoDetailController);

    InstituicaoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Instituicao', 'Endereco', 'Telefone'];

    function InstituicaoDetailController($scope, $rootScope, $stateParams, previousState, entity, Instituicao, Endereco, Telefone) {
        var vm = this;

        vm.instituicao = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('churchApp:instituicaoUpdate', function(event, result) {
            vm.instituicao = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
