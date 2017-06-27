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

app.controller('zahtevController', [
		'$rootScope',
		'$scope',
		'$location',
		'ngNotify',
		'zahtevService',
		function($rootScope, $scope, $location, ngNotify, zahtevService) {

			$scope.novoZaglavlje = {};

			$scope.preuzmiZaglavlja = function() {
				fakturaService.preuzmiZaglavlja().then(function(response) {
					if (response.data) {
						$scope.svaZaglavlja = response.data;
					}
				});
			}
			
			$scope.posaljiZahtev = function() {
				zahtevService.posaljiZahtev($scope.zahtev)
						.then(function(response) {
						});
			}
			
		} ]);