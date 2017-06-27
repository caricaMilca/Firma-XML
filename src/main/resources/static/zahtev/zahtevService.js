var app = angular.module('webApp');

app.factory('zahtevService', function zahtevService($http) {

	
	zahtevService.posaljiZahtev = function(zahtev) {
		return $http.post("/zahtevi/posaljiZahtev",
				zahtev);
	}

	
	
	return zahtevService;

});