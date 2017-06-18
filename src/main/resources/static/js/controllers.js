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
							
							$rootScope.kojiKlijenti = 'svi';
							$rootScope.nextFormDjelatnost = null;
							$rootScope.kojiRacuni = 'svi';
							$rootScope.nextFormKlijent = null;
							
							sessionService
									.preuzmiKlijenta()
									.then(
											function(
													response) {
												if (response.status == 200) {
													$rootScope.korisnik = response.data;
												}
											});
							sessionService
							.preuzmiZaposlenog()
							.then(
									function(
											response) {
										if (response.status == 200) {
											$rootScope.korisnik = response.data;
											if(response.data.ulogaZ == 'Administrator')
												$location.path('/Admin/registracijaSalteruse');
										}
									});
							
							if($rootScope.korisnik == undefined)
								$location.path('/index');
							
								$scope.changePassword = function() {
								if($scope.lozinka.stara == $rootScope.korisnik.lozinka){
									sessionService.changePassword($scope.lozinka.nova).then(function(response){
										$location.path('/index');
										ngNotify.set('Uspjesno promjenjena lozinka' , {
											type : 'success'
										});
									});
								} else {
									lozinka.nova = '';
									lozinka.stara = '';
									$location.path('/changePassword')
								}
							}
							
								$scope.regSalterusu = function() {
									sessionService
											.regSalterusu($scope.novi)
											.then(
													function(response) {
														$location
																.path('/Admin/registracijaSalteruse');
														ngNotify
																.set(
																		'Uspjesna registracija',
																		{
																			type : 'success'
																		});
														$scope.novi = null;
													});
								}
								
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
													if (response.status == 200)
														if (response.data.uloga === "Klijent")
															sessionService
																	.preuzmiKlijenta()
																	.then(
																			function(
																					response) {
																				if (response.status == 200) {
																					ngNotify.set('Uspjesno logovanje' , {
																						type : 'success'
																					});
																					$rootScope.korisnik = response.data;
																				}
																			});
														else
															sessionService
																	.preuzmiZaposlenog()
																	.then(
																			function(
																					response) {
																				if (response.status == 200) {
																					ngNotify.set('Uspjesno logovanje' , {
																						type : 'success'
																					});
																					$rootScope.korisnik = response.data;
																					if(response.data.ulogaZ == 'Administrator')
																						$location.path('/Admin/registracijaSalteruse');
																					
																				}
																			});

												}).catch(function(response) {
													ngNotify.set('Korisnik ne postoji' , {
														type : 'error',
													    sticky: true
													});
													console.error('Gists error', response.status, response.data)
												  });
							}
							
							
							$scope.preuzmiKlijente = function() {
								$location.path('/Klijent/klijenti');
							}
						} ]);
