angular.module('app', []).controller('indexController', function ($scope, $http) {
    $scope.path = 'http://localhost:8189/market/api/v1/products'

    $scope.loadProducts = function () {
        $http.get($scope.path)
            .then(function (response) {
                $scope.productList = response.data;
            });
    };

    $scope.addProduct = function (productId) {
        $http.post($scope.path + "/add/" + productId)
            .then(function (response) {
                $scope.cart = response.data["products"];
            })
    };

    $scope.deleteProductById = function (productId) {
        $http.delete($scope.path + "/" + productId)
            .then(function () {
                $scope.loadProducts();
            })
    };
    $scope.loadProducts();
})