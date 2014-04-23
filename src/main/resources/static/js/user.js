/**
 * Created by litlhope on 2014. 4. 21..
 */
var myApp = angular.module("myApp", ["ngRoute"]);

myApp.controller("MainCtrl", ["$scope", "$http", function($scope, $http) {

}]);

myApp.controller("ListCtrl", ["$scope", "$http", "$location", function($scope, $http, $location) {
	// 사용자 데이터를 가져온다.
	$http({
		method: "GET",
		url: "/user"
	})
	.success(function(data, status, headers, config) {
		$scope.users = data;
	})
	.error(function(data, status, headers, config) {

	});

	$scope.createUser = function() {
		$location.path("/user/add");
	}
}]);

myApp.controller("FormCtrl", ["$scope", "$http", "$routeParams", "$location",
		function($scope, $http, $routeParams, $location) {
	$scope.params = $routeParams;
	if ($scope.params.id) {
		$http({
			method: "GET",
			url: "/user/" + $scope.params.id
		})
			.success(function(data, status, headers, config) {
				$scope.user = data;
			})
			.error(function(data, status, headers, config) {

			});
	}

	$scope.save = function(id) {
		console.log($scope.user);
		if (id) {
			$http.put("/user", $scope.user);
		} else {
//			$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
			$http.defaults.headers.post["Content-Type"] = "application/json; charset=UTF-8";
			$http.post("/user", $scope.user)
				.success(function(data, status, headers, config) {
					$location.path("/user/list");
				})
				.error(function(data, status, headers, config) {
					alert("error");
				});
		}
	}
}]);

myApp.config(["$routeProvider", "$locationProvider", function($routeProvider, $locationProvider) {
	$routeProvider
		.when("/user/list", {
			templateUrl: "/user/list.html",
			controller: "ListCtrl"
		})
		.when("/user/modify/:id", {
			templateUrl: "/user/form.html",
			controller: "FormCtrl"
		})
		.when("/user/add", {
			templateUrl: "/user/form.html",
			controller: "FormCtrl"
		})
		.otherwise({
			redirectTo: "/user/list"
		});
	$locationProvider.html5Mode(true);
}]);
