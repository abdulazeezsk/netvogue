function CtrlMain($scope, currentvisitedprofile, $route, $routeParams, search, mynotifications) {
	$scope.title = "Profile";
	netvogue.initialize();
	$scope.iambrand = netvogue.isbrand;
	
	$scope.updatenotifications = function() {
		$scope.entityname 			= mynotifications.getname();
		$scope.myprofileid			= mynotifications.getprofileid();
		$scope.profilepic 			= mynotifications.getprofilepic();
		$scope.unreadnotifications 	= mynotifications.getnumberofunreadnotifications();
		$scope.notifications 		= mynotifications.getunreadnotifications();
		$scope.contactinfo 		= {};
	    $scope.getcontactinfo 	= addresstostring($scope.contactinfo);
	    
		currentvisitedprofile.setmyprofileid($scope.myprofileid);
		createpusherchannel($scope.myprofileid);
		//createpubnubchannel($scope.myprofileid);
	};
	
	mynotifications.notificationsunread().success(function(data) {
		mynotifications.setunreadnotifications(data);
		$scope.updatenotifications();
	}).error(function(data) {
		
	});
	
	$scope.$on('profilepicchanged', function(event, profilepic) {
		$scope.profilepic = profilepic;
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
	var createpusherchannel = function(id) {
		//Register pusher to receive notifications
		$scope.pusher = new Pusher('15b40a25fa57725931ad'); // Replace with your app key
		var channel = $scope.pusher.subscribe(id);
		channel.bind('notification', function(data) {
		      alert(data);
		      $scope.unreadnotifications++ ;
		      $scope.notifications.push(data);
		 });
	};
	
	/*var createpubnubchannel = function(id) {
		PUBNUB.subscribe({
		    channel  : id,
		    callback : function(message) { alert("message"); },
		    error    : function(e) {
		        // Do not call subscribe() here!
		        // PUBNUB will auto-reconnect.
		        console.log(e);
		    }
		});
	};*/
}