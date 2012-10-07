'use strict';

/* Add and Edit Controllers */

function MyCtrlAddGallery($scope, $routeParams, $location, srvgallery, mygallery, currentvisitedprofile) {
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
	    $scope.entityname  		= srvgallery.getname($routeParams);
	    $scope.galleryname  	= srvgallery.getgalleryname($routeParams);
	    $scope.existingfiles	= srvgallery.getphotos($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    srvgallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, "").success(function(data) {
    	srvgallery.setphotoslocally(data, $routeParams);
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
	
	$scope.updatephoto = function(label, seasonname, photoid) {
		mygallery.savephotoinfo(ajaxrequestcall, label, seasonname, photoid).success(function(data) {
			if(data.status == true) {
				//
			} else {
				alert("error" + data.status + data.error);
			}
		}).error(function(data) {
			
		});
		alert(label + seasonname + photoid);
	};
	
	$scope.deletephoto = function(photoid) {
		mygallery.deletephoto(ajaxrequestcall, photoid).success(function(data) {
			mygallery.deletephotoslocally(photoid);
			$scope.existingfiles	= srvgallery.getphotos($routeParams);
		}).error(function(data) {
			alert("error: " + data.error);
		});
	};
}

function MyCtrlAddPrintCampaign($scope, $routeParams, $location, srvgallery, mygallery, currentvisitedprofile) {

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
	    $scope.entityname  		= srvgallery.getname($routeParams);
	    $scope.galleryname  	= srvgallery.getgalleryname($routeParams);
	    $scope.existingfiles	= srvgallery.getphotos($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    srvgallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, "").success(function(data) {
    	srvgallery.setphotoslocally(data, $routeParams);
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
	
	$scope.updatephoto = function(label, seasonname, photoid) {
		mygallery.savephotoinfo(ajaxrequestcall, label, seasonname, photoid).success(function(data) {
			if(data.status == true) {
				//
			} else {
				alert("error" + data.status + data.error);
			}
		}).error(function(data) {
			
		});
		alert(label + seasonname + photoid);
	};
	
	$scope.deletephoto = function(photoid) {
		mygallery.deletephoto(ajaxrequestcall, photoid).success(function(data) {
			mygallery.deletephotoslocally(photoid);
			$scope.existingfiles	= srvgallery.getphotos($routeParams);
		}).error(function(data) {
			alert("error: " + data.error);
		});
	};
}

function MyCtrlAddVideoCampaign($scope, $routeParams, currentvisitedprofile) {

    $scope.backButton = currentvisitedprofile.getBackHistory();
    $scope.addDetails = true;
    $scope.newvideocampaign = {};
    $scope.AddVideocampaign = function (video) {
        $scope.newvideocampaign = angular.copy(video);
        $scope.addDetails = false;
    };

    $scope.privacyset = [
	                   {
	                       "privacytype": "PUBLIC"
	                   },
                       {
                           "privacytype": "PRIVATE"
                       },
                       {
                           "privacytype": "NETWORK"
                       }
                       ];
}

function MyCtrlAddNewsletter($scope, $routeParams, $location, srvgallery, mygallery, currentvisitedprofile) {

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
	    $scope.entityname  		= srvgallery.getname($routeParams);
	    $scope.galleryname  	= srvgallery.getgalleryname($routeParams);
	    $scope.existingfiles	= srvgallery.getphotos($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    srvgallery.photos(ajaxrequestcall, $routeParams, $scope.galleryid, "").success(function(data) {
    	srvgallery.setphotoslocally(data, $routeParams);
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
	
	$scope.updatephoto = function(label, seasonname, photoid) {
		mygallery.savephotoinfo(ajaxrequestcall, label, seasonname, photoid).success(function(data) {
			if(data.status == true) {
				//
			} else {
				alert("error" + data.status + data.error);
			}
		}).error(function(data) {
			
		});
		alert(label + seasonname + photoid);
	};
	
	$scope.deletephoto = function(photoid) {
		mygallery.deletephoto(ajaxrequestcall, photoid).success(function(data) {
			mygallery.deletephotoslocally(photoid);
			$scope.existingfiles	= srvgallery.getphotos($routeParams);
		}).error(function(data) {
			alert("error: " + data.error);
		});
	};
}

function MyCtrlAddCollections($scope, $routeParams, $location, currentvisitedprofile, srvcollection, mycollection) {

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
	    $scope.entityname  		= srvcollection.getname($routeParams);
	    $scope.galleryname  	= srvcollection.getgalleryname($routeParams);
	    $scope.existingfiles	= srvcollection.getphotos($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    srvcollection.photos($routeParams, $scope.galleryid, "").success(function(data) {
    	srvcollection.setphotoslocally(data, $routeParams);
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
	
	$scope.updatephoto = function(label, seasonname, photoid) {
		mycollection.savephotoinfo(label, seasonname, photoid).success(function(data) {
			if(data.status == true) {
				//
			} else {
				alert("error" + data.status + data.error);
			}
		}).error(function(data) {
			
		});
		alert(label + seasonname + photoid);
	};
	
	$scope.deletephoto = function(photoid) {
		mycollection.deletephoto(ajaxrequestcall, photoid).success(function(data) {
			mygallery.deletephotoslocally(photoid);
			$scope.existingfiles	= srvcollection.getphotos($routeParams);
		}).error(function(data) {
			alert("error: " + data.error);
		});
	};
}

function MyCtrlAddStyle($scope, $routeParams, currentvisitedprofile, srvstylesheet, mystylesheet) {

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
	$scope.existingfiles = [];
	$scope.edit = false;
	
	$scope.updatedata = function() {
	    $scope.entityname  		= srvstylesheet.getname($routeParams);
	    $scope.stylesheetname  	= srvstylesheet.getstylesheetname($routeParams);
	    $scope.styles			= srvstylesheet.getstyles($routeParams);
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
    srvstylesheet.styles($routeParams, $scope.stylesheetid, "").success(function(data) {
    	srvstylesheet.setstyleslocally(data, $routeParams);
    	$scope.updatedata();
    }).error(function(data) {
    	
    });
	
	$scope.exitstylepane = function() {
		$scope.newstyle.empty();
		$scope.existingfiles = [];
		$scope.edit = false;
	};
	
	$scope.editstyle   = function(style) {
		//Convert this to $scope.newstyle and open edit window for this...
		$scope.newstyle.copy(style);
		$scope.existingfiles = style.availableImages;
		$scope.edit = true;
	};
	
	$scope.updatestyle = function() {
		//Add sizes
		for(var i=0;i < $scope.stylesizes.length;i++) {
			if($scope.stylesizes[i].available == true) {
				$scope.newstyle.availableSizes.push($scope.stylesizes[i].size);
			}
		}
		
		//Add Images
		for(var i=0;i < $scope.existingfiles.length;i++) {
			$scope.newstyle.availableImages.push($scope.existingfiles[i].uniqueid);
		}
		
		//Add colors
		//Add this logic here
		
		mystylesheet.updatestyle($scope.newstyle, $scope.edit).success(function(data) {
			if(data.status == true) {
				mystylesheet.updatestyleslocally(data.style);
				$scope.styles	= srvstylesheet.getstyles($routeParams);
				//alert("Updated successfully" + data.status);
			} else {
				alert("error" + data.status + data.error);
			}
			$scope.exitstylepane();
		}).error(function(data) {
			
		});
	};
	
	$scope.deletestyle = function(styleid) {
		mystylesheet.deletestyle(styleid).success(function(data) {
			mystylesheet.deletestyleslocally(styleid);
			$scope.styles	= srvstylesheet.getstyles($routeParams);
		}).error(function(data) {
			alert("error: " + data.error);
		});
	};    
}
function MyCtrlAddLinesheets($scope, $routeParams, currentvisitedprofile, srvlinesheet, mylinesheet, srvstylesheet) {

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
	    $scope.entityname  		= srvlinesheet.getname($routeParams);
	    $scope.linesheetname  	= srvlinesheet.getlinesheetname($routeParams);
	    $scope.styles			= srvlinesheet.getstyles($routeParams);
    };
    
    //Get all the profile data from the Server through AJAX everytime user comes here. 
    //This should be functionality in all pages except user goes to edit pages through 'edit'. ex: profilesettings, editcollections etc
    srvstylesheet.stylesbycategory($routeParams, $scope.category, "").success(function(data) {
    	$scope.allstyles = data.styles;
    	updateallstyles();
    }).error(function(data) {
    	
    });
	
    srvlinesheet.styles($routeParams, $scope.linesheetid, "").success(function(data) {
    	srvlinesheet.setstyleslocally(data, $routeParams);
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
				$scope.styles	= srvlinesheet.getstyles($routeParams);
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
			$scope.styles	= srvlinesheet.getstyles($routeParams);
			$scope.allstyles.push(style);
		}).error(function(data) {
			alert("error: " + data.error);
		});
	};
}

function MyCtrlEditCollections($scope, $routeParams, currentvisitedprofile) {

	if($scope.parent.iambrand == false) {
		$location.url("collections");
	}
	
    $scope.backButton = currentvisitedprofile.getBackHistory();
    $scope.addDetails = true;
    $scope.newcollection = {};
    $scope.UpdateCollection = function (collection) {
        $scope.newcollection = angular.copy(collection);
        $scope.addDetails = false;
    }

    $scope.categories = [
	                   {
	                       "categorytype": "APPAREL"
	                   },
                       {
                           "categorytype": "BAGS"
                       },
                       {
                           "categorytype": "SHOES"
                       },
                       {
                           "categorytype": "ACCESSORIES"
                       }
                       ];

    $scope.privacyset = [
	                   {
	                       "privacytype": "PUBLIC"
	                   },
                       {
                           "privacytype": "PRIVATE"
                       },
                       {
                           "privacytype": "NETWORK"
                       }
                       ];

    $scope.collectionscover = [
		                   {
		                       "collectionlistitemid": "collectionId",
		                       "collectionbrandname": "Calvin Klien",
		                       "collectionseason": "Spring 2012",
		                       "collectioncoverpic": "http://placehold.it/100x130"

		                   }
	                       ];
    $scope.collections = [
		                   {
		                       "collectionlistitemid": "collectionId",
		                       "collectionbrandname": "Calvin Klien",
		                       "collectionseason": "Spring 2012",
		                       "collectioncoverpic": "http://placehold.it/85x114"

		                   },
	                       {
	                           "collectionlistitemid": "collectionId",
	                           "collectionbrandname": "Calvin Klien",
	                           "collectionseason": "Spring 2012",
	                           "collectioncoverpic": "http://placehold.it/85x114"

	                       },
	                       {
	                           "collectionlistitemid": "collectionId",
	                           "collectionbrandname": "Calvin Klien",
	                           "collectionseason": "Spring 2012",
	                           "collectioncoverpic": "http://placehold.it/85x114"

	                       }
	                       ];

}

function MyCtrlEditLinesheets($scope, $routeParams, currentvisitedprofile) {

	if($scope.parent.iambrand == false) {
		$location.url("linesheets");
	}
    $scope.backButton = currentvisitedprofile.getBackHistory();
    $scope.addDetails = true;
    $scope.linesheets = [
	                   {
	                       "linesheetlistitemid": "linesheetId",
	                       "linesheetbrandname": "Calvin Klien",
	                       "linesheetseason": "Spring 2012",
	                       "linesheetdeliverydate": "25/04/2012",
	                       "linesheetcoverpic": "http://placehold.it/100x130"

	                   }
                       ];
    $scope.styles = [
	                   {
	                       "stylelistitemid": "styleId",
	                       "stylename": "Studded Winston",
	                       "stylebrandname": "Calvin Klien",
	                       "styleseason": "Spring 2012",
	                       "styledeliverydate": "25/04/2012",
	                       "styleprice": "5000",
	                       "stylecoverpic": "http://placehold.it/85x114"

	                   },
                       {
                           "stylelistitemid": "styleId",
                           "stylename": "Studded Winston",
                           "stylebrandname": "Calvin Klien",
                           "styleseason": "Spring 2012",
                           "styledeliverydate": "25/04/2012",
                           "styleprice": "5000",
                           "stylecoverpic": "http://placehold.it/85x114"

                       },
                       {
                           "stylelistitemid": "styleId",
                           "stylename": "Smith trench",
                           "stylebrandname": "Calvin Klien",
                           "styleseason": "Spring 2012",
                           "styledeliverydate": "25/04/2012",
                           "styleprice": "5000",
                           "stylecoverpic": "http://placehold.it/85x114"

                       }
                       ];

}

function MyCtrlEditNewsletters($scope, $routeParams, currentvisitedprofile) {

    $scope.backButton = currentvisitedprofile.getBackHistory();
    $scope.addDetails = true;
    $scope.newnewsletter = {};
    $scope.UpdateNewsletter = function (newsletter) {
        $scope.newNewsletter = angular.copy(newsletter);
        $scope.addDetails = false;
    }

    $scope.privacyset = [
	                   {
	                       "privacytype": "PUBLIC"
	                   },
                       {
                           "privacytype": "PRIVATE"
                       },
                       {
                           "privacytype": "NETWORK"
                       }
                       ];

    $scope.newsletters = [
	                       {
	                           "newsletterlistitemid": "NewsletterId",
	                           "newsletterlistitem": "Matches",
	                           "newslettername": "Vogue Magzine jan 2012",
	                           "newslettercoverpic": "http://placehold.it/85x114"
	                       }
	                       ];
    $scope.newsletterscover = [
	                       {
	                           "newsletterlistitemid": "NewsletterId",
	                           "newsletterlistitem": "Matches",
	                           "newslettername": "Vogue Magzine jan 2012",
	                           "newslettercoverpic": "http://placehold.it/100x130"
	                       }
	                       ];

}

function MyCtrlEditPrintCampaigns($scope, $routeParams, currentvisitedprofile) {

    $scope.backButton = currentvisitedprofile.getBackHistory();
    $scope.addDetails = true;
    $scope.newprintcampaign = {};
    $scope.UpdatePrintcampaign = function (printcampaign) {
        $scope.newPrintcampaign = angular.copy(printcampaign);
        $scope.addDetails = false;
    }


    $scope.privacyset = [
	                   {
	                       "privacytype": "PUBLIC"
	                   },
                       {
                           "privacytype": "PRIVATE"
                       },
                       {
                           "privacytype": "NETWORK"
                       }

                       ];

    $scope.printcampaigns = [
                       {
                           "printcampaignlistitemid": "PrintcampaignId",
                           "printcampaignlistitem": "Matches",
                           "printcampaignname": "Matches Spring 2012",
                           "printcampaigncoverpic": "http://placehold.it/100x130"

                       }
                       ];

}

function MyCtrlEditVideoCampaigns($scope, $routeParams, currentvisitedprofile) {

    $scope.backButton = currentvisitedprofile.getBackHistory();
    $scope.addDetails = true;
    $scope.newvideocampaign = {};
    $scope.UpdateVideocampaign = function (video) {
        $scope.newvideocampaign = angular.copy(video);
        $scope.addDetails = false;
    }

    $scope.privacyset = [
	                   {
	                       "privacytype": "PUBLIC"
	                   },
                       {
                           "privacytype": "PRIVATE"
                       },
                       {
                           "privacytype": "NETWORK"
                       }
                       ];

    $scope.videocampaigns = [
	                       {
	                           "videocampaignlistitemid": "VideocampaignId",
	                           "videocampaignlistitem": "Matches",
	                           "videocampaignname": "Spring/2012",
	                           "videocampaigncoverpic": "http://placehold.it/231x145"

	                       }
	                       ];

}

function MyCtrlEditStyles($scope, $routeParams, currentvisitedprofile) {

	if($scope.parent.iambrand == false) {
		$location.url("linesheets");
	}
    $scope.backButton = currentvisitedprofile.getBackHistory();
    $scope.addDetails = true;
    $scope.categories = [
	                   {
	                       "categorytype": "APPAREL"
	                   },
                       {
                           "categorytype": "BAGS"
                       },
                       {
                           "categorytype": "SHOES"
                       },
                       {
                           "categorytype": "ACCESSORIES"
                       }
                       ];

    $scope.privacyset = [
	                   {
	                       "privacytype": "PUBLIC"
	                   },
                       {
                           "privacytype": "PRIVATE"
                       },
                       {
                           "privacytype": "NETWORK"
                       }
                       ];
  $scope.styles = [
	                   {
	                       "stylelistitemid": "styleId",
	                       "stylename": "Studded Winston",
	                       "stylebrandname": "Calvin Klien",
	                       "styleseason": "Spring 2012",
	                       "styledeliverydate": "25/04/2012",
	                       "styledescription": "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
	                       "styleprice": "5000",
	                       "stylecoverpic": "http://placehold.it/90x119"

	                   },
                       {
                           "stylelistitemid": "styleId",
                           "stylename": "Studded Winston",
                           "stylebrandname": "Calvin Klien",
                           "styleseason": "Spring 2012",
                           "styledeliverydate": "25/04/2012",
                           "styledescription": "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
                           "styleprice": "5000",
                           "stylecoverpic": "http://placehold.it/90x119"

                       },
                       {
                           "stylelistitemid": "styleId",
                           "stylename": "Smith trench",
                           "stylebrandname": "Calvin Klien",
                           "styleseason": "Spring 2012",
                           "styledeliverydate": "25/04/2012",
                           "styledescription": "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
                           "styleprice": "5000",
                           "stylecoverpic": "http://placehold.it/90x119"

                       }
                       ];
}
