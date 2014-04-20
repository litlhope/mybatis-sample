/**
 * Created by litlhope on 2014. 4. 20..
 */
var myApp = angular.module("myApp", []);

myApp.directive("customButton", function() {
	return {
		restrict: "A",
		replace: true,
		transclude: true,
		template: '<a href="" class="myawesomebutton" ng-transclude>' +
			'<i class="icon-ok-sign"></i>' +
			'</a> ',
		link: function(scope, element, attrs) {
			// DOM 및 이벤트 처리
//			console.log(scope);
//			console.log(element);
//			console.log(attrs);
		}
	};
});