'use strict';

/* Controllers */

function HomeCtrl($scope, myusers) {
	$scope.users = '';
	$scope.getUsers = function() {
		myusers.users('').success(function(data) {
			$scope.users = data;
		});
	};
	$scope.getUsers();
}

function BoutiqueCtrl($scope, myusers) {
	$scope.users = '';
	$scope.getUsers = function() {
		myusers.users('BOUTIQUE').success(function(data) {
			$scope.users = data;
		});
	};
	$scope.getUsers();
}

function BrandCtrl($scope, myusers) {
	$scope.users = '';
	$scope.getUsers = function() {
		myusers.users('BRAND').success(function(data) {
			$scope.users = data;
		});
	};
	$scope.getUsers();
}

function ActivationCtrl($scope, myadmin) {
	$scope.activateUser = function() {
		var datatosend = {
				"userName" 	: $scope.username,
				"email"		: $scope.email
		};
		myadmin.manageUser(datatosend, '/admin/activateUser').success(function(data) {
			if(data.status == true) {
				alert('Account Activated');
			}
			else{
				alert(data.error);
			}
		});
	};
}

function DeactivationCtrl($scope, $routeParams, myadmin) {
	$scope.username = '';
	$scope.email = '';
	$scope.msg = '';
	$scope.deactivateUser = function() {
		var datatosend = {
				"userName" 	: $scope.username,
				"email"		: $scope.email,
//				"name"		: ''
		};
		myadmin.manageUser(datatosend, '/admin/deactivateUser').success(function(data) {
			if(data.status == true) {
				alert('Account Deactivated');
			}else{
				alert(data.error);
			}
		});
	};
}