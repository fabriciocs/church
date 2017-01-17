'use strict';

describe('Controller Tests', function() {

    describe('Instituicao Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockInstituicao, MockEndereco, MockTelefone;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockInstituicao = jasmine.createSpy('MockInstituicao');
            MockEndereco = jasmine.createSpy('MockEndereco');
            MockTelefone = jasmine.createSpy('MockTelefone');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Instituicao': MockInstituicao,
                'Endereco': MockEndereco,
                'Telefone': MockTelefone
            };
            createController = function() {
                $injector.get('$controller')("InstituicaoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'churchApp:instituicaoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
