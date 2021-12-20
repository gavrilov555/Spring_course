angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                $scope.ProductsList = response.data;
            });
    };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.changeCost = function (productId, delta) {
        $http({
            url: contextPath + '/products/change_cost',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    /*$scope.Filters = function () {
            $http({
                url: contextPath + '/products/cost_between',
                method: 'GET',
                params: {
                    min: $scope.min,
                    max: $scope.max
                }
            }).then(function (response) {
                $scope.ProductsList = response.data;
                $scope.min = null;
                $scope.max = null;
            });
        } */


         $scope.loadProducts = function (pageIndex = 1) {
                $http({
                    url: contextPath + '/products',
                    method: 'GET',
                    params: {
                        title_part: $scope.filter ? $scope.filter.title_part : null,
                        min_cost: $scope.filter ? $scope.filter.min_cost : null,
                        max_cost: $scope.filter ? $scope.filter.max_cost : null
                    }
                }).then(function (response) {
                    $scope.ProductsList = response.data.content;
                });
            };


            $scope.changeCount = function (id, count) {
                    $http({
                        url: contextPath + "/carts",
                        method: 'PUT',
                        params: {
                            id: id,
                            count: count
                        }
                    }).then(function (response) {
                        $scope.loadCartService();
                    });
                }
                $scope.addProductToCart = function (id) {
                    $http({
                        url: contextPath + "/carts",
                        method: 'POST',
                        params: {
                            id: id
                        }
                    }).then(function (response) {
                        $scope.loadCartService();
                    });
                }
                $scope.loadCartService = function () {
                    $http.get(contextPath + '/carts')
                        .then(function (response) {
                            $scope.CartList = response.data;
                        });
                };
                $scope.deleteFromCart = function (id) {
                    $http.delete(contextPath + '/carts/' + id)
                        .then(function (response) {
                            $scope.loadCartService();
                        });
                }

    $scope.loadProducts();
    $scope.loadCartService();
});