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
	$scope.filesadded	= function(element) {
		$scope.$apply(function($scope) {
			// Turn the FileList object into an Array
			$scope.$broadcast('filesadded', element.files);
		});
	};
	
	$scope.updatephoto = function(label, seasonname, photoid) {
		mygallery.savephotoinfo(ajaxrequestcall, label, seasonname, photoid).success(function(data) {
			if(data.status == true) {
				alert("Updated successfully" + data.status);
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
    
	$scope.filesadded	= function(element) {
		$scope.$apply(function($scope) {
			// Turn the FileList object into an Array
			$scope.$broadcast('filesadded', element.files);
		});
	};
	
	$scope.updatephoto = function(label, seasonname, photoid) {
		mygallery.savephotoinfo(ajaxrequestcall, label, seasonname, photoid).success(function(data) {
			if(data.status == true) {
				alert("Updated successfully" + data.status);
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
}


function MyCtrlAddNewsletter($scope, $routeParams, $location, srvgallery, mygallery, currentvisitedprofile) {

	$scope.isMyProfile 		= currentvisitedprofile.isMyProfile();
	if(!$scope.isMyProfile) {
		$location.url($routeParams.profileid + "/newsletter");
	}
	
	var ajaxrequestcall	 = "editorial";
    $scope.$parent.title	= "Add Editorials";
	$scope.newfiles = [];
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
    
	$scope.filesadded	= function(element) {
		$scope.$apply(function($scope) {
			// Turn the FileList object into an Array
			$scope.$broadcast('filesadded', element.files);
		});
	};
	
	$scope.updatephoto = function(label, seasonname, photoid) {
		mygallery.savephotoinfo(ajaxrequestcall, label, seasonname, photoid).success(function(data) {
			if(data.status == true) {
				alert("Updated successfully" + data.status);
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
	
    $scope.$parent.title	= "Add Collection";
	$scope.newfiles = [];
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
    
	$scope.filesadded	= function(element) {
		$scope.$apply(function($scope) {
			// Turn the FileList object into an Array
			$scope.$broadcast('filesadded', element.files);
		});
	};
	
	$scope.updatephoto = function(label, seasonname, photoid) {
		mycollection.savephotoinfo(label, seasonname, photoid).success(function(data) {
			if(data.status == true) {
				alert("Updated successfully" + data.status);
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

function MyCtrlAddLinesheets($scope, $routeParams, currentvisitedprofile) {

	if($scope.parent.iambrand == false) {
		$location.url("linesheets");
	}
    $scope.backButton = currentvisitedprofile.getBackHistory();
    $scope.addDetails = true;
    $scope.AddLinesheet = function () {
        $scope.addDetails = false;
    }

    $scope.linesheets = [
	                   {
	                       "linesheetlistitemid": "linesheetId",
	                       "linesheetbrandname": "Calvin Klien",
	                       "linesheetseason": "Spring 2012",
	                       "linesheetdeliverydate": "25/04/2012",
	                       "linesheetcoverpic": "http://placehold.it/231x306"

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
	                       "stylecoverpic": "http://placehold.it/90x119"

	                   },
                       {
                           "stylelistitemid": "styleId",
                           "stylename": "Studded Winston",
                           "stylebrandname": "Calvin Klien",
                           "styleseason": "Spring 2012",
                           "styledeliverydate": "25/04/2012",
                           "styleprice": "5000",
                           "stylecoverpic": "http://placehold.it/90x119"

                       },
                       {
                           "stylelistitemid": "styleId",
                           "stylename": "Smith trench",
                           "stylebrandname": "Calvin Klien",
                           "styleseason": "Spring 2012",
                           "styledeliverydate": "25/04/2012",
                           "styleprice": "5000",
                           "stylecoverpic": "http://placehold.it/90x119"

                       }
                       ];
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

function MyCtrlAddStyle($scope, $routeParams, currentvisitedprofile) {

	if($scope.parent.iambrand == false) {
		$location.url("linesheets");
	}
    $scope.backButton = currentvisitedprofile.getBackHistory();
    $scope.addDetails = true;
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
    $scope.linesheets = [
	                   {
	                       "linesheetlistitemid": "linesheetId",
	                       "linesheetbrandname": "Calvin Klien",
	                       "linesheetseason": "Spring 2012",
	                       "linesheetdeliverydate": "25/04/2012",
	                       "linesheetcoverpic": "http://placehold.it/100x130"

	                   }
                       ];

}