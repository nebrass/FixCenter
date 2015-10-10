'use strict';

var app = angular.module('NetBeans', [
    'NetBeans.services',
    'NetBeans.controllers'
]);

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
]);