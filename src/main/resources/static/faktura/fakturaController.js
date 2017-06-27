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

app.controller('fakturaController', [
		'$rootScope',
		'$scope',
		'$location',
		'ngNotify',
		'fakturaService',
		function($rootScope, $scope, $location, ngNotify, fakturaService) {

			$scope.novoZaglavlje = {};
			$rootScope.sveFakture = {};
			$scope.iskljuci = true;
			
			$scope.preuzmiZaglavlja = function() {
				fakturaService.preuzmiZaglavlja().then(function(response) {
					if (response.data) {
						$scope.svaZaglavlja = response.data;
					}
				});
			}
			
			$scope.preuzmiStavke = function(id) {
				fakturaService.preuzmiStavke(id).then(function(response) {
					if (response.data) {
						$rootScope.sveStavke = response.data;
					}
				});
			}
			
			$scope.preuzmiPrimljeneFakture = function() {
				fakturaService.preuzmiPrimljeneFakture().then(function(response) {
					if (response.data) {
						$rootScope.sveFakture = response.data;
					}
				});
			}

			$scope.init = function() {
				$scope.preuzmiZaglavlja();
			}

			$scope.dodajZaglavlje = function() {
				fakturaService.dodajZaglavlje($scope.novoZaglavlje).then(
						function(response) {
							if (response.data) {
								ngNotify.set('Uspesno dodavanje zaglavlja', {
									type : 'success'
								});
								$scope.svaZaglavlja.push(response.data);
								$scope.preuzmiZaglavlja();
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
				if(selected.iznosZaUplatu == 0){
					$scope.iskljuci = true;
				} else
					$scope.iskljuci = false;
			}
			
			$scope.setSelectedFaktura = function(selected) {
				$rootScope.selectedFaktura = selected;
			}

			$scope.dodajStavku = function() {
				fakturaService.dodajStavku($rootScope.selectedZaglavlje.id,
						$scope.novaStavka).then(function(response) {
					if (response.data) {
						ngNotify.set('Uspesno dodavanje stavke', {
							type : 'success'
						});
						$location.path('/faktura/fakture');
					}
				});
			}

			$scope.posaljiNaStavku = function() {
				$scope.preuzmiStavke($rootScope.selectedZaglavlje.id);
				$location.path('/faktura/stavka');
			}

			$scope.odustaniOdStavke = function() {
				$location.path('/faktura/fakture');
			}

			$scope.kreirajHTML = function() {
				fakturaService.kreirajHTML($rootScope.selectedZaglavlje.id)
						.then(function(response) {
						});
			}

			$scope.kreirajPDF = function() {
				fakturaService.kreirajPDF($rootScope.selectedZaglavlje.id)
						.then(function(response) {
						});
			}
			
			$scope.posaljiFakturu = function() {
				fakturaService.posaljiFakturu($rootScope.selectedZaglavlje.id)
						.then(function(response) {
						});
			}
			
			$scope.posaljiNalog = function() {
				if($scope.selectedFaktura.zaglavljeFakture.hitno == undefined)
					$scope.selectedFaktura.zaglavljeFakture.hitno = false;
				fakturaService.posaljiNalog($rootScope.selectedFaktura.id, $scope.selectedFaktura.zaglavljeFakture.hitno)
						.then(function(response) {
						});
			}

		} ]);
