'use strict';


// Declare app level module which depends on filters, and services
angular.module('netVogue', ['netVogue.constants', 'netVogue.filters', 'netVogue.services', 'netVogue.directives', 'ui']).
	config(['$locationProvider', function($locationProvider) {
		//$locationProvider.html5Mode(true).hashPrefix('app/index.html/');
	}]).
  config(['$routeProvider', function($routeProvider) {
	  var	homepage = '/profile';
	  if(true == netvogue.firsttimeLogin) {
		  homepage = '/initiate';
	  }
	  $routeProvider.when('/accountsettings',			{templateUrl: 'templates/Account_Settings.htm', 					controller: MyCtrlAccountSettings});
	  $routeProvider.when('/addcollections',			{templateUrl: 'templates/brand/Add_Collections.htm', 				controller: MyCtrlAddCollections});
	  $routeProvider.when('/addlinesheets',             {templateUrl: 'templates/brand/Add_Linesheets.htm',              	controller: MyCtrlAddLinesheets });
	  $routeProvider.when('/addstyle',                  {templateUrl: 'templates/brand/Add_Style.htm',                   	controller: MyCtrlAddStyle});
	  $routeProvider.when('/addnewsletter',				{templateUrl: 'templates/Add_Newsletter.htm', 						controller: MyCtrlAddNewsletter});
	  $routeProvider.when('/addprintcampaign', 			{templateUrl: 'templates/Add_PrintCampaign.htm', 					controller: MyCtrlAddPrintCampaign});
	  $routeProvider.when('/advancesearch', 			{templateUrl: 'templates/AdvSearch.htm', 							controller: MyCtrlAdvancedSearch });
	  $routeProvider.when('/collections', 				{templateUrl: 'templates/brand/Collections.htm', 					controller: MyCtrlCollections });
	  $routeProvider.when('/:profileid/collections', 	{templateUrl: 'templates/brand/Collections.htm', 					controller: MyCtrlCollections });
	  $routeProvider.when('/collection', 				{templateUrl: 'templates/brand/Collection.htm', 					controller: MyCtrlCollection });
	  $routeProvider.when('/:profileid/collection', 				{templateUrl: 'templates/brand/Collection.htm', 					controller: MyCtrlCollection });
	  $routeProvider.when('/viewcollection', 			{templateUrl: 'templates/brand/View_Collection.htm', 				controller: MyCtrlViewcollection });	  
	  $routeProvider.when('/corner',					{templateUrl: 'templates/Corner.htm', 								controller: MyCtrlCorner});
	  $routeProvider.when('/:profileid/corner',			{templateUrl: 'templates/Corner.htm', 								controller: MyCtrlCorner});
	  $routeProvider.when('/initiate', 					{templateUrl: 'templates/initiate.htm', 							controller: MyCtrlInitiate });
	  $routeProvider.when('/linesheets', 				{templateUrl: 'templates/brand/Linesheets.htm', 					controller: MyCtrlLinesheets });
	  $routeProvider.when('/:profileid/linesheets', 	{templateUrl: 'templates/brand/Linesheets.htm', 					controller: MyCtrlLinesheets });
	  $routeProvider.when('/styles', 					{templateUrl: 'templates/brand/Styles.htm', 						controller: MyCtrlStyles });
	  $routeProvider.when('/:profileid/styles', 		{templateUrl: 'templates/brand/Styles.htm', 						controller: MyCtrlStyles });
	  $routeProvider.when('/style', 					{templateUrl: 'templates/brand/Style.htm', 							controller: MyCtrlStyle });
	  $routeProvider.when('/:profileid/style', 			{templateUrl: 'templates/brand/Style.htm', 							controller: MyCtrlStyle });
	  $routeProvider.when('/network', 					{templateUrl: 'templates/Network.htm', 								controller: MyCtrlNetwork});
	  $routeProvider.when('/:profileid/network', 		{templateUrl: 'templates/Network.htm', 								controller: MyCtrlNetwork});
	  $routeProvider.when('/newsletter', 				{templateUrl: 'templates/Newsletter.htm', 							controller: MyCtrlNewsletter });
	  $routeProvider.when('/editorial', 				{templateUrl: 'templates/Editorial.htm', 							controller: MyCtrlEditorial });
	  $routeProvider.when('/:profileid/editorial', 		{templateUrl: 'templates/Editorial.htm', 							controller: MyCtrlEditorial });
	  $routeProvider.when('/viewnewsletter', 			{templateUrl: 'templates/View_Newsletter.htm', 						controller: MyCtrlViewNewsLetters });
	  $routeProvider.when('/:profileid/newsletter', 	{templateUrl: 'templates/Newsletter.htm', 							controller: MyCtrlNewsletter });
	  $routeProvider.when('/printcampaign', 			{templateUrl: 'templates/PrintCampaign.htm', 						controller: MyCtrlPrintcampaign });
	  $routeProvider.when('/:profileid/printcampaign', 	{templateUrl: 'templates/PrintCampaign.htm', 						controller: MyCtrlPrintcampaign });
	  $routeProvider.when('/campaign',					{templateUrl: 'templates/campaign.htm',         		 			controller: MyCtrlCampaign });
	  $routeProvider.when('/:profileid/campaign',		{templateUrl: 'templates/campaign.htm',         		 			controller: MyCtrlCampaign });
	  $routeProvider.when('/viewprintcampaign', 		{templateUrl: 'templates/View_PrintCampaign.htm', 					controller: MyCtrlviewPrintcampaign });
	  $routeProvider.when('/profilesettings', 			{templateUrl: 'templates/Profile_Settings.htm', 					controller: MyCtrlProfileSettings});
	  $routeProvider.when('/profile', 					{templateUrl: 'templates/Profile.htm', 								controller: MyCtrlProfile});
	  $routeProvider.when('/:profileid/profile',		{templateUrl: 'templates/Profile.htm', 								controller: MyCtrlProfile});	  
	  $routeProvider.when('/viewcollection', 			{templateUrl: 'templates/brand/View_Collection.htm', 			controller: MyCtrlViewcollection });
	  $routeProvider.when('/gallery',                   { templateUrl: 'templates/Gallery.htm',                    			controller: MyCtrlGallery });
	  $routeProvider.when('/:profileid/gallery',        { templateUrl: 'templates/Gallery.htm',                    			controller: MyCtrlGallery });
	  $routeProvider.when('/help',                      { templateUrl: 'templates/Help.htm',                       			controller: MyCtrlHelp });
	  $routeProvider.when('/privacysettings',           { templateUrl: 'templates/Privacy_Settings.htm',           			controller: MyCtrlPrivacySettings });
	  $routeProvider.when('/notification',              { templateUrl: 'templates/Notifications.htm',              			controller: MyCtrlNotifications });
	  $routeProvider.when('/addgallery',              	{ templateUrl: 'templates/Add_Gallery.htm',              			controller: MyCtrlAddGallery });
	  $routeProvider.when('/stylesheets',             	{ templateUrl: 'templates/brand/Stylesheets.htm',              	controller: MyCtrlStylesheets });
	  $routeProvider.when('/stylesheet',             	{ templateUrl: 'templates/brand/Stylesheet.htm',              	controller: MyCtrlStylesheet });
	  $routeProvider.when('/stylepreview',             	{ templateUrl: 'templates/brand/Style_Preview.htm',              controller: MyCtrlStylesPreview });
	  $routeProvider.when('/photos',             		{ templateUrl: 'templates/Photos.htm',           		   			controller: MyCtrlPhotos });
	  $routeProvider.when('/:profileid/photos',         { templateUrl: 'templates/Photos.htm',           		   			controller: MyCtrlPhotos });
	  $routeProvider.when('/viewphotos',             	{ templateUrl: 'templates/View_Photos.htm',     	     			controller: MyCtrlViewPhotos });
	  $routeProvider.when('/campaign',					{ templateUrl: 'templates/campaign.htm',         			 		controller: MyCtrlCampaign });
	  $routeProvider.when('/invite',					{ templateUrl: 'templates/invite.htm',         			 			controller: MyCtrlInvite });
	  //Related to ecommerce
	  $routeProvider.otherwise({redirectTo: homepage});
	  $routeProvider.when('/mycart',					{ templateUrl: 'templates/mycart.htm',         			 		controller: MyCtrlCart });
	  $routeProvider.when('/boutiqueorders',			{ templateUrl: 'templates/boutiqueorders.htm',         			controller: MyCtrlBoutiqueOrders });
	  $routeProvider.when('/brandorders',				{ templateUrl: 'templates/brandorders.htm',         			controller: MyCtrlBrandOrders });
	  $routeProvider.when('/revieworders',				{ templateUrl: 'templates/Revieworder.htm',         			controller: MyCtrlRevieworders });
	  $routeProvider.when('/sample',					{ templateUrl: 'templates/sample.htm',         					controller: MyCtrlsample });
	  $routeProvider.when('/mycorner',					{ templateUrl: 'templates/users/user_Corner.htm',         		controller: MyCtrlUserCorner });
	  $routeProvider.when('/discover',					{ templateUrl: 'templates/users/userAdvancedSearch.htm',        controller: MyCtrlUserSearch });
	  $routeProvider.when('/Following',					{ templateUrl: 'templates/users/user_Following.htm',         	controller: MyCtrlUserFollowing });
	  $routeProvider.when('/userAccountSettings',		{ templateUrl: 'templates/users/user_AccountSettings.htm',      controller: MyCtrlUserAccountSettings });
	  $routeProvider.when('/boutiqueLine',				{ templateUrl: 'templates/users/line.htm',      				controller: MyCtrlBoutiqueLine });
	  $routeProvider.when('/boutiqueStyles',			{ templateUrl: 'templates/users/user_Styles.htm',      			controller: MyCtrlBoutiqueStyles });
	  $routeProvider.when('/boutiqueStyle',				{ templateUrl: 'templates/users/user_Style.htm',      			controller: MyCtrlBoutiqueStyle });

  }]).run( function($rootScope, currentvisitedprofile) {

	    // register listener to watch route changes
	    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
	    	var home = false;
	    	if(angular.isUndefined(current)) {
	    		home = true;
	    	}
	    	currentvisitedprofile.profilevisitChange(next.params, home);
	    });
});