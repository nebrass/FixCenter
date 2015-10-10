'use strict';

var services = angular.module('NetBeans.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.factory('CustomersFactory', function ($resource) {
    return $resource(baseUrl + '/FixCenter/rest/customers', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    })
});

services.factory('CustomerFactory', function ($resource) {
    return $resource(baseUrl + '/FixCenter/rest/customers/:id', {}, {
        show: { method: 'GET' },
        update: { method: 'PUT', params: {id: '@id'} },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});
