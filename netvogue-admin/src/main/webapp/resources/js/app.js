'use strict';

// Declare app level module which depends on filters, and services
angular.module('netvogue-admin', [ 'netvogue-admin.services' ]).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/home', {
				templateUrl : 'templates/user-list.html',
				controller : HomeCtrl
			});
			$routeProvider.when('/boutiques', {
				templateUrl : 'templates/user-list.html',
				controller : BoutiqueCtrl
			});
			$routeProvider.when('/brands', {
				templateUrl : 'templates/user-list.html',
				controller : BrandCtrl
			});
			$routeProvider.when('/activate', {
				templateUrl : 'templates/activate-user.html',
				controller : ActivationCtrl
			});
			$routeProvider.when('/deactivate', {
				templateUrl : 'templates/deactivate-user.html',
				controller : DeactivationCtrl
			});
			$routeProvider.otherwise({
				redirectTo : '/home'
			});
		} ]);
