angular.module('market-front').controller('welcomeController', function ($scope, $http) {
    const contextPath = 'http://localhost:5555/recommendation/';


        $scope.loadProducts = function () {
            $http({
                url: contextPath + 'api/v1/products/purchased',
                method: 'GET'
            }).then(function (response) {
                $scope.frequentlyPurchasedProducts = response.data;
            });
        };
        $scope.loadCartProducts = function () {
            $http({
                url: contextPath + 'api/v1/products/folding',
                method: 'GET'
            }).then(function (response) {
                $scope.frequentlyFoldingProducts = response.data;
            });
        };


        $scope.loadProducts();
        $scope.loadCartProducts();

    });