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
		mynetwork, trending,recommendations) {
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
	$scope.recommendations = recommendations.getRecommendations();
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

function MyCtrlPrintcampaign($scope, $routeParams, currentvisitedprofile,
		srvprofile) {

	$scope.$parent.title = 'Printcampaigns';

	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.printcampaigns = srvprofile.getprintcampaigns($routeParams);

}
function MyCtrlCampaign($scope, $routeParams, currentvisitedprofile,
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

function MyCtrlEditorial($scope, $routeParams, currentvisitedprofile,
		srvprofile) {

	$scope.$parent.title = 'Newsletter';

	$scope.entityname = currentvisitedprofile.getEntityName();
	$scope.isMyProfile = currentvisitedprofile.isMyProfile();

	$scope.links = currentvisitedprofile.getleftpanellinks();
	$scope.backButton = currentvisitedprofile.getBackHistory();
	$scope.newsletters = srvprofile.getnewsletters($routeParams);

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

function MyCtrlCollection($scope, $routeParams, currentvisitedprofile,
		srvprofile) {

	$scope.$parent.title = "Collection";
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
	
	$scope.immediate = "true";

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