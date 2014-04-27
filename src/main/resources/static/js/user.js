/**
 * Created by litlhope on 2014. 4. 21..
 */
var myApp = angular.module("myApp", ["ngRoute"]);

myApp.controller("MainCtrl", ["$scope", "$http", function($scope, $http) {

}]);

myApp.controller("ListCtrl", ["$scope", "$http", "$location", "$route",
		function($scope, $http, $location, $route) {
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
		$location.path("/user/add").replace();
	}

	$scope.delete = function(id) {
//		$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
		$http.delete("/user/" + id).success(function(data, status, headers, config) {
//			$location.path("/user/list").replace();
			$route.reload();
		}).error(function(data, status, headers, config) {
			alert("error");
		});
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
		var requestFunc = id ? $http.put : $http.post;

		$http.defaults.headers.post["Content-Type"] = "application/json; charset=UTF-8";
		requestFunc("/user", $scope.user).success(function(data, status, headers, config) {
			$location.path("/user/list").replace();
		}).error(function(data, status, headers, config) {
			alert("error");
		});
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
	/*
	 * html5Mode를 사용하면 URL을 위의 when의 URL로 변경한다.
	 * 이경우 브라우저의 새로고침 버튼을 누를 경우 404 Page Not Found. 가 발생한다.
	 *
	 * false로 하면 URL이
	 * /user/main.html#/user/list 와 같은 형태로 작동된다.
	 * 고객쪽에서는 보기 싫어 할수도... 다른 방법이 없는지 찾아봐야 할 듯 하다.
	 */
	$locationProvider.html5Mode(false);
}]);
