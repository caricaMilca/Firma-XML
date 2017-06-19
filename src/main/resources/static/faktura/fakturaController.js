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

			$scope.zaglavlje = true;
			

			$scope.prikazi = function() {
				$scope.zaglavlje = false;
			}

			$scope.preuzmiZaglavlja = function() {
				fakturaService.preuzmiZaglavlja().then(function(response) {
					if (response.data) {
						$scope.svaZaglavlja = response.data;
					}
				});
			}

			function preuzmiZaglavlja() {
				$scope.preuzmiZaglavlja();
			}

			$scope.dodajZaglavlje = function() {
				fakturaService.dodajZaglavlje($scope.novoZaglavlje).then(
						function(response) {
							if (response.data) {
								ngNotify.set('Uspesno dodavanje zaglavlja', {
									type : 'success'
								});
								// $scope.svaZaglavlja.push(response.data);
								$scope.preuzmiZaglavlja();
								$scope.novoZaglavlje = null;
								$scope.show = null;
							}
						});
			}

			$scope.odustani = function() {
				$scope.selectedZaglavlje = null;
				$scope.novoZaglavlje = null;
				$scope.show = null;
				refresh();
			}

			$scope.odustani2 = function() {
				$scope.zaglavlje = true;
			}

			function refresh() {
				$scope.preuzmiZaglavlja();
				$scope.novoZaglavlje = null;
				$scope.selectedZaglavlje = null;
			}

			$scope.setSelectedZaglavlje = function(selected) {
				$scope.selectedZaglavlje = selected;
				$scope.show = 10;
				$scope.novoZaglavlje = angular.copy(selected);

			}

			$scope.dodajStavku = function() {
				fakturaService.dodajStavku($scope.selectedZaglavlje.id,
						$scope.novaStavka).then(function(response) {
					if (response.data) {
						ngNotify.set('Uspesno dodavanje stavke', {
							type : 'success'
						});
					}
					$scope.zaglavlje = true;
				});
			}

		} ]);
