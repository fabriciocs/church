(function() {
    'use strict';

    angular
        .module('churchApp')
        .controller('InstituicaoDeleteController',InstituicaoDeleteController);

    InstituicaoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Instituicao'];

    function InstituicaoDeleteController($uibModalInstance, entity, Instituicao) {
        var vm = this;

        vm.instituicao = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Instituicao.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
