(function() {
    'use strict';

    angular
        .module('churchApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('telefone', {
            parent: 'entity',
            url: '/telefone?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'churchApp.telefone.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/telefone/telefones.html',
                    controller: 'TelefoneController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('telefone');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('telefone-detail', {
            parent: 'entity',
            url: '/telefone/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'churchApp.telefone.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/telefone/telefone-detail.html',
                    controller: 'TelefoneDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('telefone');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Telefone', function($stateParams, Telefone) {
                    return Telefone.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'telefone',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('telefone-detail.edit', {
            parent: 'telefone-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/telefone/telefone-dialog.html',
                    controller: 'TelefoneDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Telefone', function(Telefone) {
                            return Telefone.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('telefone.new', {
            parent: 'telefone',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/telefone/telefone-dialog.html',
                    controller: 'TelefoneDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                descricao: null,
                                numero: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('telefone', null, { reload: 'telefone' });
                }, function() {
                    $state.go('telefone');
                });
            }]
        })
        .state('telefone.edit', {
            parent: 'telefone',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/telefone/telefone-dialog.html',
                    controller: 'TelefoneDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Telefone', function(Telefone) {
                            return Telefone.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('telefone', null, { reload: 'telefone' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('telefone.delete', {
            parent: 'telefone',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/telefone/telefone-delete-dialog.html',
                    controller: 'TelefoneDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Telefone', function(Telefone) {
                            return Telefone.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('telefone', null, { reload: 'telefone' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
