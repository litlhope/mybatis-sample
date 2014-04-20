/**
 * Created by litlhope on 2014. 4. 20..
 */
var myApp = angular.module("myApp", []);

// 컨트롤러
myApp.controller("MainCtrl", ["$scope", function($scope) {
	$scope.text = "Hello, Angular fanatic";
}])