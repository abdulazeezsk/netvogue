function CtrlMain($scope, currentvisitedprofile, $route, $routeParams, search, mynotifications) {
	$scope.title = "Profile";
	netvogue.initialize();
	$scope.iambrand = netvogue.isbrand;
	$scope.entityname = netvogue.entityname; //Boutique or Brand name
	
	$scope.updatenotifications = function() {
		$scope.entityname 			= mynotifications.getname();
		$scope.profilepic 			= mynotifications.getprofilepic();
		$scope.unreadnotifications 	= mynotifications.getnumberofunreadnotifications();
		$scope.notifications 		= mynotifications.getunreadnotifications();
	};
	
	mynotifications.notificationsunread().success(function(data) {
		mynotifications.setunreadnotifications(data);
		$scope.updatenotifications();
	}).error(function(data) {
		
	});
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