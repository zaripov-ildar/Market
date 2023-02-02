var app = angular.module('app', ['ngRoute']);

app.config(function ($routProvider))

var main = angular.module('main', []).controller('indexController', function ($scope, $http) {
    $scope.path = 'http://localhost:8189/market/api/v1'

    $scope.loadProducts = function () {
        $http.get($scope.path + '/products')
            .then(function (response) {
                $scope.productList = response.data;
            });
    };
    $scope.deleteProductById = function (productId) {
        $http.delete($scope.path + "/products/" + productId)
            .then(function () {
                $scope.loadProducts();
            })
    };

    $scope.loadCart = function () {
        $http.get($scope.path + '/cart')
            .then(function (response) {
                $scope.cart = response.data;
                console.log(response.data);
            })
    }

    $scope.changeAmount = function (productId, delta) {
        $http({
            url: $scope.path + "/cart/changeAmount",
            method: "GET",
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function () {
            $scope.loadCart();
        })
    };
    $scope.deleteFromCartById = function (productId) {
        $http.get($scope.path + "/cart/delete/" + productId)
            .then(function () {
                $scope.loadCart();
            })
    };
    $scope.clearCart = function () {
        $http.get($scope.path + "/cart/clear")
            .then(function () {
                $scope.loadCart();
            })
    };
    $scope.makeOrder = function () {
        $http.get($scope.path + "/cart/order")
            .then(function (response) {
                const order = response.data;
                alert("You've made the  order #" + order.id);
            })
    }
    $scope.loadProducts();
    $scope.loadCart()
})