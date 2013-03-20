'use strict';

/* Services */

// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('netvogue-admin.services', []).value('version', '0.1').service(
		'myusers', function($http) {
			return {
				users : function(userType) {
					var datatosend = {
						"userType" : userType
					};
					var config = {
						method : "GET",
						params : datatosend,
						url : "admin/userlist"
					};
					return $http(config);
				}
			};

		}).service(
				'myadmin', function($http) {
					return {
						manageUser : function(datatosend, url) {
							alert("test2");
							var config = {
								method : "PUT",
								data : datatosend,
								url : url
							};
							return $http(config);
						}
					};

				});
