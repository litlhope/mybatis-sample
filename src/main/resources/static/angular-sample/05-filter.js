/**
 * Created by litlhope on 2014. 4. 20..
 */
var myApp = angular.module("myApp", []);

myApp.filter("reverse", function() {
	return function(input, uppercase) {
		var out = "";
		for (var inx = 0; inx < input.length; inx++) {
			out = input.charAt(inx) + out;
		}

		if (uppercase) {
			out = out.toUpperCase();
		}

		return out;
	}
});

myApp.controller("FilterCtrl", ["$scope", function($scope) {
	$scope.greeting = "Todd Motto";

	$scope.numbers = [1, 5, 3, 20, 100, 63, 25, 97, 28];
	$scope.lowerBound = 42;

	// 컨트롤러 내부의 필터?
	$scope.greaterThanNum = function(item) {
		return item > $scope.lowerBound;
	}
}]);