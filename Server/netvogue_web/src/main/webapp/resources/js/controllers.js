'use strict';

/* Controllers */
function MyCtrlInitiate($scope, $location) {
	$scope.initiated = false;
	$scope.finish = function() {
		//Send data to server
		$scope.$apply( function() {
			$scope.initiated = true;
		});
		return true;
	};
	$scope.$watch('initiated', function(newValue, oldValue) {
		if(true == newValue) {
			$location.url('/corner');
		}
	});
}

function MyCtrlProfile($scope, $routeParams, srvprofile, currentvisitedprofile, trending, mynetwork) {
    $scope.navClass = function (page1) {
        return {
            //last: this.$last,
            active: page1 && this.currentPage == page1
        };
    };
    $scope.currentPage = 'Profile';
    $scope.$parent.title	= "Profile";
    $scope.isMyProfile 		= currentvisitedprofile.isMyProfile();
	
    //This will initialize all the variables inside controller.
    //Also, if there is any existing data, this data will be shown to user until we get response from server
    $scope.updatedata = function() {
	    $scope.entityname  		= srvprofile.getname($routeParams);
	    $scope.aboutus 			= srvprofile.getaboutus($routeParams);
	    $scope.brandscarried 	= srvprofile.getbrandscarried($routeParams);
	    $scope.productline 		= srvprofile.getproductline($routeParams);
	    $scope.contactinfo 		= srvprofile.getcontactinfo($routeParams);
	    $scope.getcontactinfo 	= addresstostring($scope.contactinfo);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    srvprofile.profileinfo($routeParams).success(function(data) {
    	srvprofile.setProfileLocally(data);
    	$scope.updatedata();
    }).error(function(data) {
    	
    });
    $scope.updatedata();
    
    //Yet to get data about trending and myfriend details
    $scope.trending = trending.getTrending();
    $scope.myfriend = mynetwork.ismyfriend($routeParams);
    $scope.links = currentvisitedprofile.getleftpanellinks();
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

function MyCtrlGallery($scope, $routeParams, $location, currentvisitedprofile, srvgallery, mygallery) {

	$scope.galleryname = "";
	$scope.$parent.title = 'Gallery';
	
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	//Related to search
	$scope.searchgalleryname = "";
	
	//Related to edit gallery
	$scope.editgalleryname   = "";
	$scope.editgalleryid	 = "";
	$scope.showEditGallery	 = false;
	
	var ajaxrequestcall	 = "gallery";
	$scope.updatedata = function() {
	    $scope.entityname  		= srvgallery.getname($routeParams);
	    $scope.galleries		= srvgallery.getgalleries($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getgalleries = function() {
    	srvgallery.galleries("getgalleries", $routeParams, $scope.searchgalleryname).success(function(data) {
        	srvgallery.setgallerylocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    $scope.getgalleries();
    $scope.updatedata();
    
    $scope.creategallery = function() {
    	mygallery.creategallery(ajaxrequestcall, $scope.galleryname).success(function(data) {
    		if(data.status == true) {
    			$location.url("addgallery?id=" + data.idcreated);
    		}
        }).error(function(data) {
        	
        });
    };
    
    $scope.editgallery = function(id, galleryname) {
    	$scope.editgalleryname   = galleryname;
    	$scope.editgalleryid	 = id;
    	$scope.showEditGallery	 = true;
    };
    
    $scope.updategallery = function() {
    	var datatosend = {
				"id" 	: $scope.editgalleryid,
				"value"	: $scope.editgalleryname,
		};
    	mygallery.updategallery(ajaxrequestcall, datatosend).success(function(data) {
    		mygallery.updategallerylocally($scope.editgalleryid, $scope.editgalleryname);
    		$scope.galleries		= srvgallery.getgalleries($routeParams);
    	}).error(function(data) {
 
    	});
    	$scope.showEditGallery	 = false;
    };
    
    $scope.deletegallery = function(galleryid) {
    	mygallery.deletegallery(ajaxrequestcall, galleryid).success(function(data) {
    		mygallery.deletegallerylocally(galleryid);
    		$scope.galleries		= srvgallery.getgalleries($routeParams);
    	}).error(function(data) {
    		
    	});
    };
}

function MyCtrlPhotos($scope, $routeParams, currentvisitedprofile, srvgallery, mygallery) {

	$scope.$parent.title = 'Gallery';
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	var ajaxrequestcall	 = "gallery";
	$scope.searchphotoname = "";
	$scope.updatedata = function() {
	    $scope.entityname  		= srvgallery.getname($routeParams);
	    $scope.galleryname  	= srvgallery.getgalleryname($routeParams);
	    $scope.photogallery		= srvgallery.getphotos($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getphotos = function() {
    	srvgallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, $scope.searchphotoname).success(function(data) {
        	srvgallery.setphotoslocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    
    $scope.getphotos();
    $scope.updatedata();
    
    $scope.deletephoto = function(uniqueid) {
    	mygallery.deletephoto(ajaxrequestcall, uniqueid).success(function(data) {
			mygallery.deletephotoslocally(uniqueid);
			$scope.photogallery	= srvgallery.getphotos($routeParams);
		}).error(function(data) {
			alert("error");
		});
    };
}

function MyCtrlViewPhotos($scope, $routeParams, currentvisitedprofile, srvgallery) {

	$scope.$parent.title = 'ViewPhotos';

	$scope.backButton = currentvisitedprofile.getBackHistory();
		
	$scope.photoid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.photoid = $routeParams.id;
	}
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.galleryid)) {
		$scope.galleryid = $routeParams.galleryid;
	}
	$scope.updatedata = function() {
	    $scope.entityname  		= srvgallery.getname($routeParams);
	    $scope.galleryname  	= srvgallery.getgalleryname($routeParams);
	    $scope.viewPhotos		= srvgallery.getphotos($routeParams);
    };
    
    $scope.updatedata();

    $scope.setphotoid = function(photoid) {
    	$scope.photoid = photoid;
    };
}

function MyCtrlPrintcampaign($scope, $routeParams, $location, currentvisitedprofile, srvgallery, mygallery) {

	$scope.galleryname = "new";
	$scope.gallerydesc = "description";	
	$scope.$parent.title = 'Print Campaigns';
	
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	$scope.searchgalleryname = ""; 
	
	//Related to edit gallery
	$scope.editgalleryname   = "";
	$scope.editgallerydesc   = "";
	$scope.editgalleryid	 = "";
	$scope.showEditGallery	 = false;
	
	var ajaxrequestcall	 = "printcampaign";
	$scope.updatedata = function() {
	    $scope.entityname  		= srvgallery.getname($routeParams);
	    $scope.galleries		= srvgallery.getgalleries($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getgalleries = function() {
    	srvgallery.galleries("getprintcampaigns", $routeParams, $scope.searchgalleryname).success(function(data) {
        	srvgallery.setgallerylocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    $scope.getgalleries();
    
    $scope.creategallery = function() {
    	var jsonrequest = new netvogue.campaignjsonrequest($scope.galleryname, $scope.gallerydesc, "");
    	mygallery.creategallery(ajaxrequestcall, jsonrequest).success(function(data) {
    		if(data.status == true) {
    			$location.url("addprintcampaign?id=" + data.idcreated);
    		}
        }).error(function(data) {
        	
        });
    };
    
    $scope.editgallery = function(id, galleryname, gallerydesc) {
    	$scope.editgalleryname   = galleryname;
    	$scope.editgallerydesc   = gallerydesc;
    	$scope.editgalleryid	 = id;
    	$scope.showEditGallery	 = true;
    };
    
    $scope.updategallery = function() {
    	var jsonrequest = new netvogue.campaignjsonrequest($scope.editgalleryname, $scope.editgallerydesc, $scope.editgalleryid);
    	mygallery.updategallery(ajaxrequestcall, jsonrequest).success(function(data) {
    		mygallery.updategallerylocally($scope.editgalleryid, $scope.editgalleryname);
    		$scope.galleries		= srvgallery.getgalleries($routeParams);
    	}).error(function(data) {
 
    	});
    	$scope.showEditGallery	 = false;
    };
    
    $scope.deletegallery = function(galleryid) {
    	mygallery.deletegallery(ajaxrequestcall, galleryid).success(function(data) {
    		mygallery.deletegallerylocally(galleryid);
    		$scope.galleries		= srvgallery.getgalleries($routeParams);
    	}).error(function(data) {
    		
    	});
    };
}

function MyCtrlCampaign($scope, $routeParams, currentvisitedprofile, srvgallery, mygallery) {

	$scope.$parent.title = 'Print Campaign';
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	var ajaxrequestcall	 = "printcampaign";
	$scope.searchphotoname = "";
	$scope.updatedata = function() {
	    $scope.entityname  		= srvgallery.getname($routeParams);
	    $scope.galleryname  	= srvgallery.getgalleryname($routeParams);
	    $scope.photogallery		= srvgallery.getphotos($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getphotos = function() {
    	srvgallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, $scope.searchphotoname).success(function(data) {
        	srvgallery.setphotoslocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    
    $scope.getphotos();
    $scope.deletephoto = function(uniqueid) {
    	mygallery.deletephoto(ajaxrequestcall, uniqueid).success(function(data) {
			mygallery.deletephotoslocally(uniqueid);
			$scope.photogallery	= srvgallery.getphotos($routeParams);
		}).error(function(data) {
			alert("error");
		});
    };
}

function MyCtrlviewPrintcampaign($scope, $routeParams, currentvisitedprofile, srvgallery) {

	$scope.$parent.title = 'ViewPhotos';

	$scope.backButton = currentvisitedprofile.getBackHistory();
		
	$scope.photoid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.photoid = $routeParams.id;
	}
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.galleryid)) {
		$scope.galleryid = $routeParams.galleryid;
	}
	$scope.updatedata = function() {
	    $scope.entityname  		= srvgallery.getname($routeParams);
	    $scope.galleryname  	= srvgallery.getgalleryname($routeParams);
	    $scope.viewPhotos		= srvgallery.getphotos($routeParams);
    };
    
    $scope.updatedata();

    $scope.setphotoid = function(photoid) {
    	$scope.photoid = photoid;
    };
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

function MyCtrlNewsletter($scope, $routeParams, $location, currentvisitedprofile, srvgallery, mygallery) {

	$scope.galleryname = "new";
	$scope.gallerydesc = "description";	
	$scope.$parent.title = 'News letters';
	
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	//Related to search
	$scope.searchgalleryname = ""; 
	$scope.searchgallerydesc = "";
	
	//Related to edit gallery
	$scope.editgalleryname   = "";
	$scope.editgallerydesc   = "";
	$scope.editgalleryid	 = "";
	$scope.showEditGallery	 = false;
	
	var ajaxrequestcall	 = "editorial";
	$scope.updatedata = function() {
	    $scope.entityname  		= srvgallery.getname($routeParams);
	    $scope.galleries		= srvgallery.getgalleries($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getgalleries = function() {
    	srvgallery.galleries("geteditorials", $routeParams, $scope.searchgalleryname).success(function(data) {
        	srvgallery.setgallerylocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    $scope.getgalleries();
    
    $scope.creategallery = function() {
    	var jsonrequest = new netvogue.campaignjsonrequest($scope.galleryname, $scope.gallerydesc, "");
    	mygallery.creategallery(ajaxrequestcall, jsonrequest).success(function(data) {
    		if(data.status == true) {
    			$location.url("addnewsletter?id=" + data.idcreated);
    		}
        }).error(function(data) {
        	
        });
    };
    
    $scope.editgallery = function(id, galleryname, gallerydesc) {
    	$scope.editgalleryname   = galleryname;
    	$scope.editgallerydesc   = gallerydesc;
    	$scope.editgalleryid	 = id;
    	$scope.showEditGallery	 = true;
    };
    
    $scope.updategallery = function() {
    	var jsonrequest = new netvogue.campaignjsonrequest($scope.editgalleryname, $scope.editgallerydesc, $scope.editgalleryid);
    	mygallery.updategallery(ajaxrequestcall, jsonrequest).success(function(data) {
    		mygallery.updategallerylocally($scope.editgalleryid, $scope.editgalleryname);
    		$scope.galleries		= srvgallery.getgalleries($routeParams);
    	}).error(function(data) {
 
    	});
    	$scope.showEditGallery	 = false;
    };
    
    $scope.deletegallery = function(galleryid) {
    	mygallery.deletegallery(ajaxrequestcall, galleryid).success(function(data) {
    		mygallery.deletegallerylocally(galleryid);
    		$scope.galleries		= srvgallery.getgalleries($routeParams);
    	}).error(function(data) {
    		
    	});
    };
}

function MyCtrlEditorial($scope, $routeParams, currentvisitedprofile, srvgallery, mygallery) {

	$scope.$parent.title = 'Newsletter';
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	var ajaxrequestcall	 = "editorial";
	$scope.searchphotoname = "";
	$scope.updatedata = function() {
	    $scope.entityname  		= srvgallery.getname($routeParams);
	    $scope.galleryname  	= srvgallery.getgalleryname($routeParams);
	    $scope.photogallery		= srvgallery.getphotos($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getphotos = function() {
    	srvgallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, $scope.searchphotoname).success(function(data) {
        	srvgallery.setphotoslocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    
    $scope.getphotos();
    $scope.deletephoto = function(uniqueid) {
    	mygallery.deletephoto(ajaxrequestcall, uniqueid).success(function(data) {
			mygallery.deletephotoslocally(uniqueid);
			$scope.photogallery	= srvgallery.getphotos($routeParams);
		}).error(function(data) {
			alert("error");
		});
    };

}


function MyCtrlViewNewsLetters($scope, $routeParams, currentvisitedprofile, srvgallery) {

	$scope.$parent.title = 'ViewNewsLetter';

	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	$scope.photoid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.photoid = $routeParams.id;
	}
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.galleryid)) {
		$scope.galleryid = $routeParams.galleryid;
	}
	$scope.updatedata = function() {
	    $scope.entityname  		= srvgallery.getname($routeParams);
	    $scope.galleryname  	= srvgallery.getgalleryname($routeParams);
	    $scope.viewPhotos		= srvgallery.getphotos($routeParams);
    };
    
    $scope.updatedata();

    $scope.setphotoid = function(photoid) {
    	$scope.photoid = photoid;
    };

}

function MyCtrlCollections($scope, $routeParams, $location, currentvisitedprofile, srvcollection, mycollection) {

	$scope.$parent.title = 'Collections';
	
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();

	//Related to new collection
	$scope.collectionname 		= "new";
	$scope.collectiondesc 		= "description";
	$scope.collectioncategory	= "";
	$scope.defaultcategories	= netvogue.defaultproductlines;
	
	//Related to edit collection
	$scope.editcollectionname   = "";
	$scope.editcollectiondesc   = "";
	$scope.editcollectioncat	= "";
	var editcollectionid	 	= "";
	$scope.showEditCollection	= false;
	
	$scope.searchcollectionname = "";
	$scope.searchcollectioncat  = "";
	$scope.searchbrandname		= "";
	$scope.updatedata = function() {
	    $scope.entityname  		= srvcollection.getname($routeParams);
	    $scope.collections		= srvcollection.getcollections($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getcollections = function() {
    	var searchcollections = {
    			"galleryname" 	:$scope.searchcollectionname,
    			"category"		:$scope.searchcollectioncat,
    			"brandname"		:$scope.searchbrandname,
    	};
    	srvcollection.collections($routeParams, searchcollections).success(function(data) {
    		srvcollection.setcollectionlocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    $scope.getcollections();
    
    $scope.createcollection = function() {
    	var jsonrequest = new netvogue.collectionjsonrequest($scope.collectionname, $scope.collectiondesc,
    																			$scope.collectioncategory);
    	mycollection.createcollection(jsonrequest).success(function(data) {
    		if(data.status == true) {
    			$location.url("addcollections?id=" + data.idcreated);
    		}
        }).error(function(data) {
        	
        });
    };
    
    $scope.editcollection = function(id, name, desc, category) {
    	$scope.editcollectionname   = name;
    	$scope.editcollectiondesc   = desc;
    	editcollectionid	 		= id;
    	$scope.editcollectioncat	= category;
    	$scope.showEditCollection	= true;
    };
    
    $scope.updatecollection = function() {
    	var jsonrequest = new netvogue.collectionjsonrequest($scope.editcollectionname, 
    			$scope.editcollectiondesc, $scope.editcollectioncat, editcollectionid);
    	mycollection.updatecollection(jsonrequest).success(function(data) {
    		mycollection.updatecollectionlocally($scope.editgalleryid, $scope.editgalleryname);//Azeez
    		$scope.collections		= srvcollection.getcollections($routeParams);
    	}).error(function(data) {
 
    	});
    	$scope.showEditCollection	 = false;
    };
    
    $scope.deletecollection = function(id) {
    	mycollection.deletecollection(id).success(function(data) {
    		mycollection.deletecollectionlocally(id);
    		$scope.collections		= srvcollection.getcollections($routeParams);
    	}).error(function(data) {
    		
    	});
    };

	$scope.searchFilter = new netvogue.searchFilter();
}

function MyCtrlCollection($scope, $routeParams, currentvisitedprofile, srvcollection, mycollection) {

	$scope.$parent.title = "Collection";
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	$scope.updatedata = function() {
	    $scope.entityname  		= srvcollection.getname($routeParams);
	    $scope.galleryname  	= srvcollection.getgalleryname($routeParams);
	    $scope.collections		= srvcollection.getcollections($routeParams);
	    $scope.photogallery		= srvcollection.getphotos($routeParams);
    };
    
    //searchcollection is not required. had to send it ,as it is mandatory in backend. need to change in backend later
    var searchcollections = {
			"galleryname" 	:"",
			"category"		:"",
			"brandname"		:""
	};
	srvcollection.collections($routeParams, searchcollections).success(function(data) {
		srvcollection.setcollectionlocally(data, $routeParams);
    	$scope.updatedata();
    }).error(function(data) {
    	
    });
	
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getphotos = function() {
    	srvcollection.photos($routeParams, $scope.galleryid, "").success(function(data) {
    		srvcollection.setphotoslocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    
    $scope.getphotos();
    $scope.deletephoto = function(uniqueid) {
    	mycollection.deletephoto(uniqueid).success(function(data) {
			mycollection.deletephotoslocally(uniqueid);
			$scope.photogallery	= srvgallery.getphotos($routeParams);
		}).error(function(data) {
			alert("error");
		});
    };
    
    $scope.setgalleryid = function(galleryid) {
    	$scope.galleryid = galleryid;
    	$scope.getphotos();
    };
}

function MyCtrlViewcollection($scope, $routeParams, currentvisitedprofile, srvcollection) {

	$scope.$parent.title = 'ViewCollection';
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	$scope.photoid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.photoid = $routeParams.id;
	}
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.galleryid)) {
		$scope.galleryid = $routeParams.galleryid;
	}
	
	$scope.collectionlikes = 0;
	$scope.updatedata = function() {
	    $scope.entityname  		= srvcollection.getname($routeParams);
	    $scope.galleryname  	= srvcollection.getgalleryname($routeParams);
	    $scope.viewPhotos		= srvcollection.getphotos($routeParams);
    };
    
    $scope.updatedata();

    $scope.setphotoid = function(photoid) {
    	$scope.photoid = photoid;
    };
}

function MyCtrlStylesheets($scope, $routeParams, $location, currentvisitedprofile, srvstylesheet, mystylesheet) {
	
	$scope.$parent.title = 'Style sheets';
	
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();

	//Related to new stylesheet
	$scope.stylesheetname = "new";
	$scope.stylesheetcat  = "description";
	$scope.defaultcategories	= netvogue.defaultproductlines;
	
	//Related to search
	$scope.searchstylesheetname = ""; 
	$scope.searchstylesheetcat  = "";
	
	//Related to edit stylesheet
	$scope.editstylesheetname   = "";
	$scope.editstylesheetcat    = "";
	$scope.editstylesheetid	 	= "";
	$scope.showEditStylesheet	 = false;	
	
	$scope.updatedata = function() {
	    $scope.entityname  		= srvstylesheet.getname($routeParams);
	    $scope.stylesheets		= srvstylesheet.getstylesheets($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getstylesheets = function() {
    	var searchstylesheets = {
    			"stylesheetname" :$scope.searchstylesheetname,
    			"category"		 :$scope.searchstylesheetcat,
    	};
    	srvstylesheet.stylesheets($routeParams, searchstylesheets).success(function(data) {
    		srvstylesheet.setstylesheetlocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    $scope.getstylesheets();
    
    $scope.createstylesheet = function() {
    	var jsonrequest = new netvogue.stylesheetjsonrequest($scope.stylesheetname, $scope.stylesheetcat);
    	mystylesheet.createstylesheet(jsonrequest).success(function(data) {
    		if(data.status == true) {
    			$location.url("addstyle?id=" + data.idcreated + "&&cat=" + netvogue.getparentcategory($scope.stylesheetcat));
    		}
        }).error(function(data) {
        	
        });
    };
    
    $scope.editstylesheet = function(id, name, category) {
    	$scope.editstylesheetname   = name;
    	$scope.editstylesheetcat    = category;
    	$scope.editstylesheetid	 	= id;
    	$scope.showEditStylesheet	= true;
    };
    
    $scope.updatestylesheet = function() {
    	var jsonrequest = new netvogue.stylesheetjsonrequest($scope.editstylesheetname, $scope.editstylesheetcat, $scope.editstylesheetid);
    	mystylesheet.updatestylesheet(jsonrequest).success(function(data) {
    		mystylesheet.updatestylesheetlocally(jsonrequest);
    		$scope.stylesheets		= srvstylesheet.getstylesheets($routeParams);
    	}).error(function(data) {
 
    	});
    	$scope.showEditStylesheet	 = false;
    };
    
    $scope.deletestylesheet = function(id) {
    	mystylesheet.deletestylesheet(id).success(function(data) {
    		mystylesheet.deletestylesheetlocally(id);
    		$scope.stylesheets		= srvstylesheet.getstylesheets($routeParams);
    	}).error(function(data) {
    		
    	});
    };

	$scope.searchFilter = new netvogue.searchFilter();
}

function MyCtrlStylesheet($scope, $routeParams, currentvisitedprofile, srvstylesheet, mystylesheet) {

	$scope.$parent.title = 'Styles';

	$scope.isMyProfile 		= currentvisitedprofile.isMyProfile();
	if(!$scope.isMyProfile) {
		$location.url($routeParams.profileid + "/stylesheets");
	}
	
	if($scope.$parent.iambrand == false) {
		$location.url("stylesheets");
	}
	
    $scope.stylesheetid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.stylesheetid = $routeParams.id;
	}
	$scope.category = "";
	if (!angular.isUndefined($routeParams.cat)) {
		$scope.category = netvogue.getparentcategory($routeParams.cat);
	}
	
	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.searchFilter = new netvogue.searchFilter();
	
	$scope.updatedata = function() {
	    $scope.entityname  		= srvstylesheet.getname($routeParams);
	    $scope.stylesheetname  	= srvstylesheet.getstylesheetname($routeParams);
	    $scope.styles			= srvstylesheet.getstyles($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    srvstylesheet.styles($routeParams, $scope.stylesheetid, "").success(function(data) {
    	srvstylesheet.setstyleslocally(data, $routeParams);
    	$scope.updatedata();
    }).error(function(data) {
    	
    });
    
	$scope.deletestyle = function(styleid) {
		mystylesheet.deletestyle(styleid).success(function(data) {
			mystylesheet.deletestyleslocally(styleid);
			$scope.styles	= srvstylesheet.getstyles($routeParams);
		}).error(function(data) {
			alert("error: " + data.error);
		});
	};
}

function MyCtrlLinesheets($scope, $routeParams, currentvisitedprofile, srvlinesheet, mylinesheet) {

	$scope.$parent.title = 'Linesheets';
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	$scope.searchFilter = new netvogue.searchFilter();
	
	$scope.immediate = "true";
	$scope.editimmediate = "false";

	//Related to new stylesheet
	$scope.linesheetname = "new";
	$scope.linesheetcat  = "";
	$scope.deliverydate  = "";
	$scope.defaultcategories	= netvogue.defaultproductlines;
	
	//Related to search
	$scope.searchlinesheetname = ""; 
	$scope.searchlinesheetcat  = "";
	
	//Related to edit stylesheet
	$scope.editlinesheetname   = "";
	$scope.editlinesheetcat    = "";
	$scope.editdeliverydate    = "";
	$scope.editlinesheetid	 	= "";
	$scope.showEditLinesheet	 = false;	
	
	$scope.updatedata = function() {
	    $scope.entityname  		= srvlinesheet.getname($routeParams);
	    $scope.linesheets		= srvlinesheet.getlinesheets($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getlinesheets = function() {
    	var searchlinesheets = {
    			"linesheetname" :$scope.searchlinesheetname,
    			"category"		 :$scope.searchlinesheetcat,
    	};
    	srvlinesheet.linesheets($routeParams, searchlinesheets).success(function(data) {
    		srvlinesheet.setlinesheetlocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    $scope.getlinesheets();
    
    $scope.createlinesheet = function() {
    	if("true" == $scope.immediate)
    		$scope.deliverydate = 0;
    	var jsonrequest = new netvogue.linesheetjsonrequest($scope.linesheetname, $scope.linesheetcat, $scope.deliverydate);
    	mylinesheet.createlinesheet(jsonrequest).success(function(data) {
    		if(data.status == true) {
    			//$location.url("addstyle?id=" + data.idcreated);
    		}
        }).error(function(data) {
        	
        });
    };
    
    $scope.editlinesheet = function(id, name, category, deliverydate) {
    	$scope.editlinesheetname   	= name;
    	$scope.editlinesheetcat    	= category;
    	$scope.editlinesheetid	 	= id;
    	$scope.editdeliverydate    	= deliverydate;
    	if(0 == $scope.editdeliverydate)
    		$scope.editimmediate = "true";
    	$scope.showEditLinesheet	= true;
    };
    
    $scope.updatelinesheet = function() {
    	var jsonrequest = new netvogue.linesheetjsonrequest($scope.editlinesheetname, 
    			$scope.editlinesheetcat, editdeliverydate, $scope.editlinesheetid);
    	mylinesheet.updatelinesheet(jsonrequest).success(function(data) {
    		mylinesheet.updatelinesheetlocally(jsonrequest);
    		$scope.linesheets		= srvlinesheet.getlinesheets($routeParams);
    	}).error(function(data) {
 
    	});
    	$scope.showEditLinesheet	 = false;
    };
    
    $scope.deletelinesheet = function(id) {
    	mylinesheet.deletelinesheet(id).success(function(data) {
    		mylinesheet.deletelinesheetlocally(id);
    		$scope.linesheets		= srvlinesheet.getlinesheets($routeParams);
    	}).error(function(data) {
    		
    	});
    };
}

function MyCtrlStylesPreview($scope, currentvisitedprofile) {

	$scope.$parent.title = 'Style';
	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.stylethumbnails = [ {
		"stylethumbnailsid" : "videoid",
		"stylethumbnail" : "img/donna_karan_adriana_lima_1.jpg"

	}, {
		"stylethumbnailsid" : "videoid",
		"stylethumbnail" : "img/donna_karan_adriana_lima_2.jpg"

	}, {
		"stylethumbnailsid" : "videoid",
		"stylethumbnail" : "img/donna_karan_adriana_lima_3.jpg"

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
		"stylecover" : $scope.stylethumbnails[0].stylethumbnail//"http://placehold.it/290x400"
	};
	
	$scope.SetmainImage=function(index)
	{
		$scope.styletdetails.stylecover = $scope.stylethumbnails[index].stylethumbnail;//"http://placehold.it/290x400"
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

function MyCtrlProfileSettings($scope, $routeParams, $http, myprofile, srvprofile) {
	
	$scope.$parent.title 	= "Profile Settings";
	$scope.productline		= netvogue.defaultproductlines; //First get all the available product lines
	//Related to brands carried/Stockists
	$scope.brandsentered	= ""; //USer input in text box
	$scope.brands 			= []; //List of brands for searching
	$scope.brandscarried	= new netvogue.hashtable();  //Names entered in control
	var brandsReceived		= new netvogue.hashtable();
	$scope.updatedata = function() {
    	$scope.aboutus 			= myprofile.getaboutus();
    	var productlinestemp 	= myprofile.getproductline();
	    for(var product in productlinestemp) {
	    	for(var defaultproduct in $scope.productline) {
	    		if(productlinestemp[product].productlinename == $scope.productline[defaultproduct].productlinename) {
	    			$scope.productline[defaultproduct].selected = true;
	    		}
	    	}
		}
	    $scope.contactinfo 		= myprofile.getcontactinfo();
	    var brandscarriedtemp 	= myprofile.getbrandscarried();
	    $scope.brandscarried.clear();
	    for(var i in brandscarriedtemp) {
	    	brandsReceived.setItem(brandscarriedtemp[i].brandname, brandscarriedtemp[i].brandusername);
	    	$scope.brandscarried.setItem(brandscarriedtemp[i].brandname, brandscarriedtemp[i].brandusername);
	    }
	};
	
	/*If user comes here through 'edit' of profile page, we are not going to get the latest information.
	  Other three ways, where user can come here are,
	  	1. By clicking 'Profile Settings' in Settings Menu -- angular.isUndefined($routeParams.t) identifies this
	  	2. By directly giving /profilesettings in browser  -- angular.isUndefined($routeParams.t) or netvogue.yettocontact identify this 
	  	3. By directly giving /profilesettings?t=0 in browser -- netvogue.yettocontact identifies this 
	  	
	  	This condition is required only to make sure that we load existing(old) information when user comes through 'edit' of profile page.
	  	Otherwise, we could have sent request to server everything like in all other pages
	*/
	if(netvogue.yettocontact || angular.isUndefined($routeParams.t)) {
		//Get all the profile data from the Server. Make AJAX call
		srvprofile.profileinfo($routeParams).success(function(data) {
        	srvprofile.setProfileLocally(data);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
	} else {
		//This else is not required in other pages except user comes to edit pages through 'edit'. ex: profilesettings, editcollections etc
		//This else condition will avoid showing old information 
		//In all other pages this should be called even after making call to server, to show user the old data till we get it from server
		$scope.updatedata();
	}
	
    if(angular.isUndefined($routeParams.t)) {
    	$scope.tabposition = 0;
    } else {
    	$scope.tabposition = $routeParams.t;
    }
    
    //Set Functions
    $scope.updateaboutus = function (aboutme, event) {
    	angular.element(event.srcElement).button('loading');
    	myprofile.posttoserver(aboutme, "aboutus").success(function(data) {
        	if(data.status == true) {
        		myprofile.setaboutus(aboutme);
        	} else {
        		alert(data.error);
        	}
        	angular.element(event.srcElement).button('reset');
        }).error(function(data) {
        	alert(data.error);
        	angular.element(event.srcElement).button('reset');
        });
    };
    $scope.updatecontactinfo = function (contactinfotemp, event) {
    	angular.element(event.srcElement).button('loading');
    	myprofile.posttoserver(contactinfotemp, "contactinfo").success(function(data) {
        	if(data.status == true) {
        		myprofile.setcontactinfo(contactinfotemp);
        	} else {
        		alert(data.error);
        	}
        	angular.element(event.srcElement).button('reset');
        }).error(function(data) {
        	alert(data.error);
        	angular.element(event.srcElement).button('reset');
        });
    };
    $scope.updateproductline = function (productlinetemp, event) {
    	angular.element(event.srcElement).button('loading');
    	var productlines = [];
    	for(var productLine in productlinetemp) {
			if(productlinetemp[productLine].selected == true) {
				productlines.push(productlinetemp[productLine].id);
			}
		}
    	myprofile.posttoserver(productlines, "productline").success(function(data) {
        	if(data.status == true) {
        		myprofile.setproductline(productlinetemp);
        	} else {
        		alert(data.error);
        	}
        	angular.element(event.srcElement).button('reset');
        }).error(function(data) {
        	alert(data.error);
        	angular.element(event.srcElement).button('reset');
        });      
    };
    $scope.updatebrandscarried = function (event) {
    	angular.element(event.srcElement).button('loading');
    	var finalbrandscarriedlist = $scope.brandscarried.values();
    	myprofile.posttoserver(finalbrandscarriedlist, "brandscarried").success(function(data) {
        	if(data.status == true) {
        		myprofile.setbrandscarried($scope.brandscarried);
        	} else {
        		alert(data.error);
        	}
        	angular.element(event.srcElement).button('reset');
        }).error(function(data) {
        	alert(data.error);
        	angular.element(event.srcElement).button('reset');
        });       
    };
    //Adding and removing into brands carried control
    $scope.brandsenteredchanged = function(brandsentered){
		if("" == brandsentered){
			return
		}
		var datatosend = {
				"username" : brandsentered
		};
		var entity = $scope.$parent.iambrand?"brand":"boutique";
		var config = {
                method: "GET",
                params: datatosend,
                url: entity + "/usersavailable"
            };
        $http(config).success(function(data) {
        	$scope.brands.splice(0, $scope.brands.length);
        	for(var user in data){
        		//Check if this data is already in brands carried. Then dont add it here.
        		if(undefined == $scope.brandscarried.getItem(data[user].name))
        			$scope.brands.push(data[user].name);
            	brandsReceived.setItem(data[user].name, data[user].username);
            };
        });
    };
    $scope.addBrandsCarried = function(brandscarried) {
		if("" == brandscarried) {
			return;
		}
		var username = brandsReceived.getItem(brandscarried);
		if(null == username)
			username = brandscarried;
    	
		$scope.brandscarried.setItem(brandscarried, username);
		$scope.brandsentered = "";
	};
    $scope.removeBrandsCarried = function(key) {
    	$scope.brandscarried.removeItem(key);
    };
    $scope.searchFilter =  new netvogue.searchFilter();
}

function MyCtrlAccountSettings($scope, $routeParams, $http, myprofile, srvprofile) {
	
	$scope.$parent.title 	= "Account Settings";
	$scope.password			= "";
		
	
	//We are getting latest information everytime from server. Not relying on any existing information
	//Do i really need to get all profile information. isn't name and email id is enough?
	srvprofile.profileinfo($routeParams).success(function(data) {
    	srvprofile.setProfileLocally(data);
    	$scope.updatedata();
    }).error(function(data) {
    	
    });
	  
    $scope.updatedata = function() {
    	//Use entityname as name of the variable for boutique/brand name. As we have same name variable in Main controller scope as well.
    	//Main controllers name will get displayed until we get data from server
    	$scope.entityname  		= myprofile.getname();
    	$scope.email 			= myprofile.getemail();
	};
    
    //Set Name
    $scope.updatename = function (newname, event) {
    	if(newname == $scope.entityname)
    		return
    	angular.element(event.srcElement).button('loading');
    	var datatosend = {
    						"data" 		: newname,
    						"password"	: $scope.password
    					 };
    	myprofile.posttoserver(datatosend, "name").success(function(data) {
        	if(data.status == true) {
        		myprofile.setname(newname);
        	} else {
        		alert(data.error);
        	}
        	angular.element(event.srcElement).button('reset');
        	$scope.password = "";
        }).error(function(data) {
        	alert(data.error);
        	angular.element(event.srcElement).button('reset');
        	$scope.password = "";
        });
    };
    
    $scope.updatepassword = function (newpassword, event) {
    	if(newpassword == $scope.password)
    		return;
    	angular.element(event.srcElement).button('loading');
    	var datatosend = {
    						"data" 		: newpassword,
    						"password"	: $scope.password //Current password
    					 };
    	myprofile.posttoserver(datatosend, "password").success(function(data) {
        	if(!data.status) {
        		alert(data.error);
        	}
        	angular.element(event.srcElement).button('reset');
        	$scope.password = "";
        }).error(function(data) {
        	alert(data.error);
        	angular.element(event.srcElement).button('reset');
        	$scope.password = "";
        });
    };
    
    $scope.updateemail = function (newemail, event) {
    	if(newemail == $scope.email)
    		return;
    	angular.element(event.srcElement).button('loading');
    	var datatosend = {
    						"data" 		: newemail,
    						"password"	: $scope.password
    					 };
    	myprofile.posttoserver(datatosend, "email").success(function(data) {
        	if(data.status == true) {
        		myprofile.setemail(newemail);
        	} else {
        		alert(data.error);
        	}
        	angular.element(event.srcElement).button('reset');
        	$scope.password = "";
        }).error(function(data) {
        	alert(data.error);
        	angular.element(event.srcElement).button('reset');
        	$scope.password = "";
        });
    };
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
		"stylethumbnail" : "img/donna_karan_adriana_lima_1.jpg"

	}, {
		"stylethumbnailsid" : "videoid",
		"stylethumbnail" : "img/donna_karan_adriana_lima_2.jpg"

	}, {
		"stylethumbnailsid" : "videoid",
		"stylethumbnail" : "img/donna_karan_adriana_lima_3.jpg"

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
		"stylecover" : $scope.stylethumbnails[0].stylethumbnail//"http://placehold.it/290x400"
	};
	
	$scope.SetmainImage=function(index)
	{
		$scope.styletdetails.stylecover = $scope.stylethumbnails[index].stylethumbnail;//"http://placehold.it/290x400"
	}

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