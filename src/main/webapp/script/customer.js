var app = angular.module('customers', []);

app.controller('customersCtrl', function ($scope, $http, $filter) {

    $scope.helloText = 'Welcome to NetBeans Day Paris';
    $scope.restURL = '/rest/customers';
    $scope.customers;
    $scope.customer;

    $scope.getAllCustomers = function () {
        $http({
            url: $scope.restURL,
            method: 'GET'
        }).success(function (data) {
            $scope.customers = data;
        }).error(function (data) {
            $scope.error();
        });
    };

    $('.form_date').datepicker({
        format: "dd/mm/yyyy"
    }).on('changeDate', function (ev) {
        console.log(ev.date.valueOf());
    });

    $scope.getAllCustomers();

    $scope.remove = function (id) {
        $http.delete($scope.restURL + "/" + id);

        var array = $scope.customers;
        for (var i = array.length - 1; i >= 0; i--) {
            if (array[i].id == id) {
                array.splice(i, 1);
            }
        }

    };

    $scope.create = function () {
        $scope.customer.birthDate = new Date($scope.customer.birthDate);

        $http.post($scope.restURL, $scope.customer, {'Content-Type': 'application/json'}).
                then($scope.success('#createModal'), $scope.error());

    };

    $scope.success = function (create) {
        var array = $scope.customers;
        for (var i = array.length - 1; i >= 0; i--) {
            if (array[i].id == $scope.customer.id && create == '#editModal') {
                array.splice(i, 1);
                console.log('Removed item : ' + array[i]);
            }
        }
        array.push($scope.customer);
        array.sort(function (a, b) {
            return a.id - b.id
        });
        $(create).modal('hide');
        $scope.customer = null;
    };

    $scope.error = function () {
        console.log('ERRROOOORRRR');
    };

    $scope.setEdited = function (cust) {
        var array = $scope.customers;
        var tmp = {
            id: cust.id,
            name: '',
            familyName: '',
            description: ''
        };

        for (var i = array.length - 1; i >= 0; i--) {
            if (array[i].id == cust.id) {
                tmp.name = array[i].name;
                tmp.familyName = array[i].familyName;
                tmp.description = array[i].description;
                tmp.birthDate = $filter('date')(array[i].birthDate, "dd/MM/yyyy");
            }
        }
        $scope.customer = tmp;
        $('#editModal').modal('show');
    };

    $scope.edit = function () {
        $http.put($scope.restURL + '/' + $scope.customer.id,
                $scope.customer).
                then($scope.success('#editModal'), $scope.error());
    };

    $scope.editv2 = function () {
        $http.put($scope.restURL + '/' + $scope.customer.id,
                $scope.customer).success(function () {
            $scope.success('#editModal');
        }).error(function () {
            $scope.error();
        });
    };



});