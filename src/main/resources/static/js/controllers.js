var app = angular.module('webApp');

app.run([ 'ngNotify', function(ngNotify) {

	ngNotify.config({
		position : 'bottom',
		duration : 1000,
		theme : 'pitchy',
		sticky : false,
	});
} ]);

app.directive('ngConfirmClick', [ function() {
	return {
		link : function(scope, element, attr) {
			var msg = attr.ngConfirmClick || "Are you sure?";
			var clickAction = attr.confirmedClick;
			element.bind('click', function(event) {
				if (window.confirm(msg)) {
					scope.$eval(clickAction)
				}
			});
		}
	};
} ]);

app.config([ '$qProvider', function($qProvider) {
	$qProvider.errorOnUnhandledRejections(false);
} ]);

app
		.controller(
				'appController',
				[
						'$rootScope',
						'$scope',
						'$location','ngNotify', 
						'SessionService',
						function($rootScope, $scope, $location,ngNotify, sessionService) {
							
							
							$scope.logout = function(){
								sessionService.logout().then(function(response){
									$rootScope.korisnik = '';
									$location.path('/login');
									$scope.logovanje = null;
								});
							}
							
							$scope.submitLogin = function() {
								sessionService
										.logIn($scope.logovanje)
										.then(
												function(response) {
													if (response.status == 200) {
														ngNotify.set('Uspjesno logovanje' , {
															type : 'success'
															});
														$rootScope.korisnik = response.data;
													}
												}).catch(function(response) {
													ngNotify.set('Korisnik ne postoji' , {
														type : 'error',
													    sticky: true
													});
													console.error('Gists error', response.status, response.data)
												  });
							}

						} ]);
