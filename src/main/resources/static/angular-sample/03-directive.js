/**
 * Created by litlhope on 2014. 4. 20..
 */
var myApp = angular.module("myApp", []);

myApp.directive("customButton", function() {
	return {
		// A: 속성, E: 요소, C: 클래스, M: 주석 기본값 EA이고, 중복으로 걸 수 있음. 하단 참조
		restrict: "A",
		// DOM의 마크업을 변경할지 여부 true이면 치환이 돼고, false이면 하위 요소로 추가된다.
		replace: true,
		// DOM의 요소(예제에서는 "Click me" 텍스트 요소)를 변환된 디렉티브 안쪽에 추가(include) 할 것인지 여부.
		transclude: true,
		// templateUrl: "templates/customButton.html" 과같이 URL로 지정 할 수도 있다.
		// 비교적 템플릿이 복작할 경우 URL로 지정하면 좋을 듯.
		template: '<a href="" class="myawesomebutton" ng-transclude>' +
			'<i class="icon-ok-sign"></i>' +
			'</a> ',
		link: function(scope, element, attrs) {
			// DOM 및 이벤트 처
//			console.log(scope);
//			console.log(element);
//			console.log(attrs);
		}
	};
});

/*
위 restrict의 AECM의 의미는 아래와 같은 형태를 지원하는지 여부를 지정하는 것이다.
 <!-- 1: 속성으로 정의 -->
 <a custom-button>Click me</a>

 <!-- 2: 요소로 정의 -->
 <custom-button>Click me</custom-button>

 <!-- 3: 클래스로 정의(IE 구버전 호환을 위해) -->
 <a class="custom-button">Click me</a>

 <!-- 4: 주석으로 정의 (데모로는 별로 안좋긴 하다) -->
 <!-- directive: custom-button -->
 */