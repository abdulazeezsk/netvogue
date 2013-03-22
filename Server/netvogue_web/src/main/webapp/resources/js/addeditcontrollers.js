'use strict';

/* Add and Edit Controllers */

function MyCtrlAddGallery($scope, $routeParams, $location, mygallery, currentvisitedprofile) {
	$scope.isMyProfile 		= currentvisitedprofile.isMyProfile();
	if(!$scope.isMyProfile) {
		$location.url($routeParams.profileid + "/gallery");
	}
	
    $scope.$parent.title	= "Add Gallery";
    var ajaxrequestcall	 = "gallery";
	$scope.newfiles = [];
	$scope.filesadded = false;
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	
	$scope.updatedata = function() {
	    $scope.entityname  		= mygallery.getname($routeParams);
	    $scope.iambrand			= mygallery.isbrand($routeParams);
	    $scope.profilepic		= mygallery.getprofilepic($routeParams);
	    $scope.galleryname  	= mygallery.getgalleryname($routeParams);
	    $scope.existingfiles	= mygallery.getphotos($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    mygallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, "").success(function(data) {
    	mygallery.setphotoslocally(data);
    	$scope.updatedata();
    }).error(function(data) {
    	
    });
	$scope.addfiles	= function(element) {
		$scope.$apply(function($scope) {
			// Turn the FileList object into an Array
			$scope.filesadded = true;
			$scope.$broadcast('filesadded', element.files);
		});
	};
	$scope.setprofilepic = function(uniqueid) {
    	var datatosend = new netvogue.jsonrequest($scope.galleryid, uniqueid);
    	mygallery.setprofilepic(ajaxrequestcall,datatosend).success(function(data) {
    		
    	}).error(function(data) {
    		
    	});
    };
    
    $scope.sortphotos = function(photosorder) {
    	mygallery.sortphotos(ajaxrequestcall, $scope.galleryid, photosorder).success(function(data) {
    		
    	}).error(function(data) {
    		
    	});
    };
    
	$scope.updatephoto = function(label, seasonname, photoid) {
		mygallery.savephotoinfo(ajaxrequestcall, label, seasonname, photoid).success(function(data) {
			if(data.status == true) {
				//
			} else {
				alert("error" + data.status + data.error);
			}
		}).error(function(data) {
			
		});
	};
	
	$scope.deletephoto = function(photoid) {
		mygallery.deletephoto(ajaxrequestcall, photoid).success(function(data) {
			mygallery.deletephotoslocally(photoid);
			//$scope.existingfiles	= mygallery.getphotos($routeParams);
		}).error(function(data) {
			alert("error: " + data.error);
		});
	};
}

function MyCtrlAddPrintCampaign($scope, $routeParams, $location, mygallery, currentvisitedprofile) {

	$scope.isMyProfile 		= currentvisitedprofile.isMyProfile();
	if(!$scope.isMyProfile) {
		$location.url($routeParams.profileid + "/printcampaign");
	}
	
	var ajaxrequestcall	 = "printcampaign";
    $scope.$parent.title	= "Add Print Campaign";
	$scope.newfiles = [];
	$scope.filesadded = false;
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	
	$scope.updatedata = function() {
	    $scope.entityname  		= mygallery.getname($routeParams);
	    $scope.iambrand			= mygallery.isbrand($routeParams);
	    $scope.profilepic		= mygallery.getprofilepic($routeParams);
	    $scope.galleryname  	= mygallery.getgalleryname($routeParams);
	    $scope.existingfiles	= mygallery.getphotos($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    mygallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, "").success(function(data) {
    	mygallery.setphotoslocally(data);
    	$scope.updatedata();
    }).error(function(data) {
    	
    });
    
    $scope.addfiles	= function(element) {
		$scope.$apply(function($scope) {
			// Turn the FileList object into an Array
			$scope.filesadded = true;
			$scope.$broadcast('filesadded', element.files);
		});
	};
	
	$scope.setprofilepic = function(uniqueid) {
    	var datatosend = new netvogue.jsonrequest($scope.galleryid, uniqueid);
    	mygallery.setprofilepic(ajaxrequestcall,datatosend).success(function(data) {
    		
    	}).error(function(data) {
    		
    	});
    };
    
    $scope.sortphotos = function(photosorder) {
    	mygallery.sortphotos(ajaxrequestcall, photosorder).success(function(data) {
    		
    	}).error(function(data) {
    		
    	});
    };
    
	$scope.updatephoto = function(label, seasonname, photoid) {
		mygallery.savephotoinfo(ajaxrequestcall, label, seasonname, photoid).success(function(data) {
			if(data.status == true) {
				//
			} else {
				alert("error" + data.status + data.error);
			}
		}).error(function(data) {
			
		});
	};
	
	$scope.deletephoto = function(photoid) {
		mygallery.deletephoto(ajaxrequestcall, photoid).success(function(data) {
			mygallery.deletephotoslocally(photoid);
		}).error(function(data) {
			alert("error: " + data.error);
		});
	};
}

function MyCtrlAddNewsletter($scope, $routeParams, $location, mygallery, currentvisitedprofile) {

	$scope.isMyProfile 		= currentvisitedprofile.isMyProfile();
	if(!$scope.isMyProfile) {
		$location.url($routeParams.profileid + "/newsletter");
	}
	
	var ajaxrequestcall	 = "editorial";
    $scope.$parent.title	= "Add Editorials";
	$scope.newfiles = [];
	$scope.filesadded = false;
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	
	$scope.updatedata = function() {
	    $scope.entityname  		= mygallery.getname($routeParams);
	    $scope.iambrand			= mygallery.isbrand($routeParams);
	    $scope.profilepic		= mygallery.getprofilepic($routeParams);
	    $scope.galleryname  	= mygallery.getgalleryname($routeParams);
	    $scope.existingfiles	= mygallery.getphotos($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    mygallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, "").success(function(data) {
    	mygallery.setphotoslocally(data);
    	$scope.updatedata();
    }).error(function(data) {
    	
    });
    
    $scope.addfiles	= function(element) {
		$scope.$apply(function($scope) {
			$scope.filesadded = true;
			// Turn the FileList object into an Array
			$scope.$broadcast('filesadded', element.files);
		});
	};
	
	$scope.setprofilepic = function(uniqueid) {
    	var datatosend = new netvogue.jsonrequest($scope.galleryid, uniqueid);
    	mygallery.setprofilepic(ajaxrequestcall,datatosend).success(function(data) {
    		
    	}).error(function(data) {
    		
    	});
    };
    
    $scope.sortphotos = function(photosorder) {
    	mygallery.sortphotos(ajaxrequestcall, photosorder).success(function(data) {
    		
    	}).error(function(data) {
    		
    	});
    };
    
	$scope.updatephoto = function(label, seasonname, photoid) {
		mygallery.savephotoinfo(ajaxrequestcall, label, seasonname, photoid).success(function(data) {
			if(data.status == true) {
				//
			} else {
				alert("error" + data.status + data.error);
			}
		}).error(function(data) {
			
		});
	};
	
	$scope.deletephoto = function(photoid) {
		mygallery.deletephoto(ajaxrequestcall, photoid).success(function(data) {
			mygallery.deletephotoslocally(photoid);
		}).error(function(data) {
			alert("error: " + data.error);
		});
	};
}

function MyCtrlAddCollections($scope, $routeParams, $location, currentvisitedprofile, mycollection) {

	$scope.isMyProfile 		= currentvisitedprofile.isMyProfile();
	if(!$scope.isMyProfile) {
		$location.url($routeParams.profileid + "/collections");
	}
	
	if($scope.$parent.iambrand == false) {
		$location.url("collections");
	}
	
    $scope.$parent.title	= "Add Collection";
	$scope.newfiles = [];
	$scope.filesadded = false;
	$scope.galleryid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.galleryid = $routeParams.id;
	}
	
	$scope.updatedata = function() {
	    $scope.entityname  		= mycollection.getname();
	    $scope.profilepic		= mycollection.getprofilepic();
	    $scope.galleryname  	= mycollection.getgalleryname();
	    $scope.brandname  		= mycollection.getbrandname();
	    $scope.existingfiles	= mycollection.getphotos();
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    mycollection.photos($routeParams, $scope.galleryid, "").success(function(data) {
    	mycollection.setphotoslocally(data);
    	$scope.updatedata();
    }).error(function(data) {
    	
    });
    
    $scope.addfiles	= function(element) {
		$scope.$apply(function($scope) {
			$scope.filesadded = true;
			// Turn the FileList object into an Array
			$scope.$broadcast('filesadded', element.files);
		});
	};
	
	$scope.setprofilepic = function(uniqueid) {
    	var datatosend = new netvogue.jsonrequest($scope.galleryid, uniqueid);
    	mycollection.setprofilepic(datatosend).success(function(data) {
    		
    	}).error(function(data) {
    		
    	});
    };
    
    $scope.sortphotos = function(photosorder) {
    	mycollection.sortphotos(photosorder).success(function(data) {
    		
    	}).error(function(data) {
    		
    	});
    };
    
	$scope.updatephoto = function(label, seasonname, photoid) {
		mycollection.savephotoinfo(label, seasonname, photoid).success(function(data) {
			if(data.status == true) {
				//
			} else {
				alert("error" + data.status + data.error);
			}
		}).error(function(data) {
			
		});
	};
	
	$scope.deletephoto = function(photoid) {
		mycollection.deletephoto(photoid).success(function(data) {
			mycollection.deletephotoslocally(photoid);
		}).error(function(data) {
			alert("error: " + data.error);
		});
	};
}

function MyCtrlAddStyle($scope, $routeParams, $location, currentvisitedprofile, mystylesheet) {

	$scope.isMyProfile 		= currentvisitedprofile.isMyProfile();
	if(!$scope.isMyProfile) {
		$location.url($routeParams.profileid + "/stylesheets");
	}
	
	if($scope.$parent.iambrand == false) {
		$location.url("stylesheets");
	}
	
	$scope.$parent.title	= "Add Style";
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
		$scope.editstyleid = $routeParams.styleid;
	}
	
	$scope.showStylePane = true;
	$scope.newstyle = new netvogue.stylejsonrequest($scope.stylesheetid);
	$scope.stylesizes = netvogue.defaultstylesizes;
	$scope.filesadded = false;
	$scope.edit = false;
	$scope.isEditMode = false;
	$scope.mainimage = "http://placehold.it/257x365";
	
	$scope.updatedata = function() {
	    $scope.entityname  		= mystylesheet.getname();
	    $scope.profilepic		= mystylesheet.getprofilepic();
	    $scope.stylesheetname  	= mystylesheet.getstylesheetname();
	    $scope.brandname		= mystylesheet.getbrandname();
	    $scope.styles			= mystylesheet.getstyles();
	    if(!angular.isUndefined($routeParams.styleid)) {
	    	for(var i=0; i < $scope.styles.length; i++) {
	    		if($scope.editstyleid == $scope.styles[i].styleid) {
	    			$scope.editstyle($scope.styles[i]);
	    			break;
	    		}
	    	}
	    }
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    mystylesheet.styles($routeParams, $scope.stylesheetid, "").success(function(data) {
    	mystylesheet.setstyleslocally(data);
    	$scope.updatedata();
    }).error(function(data) {
    	
    });
	
    $scope.filesadded	= function(element) {
		$scope.$apply(function(scope) {
			// Turn the FileList object into an Array
			//addfiles(element.files);
			$scope.newfiles = [];
			$scope.newfiles.push("");
			$scope.senttoserver = true;
		});
	};
	
	$scope.exitstylepane = function() {
		$scope.newstyle.empty();
		$scope.edit = false;
		$scope.isEditMode = false;
		$scope.mainimage = "http://placehold.it/257x365";
	};
	
	$scope.editstyle   = function(style) {
		//Convert this to $scope.newstyle and open edit window for this...
		if(!angular.isUndefined(style)) {
			$scope.newstyle.copy(style);
			$scope.mainimage = $scope.newstyle.availableImages[0].thumbnail_url;
			$scope.edit = true;
		}
		$scope.isEditMode = true;
	};
	
	$scope.updatestyle = function() {
		//Add sizes
		for(var i=0;i < $scope.stylesizes.length;i++) {
			if($scope.stylesizes[i].available == true) {
				$scope.newstyle.availableSizes.push($scope.stylesizes[i].size);
			}
		}
		
		//Add colors
		//Add this logic here
		
		mystylesheet.updatestyle($scope.newstyle, $scope.edit).success(function(data) {
			if(data.status == true) {
				mystylesheet.updatestyleslocally(data.style);
				$scope.styles	= mystylesheet.getstyles($routeParams);
				$scope.exitstylepane();
				//alert("Updated successfully" + data.status);
			} else {
				alert("error" + data.status + data.error);
				
			}
		}).error(function(data) {
			
		});
	};
	
	$scope.deletestyle = function(styleid) {
		if(angular.isUndefined(styleid)) {
			$scope.newstyle.empty();
			$scope.isEditMode = false;
		}
		mystylesheet.deletestyle(styleid).success(function(data) {
			mystylesheet.deletestyleslocally(styleid);
			$scope.styles	= mystylesheet.getstyles($routeParams);
			if(styleid == $scope.newstyle.styleid) {
				$scope.exitstylepane();
			}
		}).error(function(data) {
			alert("error: " + data.error);
		});
	};
	
	$scope.deleteimage = function(uniqueid) {
		$scope.newstyle.removeimage(uniqueid);
	};
	
	$scope.stylesorderchanged = function() {
		console.log("came here");
		$scope.mainimage = $scope.newstyle.availableImages[0].thumbnail_url;
	};
}
function MyCtrlAddLinesheets($scope, $routeParams, currentvisitedprofile, mylinesheet, mystylesheet) {

	if($scope.$parent.iambrand == false) {
		$location.url("linesheets");
	}
    
	$scope.backButton = currentvisitedprofile.getBackHistory();
    $scope.$parent.title	= "Add Linesheets";
    
    $scope.linesheetid = "";
	if (!angular.isUndefined($routeParams.id)) {
		$scope.linesheetid = $routeParams.id;
	}
	$scope.category = "";
	if (!angular.isUndefined($routeParams.cat)) {
		$scope.category = $routeParams.cat;
	}
	
	$scope.allstyles = [];
	$scope.styles    = [];
	$scope.updatedata = function() {
	    $scope.entityname  		= mylinesheet.getname();
	    $scope.profilepic		= mylinesheet.getprofilepic();
	    $scope.linesheetname  	= mylinesheet.getlinesheetname();
	    $scope.brandname		= mylinesheet.getbrandname();
	    $scope.styles			= mylinesheet.getstyles();
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    mystylesheet.stylesbycategory($routeParams, $scope.category, "").success(function(data) {
    	$scope.allstyles = data.styles;
    	updateallstyles();
    }).error(function(data) {
    	
    });
	
    mylinesheet.styles($routeParams, $scope.linesheetid, "").success(function(data) {
    	mylinesheet.setstyleslocally(data);
    	$scope.updatedata();
    	updateallstyles();
    }).error(function(data) {
    	
    });
	
    var updateallstyles = function() {
    	for(var i=0; i < $scope.styles.length; i++) {
    		for(var j=0; j < $scope.allstyles.length; j++) {
    			if($scope.styles[i].styleid == $scope.allstyles[j].styleid) {
    				$scope.allstyles.splice(j, 1);
    				break;
    			}
    		}
    	}
    };
	$scope.addstyle = function(newstyle) {
		//Add sizes
		var jsonrequest = {
				"id"	: $scope.linesheetid,
				"value"	: newstyle.styleid
		};
		mylinesheet.addstyle(jsonrequest).success(function(data) {
			if(data.status == true) {
				mylinesheet.addstylelocally(newstyle);
				$scope.styles	= mylinesheet.getstyles($routeParams);
				updateallstyles();
				//alert("added successfully" + data.status);
			} else {
				alert("error" + data.status + data.error);
			}
		}).error(function(data) {
			
		});
	};
	
	$scope.deletestyle = function(style) {
		mylinesheet.deletestyle(style.styleid).success(function(data) {
			mylinesheet.deletestyleslocally(style.styleid);
			$scope.styles	= mylinesheet.getstyles($routeParams);
			$scope.allstyles.push(style);
		}).error(function(data) {
			alert("error: " + data.error);
		});
	};
}