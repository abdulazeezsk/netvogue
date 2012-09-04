function CtrlMain($scope, currentvisitedprofile, $route, $routeParams) {
	$scope.title = "Profile";
	netvogue.initialize();
	$scope.iambrand = netvogue.isbrand;
	$scope.brandsorboutiques = "Brands";
	$scope.entityname = "Matches"; //Boutique or Brand name
	$scope.notifications = {
		"Totalnotifications" : "1",
		"Notificationstext"	 : [
								{
										"text" 			: "notification1",
										"unreadstatus" 	: "true"
								}
							   ]
	};
	
	$scope.$watch('iambrand', function(newValue, oldValue) {
		if(newValue == true) {
			$scope.brandsorboutiques = "Boutiques";
		} else {
			$scope.brandsorboutiques = "Brands";
		}
	});
	
	$scope.focused   = false;
	$scope.searchText = "";
	$scope.searchResults = [
	                        new netvogue.basicSearch("Azeez", "Hyderabad"),
	                        new netvogue.basicSearch("Azeez1", "Hyderabad"),
	                        new netvogue.basicSearch("Azeez2", "Hyderabad")
	                        ];
}