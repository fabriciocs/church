(function() {
    'use strict';
    angular
        .module('churchApp')
        .factory('Instituicao', Instituicao);

    Instituicao.$inject = ['$resource', 'DateUtils'];

    function Instituicao ($resource, DateUtils) {
        var resourceUrl =  'api/instituicaos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dataCadastro = DateUtils.convertLocalDateFromServer(data.dataCadastro);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dataCadastro = DateUtils.convertLocalDateToServer(copy.dataCadastro);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dataCadastro = DateUtils.convertLocalDateToServer(copy.dataCadastro);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
