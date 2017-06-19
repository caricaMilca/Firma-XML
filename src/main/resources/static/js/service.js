var app = angular.module('webApp');

app.factory('SessionService', function sessionService($http) {

	sessionService.logIn = function(logovanje) {
		return $http.get("/firma/login/" + logovanje.port + "/"
				+ logovanje.lozinka);
	}

	sessionService.logout = function() {
		return $http.get("/firma/logout");
	}

	return sessionService;

});