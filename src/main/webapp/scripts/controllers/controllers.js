'use strict';

/* Controllers */

var app = angular.module('NetBeans.controllers', ["ngTable", 'ui.bootstrap']);


// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
app.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

app.controller('CustomerListCtrl', ['$rootScope', '$scope', 'CustomersFactory', 'CustomerFactory', '$location', 'ngTableParams',
    function ($rootScope, $scope, CustomersFactory, CustomerFactory, $location, ngTableParams) {

        /* callback for ng-click 'editCustomer': */
        $scope.editCustomer = function (customerId) {
            $location.path('/customer-detail/' + customerId);
        };

        /* callback for ng-click 'deleteCustomer': */
        $scope.deleteCustomer = function (customerId) {
            CustomerFactory.delete({id: customerId});
            $scope.customers = CustomersFactory.query();

        };

        /* callback for ng-click 'createCustomer': */
        $scope.createNewCustomer = function () {
            $location.path('/customer-creation');
        };

        $rootScope.customers = CustomersFactory.query();
        $rootScope.customerList = new ngTableParams({
            page: 1, // show first page
            total: $scope.customers.length, // length of data
            count: 10           // count per page
        });
    }]);

app.controller('CustomerDetailCtrl', ['$scope', '$routeParams', 'CustomerFactory', '$location',
    function ($scope, $routeParams, CustomerFactory, $location) {

        /* callback for ng-click 'updateCustomer': */
        $scope.updateCustomer = function () {
            CustomerFactory.update($scope.customer);
            $location.path('/customer-list');
        };

        /* callback for ng-click 'cancel': */
        $scope.cancel = function () {
            $location.path('/customer-list');
        };

        $scope.customer = CustomerFactory.show({id: $routeParams.id});
    }]);

app.controller('CustomerCreationCtrl', ['$rootScope', '$scope', '$filter', 'CustomersFactory',
    function ($rootScope, $scope, $filter, CustomersFactory) {

        $scope.today = function () {
            $scope.dt = new Date();
        };
        $scope.today();

        $scope.getDayClass = function (date, mode) {
            if (mode === 'day') {
                var dayToCheck = new Date(date).setHours(0, 0, 0, 0);

                for (var i = 0; i < $scope.events.length; i++) {
                    var currentDay = new Date($scope.events[i].date).setHours(0, 0, 0, 0);

                    if (dayToCheck === currentDay) {
                        return $scope.events[i].status;
                    }
                }
            }

            return '';
        };

        /* callback for ng-click 'createNewCustomer': */
        $scope.createNewCustomer = function () {
            console.log($scope.customer.birthDate);
            //$scope.customer.birthDate = $filter('date')($scope.customer.birthDate, "yyyy-MM-ddTHH:mm:sss")

            CustomersFactory.create($scope.customer);
            $('#myModal').modal('hide');
            console.log($scope.customer.birthDate);
            $rootScope.customers = CustomersFactory.query();
            $rootScope.$apply();
        }
    }]);
