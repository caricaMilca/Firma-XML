var app = angular.module('webApp', [ 'ngRoute'  , 'ngNotify']);

// routeProvider
app.config(function($routeProvider, $locationProvider) {

	$routeProvider.when('login', {
		templateUrl : 'index.html',
		controller : 'appController'
	}).when('/faktura/fakture', {
		templateUrl: '/faktura/zaglavlje.html',
		controller: 'fakturaController'
	})
});
