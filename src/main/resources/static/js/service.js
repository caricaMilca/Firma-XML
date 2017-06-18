var app = angular.module('webApp');

app.factory('SessionService', function sessionService($http) {
	sessionService.logIn = function(logovanje) {
		return $http.get("/korisnik/logovanje/"+logovanje.korisnickoIme+"/"+logovanje.lozinka);
	}

	sessionService.changePassword = function(lozinka){
		return $http.put("korisnik/promenaLozinke/"+lozinka);
	}
	
	sessionService.logout = function(){
		return $http.get("/korisnik/logout");
	}
	
	sessionService.preuzmiKlijenta = function() {
		return $http.get("/klijent/preuzmiKlijenta/");
	}
	
	sessionService.preuzmiZaposlenog = function() {
		return $http.get("/zaposleni/preuzmiZaposlenog/");
	}
	
	sessionService.regSalterusu = function(salterusa){
		return $http.post("/zaposleni/registracijaSalteruse", salterusa);
	}
	
	return sessionService;
	
});