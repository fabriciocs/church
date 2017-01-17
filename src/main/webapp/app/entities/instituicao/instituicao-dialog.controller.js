(function() {
    'use strict';

    angular
        .module('churchApp')
        .controller('InstituicaoDialogController', InstituicaoDialogController);

    InstituicaoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Instituicao', 'Endereco', 'Telefone'];

    function InstituicaoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Instituicao, Endereco, Telefone) {
        var vm = this;

        vm.instituicao = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.enderecos = Endereco.query();
        vm.telefones = Telefone.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.instituicao.id !== null) {
                Instituicao.update(vm.instituicao, onSaveSuccess, onSaveError);
            } else {
                Instituicao.save(vm.instituicao, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('churchApp:instituicaoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dataCadastro = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
