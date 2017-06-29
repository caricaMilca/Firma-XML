var app = angular.module('webApp', [ 'ngRoute', 'ngNotify' ]);

// routeProvider
app.config(function($routeProvider, $locationProvider) {

	$routeProvider.when('login', {
		templateUrl : 'index.html',
		controller : 'appController'
	}).when('/faktura/fakture', {
		templateUrl : '/faktura/zaglavlje.html',
		controller : 'fakturaController'
	}).when('/faktura/stavka', {
		templateUrl : '/faktura/novaStavka.html',
		controller : 'fakturaController'
	}).when('/faktura/primljeneFakture', {
		templateUrl : '/faktura/primljeneFakture.html',
		controller : 'fakturaController'
	}).when('/zahtev/zahtevi', {
		templateUrl : '/zahtev/noviZahtev.html',
		controller : 'zahtevController'
	}).when('/htmlFaktura', {
		templateUrl : '/faktura.html'
	}).when('/pdfFaktura', {
		templateUrl : '/faktura.pdf'
	})
});
