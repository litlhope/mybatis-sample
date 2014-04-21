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
}]);