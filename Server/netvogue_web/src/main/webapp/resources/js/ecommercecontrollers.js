function MyCtrlCart($scope, $routeParams, $location, currentvisitedprofile) {

}

function MyCtrlBoutiqueOrders($scope, $routeParams, $location, currentvisitedprofile) {
	
}

function MyCtrlBrandOrders($scope, $routeParams, $location, currentvisitedprofile) {
	
}

function MyCtrlInvite($scope, $routeParams, $location, currentvisitedprofile)
{
	$scope.$parent.title = 'Invite';
//	$scope.entityname = currentvisitedprofile.getEntityName();
//	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
//	$scope.backButton = currentvisitedprofile.getBackHistory();

}

function MyCtrlRevieworders($scope, $routeParams, $location, currentvisitedprofile)
{
	$scope.$parent.title = 'Revieworders';
}

function MyCtrlsample($scope, $routeParams, $location, currentvisitedprofile)
{
	$scope.$parent.title = 'sample';
}
