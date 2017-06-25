var app = angular.module('webApp');

app.factory('fakturaService', function fakturaService($http) {

	fakturaService.preuzmiZaglavlja = function() {
		return $http.get("/zaglavljeFakture/svaZaglavljaFakture");
	}

	fakturaService.preuzmiStavke = function(zaglavljeId) {
		return $http.get("/stavkaFaktura/sveStavkeFakture/" + zaglavljeId);
	}

	fakturaService.preuzmiPrimljeneFakture = function() {
		return $http.get("/faktura/ulazneFakture");
	}
	
	fakturaService.dodajZaglavlje = function(zaglavljeFakture) {
		return $http.post("/zaglavljeFakture/registracijaZaglavljaFakture",
				zaglavljeFakture);
	}

	fakturaService.dodajStavku = function(zaglavljeId, stavka) {
		return $http
				.post("/faktura/registracijaFakture/" + zaglavljeId, stavka);
	}

	fakturaService.kreirajHTML = function(zaglavljeId) {
		return $http.get("/faktura/kreirajHTMLFakture/" + zaglavljeId);
	}

	fakturaService.kreirajPDF = function(zaglavljeId) {
		return $http.get("/faktura/kreirajPDFFakture/" + zaglavljeId);
	}

	fakturaService.posaljiFakturu = function(zaglavljeId) {
		return $http.post("/faktura/slanjeFakture/" + zaglavljeId);
	}
	
	fakturaService.posaljiNalog = function(zaglavljeId, hitno) {
		return $http.get("/nalog/posaljiNalog/" + zaglavljeId + "/" + hitno);
	}
	
	return fakturaService;

});