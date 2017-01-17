'use strict';

describe('Controller Tests', function() {

    describe('Endereco Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockEndereco, MockCidade, MockTelefone;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockEndereco = jasmine.createSpy('MockEndereco');
            MockCidade = jasmine.createSpy('MockCidade');
            MockTelefone = jasmine.createSpy('MockTelefone');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Endereco': MockEndereco,
                'Cidade': MockCidade,
                'Telefone': MockTelefone
            };
            createController = function() {
                $injector.get('$controller')("EnderecoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'churchApp:enderecoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
