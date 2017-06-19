var app = angular.module('webApp');

app.factory('fakturaService', function fakturaService($http) {

	fakturaService.preuzmiZaglavlja = function() {
		return $http.get("/zaglavljeFakture/svaZaglavljaFakture");
	}

	fakturaService.dodajZaglavlje = function(zaglavljeFakture) {
		return $http.post("/zaglavljeFakture/registracijaZaglavljaFakture", zaglavljeFakture);
	}
	
	fakturaService.dodajStavku = function(zaglavljeId, stavka) {
		return $http.post("/faktura/registracijaFakture/" + zaglavljeId, stavka);
	}
	
	return fakturaService;

});