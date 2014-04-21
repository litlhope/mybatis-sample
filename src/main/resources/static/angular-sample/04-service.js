/**
 * Created by litlhope on 2014. 4. 20..
 */
var myApp = angular.module("myApp", []);

myApp.service("Math", function() {
	this.multiply = function(x, y) {
		return x * y;
	}
});

myApp.controller("ServiceCtrl", ["$scope", "Math", function($scope, Math) {
	$scope.x = 12;
	$scope.y = 24;

	$scope.multiplyResult = Math.multiply($scope.x, $scope.y);
}]);