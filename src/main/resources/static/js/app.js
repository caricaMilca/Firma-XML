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
<<<<<<< HEAD
	}).when('/htmlFaktura', {
		templateUrl : '/faktura.html'
	}).when('/pdfFaktura', {
		templateUrl : '/faktura.pdf'
=======
	}).when('/zahtev/presek', {
		templateUrl : '/zahtev/presek.html',
		controller : 'zahtevController'
>>>>>>> 7b0de61a7b96005824e60e0aee49d707616fb1b2
	})
});
