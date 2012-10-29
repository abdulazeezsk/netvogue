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

function MyCtrlProfile($scope, $routeParams, $timeout, srvprofile, currentvisitedprofile, 
		trending, myreferences, mynetwork) {
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
		$scope.entityname = "";
		$scope.profilepic = "";
	}
    $scope.links = currentvisitedprofile.getleftpanellinks();
    $scope.gettingprofile = true;
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
    		$scope.gettingprofile = false;
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
    
    $scope.pagenumber = 0;
    var getreferences = function() {
	    myreferences.references($scope.pagenumber).success(function(data) {
	    	if(null != data && data.length > 0) {
		    	$scope.references = angular.copy(data);
		    	/*$timeout(function() {
		    		$scope.pagenumber++;
		    		getreferences();
		    	}, 5000);*/
	    	}
	    }).error(function(data) {
	    	
	    });
    };
    getreferences();
    
    //Yet to get data about trending and myfriend details
    $scope.trending = trending.getTrending();
}

function MyCtrlNetwork($scope, $routeParams, $timeout, myprofile, currentvisitedprofile, mynetwork, 
		myreferences, trending) {
	$scope.navClass = function(page1) {
		return {
			// last: this.$last,
			active : page1 && this.currentPage == page1
		};
	};
	$scope.currentPage = 'Network';
	$scope.$parent.title = "Network";
	$scope.isMyProfile 		= currentvisitedprofile.isMyProfile();
	if (!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
		$scope.entityname = "";
		$scope.profilepic = "";
	}
	$scope.links = currentvisitedprofile.getleftpanellinks();
	
	$scope.gettingnetwork = true;
	$scope.updatedata = function() {
		$scope.entityname 		= mynetwork.getname();
		$scope.iambrand			= mynetwork.isbrand();
		$scope.profilepic 		= mynetwork.getprofilepic();
		$scope.contactinfo		= mynetwork.getcontactinfo();
		$scope.mynetwork 		= mynetwork.getnetworks();
		$scope.getcontactinfo 	= addresstostring($scope.contactinfo);
	};
	
	mynetwork.networks($routeParams, false).success(function(data) {
		mynetwork.setnetworkslocally(data);
		$scope.updatedata();
		$scope.gettingnetwork = false;
	}).error(function(data) {
		
	});
	
	$scope.confirmnetwork = function(profileid) {
		mynetwork.confirmnetwork(profileid).success(function(data) {
			if(data.status == true) {
				mynetwork.confirmnetworklocally(profileid);
				$scope.mynetwork 		= mynetwork.getnetworks();
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
			$scope.mynetwork 		= mynetwork.getnetworks();
		}).error(function(data) {
			
		});
	};
	
	$scope.blocknetwork = function(profileid) {
		mynetwork.blocknetwork(profileid).success(function(data) {
			mynetwork.blocknetworklocally(profileid);
			$scope.mynetwork 		= mynetwork.getnetworks();
		}).error(function(data) {
			
		});
	};
	
	$scope.unblocknetwork = function(profileid) {
		mynetwork.unblocknetwork(profileid).success(function(data) {
			mynetwork.deletenetworklocally(profileid);
			$scope.mynetwork 		= mynetwork.getnetworks();
		}).error(function(data) {
			
		});
	};
	
	$scope.pagenumber = 0;
    var getreferences = function() {
	    myreferences.references($scope.pagenumber).success(function(data) {
	    	if(null != data && data.length > 0) {
		    	$scope.references = angular.copy(data);
		    	/*$timeout(function() {
		    		$scope.pagenumber++;
		    		getreferences();
		    	}, 5000);*/
	    	}
	    }).error(function(data) {
	    	
	    });
    };
    getreferences();
	//trending data
	$scope.trending = trending.getTrending();
}

function MyCtrlCorner($scope, $routeParams, $timeout, mytimeline, currentvisitedprofile, 
		mynetwork, trending, myreferences) {
	$scope.navClass = function(page1) {
		return {
			// last: this.$last,
			active : page1 && this.currentPage == page1
		};
	};
	$scope.currentPage = 'Corner';
	$scope.$parent.title = "Corner";
	$scope.isMyProfile 		= currentvisitedprofile.isMyProfile();
	$scope.gettingupdates = true;
	$scope.links = currentvisitedprofile.getleftpanellinks();
	if (!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
		$scope.entityname = "";
		$scope.profilepic = "";
	}
	
	$scope.routeparams = {
			
	};
	$scope.newupdate ="";
	
	$scope.updatedata = function(routeparams) {
		$scope.entityname 		= mytimeline.getname(routeparams);
		$scope.iambrand			= mytimeline.isbrand(routeparams);
		$scope.profilepic 		= mytimeline.getprofilepic(routeparams);
		$scope.contactinfo		= mytimeline.getcontactinfo(routeparams);
		$scope.newsfeeds 		= mytimeline.getupdates(routeparams);
		$scope.getcontactinfo 	= addresstostring($scope.contactinfo);
		
		createpusherchannel();
	};
	
	$scope.getupdates = function(routeparams) {
		mytimeline.updates(routeparams).success(function(data) {
			mytimeline.setupdateslocally(data);
			$scope.updatedata(routeparams);
			$scope.gettingupdates = false;
		}).error(function(data) {
		});
	};
	
	$scope.getmyupdates = function() {
		$scope.routeparams.profileid = $scope.$parent.myprofileid;
		$scope.getupdates($scope.routeparams);
	};	
	$scope.getallupdates = function() {
		$scope.routeparams.profileid = undefined;
		$scope.getupdates($routeParams); //This is capital P only -- not a mistake
	};	
	$scope.getallupdates();
	
	mynetwork.networks($routeParams, true).success(function(data) {
		$scope.mynetwork 		= data.networks;
	}).error(function(data) {
		
	});
	
	$scope.addupdate = function() {
		$scope.addingupdate = true;
		mytimeline.addupdate($scope.newupdate).success(function(data) {
			if(data != null) {
				mytimeline.addupdatelocally(data);
				$scope.newsfeeds 		= mytimeline.getupdates($scope.routeparams);
				$scope.newupdate = "";
			} else {
				alert("There is some error");
			}
			$scope.addingupdate = false;
		}).error(function(data) {
			alert("error");
			$scope.addingupdate = false;
		});
	};
	
	$scope.editupdate = function(id, update) {
		mytimeline.editupdate(id, update).success(function(data) {
			if(data.status == true) {
				mytimeline.editupdatelocally(id, update);
				$scope.newsfeeds 		= mytimeline.getupdates($scope.routeparams);
			} else {
				alert(data.error);
			}
		}).error(function(data) {
			alert("error");
		});
	};
	
	$scope.deleteupdate = function(id) {
		$scope.deletingupdate = true;
		mytimeline.deleteupdate(id).success(function(data) {
			if(data.status == true) {
				mytimeline.editupdatelocally(id);
				$scope.newsfeeds 		= mytimeline.getupdates($scope.routeparams);
			} else {
				alert(data.error);
			}
			$scope.deletingupdate = false;
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
	
	$scope.pagenumber = 0;
    var getreferences = function() {
	    myreferences.references($scope.pagenumber).success(function(data) {
	    	if(null != data && data.length > 0) {
		    	$scope.references = angular.copy(data);
		    	/*$timeout(function() {
		    		$scope.pagenumber++;
		    		getreferences();
		    	}, 5000);*/
	    	}
	    }).error(function(data) {
	    	
	    });
    };
    getreferences();
	$scope.trending = trending.getTrending();
}

function MyCtrlGallery($scope, $routeParams, $location, currentvisitedprofile, mygallery) {

	$scope.$parent.title = 'Gallery';
	
	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
		$scope.entityname = "";
		$scope.profilepic = "";
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
	$scope.gettinggallery = false;
	
	$scope.updatedata = function() {
	    $scope.entityname  		= mygallery.getname($routeParams);
	    $scope.iambrand			= mygallery.isbrand($routeParams);
	    $scope.profilepic		= mygallery.getprofilepic($routeParams);
	    $scope.galleries		= mygallery.getgalleries($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getgalleries = function() {
    	$scope.gettinggallery = true;
    	mygallery.galleries("getgalleries", $routeParams, $scope.searchgalleryname).success(function(data) {
    		$scope.gettinggallery = false;
    		mygallery.setgallerylocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	$scope.gettinggallery = false;
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
		$scope.entityname = "";
		$scope.profilepic = "";
	}
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	var ajaxrequestcall	 = "gallery";
	$scope.searchphotoname = "";
	$scope.gettingphotos = false;
	
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
    	$scope.gettingphotos = true;
    	mygallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, $scope.searchphotoname).success(function(data) {
    		mygallery.setphotoslocally(data, $routeParams);
        	$scope.updatedata();
        	$scope.gettingphotos = false;
        }).error(function(data) {
        	$scope.gettingphotos = false;
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
	
	$scope.entityname = "";
	$scope.profilepic = "";
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
		$scope.entityname = "";
		$scope.profilepic = "";
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
	$scope.gettinggallery = false;
	
	$scope.updatedata = function() {
	    $scope.entityname  		= mygallery.getname($routeParams);
	    $scope.iambrand			= mygallery.isbrand($routeParams);
	    $scope.profilepic		= mygallery.getprofilepic($routeParams);
	    $scope.galleries		= mygallery.getgalleries($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getgalleries = function() {
    	$scope.gettinggallery = true;
    	mygallery.galleries("getprintcampaigns", $routeParams, $scope.searchgalleryname).success(function(data) {
    		mygallery.setgallerylocally(data, $routeParams);
        	$scope.updatedata();
        	$scope.gettinggallery = false;
        }).error(function(data) {
        	$scope.gettinggallery = false;
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
		$scope.entityname = "";
		$scope.profilepic = "";
	}
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	var ajaxrequestcall	 = "printcampaign";
	$scope.gettingphotos = false;
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
        	$scope.gettingphotos = true;
        }).error(function(data) {
        	$scope.gettingphotos = false;
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

function MyCtrlNewsletter($scope, $routeParams, $location, currentvisitedprofile, mygallery) {

	$scope.$parent.title = 'News letters';
	
	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
		$scope.entityname = "";
		$scope.profilepic = "";
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
	$scope.gettinggallery = false;
	
	$scope.updatedata = function() {
	    $scope.entityname  		= mygallery.getname($routeParams);
	    $scope.iambrand			= mygallery.isbrand($routeParams);
	    $scope.profilepic		= mygallery.getprofilepic($routeParams);
	    $scope.galleries		= mygallery.getgalleries($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getgalleries = function() {
    	$scope.gettinggallery = true;
    	mygallery.galleries("geteditorials", $routeParams, $scope.searchgalleryname).success(function(data) {
    		$scope.gettinggallery = false;
    		mygallery.setgallerylocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	$scope.gettinggallery = false;
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
		$scope.entityname = "";
		$scope.profilepic = "";
	}
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	var ajaxrequestcall	 = "editorial";
	$scope.gettingphotos = false;
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
        	$scope.gettingphotos = true;
        }).error(function(data) {
        	$scope.gettingphotos = false;
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
		$scope.entityname = "";
		$scope.profilepic = "";
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
	$scope.searchbrandname		= "";
	$scope.searchFilter = new netvogue.searchFilter();
	
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
    			"category"		:$scope.searchFilter.getCheckedFilters(),
    			"brandname"		:$scope.searchbrandname,
    	};
    	mycollection.collections($routeParams, searchcollections).success(function(data) {
    		mycollection.setcollectionlocally(data);
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
    		mycollection.updatecollectionlocally(editcollectionid, $scope.editcollectionname, $scope.editcollectiondesc);
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
}

function MyCtrlCollection($scope, $routeParams, currentvisitedprofile, mycollection) {

	$scope.$parent.title = "Collection";
	
	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
		$scope.entityname = "";
		$scope.profilepic = "";
	}
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	$scope.updatedata = function() {
	    $scope.entityname  		= mycollection.getname();
	    $scope.profilepic		= mycollection.getprofilepic();
	    $scope.galleryname  	= mycollection.getgalleryname();
	    $scope.brandname		= mycollection.getbrandname();
	    $scope.collections		= mycollection.getcollections();
	    $scope.photogallery		= mycollection.getphotos();
    };
    
    //searchcollection is not required. had to send it ,as it is mandatory in backend. need to change in backend later
    var searchcollections = {
			"galleryname" 	:"",
			"category"		:"",
			"brandname"		:""
	};
    mycollection.collections($routeParams, searchcollections).success(function(data) {
    	mycollection.setcollectionlocally(data);
		$scope.collections		= mycollection.getcollections();
    }).error(function(data) {
    	
    });
	
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getphotos = function() {
    	mycollection.photos($routeParams, $scope.galleryid, "").success(function(data) {
    		mycollection.setphotoslocally(data);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    
    $scope.getphotos();
    $scope.deletephoto = function(uniqueid) {
    	mycollection.deletephoto(uniqueid).success(function(data) {
			mycollection.deletephotoslocally(uniqueid);
			$scope.photogallery	= mycollection.getphotos();
		}).error(function(data) {
			alert("error");
		});
    };
    
    $scope.setgalleryid = function(galleryid) {
    	$scope.galleryid = galleryid;
    	$scope.getphotos();
    };
}

function MyCtrlViewcollection($scope, $routeParams, currentvisitedprofile, mycollection) {

	$scope.$parent.title = 'ViewCollection';
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	$scope.photoid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.photoid = $routeParams.id;
		$scope.entityname = "";
		$scope.profilepic = "";
	}
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.galleryid)) {
		$scope.galleryid = $routeParams.galleryid;
	}
	
	$scope.collectionlikes = 0;
	$scope.updatedata = function() {
	    $scope.entityname  		= mycollection.getname($routeParams);
	    $scope.profilepic		= mycollection.getprofilepic($routeParams);
	    $scope.galleryname  	= mycollection.getgalleryname($routeParams);
	    $scope.viewPhotos		= mycollection.getphotos($routeParams);
    };
    
    $scope.getphotos = function() {
    	mycollection.photos($routeParams, $scope.galleryid, "").success(function(data) {
    		mycollection.setphotoslocally(data, $routeParams);
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

function MyCtrlStylesheets($scope, $routeParams, $location, currentvisitedprofile, mystylesheet) {
	
	$scope.$parent.title = 'Style sheets';
	
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();

	//Related to new stylesheet
	$scope.stylesheetname = "new";
	$scope.stylesheetcat  = "description";
	$scope.defaultcategories	= netvogue.defaultproductlines;
	
	//Related to search
	$scope.searchstylesheetname = ""; 
	$scope.searchFilter = new netvogue.searchFilter();
	
	//Related to edit stylesheet
	$scope.editstylesheetname   = "";
	$scope.editstylesheetcat    = "";
	$scope.editstylesheetid	 	= "";
	$scope.showEditStylesheet	 = false;	
	
	$scope.gettingstylesheets = false;
	
	$scope.updatedata = function() {
	    $scope.entityname  		= mystylesheet.getname($routeParams);
	    $scope.profilepic		= mystylesheet.getprofilepic($routeParams);
	    $scope.stylesheets		= mystylesheet.getstylesheets($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getstylesheets = function() {
    	var searchstylesheets = {
    			"stylesheetname" :$scope.searchstylesheetname,
    			"category"		 :$scope.searchFilter.getCheckedFilters()
    	};
    	$scope.gettingstylesheets = true;
    	mystylesheet.stylesheets($routeParams, searchstylesheets).success(function(data) {
    		mystylesheet.setstylesheetlocally(data, $routeParams);
        	$scope.updatedata();
        	$scope.gettingstylesheets = false;
        }).error(function(data) {
        	$scope.gettingstylesheets = false;
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
    		$scope.stylesheets		= mystylesheet.getstylesheets($routeParams);
    	}).error(function(data) {
 
    	});
    	$scope.showEditStylesheet	 = false;
    };
    
    $scope.deletestylesheet = function(id) {
    	mystylesheet.deletestylesheet(id).success(function(data) {
    		mystylesheet.deletestylesheetlocally(id);
    		$scope.stylesheets		= mystylesheet.getstylesheets($routeParams);
    	}).error(function(data) {
    		
    	});
    };
}

function MyCtrlStylesheet($scope, $routeParams, currentvisitedprofile, mystylesheet) {

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
	
	//Search related
	$scope.searchstyleno = "";
	$scope.searchfabrication = "";
	$scope.searchfromprice = 0;
	$scope.searchtoprice = 0;
	$scope.searchstylesheetname = "";
	
	$scope.gettingstyles = false;
	
	$scope.updatedata = function() {
	    $scope.entityname  		= mystylesheet.getname($routeParams);
	    $scope.profilepic		= mystylesheet.getprofilepic($routeParams);
	    $scope.stylesheetname  	= mystylesheet.getstylesheetname($routeParams);
	    $scope.brandname		= mystylesheet.getbrandname();
	    $scope.styles			= mystylesheet.getstyles($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    var getstyles = function() {
	    if(null == $scope.fromprice)
			$scope.fromprice = 0;
		if(null == $scope.toprice)
			$scope.toprice = 0;
		$scope.gettingstyles = true;
	    mystylesheet.styles($routeParams, $scope.stylesheetid, $scope.searchstyleno, $scope.searchfabrication, 
	    		$scope.searchfromprice, $scope.searchtoprice).success(function(data) {
	    	mystylesheet.setstyleslocally(data, $routeParams);
	    	$scope.updatedata();
	    	$scope.gettingstyles = false;
	    }).error(function(data) {
	    	alert(data);
	    	$scope.gettingstyles = false;
	    });
    };
    getstyles();
    
    $scope.getstylesheets = function() {
    	var searchstylesheets = {
    			"stylesheetname" :$scope.searchstylesheetname,
    	};
    	mystylesheet.stylesheets($routeParams, searchstylesheets).success(function(data) {
    		mystylesheet.setstylesheetlocally(data, $routeParams);
    		$scope.stylesheets		= mystylesheet.getstylesheets($routeParams);
        }).error(function(data) {
        	alert(data);
        });
    };
    $scope.getstylesheets();
    
    $scope.setstylesheet = function(galleryid) {
    	$scope.stylesheetid = id;
    	$scope.getstyles();
    };
    
	$scope.deletestyle = function(styleid) {
		mystylesheet.deletestyle(styleid).success(function(data) {
			mystylesheet.deletestyleslocally(styleid);
			$scope.styles	= mystylesheet.getstyles($routeParams);
		}).error(function(data) {
			alert("error: " + data.error);
		});
	};
}

function MyCtrlStylesPreview($scope, $routeParams, currentvisitedprofile, mystylesheet) {

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
	    $scope.entityname  		= mystylesheet.getname($routeParams);
	    $scope.stylesheetname  	= mystylesheet.getstylesheetname($routeParams);
	    $scope.brandname		= mystylesheet.getbrandname();
	    $scope.styles			= mystylesheet.getstyles($routeParams);
    	for(var i=0; i < $scope.styles.length; i++) {
    		if($scope.styleid == $scope.styles[i].styleid) {
    			$scope.mainstyle = $scope.styles[i];
    			break;
    		};
    	};
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    mystylesheet.styles($routeParams, $scope.stylesheetid, "").success(function(data) {
    	mystylesheet.setstyleslocally(data, $routeParams);
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

function MyCtrlLinesheets($scope, $routeParams, $location, currentvisitedprofile, mylinesheet) {

	$scope.$parent.title = 'Linesheets';
	
	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
		$scope.entityname = "";
		$scope.profilepic = "";
	}
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	
	$scope.immediate = "true";
	$scope.editimmediate = "false";

	//Related to new stylesheet
	$scope.linesheetname = "new";
	$scope.linesheetcat  = "";
	$scope.deliverydate  = "";
	$scope.defaultcategories	= netvogue.defaultproductlines;
	
	//Related to search
	$scope.searchlinesheets = new netvogue.linesheetsearchjsonrequest();
	$scope.searchFilter = new netvogue.searchFilter();
	$scope.searchdeliverydate = "all";
	$scope.fromdate = "";
	$scope.todate	= "";
	
	//Related to edit stylesheet
	$scope.editlinesheetname   = "";
	$scope.editlinesheetcat    = "";
	$scope.editdeliverydate    = "";
	$scope.editlinesheetid	 	= "";
	$scope.showEditLinesheet	 = false;	
	
	$scope.updatedata = function() {
	    $scope.entityname  		= mylinesheet.getname($routeParams);
	    $scope.profilepic		= mylinesheet.getprofilepic($routeParams);
	    $scope.linesheets		= mylinesheet.getlinesheets($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getlinesheets = function(search) {
    	var getdata;
    	if(search)
    		getdata = mylinesheet.linesheets($routeParams, $scope.searchlinesheets);
    	else 
    		getdata = mylinesheet.linesheets($routeParams);
    	getdata.success(function(data) {
    		mylinesheet.setlinesheetlocally(data, $routeParams);
        	$scope.updatedata();
        }).error(function(data) {
        	
        });
    };
    $scope.getlinesheets(false);
    
    var firstcall = true;
    $scope.searchlinesheetsfn = function() {
    	if($scope.searchdeliverydate == "all") {
    		$scope.searchlinesheets.fromdate 	= "0";
    		$scope.searchlinesheets.todate		= "0";
    		$scope.fromdate = "";
    		$scope.todate	= "";
    	} else if($scope.searchdeliverydate == "immediates") {
    		$scope.searchlinesheets.fromdate 	= "Immediate";
    		$scope.searchlinesheets.todate 		= "Immediate";
    		$scope.fromdate = "";
    		$scope.todate	= "";
    	} else {
    		$scope.searchlinesheets.fromdate 	= $scope.fromdate;
    		$scope.searchlinesheets.todate 		= $scope.todate;
    	}
    	
    	$scope.searchlinesheets.category = $scope.searchFilter.getCheckedFilters();
    	var temp = $scope.searchlinesheets;
    	var empty = false;
    	if(firstcall) {
    		$scope.getlinesheets(true);
    		firstcall = false;
    		return;
    	}
    	
    	if(	"" == temp.linesheetname &&
    			"" == temp.brandname &&
    			(temp == this.category || "" == temp.category) &&
    			(null == temp.fromdate || 0 == temp.fromdate) &&
    			(null == temp.todate || 0 == temp.todate) &&
    			(0 == temp.fromprice) &&
    			(0 == temp.toprice)) {
    			empty = true;
    	}
    	if(!empty)
    		$scope.getlinesheets(true);
    };
    
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
    		$scope.linesheets		= mylinesheet.getlinesheets($routeParams);
    	}).error(function(data) {
 
    	});
    	$scope.showEditLinesheet	 = false;
    };
    
    $scope.deletelinesheet = function(id) {
    	mylinesheet.deletelinesheet(id).success(function(data) {
    		mylinesheet.deletelinesheetlocally(id);
    		$scope.linesheets		= mylinesheet.getlinesheets($routeParams);
    	}).error(function(data) {
    		
    	});
    };
}


function MyCtrlStyles($scope, $routeParams, currentvisitedprofile, mylinesheet) {

	$scope.$parent.title = 'Styles';

	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
		$scope.entityname = "";
		$scope.profilepic = "";
	}
	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.isMyProfile 		= currentvisitedprofile.isMyProfile();
	
    $scope.linesheetid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.linesheetid = $routeParams.id;
	}
	$scope.category = "";
	if (!angular.isUndefined($routeParams.cat)) {
		$scope.category = $routeParams.cat;
	}
	
	//Search related
	$scope.searchstyleno = "";
	$scope.searchfromprice = 0;
	$scope.searchtoprice = 0;
	$scope.searchlinesheetname = "";
	
	$scope.updatedata = function() {
	    $scope.entityname  		= mylinesheet.getname();
	    $scope.profilepic		= mylinesheet.getprofilepic();
	    $scope.linesheetname  	= mylinesheet.getlinesheetname();
	    $scope.brandname		= mylinesheet.getbrandname();
	    $scope.linesheets		= mylinesheet.getlinesheets();
	    $scope.styles			= mylinesheet.getstyles();
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    $scope.getstyles = function() {
    	if(null == $scope.fromprice)
    		$scope.fromprice = 0;
    	if(null == $scope.toprice)
    		$scope.toprice = 0;
    	mylinesheet.styles($routeParams, $scope.linesheetid, $scope.searchstyleno, 
    									$scope.searchfromprice, $scope.searchtoprice).success(function(data) {
    		mylinesheet.setstyleslocally(data);
	    	$scope.updatedata();
	    }).error(function(data) {
	    	
	    });
    };
    $scope.getstyles();
    
    $scope.getlinesheets = function() {
    	var searchlinesheets = {
    			"linesheetname" :$scope.searchlinesheetname,
    	};
    	mylinesheet.linesheets($routeParams, searchlinesheets).success(function(data) {
    		mylinesheet.setlinesheetlocally(data, $routeParams);
    		$scope.linesheets		= mylinesheet.getlinesheets();
        }).error(function(data) {
        	alert(data);
        });
    };
    
    $scope.setlinesheet = function(linesheetid) {
    	$scope.linesheetid = linesheetid;
    	$scope.getstyles();
    };
}

function MyCtrlStyle($scope, $routeParams, currentvisitedprofile, mylinesheet) {

	$scope.$parent.title = 'Style';
	
	$scope.profileid = "";
	if(!angular.isUndefined($routeParams.profileid)) {
		$scope.profileid = $routeParams.profileid;
		$scope.entityname = "";
		$scope.profilepic = "";
	}
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
	
	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.updatedata = function() {
	    $scope.entityname  		= mylinesheet.getname();
	    $scope.profilepic		= mylinesheet.getprofilepic();
	    $scope.linesheetname  	= mylinesheet.getlinesheetname();
	    $scope.brandname		= mylinesheet.getbrandname();
	    $scope.styles			= mylinesheet.getstyles();
    	for(var i=0; i < $scope.styles.length; i++) {
    		if($scope.styleid == $scope.styles[i].styleid) {
    			$scope.mainstyle = $scope.styles[i];
    			break;
    		}
    	}
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    mylinesheet.styles($routeParams, $scope.linesheetid, "").success(function(data) {
    	mylinesheet.setstyleslocally(data);
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
	    	$scope.brandscarried.setItem(brandscarriedtemp[i].brandname, brandscarriedtemp[i]);
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
        	srvprofile.setProfileLocally(data, $routeParams);
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
    /*$scope.updatebrandscarried = function (event) {
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
    };*/
    $scope.updatebrandscarried = function (username, addorremoveurl) {
		myprofile.posttoserver(username, addorremoveurl).success(function(data) {
    		if(addorremoveurl == "removebrandscarried") {
    			if(data.status == true) {
    				$scope.brandscarried.removeItem(username);
    			} else {
    	    		alert(data.error);
    	    	}
    		} else {
    			if(data.brandusername != "") {
    				$scope.brandscarried.setItem(data.brandname, data);
    				$scope.brandsentered = "";
    			}
    		}
    		myprofile.setbrandscarried($scope.brandscarried);
	    }).error(function(data) {
	    	alert(data.error);
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
    	
		$scope.updatebrandscarried(username, "addbrandscarried");
	};
    $scope.removeBrandsCarried = function(key) {
    	var username = $scope.brandscarried.getItem(key).brandusername; //Here it is no brandsreceived its brands carried, as usernames are already added
		if(null == username)
			username = brandscarried;
		
		$scope.updatebrandscarried(username, "removebrandscarried");
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

function MyCtrlAdvancedSearch($scope, $routeParams, $http, search) {

	$scope.$parent.title = 'AdvancedSearch';
	
	var query = "";
	if (!angular.isUndefined($routeParams.query)) {
		query = $routeParams.query;
	}
	
	$scope.pagenumber = 0;
	$scope.pagenumbers = [ 1, 2, 3, 4 ];
	$scope.pageSize = 12;
	
	//Search related
	$scope.isbrandsearch = "true";
	$scope.search = {
			name: "",
			location:"",
			stockists: [],
			fromprice: 0,
			toprice: 0
	};
	$scope.searching = true;
	
	$scope.search.getstockists =  function() {
		var result = "";
		for(var i=0;i<this.stockists.length;i++) {
			result += (this.filters[i].children[j].name) + ",";
		}
		return result;
	};
	
	search.getallusers(query).success(function(data) {
		$scope.entityname		= data.name;
		$scope.profilepic		= data.profilepic;
		$scope.advancedsearch	= data.users;
		$scope.searching = false;
	}).error(function(data) {
		$scope.searching = false;	
	});
	
	$scope.searchFilter = new netvogue.searchFilter();
	$scope.getsearchResults = function() {
		$scope.searching = true;
		var categories = $scope.searchFilter.getCheckedFilters();
		var stockists = $scope.search.getstockists();
		search.getadvancedsearchresults($scope.search.name, $scope.search.location, 
										categories, $scope.isbrandsearch, stockists).
		success(function(data) {
			$scope.advancedsearch = data;
			$scope.searching = false;
	    }).
	    error(function(data) {
	    	$scope.searching = false;
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

function MyCtrlNotifications($scope, $routeParams, currentvisitedprofile, mynotifications, mynetwork) {

	// $scope.currentPage = 'Notification';
	$scope.$parent.title = "Notification";

	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	// $scope.mynotification = srvprofile.getnotification();
	$scope.backButton = currentvisitedprofile.getBackHistory();

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