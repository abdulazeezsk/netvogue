function CtrlMain($scope, currentvisitedprofile, $route, $routeParams, search) {
	$scope.title = "Profile";
	netvogue.initialize();
	$scope.iambrand = netvogue.isbrand;
	$scope.entityname = netvogue.entityname; //Boutique or Brand name
	$scope.notifications = {
		"Totalnotifications" : "1",
		"Notificationstext"	 : [
								{
										"text" 			: "notification1",
										"unreadstatus" 	: "true"
								}
							   ]
	};
	
	// Not required anymore, as we have changed Find Boutiques and Brands to directory
	/*$scope.brandsorboutiques = "Brands";
	$scope.$watch('iambrand', function(newValue, oldValue) {
		if(newValue == true) {
			$scope.brandsorboutiques = "Boutiques";
		} else {
			$scope.brandsorboutiques = "Brands";
		}
	});*/
	
	//Related to search box in header
	$scope.focused   = false;
	$scope.searchText = "";
	$scope.searchResults = [];
	
	$scope.getsearchResults = function(query) {
		search.getbasicsearchresults(query).success(function(data) {
			$scope.searchResults = data;
	    }).error(function(data) {
	    	
	    });
	};
}