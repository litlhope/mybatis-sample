/**
 * Created by litlhope on 2014. 4. 20..
 */
var myApp = angular.module("myApp", []);

myApp.controller("MainCtrl", ["$scope", "$http", function($scope, $http) {
	// 필터링 할 입력폼을 연결한다.
	$scope.searchName = "";

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

	$scope.search = function(user) {
		if ($scope.searchName === "") {
			return true;
		} else {
			return user.userName.indexOf($scope.searchName) > -1;
		}
	}
}]);