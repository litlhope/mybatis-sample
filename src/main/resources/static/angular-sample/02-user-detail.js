/**
 * Created by litlhope on 2014. 4. 20..
 */
var myApp = angular.module("myApp", []);

myApp.controller("UserCtrl", ["$scope", function($scope) {
	$scope.user = {};
	$scope.user.details = {
		"user_name": "Seong-il We",
		"id": 89101112
	};
}]);