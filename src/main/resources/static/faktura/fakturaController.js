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
				'fakturaController',
				[
						'$rootScope',
						'$scope',
						'$location',
						'ngNotify',
						'fakturaService',
						function($rootScope, $scope, $location, ngNotify,
								fakturaService) {

							$rootScope.kupacFirma = {};
							$scope.novoZaglavlje = {};
							$rootScope.sveFakture = {};
							$scope.iskljuci = true;
							$scope.novoZaglavlje.nazivKupca = null;
							$scope.novoZaglavlje.adresaKupca = null;
							$scope.novoZaglavlje.pibKupca = null;

							$scope.preuzmiZaglavlja = function() {
								fakturaService
										.preuzmiZaglavlja()
										.then(
												function(response) {
													if (response.data) {
														$scope.svaZaglavlja = response.data;
													}
												});
							}

							$scope.preuzmiFirme = function() {
								fakturaService
										.preuzmiFirme()
										.then(
												function(response) {
													if (response.data) {
														$rootScope.kupci = response.data;
														$rootScope.kupacFirma = response.data[0];
													}
												});
							}

							$scope.preuzmiStavke = function(id) {
								fakturaService
										.preuzmiStavke(id)
										.then(
												function(response) {
													if (response.data) {
														$rootScope.sveStavke = response.data;
													}
												});
							}

							$scope.preuzmiNaloge = function() {
								fakturaService
										.preuzmiNaloge()
										.then(
												function(response) {
													if (response.data) {
														$rootScope.sviNalozi = response.data;
													}
												});
							}

							$scope.preuzmiPrimljeneFakture = function() {
								fakturaService
										.preuzmiPrimljeneFakture()
										.then(
												function(response) {
													if (response.data) {
														$rootScope.sveFakture = response.data;
													}
												});
							}

							$scope.init = function() {
								$scope.preuzmiZaglavlja();
								$scope.preuzmiFirme();
							}

							$scope.dodajZaglavlje = function() {
								fakturaService
										.dodajZaglavlje($scope.novoZaglavlje)
										.then(
												function(response) {
													if (response.data) {
														ngNotify
																.set(
																		'Uspesno dodavanje zaglavlja',
																		{
																			type : 'success'
																		});
														$scope.svaZaglavlja
																.push(response.data);
														$scope
																.preuzmiZaglavlja();
														$scope.novoZaglavlje = null;
														$scope.show = null;
													}
												});
							}

							$scope.odustani = function() {
								$rootScope.selectedZaglavlje = null;
								$scope.novoZaglavlje = null;
								$scope.show = null;
								refresh();
							}

							function refresh() {
								$scope.preuzmiZaglavlja();
								$scope.novoZaglavlje = null;
								$rootScope.selectedZaglavlje = null;
							}

							$scope.setSelectedZaglavlje = function(selected) {
								$rootScope.selectedZaglavlje = selected;
								$scope.novoZaglavlje = angular.copy(selected);
								if (selected.iznosZaUplatu == 0) {
									$scope.iskljuci = true;
								} else
									$scope.iskljuci = false;
							}

							$scope.setSelectedFaktura = function(selected) {
								$rootScope.selectedFaktura = selected;
							}

							$scope.setSelectedNalog = function(selected) {
								$rootScope.selectedNalog = selected;
							}

							$scope.setSelectedKupac = function(selected) {
								$rootScope.kupacFirma = selected;
								$scope.novoZaglavlje.nazivKupca = selected.naziv;
								$scope.novoZaglavlje.adresaKupca = selected.adresa;
								$scope.novoZaglavlje.pibKupca = selected.pib;
							}

							$scope.dodajStavku = function() {
								fakturaService
										.dodajStavku(
												$rootScope.selectedZaglavlje.id,
												$scope.novaStavka)
										.then(
												function(response) {
													if (response.data) {
														ngNotify
																.set(
																		'Uspesno dodavanje stavke',
																		{
																			type : 'success'
																		});
														$location
																.path('/faktura/fakture');
													}
												});
							}

							$scope.posaljiNaStavku = function() {
								$scope
										.preuzmiStavke($rootScope.selectedZaglavlje.id);
								$location.path('/faktura/stavka');
							}

							$scope.odustaniOdStavke = function() {
								$location.path('/faktura/fakture');
							}

							$scope.kreirajHTML = function() {
								fakturaService
										.kreirajHTML(
												$rootScope.selectedFaktura.id)
										.then(
												function(response) {
													if (response.status == 201)
														$location
																.path('/htmlFaktura');
													else
														ngNotify
																.set(
																		'Bezuspjesno kreiranje html-a',
																		{
																			type : 'info'
																		});
												});
							}

							$scope.kreirajPDF = function() {
								fakturaService
										.kreirajPDF(
												$rootScope.selectedFaktura.id)
										.then(
												function(response) {
													if (response.status == 201) {
														var win = window.open();
														win.location = "C:/Users/FixMe/Documents/8%20semestar/WebIXML/Firma-XML/src/main/resources/static/faktura.pdf";

													} else
														ngNotify
																.set(
																		'Bezuspjesno kreiranje pdf-a',
																		{
																			type : 'info'
																		});

												});
							}

							$scope.kreirajHTMLzaNalog = function() {
								fakturaService
										.kreirajHTMLzaNalog(
												$rootScope.selectedNalog.id)
										.then(
												function(response) {
													if (response.status == 201)
														$location
																.path('/htmlNalog');
													else
														ngNotify
																.set(
																		'Bezuspjesno kreiranje html-a',
																		{
																			type : 'info'
																		});
												});
							}

							$scope.kreirajPDFzaNalog = function() {
								fakturaService
										.kreirajPDFzaNalog(
												$rootScope.selectedNalog.id)
										.then(
												function(response) {
													if (response.status == 201) {
														var win = window.open();
														win.location = "C:/Users/FixMe/Documents/8%20semestar/WebIXML/Firma-XML/src/main/resources/static/nalog.pdf";

													} else
														ngNotify
																.set(
																		'Bezuspjesno kreiranje pdf-a',
																		{
																			type : 'info'
																		});

												});
							}

							$scope.posaljiFakturu = function() {
								fakturaService
										.posaljiFakturu(
												$rootScope.selectedZaglavlje.id)
										.then(
												function(response) {
													if (response.status == 200) {
														ngNotify
																.set(
																		'Uspjesno prosledjena faktura',
																		{
																			type : 'success'
																		});
														$scope.selectedZaglavlje = null;
													} else
														ngNotify
																.set(
																		'Bezuspjesno prosedjena faktura',
																		{
																			type : 'info'
																		});
												});
							}

							$scope.posaljiNalog = function() {
								if ($scope.selectedFaktura.zaglavljeFakture.hitno == undefined)
									$scope.selectedFaktura.zaglavljeFakture.hitno = false;
								fakturaService
										.posaljiNalog(
												$rootScope.selectedFaktura.id,
												$scope.selectedFaktura.zaglavljeFakture.hitno)
										.then(
												function(response) {
													if (response.status == 200) {
														ngNotify
																.set(
																		'Uspjesno prosledjen nalog',
																		{
																			type : 'success'
																		});
														$scope
																.preuzmiPrimljeneFakture();
														$scope.selectedFaktura = null;
													} else
														ngNotify
																.set(
																		'Bezuspjesno prosedjen nalog',
																		{
																			type : 'info'
																		});
												});
							}

						} ]);
