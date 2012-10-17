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
    if (!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
	}
    
    $scope.entityname  		= "";
    $scope.profilepic		= "";
    $scope.links = currentvisitedprofile.getleftpanellinks();
    
    $scope.showCreateNetwork = false;
    //This will initialize all the variables inside controller.
    //Also, if there is any existing data, this data will be shown to user until we get response from server
    $scope.updatedata = function() {
	    $scope.entityname  		= srvprofile.getname($routeParams);
	    $scope.iambrand			= srvprofile.isbrand($routeParams);
	    $scope.aboutus 			= srvprofile.getaboutus($routeParams);
	    $scope.profilepic		= srvprofile.getprofilepic($routeParams);
	    $scope.brandscarried 	= srvprofile.getbrandscarried($routeParams);
	    $scope.productline 		= srvprofile.getproductline($routeParams);
	    $scope.contactinfo 		= srvprofile.getcontactinfo($routeParams);
	    $scope.getcontactinfo 	= addresstostring($scope.contactinfo);
	    
	    //PENDING CONFIRMED NONE
	    if(!$scope.isMyProfile) {
	    	$scope.networkstatus = srvprofile.getnetworkstatus();
	    }
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    srvprofile.profileinfo($routeParams).success(function(data) {
    	if(data.status == true) {
    		srvprofile.setProfileLocally(data, $routeParams);
    		$scope.updatedata();
    	} else {
    		alert("No user available with this name");
    	}
    }).error(function(data) {
    	
    });
    
    $scope.shownetwork = function() {
    	$scope.showCreateNetwork = true;
    };
    
    $scope.createnetwork = function() {
    	mynetwork.createnetwork($scope.profileid).success(function(data) {
        	if(data.status == true) {
        		$scope.networkstatus = "PENDING";
        	} else {
        		alert(data.error);
        	}
        	$scope.showCreateNetwork = false;
    	}).error(function(data) {
    		
    	});
    };
        
    //Yet to get data about trending and myfriend details
    $scope.trending = trending.getTrending();
}

function MyCtrlNetwork($scope, $routeParams, myprofile, currentvisitedprofile,
		srvnetwork, mynetwork, trending) {
	$scope.navClass = function(page1) {
		return {
			// last: this.$last,
			active : page1 && this.currentPage == page1
		};
	};
	$scope.currentPage = 'Network';
	$scope.$parent.title = "Network";
	$scope.isMyProfile 		= currentvisitedprofile.isMyProfile();

	$scope.entityname = "";
	$scope.profilepic = "";
	$scope.links = currentvisitedprofile.getleftpanellinks();
	
	$scope.updatedata = function() {
		$scope.entityname 		= srvnetwork.getname($routeParams);
		$scope.iambrand			= srvnetwork.isbrand($routeParams);
		$scope.profilepic 		= srvnetwork.getprofilepic($routeParams);
		$scope.contactinfo		= srvnetwork.getcontactinfo($routeParams);
		$scope.mynetwork 		= srvnetwork.getnetworks($routeParams);
		$scope.getcontactinfo 	= addresstostring($scope.contactinfo);
	};
	
	srvnetwork.networks($routeParams).success(function(data) {
		srvnetwork.setnetworkslocally($routeParams, data);
		$scope.updatedata();
	}).error(function(data) {
		
	});
	
	$scope.confirmnetwork = function(profileid) {
		mynetwork.confirmnetwork(profileid).success(function(data) {
			if(data.status == true) {
				mynetwork.confirmnetworklocally(profileid);
				$scope.mynetwork 		= srvnetwork.getnetworks($routeParams);
			} else {
				alert(data.error);
			}
		}).error(function(data) {
			alert("error");
		});
	};
	
	$scope.deletenetwork = function(profileid) {
		mynetwork.deletenetwork(profileid).success(function(data) {
			mynetwork.deletenetworklocally(profileid);
			$scope.mynetwork 		= srvnetwork.getnetworks($routeParams);
		}).error(function(data) {
			
		});
	};
	
	$scope.blocknetwork = function(profileid) {
		mynetwork.blocknetwork(profileid).success(function(data) {
			mynetwork.blocknetworklocally(profileid);
			$scope.mynetwork 		= srvnetwork.getnetworks($routeParams);
		}).error(function(data) {
			
		});
	};
	
	$scope.unblocknetwork = function(profileid) {
		mynetwork.unblocknetwork(profileid).success(function(data) {
			mynetwork.deletenetworklocally(profileid);
			$scope.mynetwork 		= srvnetwork.getnetworks($routeParams);
		}).error(function(data) {
			
		});
	};
	
	//trending data
	$scope.trending = trending.getTrending();
}

function MyCtrlCorner($scope, $routeParams, srvtimeline, mytimeline, currentvisitedprofile, trending) {
	$scope.navClass = function(page1) {
		return {
			// last: this.$last,
			active : page1 && this.currentPage == page1
		};
	};
	$scope.currentPage = 'Corner';
	$scope.$parent.title = "Corner";
	$scope.isMyProfile 		= currentvisitedprofile.isMyProfile();

	$scope.entityname = "";
	$scope.profilepic = "";
	$scope.links = currentvisitedprofile.getleftpanellinks();
	if (!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
	}
	
	$scope.routeparams = {
			
	};
	$scope.newupdate ="";
	$scope.updatedata = function(routeparams) {
		$scope.entityname 		= srvtimeline.getname(routeparams);
		$scope.iambrand			= srvtimeline.isbrand(routeparams);
		$scope.profilepic 		= srvtimeline.getprofilepic(routeparams);
		$scope.contactinfo		= srvtimeline.getcontactinfo(routeparams);
		$scope.newsfeeds 		= srvtimeline.getupdates(routeparams);
		$scope.getcontactinfo 	= addresstostring($scope.contactinfo);
		
		createpusherchannel();
	};
	
	$scope.getupdates = function(routeparams) {
		srvtimeline.updates(routeparams).success(function(data) {
			srvtimeline.setupdateslocally(routeparams, data);
			$scope.updatedata(routeparams);
		}).error(function(data) {
		});
	};
	
	$scope.getmyupdates = function() {
		$scope.routeparams.profileid = $scope.$parent.myprofileid;
		$scope.getupdates($scope.routeparams);
	};
	
	$scope.getallupdates = function() {
		$scope.routeparams.profileid = undefined;
		$scope.getupdates($routeParams);
	};
	
	$scope.getallupdates();
	
	$scope.addupdate = function() {
		mytimeline.addupdate($scope.newupdate).success(function(data) {
			if(data != null) {
				mytimeline.addupdatelocally(data);
				$scope.newsfeeds 		= srvtimeline.getupdates($routeParams);
				$scope.newupdate = "";
			} else {
				alert("There is some error");
			}
		}).error(function(data) {
			alert("error");
		});
	};
	
	$scope.editupdate = function(id, update) {
		mytimeline.editupdate(id, update).success(function(data) {
			if(data.status == true) {
				mytimeline.editupdatelocally(id, update);
				$scope.newsfeeds 		= srvtimeline.getupdates($routeParams);
			} else {
				alert(data.error);
			}
		}).error(function(data) {
			alert("error");
		});
	};
	
	$scope.deleteupdate = function(id) {
		mytimeline.deleteupdate(id).success(function(data) {
			if(data.status == true) {
				mytimeline.editupdatelocally(id);
				$scope.newsfeeds 		= srvtimeline.getupdates($routeParams);
			} else {
				alert(data.error);
			}
		}).error(function(data) {
			alert("error");
		});
	};

	//for new updates
	var createpusherchannel = function() {
		var id;
		if (!angular.isUndefined($routeParams.profileid)) {
			id = $routeParams.profileid;
		} else {
			id = $scope.$parent.myprofileid;
		}
		
		//Register pusher to receive notifications
		var channel = $scope.$parent.pusher.subscribe(id);
		channel.bind('statusupdate', function(data) {
		      $scope.newsfeeds.push(data);
		 });
	};
	$scope.trending = trending.getTrending();
}

function MyCtrlGallery($scope, $routeParams, $location, currentvisitedprofile, mygallery) {

	$scope.$parent.title = 'Gallery';
	
	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
	}
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	$scope.galleryname = "";
	//Related to search
	$scope.searchgalleryname = "";
	
	//Related to edit gallery
	$scope.editgalleryname   = "";
	$scope.editgalleryid	 = "";
	$scope.showEditGallery	 = false;
	
	var ajaxrequestcall	 = "gallery";
	$scope.updatedata = function() {
	    $scope.entityname  		= mygallery.getname($routeParams);
	    $scope.iambrand			= mygallery.isbrand($routeParams);
	    $scope.profilepic		= mygallery.getprofilepic($routeParams);
	    $scope.galleries		= mygallery.getgalleries($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getgalleries = function() {
    	mygallery.galleries("getgalleries", $routeParams, $scope.searchgalleryname).success(function(data) {
    		mygallery.setgallerylocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    $scope.getgalleries();
    
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
    		$scope.galleries		= mygallery.getgalleries($routeParams);
    	}).error(function(data) {
 
    	});
    	$scope.showEditGallery	 = false;
    };
    
    $scope.deletegallery = function(galleryid) {
    	mygallery.deletegallery(ajaxrequestcall, galleryid).success(function(data) {
    		mygallery.deletegallerylocally(galleryid);
    		$scope.galleries		= mygallery.getgalleries($routeParams);
    	}).error(function(data) {
    		
    	});
    };
}

function MyCtrlPhotos($scope, $routeParams, currentvisitedprofile, mygallery) {

	$scope.$parent.title = 'Gallery';
	
	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
	}
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	var ajaxrequestcall	 = "gallery";
	$scope.searchphotoname = "";
	$scope.updatedata = function() {
	    $scope.entityname  		= mygallery.getname($routeParams);
	    $scope.iambrand			= mygallery.isbrand($routeParams);
	    $scope.profilepic		= mygallery.getprofilepic($routeParams);
	    $scope.galleryname  	= mygallery.getgalleryname($routeParams);
	    $scope.photogallery		= mygallery.getphotos($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getphotos = function() {
    	mygallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, $scope.searchphotoname).success(function(data) {
    		mygallery.setphotoslocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    $scope.getphotos();
    
    $scope.deletephoto = function(uniqueid) {
    	mygallery.deletephoto(ajaxrequestcall, uniqueid).success(function(data) {
			mygallery.deletephotoslocally(uniqueid);
			$scope.photogallery	= mygallery.getphotos($routeParams);
		}).error(function(data) {
			alert("error");
		});
    };
}

function MyCtrlViewPhotos($scope, $routeParams, currentvisitedprofile, mygallery) {

	$scope.$parent.title = 'ViewPhotos';

	$scope.photoid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.photoid = $routeParams.id;
	}
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.galleryid)) {
		$scope.galleryid = $routeParams.galleryid;
	}
	
	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.updatedata = function() {
	    $scope.entityname  		= mygallery.getname($routeParams);
	    $scope.iambrand			= mygallery.isbrand($routeParams);
	    $scope.profilepic		= mygallery.getprofilepic($routeParams);
	    $scope.galleryname  	= mygallery.getgalleryname($routeParams);
	    $scope.viewPhotos		= mygallery.getphotos($routeParams);
    };
    
    mygallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, $scope.searchphotoname).success(function(data) {
    	mygallery.setphotoslocally(data, $routeParams);
    	$scope.updatedata();
    }).error(function(data) {
    	
    });
    
    $scope.setphotoid = function(photoid) {
    	$scope.photoid = photoid;
    };
}

function MyCtrlPrintcampaign($scope, $routeParams, $location, currentvisitedprofile, mygallery) {

	$scope.$parent.title = 'Print Campaigns';
	
	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
	}
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	$scope.galleryname = "new";
	$scope.gallerydesc = "description";	
	
	$scope.searchgalleryname = ""; 
	
	//Related to edit gallery
	$scope.editgalleryname   = "";
	$scope.editgallerydesc   = "";
	$scope.editgalleryid	 = "";
	$scope.showEditGallery	 = false;
	
	var ajaxrequestcall	 = "printcampaign";
	$scope.updatedata = function() {
	    $scope.entityname  		= mygallery.getname($routeParams);
	    $scope.iambrand			= mygallery.isbrand($routeParams);
	    $scope.profilepic		= mygallery.getprofilepic($routeParams);
	    $scope.galleries		= mygallery.getgalleries($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getgalleries = function() {
    	mygallery.galleries("getprintcampaigns", $routeParams, $scope.searchgalleryname).success(function(data) {
    		mygallery.setgallerylocally(data, $routeParams);
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
    		$scope.galleries		= mygallery.getgalleries($routeParams);
    	}).error(function(data) {
 
    	});
    	$scope.showEditGallery	 = false;
    };
    
    $scope.deletegallery = function(galleryid) {
    	mygallery.deletegallery(ajaxrequestcall, galleryid).success(function(data) {
    		mygallery.deletegallerylocally(galleryid);
    		$scope.galleries		= mygallery.getgalleries($routeParams);
    	}).error(function(data) {
    		
    	});
    };
}

function MyCtrlCampaign($scope, $routeParams, currentvisitedprofile, mygallery) {

	$scope.$parent.title = 'Print Campaign';
	
	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
	}
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	var ajaxrequestcall	 = "printcampaign";
	$scope.searchphotoname = "";
	$scope.updatedata = function() {
	    $scope.entityname  		= mygallery.getname($routeParams);
	    $scope.iambrand			= mygallery.isbrand($routeParams);
	    $scope.profilepic		= mygallery.getprofilepic($routeParams);
	    $scope.galleryname  	= mygallery.getgalleryname($routeParams);
	    $scope.photogallery		= mygallery.getphotos($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getphotos = function() {
    	mygallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, $scope.searchphotoname).success(function(data) {
    		mygallery.setphotoslocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    
    $scope.getphotos();
    $scope.deletephoto = function(uniqueid) {
    	mygallery.deletephoto(ajaxrequestcall, uniqueid).success(function(data) {
			mygallery.deletephotoslocally(uniqueid);
			$scope.photogallery	= mygallery.getphotos($routeParams);
		}).error(function(data) {
			alert("error");
		});
    };
}

function MyCtrlviewPrintcampaign($scope, $routeParams, currentvisitedprofile) {

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
	var ajaxrequestcall	 = "printcampaign";
	$scope.updatedata = function() {
	    $scope.entityname  		= mygallery.getname($routeParams);
	    $scope.iambrand			= mygallery.isbrand($routeParams);
	    $scope.profilepic		= mygallery.getprofilepic($routeParams);
	    $scope.galleryname  	= mygallery.getgalleryname($routeParams);
	    $scope.viewPhotos		= mygallery.getphotos($routeParams);
    };
    
    $scope.updatedata();
    
    mygallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, $scope.searchphotoname).success(function(data) {
    	mygallery.setphotoslocally(data, $routeParams);
    	$scope.updatedata();
    }).error(function(data) {
    	
    });
    
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

function MyCtrlNewsletter($scope, $routeParams, $location, currentvisitedprofile, mygallery) {

	$scope.$parent.title = 'News letters';
	
	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
	}
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	$scope.galleryname = "new";
	$scope.gallerydesc = "description";	
	
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
	    $scope.entityname  		= mygallery.getname($routeParams);
	    $scope.iambrand			= mygallery.isbrand($routeParams);
	    $scope.profilepic		= mygallery.getprofilepic($routeParams);
	    $scope.galleries		= mygallery.getgalleries($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getgalleries = function() {
    	mygallery.galleries("geteditorials", $routeParams, $scope.searchgalleryname).success(function(data) {
    		mygallery.setgallerylocally(data, $routeParams);
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
    		$scope.galleries		= mygallery.getgalleries($routeParams);
    	}).error(function(data) {
 
    	});
    	$scope.showEditGallery	 = false;
    };
    
    $scope.deletegallery = function(galleryid) {
    	mygallery.deletegallery(ajaxrequestcall, galleryid).success(function(data) {
    		mygallery.deletegallerylocally(galleryid);
    		$scope.galleries		= mygallery.getgalleries($routeParams);
    	}).error(function(data) {
    		
    	});
    };
}

function MyCtrlEditorial($scope, $routeParams, currentvisitedprofile, mygallery) {

	$scope.$parent.title = 'Newsletter';
	
	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
	}
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	var ajaxrequestcall	 = "editorial";
	$scope.searchphotoname = "";
	$scope.updatedata = function() {
	    $scope.entityname  		= mygallery.getname($routeParams);
	    $scope.iambrand			= mygallery.isbrand($routeParams);
	    $scope.profilepic		= mygallery.getprofilepic($routeParams);
	    $scope.galleryname  	= mygallery.getgalleryname($routeParams);
	    $scope.photogallery		= mygallery.getphotos($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getphotos = function() {
    	mygallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, $scope.searchphotoname).success(function(data) {
    		mygallery.setphotoslocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    
    $scope.getphotos();
    $scope.deletephoto = function(uniqueid) {
    	mygallery.deletephoto(ajaxrequestcall, uniqueid).success(function(data) {
			mygallery.deletephotoslocally(uniqueid);
			$scope.photogallery	= mygallery.getphotos($routeParams);
		}).error(function(data) {
			alert("error");
		});
    };
}

function MyCtrlViewNewsLetters($scope, $routeParams, currentvisitedprofile, mygallery) {

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
	var ajaxrequestcall	 = "editorial";
	$scope.updatedata = function() {
	    $scope.entityname  		= mygallery.getname($routeParams);
	    $scope.iambrand			= mygallery.isbrand($routeParams);
	    $scope.profilepic		= mygallery.getprofilepic($routeParams);
	    $scope.galleryname  	= mygallery.getgalleryname($routeParams);
	    $scope.viewPhotos		= mygallery.getphotos($routeParams);
    };
    
    $scope.getphotos = function() {
    	mygallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, $scope.searchphotoname).success(function(data) {
    		mygallery.setphotoslocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    
    $scope.getphotos();

    $scope.setphotoid = function(photoid) {
    	$scope.photoid = photoid;
    };

}

function MyCtrlCollections($scope, $routeParams, $location, currentvisitedprofile, mycollection) {

	$scope.$parent.title = 'Collections';
	
	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
	}
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
	    $scope.entityname  		= mycollection.getname($routeParams);
	    $scope.profilepic		= mycollection.getprofilepic($routeParams);
	    $scope.collections		= mycollection.getcollections($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getcollections = function() {
    	var searchcollections = {
    			"galleryname" 	:$scope.searchcollectionname,
    			"category"		:$scope.searchcollectioncat,
    			"brandname"		:$scope.searchbrandname,
    	};
    	mycollection.collections($routeParams, searchcollections).success(function(data) {
    		mycollection.setcollectionlocally(data, $routeParams);
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
    		$scope.collections		= mycollection.getcollections($routeParams);
    	}).error(function(data) {
 
    	});
    	$scope.showEditCollection	 = false;
    };
    
    $scope.deletecollection = function(id) {
    	mycollection.deletecollection(id).success(function(data) {
    		mycollection.deletecollectionlocally(id);
    		$scope.collections		= mycollection.getcollections($routeParams);
    	}).error(function(data) {
    		
    	});
    };

	$scope.searchFilter = new netvogue.searchFilter();
}

function MyCtrlCollection($scope, $routeParams, currentvisitedprofile, mycollection) {

	$scope.$parent.title = "Collection";
	
	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
	}
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	$scope.updatedata = function() {
	    $scope.entityname  		= mycollection.getname($routeParams);
	    $scope.profilepic		= mycollection.getprofilepic($routeParams);
	    $scope.galleryname  	= mycollection.getgalleryname($routeParams);
	    $scope.collections		= mycollection.getcollections($routeParams);
	    $scope.photogallery		= mycollection.getphotos($routeParams);
    };
    
    //searchcollection is not required. had to send it ,as it is mandatory in backend. need to change in backend later
    var searchcollections = {
			"galleryname" 	:"",
			"category"		:"",
			"brandname"		:""
	};
    mycollection.collections($routeParams, searchcollections).success(function(data) {
    	mycollection.setcollectionlocally(data, $routeParams);
		$scope.collections		= mycollection.getcollections($routeParams);
    }).error(function(data) {
    	
    });
	
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getphotos = function() {
    	mycollection.photos($routeParams, $scope.galleryid, "").success(function(data) {
    		mycollection.setphotoslocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    
    $scope.getphotos();
    $scope.deletephoto = function(uniqueid) {
    	mycollection.deletephoto(uniqueid).success(function(data) {
			mycollection.deletephotoslocally(uniqueid);
			$scope.photogallery	= mycollection.getphotos($routeParams);
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
	    $scope.profilepic		= srvcollection.getprofilepic($routeParams);
	    $scope.galleryname  	= srvcollection.getgalleryname($routeParams);
	    $scope.viewPhotos		= srvcollection.getphotos($routeParams);
    };
    
    $scope.getphotos = function() {
    	srvcollection.photos($routeParams, $scope.galleryid, "").success(function(data) {
    		srvcollection.setphotoslocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    
    $scope.getphotos();
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
	    $scope.profilepic		= srvstylesheet.getprofilepic($routeParams);
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
	    $scope.profilepic		= srvstylesheet.getprofilepic($routeParams);
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

function MyCtrlStylesPreview($scope, $routeParams, currentvisitedprofile, srvstylesheet, mystylesheet) {

	$scope.$parent.title = 'Style';
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
    $scope.stylesheetid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.stylesheetid = $routeParams.id;
	}
	$scope.category = "";
	if (!angular.isUndefined($routeParams.cat)) {
		$scope.category = $routeParams.cat;
	}
	if (!angular.isUndefined($routeParams.styleid)) {
		$scope.styleid = $routeParams.styleid;
	}
	
	$scope.updatedata = function() {
	    $scope.entityname  		= srvstylesheet.getname($routeParams);
	    $scope.stylesheetname  	= srvstylesheet.getstylesheetname($routeParams);
	    $scope.styles			= srvstylesheet.getstyles($routeParams);
    	for(var i=0; i < $scope.styles.length; i++) {
    		if($scope.styleid == $scope.styles[i].styleid) {
    			$scope.mainstyle = $scope.styles[i];
    			break;
    		}
    	}
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    srvstylesheet.styles($routeParams, $scope.stylesheetid, "").success(function(data) {
    	srvstylesheet.setstyleslocally(data, $routeParams);
    	$scope.updatedata();
    }).error(function(data) {
    	
    });
    
    $scope.setmainstyle = function(style) {
    	$scope.mainstyle = style;
    };
    
	$scope.SetmainImage=function(index)
	{
		$scope.styletdetails.stylecover = $scope.stylethumbnails[index].stylethumbnail;//"http://placehold.it/290x400"
	};
}

function MyCtrlLinesheets($scope, $routeParams, $location, currentvisitedprofile, srvlinesheet, mylinesheet) {

	$scope.$parent.title = 'Linesheets';
	
	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
	}
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
	    $scope.profilepic		= srvlinesheet.getprofilepic($routeParams);
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
    			$location.url("addlinesheets?id=" + data.idcreated + "&&cat=" + $scope.linesheetcat);
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
    			$scope.editlinesheetcat, $scope.editdeliverydate, $scope.editlinesheetid);
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


function MyCtrlStyles($scope, $routeParams, currentvisitedprofile, srvlinesheet, mylinesheet) {

	$scope.$parent.title = 'Styles';

	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
	}
	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.isMyProfile 		= currentvisitedprofile.isMyProfile();
	
	if($scope.$parent.iambrand == false) {
		$location.url("linesheets");
	}
	
    $scope.linesheetid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.linesheetid = $routeParams.id;
	}
	$scope.category = "";
	if (!angular.isUndefined($routeParams.cat)) {
		$scope.category = $routeParams.cat;
	}
	
	$scope.updatedata = function() {
	    $scope.entityname  		= srvlinesheet.getname($routeParams);
	    $scope.profilepic		= srvlinesheet.getprofilepic($routeParams);
	    $scope.linesheetname  	= srvlinesheet.getlinesheetname($routeParams);
	    $scope.linesheets		= srvlinesheet.getlinesheets($routeParams);
	    $scope.styles			= srvlinesheet.getstyles($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getstyles = function() {
	    srvlinesheet.styles($routeParams, $scope.linesheetid, "").success(function(data) {
	    	srvlinesheet.setstyleslocally(data, $routeParams);
	    	$scope.updatedata();
	    }).error(function(data) {
	    	
	    });
    };
    $scope.getstyles();
    
    $scope.setlinesheet = function(linesheetid) {
    	$scope.linesheetid = linesheetid;
    	$scope.getstyles();
    };
}

function MyCtrlStyle($scope, $routeParams, currentvisitedprofile, srvlinesheet, mylinesheet) {

	$scope.$parent.title = 'Style';
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	$scope.linesheetid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.linesheetid = $routeParams.id;
	}
	$scope.category = "";
	if (!angular.isUndefined($routeParams.cat)) {
		$scope.category = $routeParams.cat;
	}
	if (!angular.isUndefined($routeParams.styleid)) {
		$scope.styleid = $routeParams.styleid;
	}
	
	$scope.updatedata = function() {
	    $scope.entityname  		= srvlinesheet.getname($routeParams);
	    $scope.profilepic		= srvlinesheet.getprofilepic($routeParams);
	    $scope.linesheetname  	= srvlinesheet.getlinesheetname($routeParams);
	    $scope.styles			= srvlinesheet.getstyles($routeParams);
    	for(var i=0; i < $scope.styles.length; i++) {
    		if($scope.styleid == $scope.styles[i].styleid) {
    			$scope.mainstyle = $scope.styles[i];
    			break;
    		}
    	}
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    srvlinesheet.styles($routeParams, $scope.linesheetid, "").success(function(data) {
    	srvlinesheet.setstyleslocally(data, $routeParams);
    	$scope.updatedata();
    }).error(function(data) {
    	
    });
    
    $scope.setmainstyle = function(style) {
    	$scope.mainstyle = style;
    };	
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
    	$scope.profilepic		= myprofile.getprofilepic();
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
        		$scope.showalertAU=true;
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
        		$scope.showalert=true;
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
        		$scope.showalertPL=true;
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
    	$scope.profilepic		= myprofile.getprofilepic();
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

function MyCtrlAdvancedSearch($scope, $http, search) {

	$scope.$parent.title = 'AdvancedSearch';
	$scope.pagenumber = 0;
	$scope.pagenumbers = [ 1, 2, 3, 4 ];
	$scope.pageSize = 12;
	
	//Search related
	$scope.isbrandsearch = "true";
	$scope.search = {
			name: "",
			location:"",
			stockists: []
	};
	
	search.getallusers().success(function(data) {
		$scope.entityname		= data.name;
		$scope.profilepic		= data.profilepic;
		$scope.advancedsearch	= data.users;
	}).error(function(data) {
		
	});
	$scope.searchFilter = new netvogue.searchFilter();
	$scope.getsearchResults = function() {
		var categories = $scope.searchFilter.getCheckedFilters();
		search.getadvancedsearchresults($scope.search.name, $scope.search.location, categories, $scope.isbrandsearch).
		success(function(data) {
			$scope.advancedsearch = data;
	    }).
	    error(function(data) {
	    	
	    });
	};
	
	$scope.brandsenteredchanged = function(brandsentered) {
		var datatosend = {
				"username" : brandsentered
		};
		var entity = "";
		if($scope.isbrandsearch) {
			entity="brand";
		} else {
			entity="boutique";
		}
		var config = {
                method: "GET",
                params: datatosend,
                url: entity + "/usersavailable"
            };
        $http(config).success(function(data) {
            $scope.usersavailable = data;
        });
	};
	
	$scope.brandsenteredchanged("");
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

function MyCtrlNotifications($scope, $routeParams, currentvisitedprofile, mynotifications, mynetwork) {

	// $scope.currentPage = 'Notification';
	$scope.$parent.title = "Notification";

	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	// $scope.mynotification = srvprofile.getnotification();
	$scope.backButton = currentvisitedprofile.getBackHistory();

	$scope.entityname = "";
	$scope.profilepic = "";
	$scope.updatenotifications = function() {
		$scope.entityname 		= mynotifications.getname();
		$scope.profilepic 		= mynotifications.getprofilepic();
		$scope.notifications 	= mynotifications.getnotifications();
	};
	
	mynotifications.notifications().success(function(data) {
		mynotifications.setnotifications(data);
		$scope.updatenotifications();
	}).error(function(data) {
		
	});
	
	$scope.confirmnetwork = function(profileid) {
		mynetwork.confirmnetwork(profileid).success(function(data) {
			mynotifications.confirmnetworklocally(profileid);
			$scope.notifications 	= mynotifications.getnotifications();
		}).error(function(data) {
			
		});
	};
	
	$scope.discardnetwork = function(profileid) {
		mynotifications.discardnetwork(profileid).success(function(data) {
			mynotifications.discardnetworklocally(profileid);
			$scope.notifications 	= mynotifications.getnotifications();
		}).error(function(data) {
			
		});
	};
}