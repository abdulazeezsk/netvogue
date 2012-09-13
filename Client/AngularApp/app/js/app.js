'use strict';


// Declare app level module which depends on filters, and services
angular.module('netVogue', ['netVogue.filters', 'netVogue.services', 'netVogue.directives']).
	config(['$locationProvider', function($locationProvider) {
		//$locationProvider.html5Mode(true).hashPrefix('app/index.html/');
	}]).
  config(['$routeProvider', function($routeProvider) {
	  var	homepage = '/profile';
	  if(true == netvogue.firsttimeLogin) {
		  homepage = '/initiate';
	  }
	  $routeProvider.when('/accountsettings',			{templateUrl: 'templates/boutique/Account_Settings.htm', 			controller: MyCtrl1});
	  $routeProvider.when('/addcollections',			{templateUrl: 'templates/boutique/Add_Collections.htm', 			controller: MyCtrlAddCollections});
	  $routeProvider.when('/addlinesheets',             {templateUrl: 'templates/boutique/Add_Linesheets.htm',              controller: MyCtrlAddLinesheets });
	  $routeProvider.when('/addstyle',                  {templateUrl: 'templates/boutique/Add_Style.htm',                   controller: MyCtrlAddStyle});
	  $routeProvider.when('/addnewsletter',				{templateUrl: 'templates/boutique/Add_Newsletter.htm', 				controller: MyCtrlAddNewsletter});
	  $routeProvider.when('/addprintcampaign', 			{templateUrl: 'templates/boutique/Add_PrintCampaign.htm', 			controller: MyCtrlAddPrintCampaign});
	  $routeProvider.when('/addvideocampaign', 			{templateUrl: 'templates/boutique/Add_VideoCampaign.htm', 			controller: MyCtrlAddVideoCampaign});
	  $routeProvider.when('/advancesearch', 			{templateUrl: 'templates/boutique/AdvSearch.htm', 					controller: MyCtrlAdvancedSearch });
	  $routeProvider.when('/collections', 				{templateUrl: 'templates/boutique/Collections.htm', 				controller: MyCtrlCollections });
	  $routeProvider.when('/collections/:collectionid', {templateUrl: 'templates/boutique/View_Collection.htm', 			controller: MyCtrlViewcollection });
	  $routeProvider.when('/:profileid/collections', 	{templateUrl: 'templates/boutique/Collections.htm', 				controller: MyCtrlCollections });
	  $routeProvider.when('/:profileid/collections/:collectionid', {templateUrl: 'templates/boutique/View_Collection.htm', 	controller: MyCtrlViewcollection });
	  $routeProvider.when('/corner',					{templateUrl: 'templates/boutique/Corner.htm', 						controller: MyCtrlCorner});
	  $routeProvider.when('/:profileid/corner',			{templateUrl: 'templates/boutique/Corner.htm', 						controller: MyCtrlCorner});
	  $routeProvider.when('/editcollections', 			{templateUrl: 'templates/boutique/Edit_Collections.htm', 			controller: MyCtrlEditCollections});
	  $routeProvider.when('/editlinesheets', 			{templateUrl: 'templates/boutique/Edit_Linesheets.htm', 			controller: MyCtrlEditLinesheets});
	  $routeProvider.when('/editnewsletters', 			{templateUrl: 'templates/boutique/Edit_Newsletters.htm', 			controller: MyCtrlEditNewsletters});
      $routeProvider.when('/editprintcampaigns', 		{templateUrl: 'templates/boutique/Edit_PrintCampaigns.htm',			controller: MyCtrlEditPrintCampaigns});
	  $routeProvider.when('/editstyles', 				{templateUrl: 'templates/boutique/Edit_Styles.htm', 				controller: MyCtrlEditStyles});
	  $routeProvider.when('/editvideocampaigns', 		{templateUrl: 'templates/boutique/Edit_VideoCampaigns.htm', 		controller: MyCtrlEditVideoCampaigns});
	  $routeProvider.when('/initiate', 					{templateUrl: 'templates/initiate.htm', 							controller: MyCtrlInitiate });
	  $routeProvider.when('/linesheets', 				{templateUrl: 'templates/boutique/Linesheets.htm', 					controller: MyCtrlLinesheets });
	  $routeProvider.when('/linesheets/:lineid', 		{templateUrl: 'templates/boutique/Styles.htm', 						controller: MyCtrlStyles });
	  $routeProvider.when('/:profileid/linesheets', 	{templateUrl: 'templates/boutique/Linesheets.htm', 					controller: MyCtrlLinesheets });
	  $routeProvider.when('/:profileid/linesheets/:lineid', 		{templateUrl: 'templates/boutique/Styles.htm', 			controller: MyCtrlStyles });
	  $routeProvider.when('/network', 					{templateUrl: 'templates/boutique/Network.htm', 					controller: MyCtrlNetwork});
	  $routeProvider.when('/:profileid/network', 		{templateUrl: 'templates/boutique/Network.htm', 					controller: MyCtrlNetwork});
	  $routeProvider.when('/newsletter', 				{templateUrl: 'templates/boutique/Newsletter.htm', 					controller: MyCtrlNewsletter });
	  $routeProvider.when('/newsletter/:newsid', 		{templateUrl: 'templates/boutique/View_Newsletter.htm', 			controller: MyCtrlViewNewsLetters });
	  $routeProvider.when('/:profileid/newsletter', 	{templateUrl: 'templates/boutique/Newsletter.htm', 					controller: MyCtrlNewsletter });
	  $routeProvider.when('/:profileid/newsletter/:newsid', 		{templateUrl: 'templates/boutique/View_Newsletter.htm', controller: MyCtrlViewNewsLetters });
	  $routeProvider.when('/printcampaign', 			{templateUrl: 'templates/boutique/PrintCampaign.htm', 				controller: MyCtrlPrintcampaign });
	  $routeProvider.when('/printcampaign/:printid', 	{templateUrl: 'templates/boutique/View_PrintCampaign.htm', 			controller: MyCtrlviewPrintcampaign });
	  $routeProvider.when('/:profileid/printcampaign', 	{templateUrl: 'templates/boutique/PrintCampaign.htm', 				controller: MyCtrlPrintcampaign });
	  $routeProvider.when('/:profileid/printcampaign/:printid', 	{templateUrl: 'templates/boutique/View_PrintCampaign.htm', 			controller: MyCtrlviewPrintcampaign });
	  $routeProvider.when('/profilesettings', 			{templateUrl: 'templates/boutique/Profile_Settings.htm', 			controller: MyCtrlProfileSettings});
	  $routeProvider.when('/profile', 					{templateUrl: 'templates/boutique/Profile.htm', 					controller: MyCtrlProfile});
	  $routeProvider.when('/:profileid/profile',		{templateUrl: 'templates/boutique/Profile.htm', 					controller: MyCtrlProfile});
	  $routeProvider.when('/style', 					{templateUrl: 'templates/boutique/Style.htm', 						controller: MyCtrlStyle });
	  $routeProvider.when('/videocampaign', 			{templateUrl: 'templates/boutique/VideoCampaign.htm', 				controller: MyCtrlVideocampaign });
	  $routeProvider.when('/videocampaign/:videoid', 	{templateUrl: 'templates/boutique/Videos.htm', 						controller: MyCtrlVideos });
	  $routeProvider.when('/:profileid/videocampaign', 	{templateUrl: 'templates/boutique/VideoCampaign.htm', 				controller: MyCtrlVideocampaign });
	  $routeProvider.when('/:profileid/videocampaign/:videoid', 	{templateUrl: 'templates/boutique/Videos.htm', 			controller: MyCtrlVideos });
	  $routeProvider.when('/viewcollection', 			{templateUrl: 'templates/boutique/View_Collection.htm', 			controller: MyCtrlViewcollection });
	  $routeProvider.when('/gallery',                   { templateUrl: 'templates/boutique/Gallery.htm',                    controller: MyCtrlGallery });
	  $routeProvider.when('/help',                      { templateUrl: 'templates/boutique/Help.htm',                       controller: MyCtrlHelp });
	  $routeProvider.when('/privacysettings',           { templateUrl: 'templates/boutique/Privacy_Settings.htm',           controller: MyCtrlPrivacySettings });
	  $routeProvider.when('/organizestyle',             { templateUrl: 'templates/boutique/Organize_Style.htm',             controller: MyCtrlOrganizestyle });
	  $routeProvider.when('/notification',              { templateUrl: 'templates/boutique/Notifications.htm',               controller: MyCtrlNotifications });
	  $routeProvider.when('/addgallery',              	{ templateUrl: 'templates/boutique/Add_Gallery.htm',               controller: MyCtrlNotifications });
	  $routeProvider.when('/editgallery',             	{ templateUrl: 'templates/boutique/Edit_Gallery.htm',               controller: MyCtrlNotifications });
	  $routeProvider.otherwise({redirectTo: homepage});
  }]).
  run( function($rootScope, currentvisitedprofile) {

	    // register listener to watch route changes
	    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
	    	var home = false;
	    	if(angular.isUndefined(current)) {
	    		home = true;
	    	}
	    	currentvisitedprofile.profilevisitChange(next.params, home);
	    });
	 })