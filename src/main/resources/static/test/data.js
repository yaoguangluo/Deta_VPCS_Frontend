var am = angular.module("data", ['ngCookies']);
am.controller('data', ['$cookieStore', '$scope', '$http', '$compile', function ($cookieStore, $scope, $http, $compile) {
	$scope.dataWS = function () {
		var askMessage = document.getElementById("data-left-textarea").value;
		$http.post('dataWS?message=' + encodeURIComponent(askMessage))
				.then(function successCallback(response) {
					document.getElementById("data-right-textarea").value 
					= decodeURIComponent(response.data.ws,"UTF-8"); 
				}, function errorCallback(response) {
				});
	}
	
	$scope.dataCX= function () {
		var askMessage = document.getElementById("data-left-textarea").value;
		$http.post('dataCX?message=' + encodeURIComponent(askMessage))
				.then(function successCallback(response) {
					document.getElementById("data-right-textarea").value 
					= decodeURIComponent(response.data.cx,"UTF-8"); 
				}, function errorCallback(response) {
				});
	}
	
	$scope.dataCY = function () {
		var askMessage = document.getElementById("data-left-textarea").value;
		$http.post('dataCY?message=' + encodeURIComponent(askMessage))
				.then(function successCallback(response) {
					document.getElementById("data-right-textarea").value 
					= decodeURIComponent(response.data.cy,"UTF-8"); 
				}, function errorCallback(response) {
				});
	}
	
	$scope.dataCG = function () {
		var askMessage = document.getElementById("data-left-textarea").value;
		$http.post('dataCG?message=' + encodeURIComponent(askMessage))
				.then(function successCallback(response) {
					document.getElementById("data-right-textarea").value
					= decodeURIComponent(response.data.cg,"UTF-8"); 
				}, function errorCallback(response) {
				});
	}
	$scope.dataCJ = function () {
		var askMessage = document.getElementById("data-left-textarea").value;
		$http.post('dataCJ?message=' + encodeURIComponent(askMessage))
				.then(function successCallback(response) {
					document.getElementById("data-right-textarea").value 
					= decodeURIComponent(response.data.cj,"UTF-8");
				}, function errorCallback(response) {
				});
	}
	$scope.dataCL = function () {
		var askMessage = document.getElementById("data-left-textarea").value;
		$http.post('dataCL?message=' + encodeURIComponent(askMessage))
				.then(function successCallback(response) {
					document.getElementById("data-right-textarea").value 
					= decodeURIComponent(response.data.cl,"UTF-8"); 
				}, function errorCallback(response) {
				});
	}
}]);