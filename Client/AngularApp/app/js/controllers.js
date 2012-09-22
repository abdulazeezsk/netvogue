'use strict';

/* Controllers */

function MyCtrl1($scope) {

}
MyCtrl1.$inject = [];

function MyCtrl2($scope) {

}
MyCtrl2.$inject = [];

function MyCtrlInitiate($scope, $location) {
	$scope.initiated = false;
	$scope.finish = function() {
		// Send data to server
		$scope.$apply(function() {
			$scope.initiated = true;
		});
		return true;
	};
	$scope.$watch('initiated', function(newValue, oldValue) {
		if (true == newValue) {
			$location.url('/corner');
		}
	});
}
function MyCtrlNetwork($scope, $routeParams, myprofile, currentvisitedprofile,
		srvprofile, trending) {
	$scope.navClass = function(page1) {
		return {
			// last: this.$last,
			active : page1 && this.currentPage == page1
		};
	};
	$scope.currentPage = 'Network';
	$scope.$parent.title = "Network";

	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.links = currentvisitedprofile.getleftpanellinks();

	$scope.mynetwork = srvprofile.getnetwork($routeParams);
	$scope.trending = trending.getTrending();
	$scope.contactinfo = myprofile.getcontactinfo();
	$scope.getcontactinfo = function() {
		return addresstostring($scope.contactinfo);
	};
}

function MyCtrlProfile($scope, $routeParams, srvprofile, currentvisitedprofile,
		trending, mynetwork) {
	$scope.navClass = function(page1) {
		return {
			// last: this.$last,
			active : page1 && this.currentPage == page1
		};
	};
	$scope.currentPage = 'Profile';
	$scope.$parent.title = "Profile";

	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.aboutus = srvprofile.getaboutus($routeParams);
	$scope.brandscarried = srvprofile.getbrandscarried($routeParams);
	$scope.productline = srvprofile.getproductline($routeParams);
	$scope.trending = trending.getTrending();

	$scope.contactinfo = srvprofile.getcontactinfo($routeParams);
	$scope.getcontactinfo = function() {
		return addresstostring($scope.contactinfo);
	};

	$scope.myfriend = mynetwork.ismyfriend($routeParams);
	$scope.links = currentvisitedprofile.getleftpanellinks();
	$scope.collections = [ {
		"collectionlistitemid" : "collection name",
		"collectionlistitem" : "Calvin klein",
		"collectionseason" : "Spring 2012",
		"collectioncoverpic" : "http://placehold.it/132x185"

	}, {
		"collectionlistitemid" : "collection name",
		"collectionlistitem" : "Donna karan",
		"collectionseason" : "Spring 2012",
		"collectioncoverpic" : "http://placehold.it/132x185"

	}, {
		"collectionlistitemid" : "collection name",
		"collectionlistitem" : "Maria will",
		"collectionseason" : "Spring 2012",
		"collectioncoverpic" : "http://placehold.it/132x185"

	}, {
		"collectionlistitemid" : "collection name",
		"collectionlistitem" : "Calvin klein",
		"collectionseason" : "Summer 2012",
		"collectioncoverpic" : "http://placehold.it/132x185"

	}, {
		"collectionlistitemid" : "collection name",
		"collecticollectionidonlistitem" : "Calvin klein",
		"collectionseason" : "Winter 2012",
		"collectioncoverpic" : "http://placehold.it/132x185"

	}, {
		"collectionlistitemid" : "collection name",
		"collectionlistitem" : "Calvin klein",
		"collectionseason" : "Fall 2012",
		"collectioncoverpic" : "http://placehold.it/132x185"

	} ];
}
function MyCtrlCorner($scope, $routeParams, srvprofile, currentvisitedprofile,
		mynetwork, trending) {
	$scope.navClass = function(page1) {
		return {
			// last: this.$last,
			active : page1 && this.currentPage == page1
		};
	};
	$scope.currentPage = 'Corner';
	$scope.$parent.title = "Corner";

	$scope.entityname = currentvisitedprofile.getEntityName();

	$scope.mynetwork = mynetwork.getmynetwork();
	$scope.trending = trending.getTrending();
	$scope.contactinfo = srvprofile.getcontactinfo($routeParams);
	$scope.getcontactinfo = function() {
		return addresstostring($scope.contactinfo);
	};

	$scope.links = currentvisitedprofile.getleftpanellinks();
	$scope.newsfeeds = [
			{
				"boutiquename" : "Boutique Name",
				"boutiqueID" : "boutique",
				"profilepiclink" : "http://placehold.it/50x50",
				"post" : "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui. Vivamus vulputate"
						+ "ipsum vel enim. Aliquam erat volutpat. Etiam a dui at neque semper ornare. Mauris"
						+ "lacus tortor, sagittis eu, dictum sit amet, facilisis eu, mauris. Praesent molestie"
						+ "ante non nibh. Suspendisse placerat eros vel velit. Vestibulum ante ipsum primis"
						+ "in faucibus orci luctus et ultrices posuere cubilia Curae; Nullam ultricies eros"
						+ "eget quam."
			}, {
				"boutiquename" : "Motorola XOOM with Wi-Fi",
				"boutiqueID" : "boutique",
				"profilepiclink" : "http://placehold.it/50x50",
				"post" : "The Next, Next Generation tablet."
			}, {
				"boutiquename" : "MOTOROLA XOOM",
				"boutiqueID" : "boutique",
				"profilepiclink" : "http://placehold.it/50x50",
				"post" : "The Next, Next Generation tablet."
			} ];
	$scope.networkfeed = [ {
		"networklistitem" : "Calvin klein",
		"profilepiclink" : "images/netwrk50x50.jpg",
		"networklistitemID" : "CalvinKlienId"
	}, {
		"networklistitem" : "Rebecca  Minkoff",
		"profilepiclink" : "http://placehold.it/50x50",
		"networklistitemID" : "RebeccaMinkoffId"
	}, {
		"networklistitem" : "Jason Myers",
		"profilepiclink" : "http://placehold.it/50x50",
		"networklistitemID" : "JasonMyersId"
	}, {
		"networklistitem" : "Akris",
		"profilepiclink" : "http://placehold.it/50x50",
		"networklistitemID" : "AkrisId"
	}, {
		"networklistitem" : "Catherine Malandrino",
		"profilepiclink" : "http://placehold.it/50x50",
		"networklistitemID" : "CatherineMalandrinoId"
	}, {
		"networklistitem" : "Derek Lam",
		"profilepiclink" : "http://placehold.it/50x50",
		"networklistitemID" : "DerekLamId"
	}, {
		"networklistitem" : "Donna Karan",
		"profilepiclink" : "http://placehold.it/50x50",
		"networklistitemID" : "DonnaKaranId"
	} ];

}

function MyCtrlGallery($scope, $routeParams, currentvisitedprofile, srvprofile) {

	$scope.$parent.title = 'Gallery';
	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.galleries = [ 
	{
		"gallerylistitemid" : "galleryId",
		"galleryname" : "Studded Winston",
		"gallerydescription" : "Spring 2012",
		"gallerydate" : "25/04/2012",
		"gallerypic" : "http://placehold.it/220x150"

	},
	{
		"gallerylistitemid" : "galleryId",
		"galleryname" : "Studded Winston",
		"gallerydescription" : "Spring 2012",
		"gallerydate" : "25/04/2012",
		"gallerypic" : "http://placehold.it/220x150"

	},
	{
		"gallerylistitemid" : "galleryId",
		"galleryname" : "Studded Winston",
		"gallerydescription" : "Spring 2012",
		"gallerydate" : "25/04/2012",
		"gallerypic" : "http://placehold.it/220x150"

	},
	{
		"gallerylistitemid" : "galleryId",
		"galleryname" : "Studded Winston",
		"gallerydescription" : "Spring 2012",
		"gallerydate" : "25/04/2012",
		"gallerypic" : "http://placehold.it/220x150"

	}, 
	{
		"gallerylistitemid" : "galleryId",
		"galleryname" : "Studded Winston",
		"gallerydescription" : "Spring 2012",
		"gallerydate" : "25/04/2012",
		"gallerypic" : "http://placehold.it/220x150"

	},
	{
		"gallerylistitemid" : "galleryId",
		"galleryname" : "Studded Winston",
		"gallerydescription" : "Spring 2012",
		"gallerydate" : "25/04/2012",
		"gallerypic" : "http://placehold.it/220x150"

	},
	{
		"gallerylistitemid" : "galleryId",
		"galleryname" : "Studded Winston",
		"gallerydescription" : "Spring 2012",
		"gallerydate" : "25/04/2012",
		"gallerypic" : "http://placehold.it/220x150"

	},
	{
		"gallerylistitemid" : "galleryId",
		"galleryname" : "Studded Winston",
		"gallerydescription" : "Spring 2012",
		"gallerydate" : "25/04/2012",
		"gallerypic" : "http://placehold.it/220x150"

	},
	{
		"gallerylistitemid" : "galleryId",
		"galleryname" : "Studded Winston",
		"gallerydescription" : "Spring 2012",
		"gallerydate" : "25/04/2012",
		"gallerypic" : "http://placehold.it/220x150"

	} 
	];

}

function MyCtrlPhotos($scope, $routeParams, currentvisitedprofile, srvprofile) {

	$scope.$parent.title = 'Gallery';
	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.photogallery = [ {
		"photolistitemid" : "photogalleryId",
		"photoname" : "Studded Winston",
		"photodescription" : "Spring 2012",
		"photodate" : "25/04/2012",
		"photocoverpic" : "http://placehold.it/220x150"

	}, {
		"photolistitemid" : "photogalleryId",
		"photoname" : "Studded Winston",
		"photodescription" : "Spring 2012",
		"photodate" : "25/04/2012",
		"photocoverpic" : "http://placehold.it/220x150"

	}, {
		"photolistitemid" : "photogalleryId",
		"photoname" : "Studded Winston",
		"photodescription" : "Spring 2012",
		"photodate" : "25/04/2012",
		"photocoverpic" : "http://placehold.it/220x150"

	}, {
		"photolistitemid" : "photogalleryId",
		"photoname" : "Studded Winston",
		"photodescription" : "Spring 2012",
		"photodate" : "25/04/2012",
		"photocoverpic" : "http://placehold.it/220x150"

	}, {
		"photolistitemid" : "photogalleryId",
		"photoname" : "Studded Winston",
		"photodescription" : "Spring 2012",
		"photodate" : "25/04/2012",
		"photocoverpic" : "http://placehold.it/220x150"

	}, {
		"photolistitemid" : "photogalleryId",
		"photoname" : "Studded Winston",
		"photodescription" : "Spring 2012",
		"photodate" : "25/04/2012",
		"photocoverpic" : "http://placehold.it/220x150"

	}, {
		"photolistitemid" : "photogalleryId",
		"photoname" : "Studded Winston",
		"photodescription" : "Spring 2012",
		"photodate" : "25/04/2012",
		"photocoverpic" : "http://placehold.it/220x150"

	}, {
		"photolistitemid" : "photogalleryId",
		"photoname" : "Studded Winston",
		"photodescription" : "Spring 2012",
		"photodate" : "25/04/2012",
		"photocoverpic" : "http://placehold.it/220x150"

	},

	{
		"photolistitemid" : "photogalleryId",
		"photoname" : "Studded Winston",
		"photodescription" : "Spring 2012",
		"photodate" : "25/04/2012",
		"photocoverpic" : "http://placehold.it/220x150"

	}
	];

}

function MyCtrlPrintcampaign($scope, $routeParams, currentvisitedprofile,
		srvprofile) {

	$scope.$parent.title = 'Printcampaign';

	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.printcampaigns = srvprofile.getprintcampaigns($routeParams);

}
function MyCtrlVideocampaign($scope, $routeParams, currentvisitedprofile,
		srvprofile) {

	$scope.$parent.title = 'Videocampaign';

	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.links = currentvisitedprofile.getleftpanellinks();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.videocampaigns = srvprofile.getvideocampaigns($routeParams);

}

function MyCtrlNewsletter($scope, $routeParams, currentvisitedprofile,
		srvprofile) {

	$scope.$parent.title = 'Newsletter';

	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.links = currentvisitedprofile.getleftpanellinks();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.newsletters = srvprofile.getnewsletters($routeParams);

}

function MyCtrlProfileSettings($scope, $routeParams, myprofile) {

	$scope.$parent.title = "Profile Settings";
	$scope.aboutus = myprofile.getaboutus();
	$scope.updateaboutus = function(aboutme) {
		myprofile.updateaboutus(aboutme);
		jQuery.growlUI('', 'Saving...');
	};

	if (angular.isUndefined($routeParams.t)) {
		$scope.tabposition = 0;
	} else {
		$scope.tabposition = $routeParams.t;
	}

	$scope.contactinfo = myprofile.getcontactinfo();
	$scope.updatecontactinfo = function(contactinfotemp) {
		myprofile.updatecontactinfo(contactinfotemp);
	};

	$scope.productline = myprofile.getproductline();
	$scope.updateproductline = function(productlinetemp) {
		myprofile.updateproductline(productlinetemp);
	};

	$scope.brandscarried = myprofile.getbrandscarried();
	$scope.updatebrandscarried = function(brandcarriedtemp) {
		myprofile.updatebrandscarried(brandcarriedtemp);
	};

	$scope.searchFilter = new netvogue.searchFilter();
}

function MyCtrlAdvancedSearch($scope, search) {

	$scope.$parent.title = 'AdvancedSearch';
	$scope.pagenumber = 0;
	$scope.pagenumbers = [ 1, 2, 3, 4 ];
	$scope.pageSize = 12;

	$scope.advancedsearch = search.getsearchresults();

	$scope.$on('$viewContentLoaded', function() {
		$jq("#slider").bxSlider();
	});
	$scope.searchFilter = new netvogue.searchFilter();
}
function MyCtrlCollections($scope, $routeParams, currentvisitedprofile,
		srvprofile) {

	$scope.$parent.title = "Collections";
	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.links = currentvisitedprofile.getleftpanellinks();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.collections = srvprofile.getcollections($routeParams);

	$scope.searchFilter = new netvogue.searchFilter();
}
function MyCtrlLinesheets($scope, $routeParams, currentvisitedprofile,
		srvprofile) {

	$scope.$parent.title = 'Linesheets';
	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.links = currentvisitedprofile.getleftpanellinks();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.linesheets = srvprofile.getlinesheets($routeParams);
	$scope.$on('$viewContentLoaded', function() {

	});

	$scope.searchFilter = new netvogue.searchFilter();

}

function MyCtrlStyles($scope, $routeParams, currentvisitedprofile) {

	$scope.$parent.title = 'Styles';

	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.stylesseason = {
		"seasonname" : "Spring 2012"
	}
	$scope.styles = [ {
		"stylelistitemid" : "styleId",
		"stylename" : "Studded Winston",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	},

	{
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	} ];

	$scope.linesheets = [ {
		"linesheetlistitemid" : "linesheetId",
		"linesheetbrandname" : "Calvin Klien",
		"linesheetname" : "Spring 2012",
		"linesheetdeliverydate" : "25/04/2012",
		"linesheetcoverpic" : "http://placehold.it/90x119"

	}, {
		"linesheetlistitemid" : "linesheetId",
		"linesheetbrandname" : "Donna Karan",
		"linesheetname" : "Spring 2012",
		"linesheetdeliverydate" : "25/04/2012",
		"linesheetcoverpic" : "http://placehold.it/90x119"

	}, {
		"linesheetlistitemid" : "linesheetId",
		"linesheetbrandname" : "Calvin Klien",
		"linesheetname" : "Spring 2012",
		"linesheetdeliverydate" : "25/04/2012",
		"linesheetcoverpic" : "http://placehold.it/90x119"

	} ];

}

function MyCtrlviewPrintcampaign($scope, $routeParams, currentvisitedprofile) {

	$scope.$parent.title = 'ViewPrintcampaign';

	$scope.entityname = currentvisitedprofile.getEntityName();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.viewPrintcampaigns = [
			{
				"printcampaignlistitemid" : "printcampaignId",
				"printcampaignname" : "Calvin Klien",
				"printcampaignseason" : "Spring 2012",
				"printcampaigndescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi"
						+ "porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit",
				"printcampaignpic" : "img/donna_karan_adriana_lima_1.jpg"

			},
			{
				"printcampaignlistitemid" : "printcampaignId",
				"printcampaignname" : "Calvin Klien",
				"printcampaignseason" : "Spring 2012",
				"printcampaigndescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi"
						+ "porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit",
				"printcampaignpic" : "img/donna_karan_adriana_lima_2.jpg"

			},
			{
				"printcampaignlistitemid" : "printcampaignId",
				"printcampaignname" : "Calvin Klien",
				"printcampaignseason" : "Spring 2012",
				"printcampaigndescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi"
						+ "porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit",
				"printcampaignpic" : "img/donna_karan_adriana_lima_3.jpg"

			}

	];

	$scope.printcampaigns = [
	{
		"printcampaignlistitemid" : "Id",
		"printcampaignname" : "Studded Winston",
		"printcampaignpic" : "http://placehold.it/90x119"

	},
	{
		"printcampaignlistitemid" : "Id",
		"printcampaignname" : "Studded Winston",
		"printcampaignpic" : "http://placehold.it/90x119"

	},
	{
		"printcampaignlistitemid" : "Id",
		"printcampaignname" : "Studded Winston",
		"printcampaignpic" : "http://placehold.it/90x119"

	}
	];

}
function MyCtrlVideos($scope, $routeParams, currentvisitedprofile) {

	$scope.$parent.title = 'Videos';

	$scope.entityname = currentvisitedprofile.getEntityName();

	$scope.backButton = currentvisitedprofile.getBackHistory();

	$scope.videos = [
			{
				"videolistitemid" : "videoid",
				"videoname" : "Calvin Klien",
				"videodescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi",
				"videothumbnail" : "http://placehold.it/90x72"

			},
			{
				"videolistitemid" : "videoid",
				"videoname" : "Calvin Klien",
				"videodescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi",
				"videothumbnail" : "http://placehold.it/90x72"

			},
			{
				"videolistitemid" : "videoid",
				"videoname" : "Calvin Klien",
				"videodescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi",
				"videothumbnail" : "http://placehold.it/90x72"

			},
			{
				"videolistitemid" : "videoid",
				"videoname" : "Calvin Klien",
				"videodescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi",
				"videothumbnail" : "http://placehold.it/90x72"
			}

	];

}

function MyCtrlStyle($scope, currentvisitedprofile) {

	$scope.$parent.title = 'Style';
	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.stylethumbnails = [ {
		"stylethumbnailsid" : "videoid",
		"stylethumbnail" : "http://placehold.it/85x114"

	}, {
		"stylethumbnailsid" : "videoid",
		"stylethumbnail" : "http://placehold.it/85x114"

	}, {
		"stylethumbnailsid" : "videoid",
		"stylethumbnail" : "http://placehold.it/85x114"

	}, {
		"stylethumbnailsid" : "videoid",
		"stylethumbnail" : "http://placehold.it/85x114"
	}

	];

	$scope.styletdetails = {
		"styleid" : "styleid",
		"stylename" : "becky red jacket",
		"styledescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi"
				+ "porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit",
		"stylefabricarion" : "Cotton",
		"stylenumber" : "RBK2345",
		"stylederliverydate" : "24/08/2012",
		"stylesize" : "S M L XL",
		"styleprice" : "12000",
		"stylecover" : "http://placehold.it/290x400"
	};

	$scope.styles = [
	 {
		"stylelistitemid" : "styleId",
		"stylename" : "Studded Winston",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/90x119"

	},
	{
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/90x119"

	},
	{
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/90x119"

	},
	{
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/90x119"

	},
	{
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/90x119"

	},
	{
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/90x119"

	} 
	
	];
	
	

}

function MyCtrlViewcollection($scope, $routeParams, currentvisitedprofile) {

	$scope.$parent.title = 'ViewCollection';

	$scope.entityname = currentvisitedprofile.getEntityName();

	$scope.backButton = currentvisitedprofile.getBackHistory();

	$scope.collectionseason = {
		"seasonname" : "Spring 2012",
		"collectionlikes" : "10 people like this"
	};
	$scope.viewcollections = [
			{
				"collectionlistitemid" : "collectionId",
				"collectionbrand" : "Calvin Klien",
				"collectionseason" : "Spring 2012",
				"collectiondescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi"
						+ "porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit",
				"collectionpic" : "img/donna_karan_adriana_lima_1.jpg"

			},
			{
				"collectionlistitemid" : "collectionId",
				"collectionbrand" : "Calvin Klien",
				"collectionseason" : "Spring 2012",
				"collectiondescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi"
						+ "porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit",
				"collectionpic" : "img/donna_karan_adriana_lima_2.jpg"

			},
			{
				"collectionlistitemid" : "collectionId",
				"collectionbrand" : "Calvin Klien",
				"collectionseason" : "Spring 2012",
				"collectiondescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi"
						+ "porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit",
				"collectionpic" : "img/donna_karan_adriana_lima_3.jpg"

			}

	];

}

function MyCtrlViewNewsLetters($scope, $routeParams, currentvisitedprofile) {

	$scope.$parent.title = 'ViewNewsLetter';

	$scope.entityname = currentvisitedprofile.getEntityName();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.viewnewsletters = [
			{
				"newsletterlistitemid" : "newsletterId",
				"newsletterbrand" : "Calvin Klien",
				"newsletterseason" : "Spring 2012",
				"newsletterdescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi"
						+ "porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit",
				"newsletterpic" : "img/donna_karan_adriana_lima_1.jpg"

			},
			{
				"newsletterlistitemid" : "newsletterId",
				"newsletterbrand" : "Calvin Klien",
				"newsletterseason" : "Spring 2012",
				"newsletterdescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi"
						+ "porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit",
				"newsletterpic" : "img/donna_karan_adriana_lima_2.jpg"

			},
			{
				"newsletterlistitemid" : "newsletterId",
				"newsletterbrand" : "Calvin Klien",
				"newsletterseason" : "Spring 2012",
				"newsletterdescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi"
						+ "porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit",
				"newsletterpic" : "img/donna_karan_adriana_lima_3.jpg"

			}

	];
	
	$scope.Editorials = [
	                     	{
	                     		"newsletterlistitemid" : "Id",
	                     		"newslettername" : "Studded Winston",
	                     		"newsletterpic" : "http://placehold.it/90x119"

	                     	},
	                     	{
	                     		"newsletterlistitemid" : "Id",
	                     		"newslettername" : "Studded Winston",
	                     		"newsletterpic" : "http://placehold.it/90x119"

	                     	},
	                     	{
	                     		"newsletterlistitemid" : "Id",
	                     		"newslettername" : "Studded Winston",
	                     		"newsletterpic" : "http://placehold.it/90x119"

	                     	}
	                     	];


}

function MyCtrlHelp($scope, $routeParams, currentvisitedprofile, srvprofile) {

	$scope.$parent.title = 'Help';
	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();

}

function MyCtrlPrivacySettings($scope, $routeParams, currentvisitedprofile,
		srvprofile) {

	$scope.$parent.title = 'Privacy Settings';
	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();

}

function MyCtrlOrganizestyle($scope, $routeParams, currentvisitedprofile,
		srvprofile) {

	$scope.$parent.title = 'Organize Style';
	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();

}

function MyCtrlNotifications($scope, $routeParams, currentvisitedprofile,
		srvprofile) {

	// $scope.currentPage = 'Notification';
	$scope.$parent.title = "Notification";

	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	// $scope.mynotification = srvprofile.getnotification();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.mynetwork = srvprofile.getnetwork($routeParams);

}

function MyCtrlPrintcampaign($scope, $routeParams, currentvisitedprofile,
		srvprofile) {

	$scope.$parent.title = 'Printcampaign';

	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.printcampaigns = srvprofile.getprintcampaigns($routeParams);

}

function MyCtrlStylesheets($scope, $routeParams, currentvisitedprofile) {

	$scope.$parent.title = 'Style sheets';

	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.searchFilter = new netvogue.searchFilter();
	$scope.stylesseason = {
		"seasonname" : "Spring 2012"
	};
	$scope.styles = [ {
		"stylelistitemid" : "styleId",
		"stylename" : "Studded Winston",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	} ];

}

function MyCtrlStylesheet($scope, $routeParams, currentvisitedprofile) {

	$scope.$parent.title = 'Styles';

	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.searchFilter = new netvogue.searchFilter();
	$scope.stylesseason = {
		"seasonname" : "Spring 2012"
	};
	$scope.styles = [ {
		"stylelistitemid" : "styleId",
		"stylename" : "Studded Winston",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	}, {
		"stylelistitemid" : "styleId",
		"stylename" : "Smith trench",
		"stylebrandname" : "Calvin Klien",
		"styleseason" : "Spring 2012",
		"styledeliverydate" : "25/04/2012",
		"styleprice" : "5000",
		"stylecoverpic" : "http://placehold.it/220x320"

	} ];

}



function MyCtrlViewPhotos($scope, $routeParams, currentvisitedprofile) {

	$scope.$parent.title = 'ViewPhotos';

	$scope.entityname = currentvisitedprofile.getEntityName();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.viewPhotos = [
			{
				"photolistitemid" : "printcampaignId",
				"photoname" : "Calvin Klien",
				"photodescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi"
									+ "porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit",
				"photopic" : "img/donna_karan_adriana_lima_1.jpg"

			},
			{
				"photolistitemid" : "printcampaignId",
				"photoname" : "Calvin Klien",
				"photodescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi"
									+ "porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit",
				"photopic" : "img/donna_karan_adriana_lima_2.jpg"
			
			},
			{
					"photolistitemid" : "printcampaignId",
					"photoname" : "Calvin Klien",
					"photodescription" : "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi"
										+ "porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit",
					"photopic" : "img/donna_karan_adriana_lima_3.jpg"

			}

	];

	$scope.photos = [
	{
		"photolistitemid" : "Id",
		"photoname" : "Studded Winston",
		"photopic" : "http://placehold.it/130x100"

	},
	{
		"photolistitemid" : "Id",
		"photoname" : "Studded Winston",
		"photopic" : "http://placehold.it/130x100"

	},
	{
		"photolistitemid" : "Id",
		"photoname" : "Studded Winston",
		"photopic" : "http://placehold.it/130x100"

	}
	];

}

