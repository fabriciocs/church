(function() {
    'use strict';

    angular
        .module('churchApp')
        .controller('TelefoneDialogController', TelefoneDialogController);

    TelefoneDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Telefone'];

    function TelefoneDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Telefone) {
        var vm = this;

        vm.telefone = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.telefone.id !== null) {
                Telefone.update(vm.telefone, onSaveSuccess, onSaveError);
            } else {
                Telefone.save(vm.telefone, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('churchApp:telefoneUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
