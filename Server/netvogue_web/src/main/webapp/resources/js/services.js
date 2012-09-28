'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('netVogue.services', []).
	value('version', '0.1').
	service('myprofile', function ($http) {
	    /*var aboutus = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui. Vivamus vulputate" +
        "ipsum vel enim. Aliquam erat volutpat. Etiam a dui at neque semper ornare. Mauris" +
        "lacus tortor, sagittis eu, dictum sit amet, facilisis eu, mauris. Praesent molestie" +
        "ante non nibh. Suspendisse placerat eros vel velit. Vestibulum ante ipsum primis" +
        "in faucibus orci luctus et ultrices posuere cubilia Curae; Nullam ultricies eros" +
        "eget quam Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui. Vivamus vulputate" +
        "ipsum vel enim. Aliquam erat volutpat. Etiam a dui at neque semper ornare. Mauris" +
        "lacus tortor, sagittis eu, dictum sit amet, facilisis eu, mauris. Praesent molestie" +
        "ante non nibh. Suspendisse placerat eros vel velit. Vestibulum ante ipsum primis" +
        "in faucibus orci luctus et ultrices posuere cubilia Curae; Nullam ultricies eros" +
        "eget quam";


	    var contactinfo = new netvogue.contactinfo(
		        "Boutiquename@gmail.com", "9949879098", "040-23371149", "040-23371149",
		        "Lorem ipsum dolor sit amet\, consectetuer adipiscing elit. Vivamus dui.",
		        "Hyderabad", "500009", "Andhra Pradesh", "India", "netvogue.org", "2012"
		    ); // email, mobile, landline1, landline2, address, city, zip, state, country, website, yearest


	    var productline = [ //	productlinename, productlinepic, selected
	                	   	new netvogue.productline("Womens RTW", "http://placehold.it/100x72", "yes"),
	                	   	new netvogue.productline("Womens Shoe", "http://placehold.it/100x72", "no"),
	                	   	new netvogue.productline("Denim", "http://placehold.it/100x72", "no"),
	                	   	new netvogue.productline("Outerwear", "http://placehold.it/100x72", "yes"),
	                	   	new netvogue.productline("Activewear", "http://placehold.it/100x72", "yes"),
	                	   	new netvogue.productline("Mens RTW", "http://placehold.it/100x72", "no"),
	                	   	new netvogue.productline("Mens Shoe", "http://placehold.it/100x72", "no"),
	                	   	new netvogue.productline("Mens Bags", "http://placehold.it/100x72", "no"),
	                	   	new netvogue.productline("Handbags", "http://placehold.it/100x72", "no"),
	                	   	new netvogue.productline("Lingerie", "http://placehold.it/100x72", "yes"),
	                	   	new netvogue.productline("Jewelry", "http://placehold.it/100x72", "no"),
	                	   	new netvogue.productline("Swimwear", "http://placehold.it/100x72", "no"),
	                	   	new netvogue.productline("Kids", "http://placehold.it/100x72", "yes"),
	                	   	new netvogue.productline("Watches", "http://placehold.it/100x72", "no"),
	                	   	new netvogue.productline("Hats", "http://placehold.it/100x72", "no"),
	                	   	new netvogue.productline("Luggage", "http://placehold.it/100x72", "no"),
	                	   	new netvogue.productline("Gifts", "http://placehold.it/100x72", "no"),
	                	   	new netvogue.productline("Candles", "http://placehold.it/100x72", "no")
						  ];
	    var brandscarried = [ //brandlistitem , brandprofilepic
		                     new netvogue.brandscarried("Calvin klein", "images/ck-beauty.jpg"),
		                     new netvogue.brandscarried("Rebecca  Minkoff Drake", "http://placehold.it/132x89"),
		                     new netvogue.brandscarried("Donna Karan", "http://placehold.it/132x89"),
		                     new netvogue.brandscarried("Ritu Beri", "http://placehold.it/132x89"),
		                     new netvogue.brandscarried("Jason Myers", "http://placehold.it/132x89"),
		                     new netvogue.brandscarried("Maria will", "http://placehold.it/132x89")
	                        ];*/
		var profileinfo = new netvogue.profile();

	    return {
	    	getprofileinfo: function() {
	    		return profileinfo;
	    	},
	    	setprofileinfo: function(profile) {
	    		angular.copy(profile, profileinfo);
	    	},
	    	getname: function() {
	    		if(angular.isUndefined(profileinfo.name))
	        		return "";
	    		return profileinfo.name;
	    	},
	    	setname: function(name) {
	    		profileinfo.name = name;
	    	},
	        getaboutus: function () {
	        	if(angular.isUndefined(profileinfo.aboutus))
	        		return "";
	            return profileinfo.aboutus;
	        },
	        setaboutus: function (aboutme) {
	        	profileinfo.aboutus = aboutme;

	        },
	        getemail: function() {
	        	if(angular.isUndefined(profileinfo.contactinfo))
	        		return "";
	            return profileinfo.contactinfo.email;
	        },
	        setemail: function(email) {
	        	if(!angular.isUndefined(profileinfo.contactinfo))
	        		profileinfo.contactinfo.email = email;
	        },
	        getcontactinfo: function () {
	        	if(angular.isUndefined(profileinfo.contactinfo))
	        		return {};
	            return profileinfo.contactinfo;
	        },
	        setcontactinfo: function (contactinfotemp) {
	        	profileinfo.contactinfo =  angular.copy(contactinfotemp);
	        },
	        getproductline: function () {
	        	if(angular.isUndefined(profileinfo.productlines))
	        		return [];
	            return profileinfo.productlines;
	        },
	        setproductline: function (productlinetemp) {
	        	profileinfo.productlines = angular.copy(productlinetemp);
	        },
	        getbrandscarried: function () {
	        	if(angular.isUndefined(profileinfo.brandscarried))
	        		return [];
	            return profileinfo.brandscarried;
	        },
	        setbrandscarried: function (brandscarriedtemp) {
	        	profileinfo.brandscarried = angular.copy(brandscarriedtemp);
	        },
	        posttoserver: function(profiledata, url) {
	        	var config = {
	                      method: "POST",
	                      data: profiledata,
	                      url: "profile/" + url
	                };
	        	return $http(config);
	        }
	    };
	}).
service('srvprofile', function ($http, myprofile, mynetwork, myprintcampaigns, myvideocampaigns, mynewsletters, mycollections, mylinesheets) {
      var profiles = [];
      var galleries= new netvogue.hashtable();
      
      /*********************************/
	  var networks = new netvogue.hashtable();
      networks.setItem("profileid1",
			  					[
			  					 	new netvogue.network("Calvin klein", "http://placehold.it/100x72", "profileid1"),
				                    new netvogue.network("Rebecca  Minkoff", "http://placehold.it/100x72", "profileid1"),
				                    new netvogue.network("Jason Myers", "http://placehold.it/100x72", "profileid1"),
				                    new netvogue.network("Akris", "http://placehold.it/100x72", "profileid1"),
				                    new netvogue.network("Catherine Malandrino", "http://placehold.it/100x72", "profileid1"),
				                    new netvogue.network("Derek Lam", "http://placehold.it/100x72", "profileid1"),
				                    new netvogue.network("Donna Karan", "http://placehold.it/100x72", "profileid1"),
	        	                 ]);

      var printcampaigns = new netvogue.hashtable();
      printcampaigns.setItem("profileid1",
	                            [
	                             new netvogue.campaign("PrintcampaignId1", "Matches", "Matches Spring 2weq2", "../images/paste2.jpg"),
	 	                        new netvogue.campaign("PrintcampaignId2", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
	 	                        new netvogue.campaign("PrintcampaignId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
	 	                        new netvogue.campaign("PrintcampaignId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
	 	                        new netvogue.campaign("PrintcampaignId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
	 	                        new netvogue.campaign("PrintcampaignId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
	 	                        new netvogue.campaign("PrintcampaignId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
	 	                        new netvogue.campaign("PrintcampaignId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306")
	                            ]);

      var videocampaigns = new netvogue.hashtable();
      videocampaigns.setItem("profileid1",
								  [
					               new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
					               new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
					               new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
					               new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
					               new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
					               new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
					               new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
					               new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
					               new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145")
					              ]);

      var newsletters = new netvogue.hashtable();
      newsletters.setItem("profileid1",
								  [
					            	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
					            	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
					            	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
					            	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
					            	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
					            	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
					            	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
					            	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
					            	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
					            	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
					            	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306")
					            ]);
      var collections = new netvogue.hashtable();
      collections.setItem("profileid1",
								  [
					               new netvogue.collection("collectionId", "Calvin Klien", "Spring 2012", "http://placehold.it/231x306"),
					               new netvogue.collection("collectionId", "Donna karan", "Spring 2012", "http://placehold.it/231x306"),
					               new netvogue.collection("collectionId", "Catherine jones", "Spring 2012", "http://placehold.it/231x306"),
					               new netvogue.collection("collectionId", "Rebecca Jonson", "Spring 2012", "http://placehold.it/231x306"),
					               new netvogue.collection("collectionId", "Givenchy", "Spring 2012", "http://placehold.it/231x306"),
					               new netvogue.collection("collectionId", "Valentino", "Spring 2012", "http://placehold.it/231x306"),
					               new netvogue.collection("collectionId", "DKNY", "Spring 2012", "http://placehold.it/231x306"),
					               new netvogue.collection("collectionId", "Rebecca Minkoff", "Spring 2012", "http://placehold.it/231x306"),
					               new netvogue.collection("collectionId", "Calvin Klien", "Spring 2012", "http://placehold.it/231x306"),
					               new netvogue.collection("collectionId", "Calvin Klien", "Spring 2012", "http://placehold.it/231x306"),
					               new netvogue.collection("collectionId", "Calvin Klien", "Spring 2012", "http://placehold.it/231x306"),
					               new netvogue.collection("collectionId", "Calvin Klien", "Spring 2012", "http://placehold.it/231x306")
					              ]);
      var linesheets = new netvogue.hashtable();
      linesheets.setItem("profileid1",
								  [
								  new netvogue.linesheet("linesheetId", "Calvin Klien", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
					              new netvogue.linesheet("linesheetId", "Donna karan", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
					              new netvogue.linesheet("linesheetId", "Catherine jones", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
					              new netvogue.linesheet("linesheetId", "Rebecca Jonson", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
					              new netvogue.linesheet("linesheetId", "Givenchy", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
					              new netvogue.linesheet("linesheetId", "Valentino", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
					              new netvogue.linesheet("linesheetId", "DKNY", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
					              new netvogue.linesheet("linesheetId", "Donna karan", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
					              new netvogue.linesheet("linesheetId", "Donna karan", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
					              new netvogue.linesheet("linesheetId", "Rebecca Minkoff", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
					              new netvogue.linesheet("linesheetId", "Donna karan", "Spring 2012", "25/04/2012", "http://placehold.it/231x306")
								  ]);
      return {
          profileinfo: function (routeparams) {
              var profileid = "";
              if (!angular.isUndefined(routeparams.profileid)) {
                  profileid = routeparams.profileid;
              }
              var config = {
                  method: "GET",
                  url: "profile/" + profileid
              };
              return $http(config);
          },
          setProfileLocally: function(profiledata) {
        	  netvogue.yettocontact = false;
              if ("" == profiledata.profileid) {
            	  myprofile.setprofileinfo(profiledata);
              } else {
            	  profiles.push(angular.copy(profiledata));
              }
          },
          getname: function(routeparams) {
        	  var result;
              if (angular.isUndefined(routeparams.profileid)) {
                  return myprofile.getname();
              } else {
                  angular.forEach(profiles, function (profile) {
                      if (angular.equals(profile['profileid'], routeparams.profileid)) {
                          result = profile['aboutus'];
                      }
                  });
              }
              return result;
	    	},
          getaboutus: function (routeparams) {
              var result;
              if (angular.isUndefined(routeparams.profileid)) {
                  return myprofile.getaboutus();
              } else {
                  angular.forEach(profiles, function (profile) {
                      if (angular.equals(profile['profileid'], routeparams.profileid)) {
                          result = profile['aboutus'];
                      }
                  });
              }
              return result;
          },
          getcontactinfo: function (routeparams) {
              var result;
              if (angular.isUndefined(routeparams.profileid)) {
                  return myprofile.getcontactinfo();
              } else {
                  angular.forEach(profiles, function (profile) {
                      if (angular.equals(profile['profileid'], routeparams.profileid)) {
                          result = profile['contactinfo'];
                      }
                  });
              }
              return result;
          },
          getproductline: function (routeparams) {
              var result;
              if (angular.isUndefined(routeparams.profileid)) {
                  return myprofile.getproductline();
              } else {
                  angular.forEach(profiles, function (profile) {
                      if (angular.equals(profile['profileid'], routeparams.profileid)) {
                          result = profile['productline'];
                      }
                  });
              }
              return result;
          },
          getbrandscarried: function (routeparams) {
              var result;
              if (angular.isUndefined(routeparams.profileid)) {
                  return myprofile.getbrandscarried();
              } else {
                  angular.forEach(profiles, function (profile) {
                      if (angular.equals(profile['profileid'], routeparams.profileid)) {
                          result = profile['brandscarried'];
                      }
                  });
              }
              return result;
          },
          getnetwork: function (routeparams) {
              var result;
              if (angular.isUndefined(routeparams.profileid)) {
                  return mynetwork.getmynetwork();
              } else {
                  result = networks.getItem(routeparams.profileid);
                  /*angular.forEach(networks, function(network) {
                  if(angular.equals(network['profileid'], routeparams.profileid)) {
                  result = network['network'];
                  }
                  })*/
                  ;
              }
              return result;
          },
          getprintcampaigns: function (routeparams) {
              var result;
              if (angular.isUndefined(routeparams.profileid)) {
                  result = myprintcampaigns.getprintcampaigns();
                  for (var campaign in result) {
                      result[campaign].printcampaignlistitemid = "printcampaign/" + result[campaign].printcampaignlistitemid;
                  }
              } else {
                  result = printcampaigns.getItem(routeparams.profileid);
                  /*angular.forEach(campaigns, function(campaign) {
                  if(angular.equals(campaign['profileid'], routeparams.profileid)) {
                  result = campaign['printcampaign'];
                  }
                  });*/
                  for (var printcampaigns in result) {
                      result[printcampaigns].printcampaignlistitemid = routeparams.profileid + "/printcampaign/" + result[printcampaigns].printcampaignlistitemid;
                  }
              }
              return result;
          },
          getvideocampaigns: function (routeparams) {
              var result;
              if (angular.isUndefined(routeparams.profileid)) {
                  result = myvideocampaigns.getvideocampaigns();
                  for (var campaign in result) {
                      result[campaign].videocampaignlistitemid = "videocampaign/" + result[campaign].videocampaignlistitemid;
                  }
              } else {
                  result = videocampaigns.getItem(routeparams.profileid);
                  /*angular.forEach(campaigns, function(campaign) {
                  if(angular.equals(campaign['profileid'], routeparams.profileid)) {
                  result = campaign['videocampaign'];
                  }
                  });*/
                  for (var campaign in result) {
                      result[campaign].videocampaignlistitemid = routeparams.profileid + "/videocampaign/" + result[campaign].videocampaignlistitemid;
                  }
              }
              return result;
          },
          getnewsletters: function (routeparams) {
              var result;
              if (angular.isUndefined(routeparams.profileid)) {
                  result = mynewsletters.getnewsletters();
                  for (var campaign in result) {
                      result[campaign].newsletterlistitemid = "newsletter/" + result[campaign].newsletterlistitemid;
                  }
              } else {
                  result = newsletters.getItem(routeparams.profileid);
                  /*angular.forEach(campaigns, function(campaign) {
                  if(angular.equals(campaign['profileid'], routeparams.profileid)) {
                  result = campaign['newsletter'];
                  for(var campaign in result) {
                  result[campaign].newsletterlistitemid = routeparams.profileid + "/newsletter/" + result[campaign].newsletterlistitemid;
                  }
                  }
                  });*/
                  for (var campaign in result) {
                      result[campaign].videocampaignlistitemid = routeparams.profileid + "/videocampaign/" + result[campaign].videocampaignlistitemid;
                  }
              }
              return result;
          },
          getcollections: function (routeparams) {
              var result;
              if (angular.isUndefined(routeparams.profileid)) {
                  result = mycollections.getcollections();
                  for (var collection in result) {
                      result[collection].collectionlistitemid = "collections/" + result[collection].collectionlistitemid;
                  }
              } else {
                  result = collections.getItem(routeparams.profileid);
                  /*angular.forEach(collections, function(collection) {
                  if(angular.equals(collection['profileid'], routeparams.profileid)) {
                  result = collection['collections'];
                  }
                  });*/
                  for (var col in result) {
                      result[col].collectionlistitemid = routeparams.profileid + "/collections/" + result[col].collectionlistitemid;
                  }
              }
              return result;
          },
          getlinesheets: function (routeparams) {
              var result;
              if (angular.isUndefined(routeparams.profileid)) {
                  result = mylinesheets.getlinesheets();
                  for (var col in result) {
                      result[col].linesheetlistitemid = "linesheets/" + result[col].linesheetlistitemid;
                  }
              } else {
                  result = linesheets.getItem(routeparams.profileid);
                  /*angular.forEach(linesheets, function(linesheet) {
                  if(angular.equals(linesheet['profileid'], routeparams.profileid)) {
                  result = linesheet['linesheets'];
                  }
                  });*/
                  for (var col in result) {
                      result[col].linesheetlistitemid = routeparams.profileid + "/linesheets/" + result[col].linesheetlistitemid;
                  }
              }
              return result;
          }
      };
  }).
  service('currentvisitedprofile', function ($location) {
      var home = true;
      var historyURL = "";
      var currentURL = "";
      var currentProfileID = "";
      var currentProfile = {
          "entityname": "Boutique Name"
      };
      return {
          isMyProfile: function () {
              return currentProfileID == "" ? true : false;
          },
          isHomePage: function () {
              return home;
          },
          getEntityName: function () {
              return currentProfile.entityname;
          },
          profilevisitChange: function (routeParams, hometemp) {
              if (angular.isUndefined(routeParams.profileid)) {
                  currentProfileID = "";
              } else if (!angular.equals(routeParams.profileid, currentProfileID)) {
                  currentProfileID = routeParams.profileid;
                  currentProfile.entityname = routeParams.profileid;
              }
              historyURL = currentURL;
              currentURL = $location.url();
              home = hometemp;
          },
          getBackHistory: function () {
              return historyURL;
          },
          getprofileid: function () {
              return currentProfileID;
          },
          getleftpanellinks: function () {
              var leftpanellinks = {
                  "corner": "#/corner",
                  "profile": "#/profile",
                  "network": "#network",
                  "gallery": "#/gallery",
                  "printcampaign": "#/printcampaign",
                  "videocampaign": "#/videocampaign",
                  "newsletter": "#/newsletter",
                  "linesheets": "#/linesheets",
                  "stylesheets": "#/stylesheets",
                  "collections": "#/collections"
              };
              if (currentProfileID != "") {
                  for (var link in leftpanellinks) {
                      leftpanellinks[link] = "#/" + currentProfileID + "/" + link;
                  }
              }
              return leftpanellinks;
          }
      };
  }).
  service('mygallery', function ($http) {
		var name;
		var galleryname;
		var galleries = [];
		var photos    = [];
		return {
			getgalleries: function() {
	    		return galleries;
	    	},
	    	setgalleries: function(galleriestemp) {
	    		name = galleriestemp.name;
	    		angular.copy(galleriestemp.galleries, galleries);
	    	},
	    	getname: function() {
	    		if(angular.isUndefined(name))
	        		return "";
	    		return name;
	    	},
	    	setname: function(name) {
	    		this.name = name;
	    	},
	    	getgalleryname: function() {
	    		if(angular.isUndefined(galleryname))
	        		return "";
	    		return galleryname;
	    	},
	    	setgalleryname: function(name) {
	    		this.galleryname = name;
	    	},
	    	creategallery: function(ajaxrequestcall, jsonrequest) {
	    		var config = {
    				method: "POST",
    				data: jsonrequest,
                    url: ajaxrequestcall + "/create"
                };
                return $http(config);
	    	},
	    	updategallery: function(ajaxrequestcall, galleryid, newname) {
	    		var datatosend = {
	    				"id" 	: galleryid,
	    				"value"	: newname,
	    		};
	    		var config = {
	    				method: "POST",
	    				data: datatosend,
	                    url: ajaxrequestcall + "/edit"
	                };
	            return $http(config);
	    	},
	    	deletegallery: function(ajaxrequestcall, galleryid) {
	    		var config = {
	    			method: "POST",
	    			data: galleryid,
	                url: ajaxrequestcall + "/delete"
	            };
	    		return $http(config);
	    	},
	    	deletegallerylocally: function(galleryid) {
	    		var index=0;
	    		for(index=0; index <  galleries.length; index++) {
	    			if(galleries[index].galleryid == galleryid) {
	    				break;
	    			}
	    		}
	    		galleries.splice(index, 1);
	    	},
	    	getphotos: function() {
	    		return photos;
	    	},
	    	setphotos: function(photostemp) {
	    		name = photostemp.name;
	    		galleryname = photostemp.galleryname;
	    		angular.copy(photostemp.photos, photos);
	    	},
	    	savephotoinfo: function(ajaxrequestcall, label, seasonname, photoid) {
	    		var datatosend = {
	    				"photoname" : label,
	    				"seasonname": seasonname,
	    				"photoid"	: photoid
	    		};
	    		var config = {
	    				method: "POST",
	    				data: datatosend,
	                    url: ajaxrequestcall + "/editphotoinfo"
	                };
	            return $http(config);
	    	},
	    	deletephoto: function(photoid) {
	    		var config = {
	    			method: "POST",
	    			data: photoid,
	                url: ajaxrequestcall + "/deletephoto"
	            };
	    		return $http(config);
	    	},
	    	deletephotoslocally: function(photoid) {
	    		var i=0;
	    		for(i=0; i <  photos.length; i++) {
	    			if(photos[i].uniqueid == photoid) {
	    				break;
	    			}
	    		}
	    		photos.splice(i, 1);
	    	}
		};
  }).
  service('srvgallery', function ($http, mygallery) {
      var galleries= new netvogue.hashtable();
      return {
    	  getname: function(routeparams) {
        	  var result;
              if (angular.isUndefined(routeparams.profileid)) {
                  return mygallery.getname();
              } else {
                  /*angular.forEach(profiles, function (profile) {
                      if (angular.equals(profile['profileid'], routeparams.profileid)) {
                          result = profile['aboutus'];
                      }
                  });*/
              }
              return result;
    	  },
    	  getgalleryname: function (routeparams) {
	          if (angular.isUndefined(routeparams.profileid)) {
	        	  return mygallery.getgalleryname();
	          }
	    	},
    	  getgalleries: function (routeparams) {
	          if (angular.isUndefined(routeparams.profileid)) {
	        	  return mygallery.getgalleries();
	          }
	      },
    	  galleries: function (ajaxrequestcall, routeparams, jsonrequest) {
	          var profileid = "";
	          if (!angular.isUndefined(routeparams.profileid)) {
	        	  profileid = routeparams.profileid;
	          }
	          var datatosend = {
		  				"galleryname" : jsonrequest
		  	};
	          var config = {
	              method: "GET",
	              params: datatosend,
	              url: ajaxrequestcall + "/" + profileid
	          };
	          return $http(config);
	      },
	      photos: function (ajaxrequestcall, routeparams, galleryid, photoname) {
	          var profileid = "";
	          if (!angular.isUndefined(routeparams.profileid)) {
	        	  profileid = routeparams.profileid;
	          }
	          var datatosend = {
	  				"photoname" : photoname,
	  				"galleryid" : galleryid
	  		};
	          var config = {
	              method: "GET",
	              params: datatosend,
	              url: ajaxrequestcall + "/getphotos/" + profileid
	          };
	          return $http(config);
	      },
	      setgallerylocally: function(galleriesdata, routeparams) {
	    	  if (angular.isUndefined(routeparams.profileid)) {
	        	  mygallery.setgalleries(galleriesdata);
	          } else {
	        	  //galleries.push(angular.copy(galleriesdata));
	          }
	      },
	      setphotoslocally: function(galleriesdata, routeparams) {
	    	  if (angular.isUndefined(routeparams.profileid)) {
	        	  mygallery.setphotos(galleriesdata);
	          } else {
	        	  //galleries.push(angular.copy(galleriesdata));
	          }
	      },
	      getphotos: function (routeparams) {
	          if (angular.isUndefined(routeparams.profileid)) {
	        	  return mygallery.getphotos();
	          }
	      }
      };
  }).
  service('trending', function () {
      var trending = [
                  {
                      "trendlistitem": "Trend1",
                      "trendpiclink": "images/images.jpg",
                      "post": "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui.",
                      "trendlistitemID": "trendname1"
                  },
                  {
                      "trendlistitem": "Trend2",
                      "trendpiclink": "http://placehold.it/90x72",
                      "post": "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui.",
                      "trendlistitemID": "trendname2"
                  },
                  {
                      "trendlistitem": "Trend3",
                      "trendpiclink": "http://placehold.it/90x72",
                      "post": "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui.",
                      "trendlistitemID": "trendname3"
                  }
                  ];
      return {
          getTrending: function () {
              return trending;
          }
      };

  }).
  service('mynetwork', function () {
      var mynetwork = [
	                   new netvogue.network("Calvin klein", "images/ck-beauty.jpg", "profileid1"),
	                   new netvogue.network("Rebecca  Minkoff", "http://placehold.it/100x72", "profileid1"),
	                   new netvogue.network("Jason Myers", "http://placehold.it/100x72", "profileid1"),
	                   new netvogue.network("Akris", "http://placehold.it/100x72", "profileid1"),
	                   new netvogue.network("Catherine Malandrino", "http://placehold.it/100x72", "profileid1"),
	                   new netvogue.network("Derek Lam", "http://placehold.it/100x72", "profileid1"),
	                   new netvogue.network("Donna Karan", "http://placehold.it/100x72", "profileid1"),
	                   ];
      return {
          getmynetwork: function () {
              return mynetwork;
          },
          ismyfriend: function (routeParams) {
              if (angular.isUndefined(routeParams.profileid)) {
                  return true;
              } else {
                  for (var network in mynetwork) {
                      if (mynetwork[network].networklistitemID == routeParams.profileid) {
                          return true;
                      }
                  }
                  return false;
              }
          }
      };
  }).
  service('myprintcampaigns', function () {
      var printcampaigns = [
	                        new netvogue.campaign("PrintcampaignId1", "Matches", "Matches Sprixzcng 2012", "http://www.withnina.com/images/paste4.jpg"),
	                        new netvogue.campaign("PrintcampaignId2", "Matches", "Matches Spring 2012", "http://www.withnina.com/images/paste2.jpg"),
	                        new netvogue.campaign("PrintcampaignId", "Matches", "Matches Spring 2012", "http://www.withnina.com/images/paste.jpg"),
	                        new netvogue.campaign("PrintcampaignId", "Matches", "Matches Spring 2012", "http://www.withnina.com/images/paste5.jpg"),
	                        new netvogue.campaign("PrintcampaignId", "Matches", "Matches Spring 2012", "http://www.withnina.com/images/paste6.jpg"),
	                        new netvogue.campaign("PrintcampaignId", "Matches", "Matches Spring 2012", "http://www.schramm-badenhop.de/files/velvet_2012.jpg"),
	                        new netvogue.campaign("PrintcampaignId", "Matches", "Matches Spring 2012", "http://www.harpersbazaar.com.au/assets/images/articles/migrated/fashion/fashionflash/HB_AMANDAANTMWINNERCOVER_001.jpg"),
                            new netvogue.campaign("PrintcampaignId", "Matches", "Matches Spring 2012", "http://www.harpersbazaar.com.au/assets/images/migrated/HB_130810_ProenzaSchouler_001.jpg"),
                            new netvogue.campaign("PrintcampaignId", "Matches", "Matches Spring 2012", "http://i653.photobucket.com/albums/uu257/paisleyrosevintage/RED/DianevonFurstenbergMikhailiaBellSleeveCoat400.jpg"),
                            new netvogue.campaign("PrintcampaignId", "Matches", "Matches Spring 2012", "http://www.azcentral.com/style/pics/020408nyfashion.jpg"),
                            new netvogue.campaign("PrintcampaignId1", "Matches", "Matches Sprixzcng 2012", "http://media.tumblr.com/tumblr_ls1ppm1xBI1qfkreb.png"),
	                        new netvogue.campaign("PrintcampaignId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306")
	                       ];
      return {
          getprintcampaigns: function () {
              return printcampaigns;
          }
      };
  }).
  service('myvideocampaigns', function () {
      var videocampaigns = [
	                        new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
	                        new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
	                        new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
	                        new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
	                        new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
	                        new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
	                        new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
	                        new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145"),
	                        new netvogue.campaign("VideocampaignId1", "Matches", "Matches Spring 2012", "http://placehold.it/231x145")
	                       ];
      return {
          getvideocampaigns: function () {
              return videocampaigns;
          }
      };
  }).
  service('mynewsletters', function () {
      var newsletters = [
	                     	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "images/paste2.jpg"),
	                     	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
	                     	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
	                     	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
	                     	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
	                     	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
	                     	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
	                     	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
	                     	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
	                     	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306"),
	                     	new netvogue.campaign("NewsletterId", "Matches", "Matches Spring 2012", "http://placehold.it/231x306")
	                     ];
      return {
          getnewsletters: function () {
              return newsletters;
          }
      };
  }).
  service('mycollections', function () { //This is collections of his network collections in case of Boutique/In case of brand, it is his own collections
      var collections = [
	                     new netvogue.collection("collectionId", "Calvin Klien", "Spring 2012", "images/paste2.jpg"),
	                     new netvogue.collection("collectionId", "Donna karan", "Spring 2012", "http://placehold.it/231x306"),
	                     new netvogue.collection("collectionId", "Catherine jones", "Spring 2012", "http://placehold.it/231x306"),
	                     new netvogue.collection("collectionId", "Rebecca Jonson", "Spring 2012", "http://placehold.it/231x306"),
	                     new netvogue.collection("collectionId", "Givenchy", "Spring 2012", "http://placehold.it/231x306"),
	                     new netvogue.collection("collectionId", "Valentino", "Spring 2012", "http://placehold.it/231x306"),
	                     new netvogue.collection("collectionId", "DKNY", "Spring 2012", "http://placehold.it/231x306"),
	                     new netvogue.collection("collectionId", "Rebecca Minkoff", "Spring 2012", "http://placehold.it/231x306"),
	                     new netvogue.collection("collectionId", "Calvin Klien", "Spring 2012", "http://placehold.it/231x306"),
	                     new netvogue.collection("collectionId", "Calvin Klien", "Spring 2012", "http://placehold.it/231x306"),
	                     new netvogue.collection("collectionId", "Calvin Klien", "Spring 2012", "http://placehold.it/231x306"),
	                     new netvogue.collection("collectionId", "Calvin Klien", "Spring 2012", "http://placehold.it/231x306")
	                    ];

      return {
          getcollections: function () {
              return collections;
          }
      };
  }).
  service('mylinesheets', function () { //This is collections of his network linesheets in case of Boutique/In case of brand, it is his own linesheets
      var linesheets = [
	                    new netvogue.linesheet("linesheetId", "Calvin Klien", "Spring 2012", "25/04/2012", "images/paste2.jpg"),
	                    new netvogue.linesheet("linesheetId", "Donna karan", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
	                    new netvogue.linesheet("linesheetId", "Catherine jones", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
	                    new netvogue.linesheet("linesheetId", "Rebecca Jonson", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
	                    new netvogue.linesheet("linesheetId", "Givenchy", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
	                    new netvogue.linesheet("linesheetId", "Valentino", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
	                    new netvogue.linesheet("linesheetId", "DKNY", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
	                    new netvogue.linesheet("linesheetId", "Donna karan", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
	                    new netvogue.linesheet("linesheetId", "Donna karan", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
	                    new netvogue.linesheet("linesheetId", "Rebecca Minkoff", "Spring 2012", "25/04/2012", "http://placehold.it/231x306"),
	                    new netvogue.linesheet("linesheetId", "Donna karan", "Spring 2012", "25/04/2012", "http://placehold.it/231x306")
	                   ];
      return {
          getlinesheets: function () {
              return linesheets;
          }
      };
  }).
  service('search', function () {
      var advancedsearch = [
	                        new netvogue.advancedsearch("brandId", "Calvin Klien", "Calvin Klien",
	                        		"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui. Vivamus vulputate" +
                                    "ipsum vel enim.", "http://placehold.it/231x145"),
                            new netvogue.advancedsearch("brandId", "Calvin Klien", "Calvin Klien",
                            		"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui. Vivamus vulputate" +
                                     "ipsum vel enim.", "http://placehold.it/231x145"),
                            new netvogue.advancedsearch("brandId", "Calvin Klien", "Calvin Klien",
                            		"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui. Vivamus vulputate" +
                            		"ipsum vel enim.", "http://placehold.it/231x145"),
                            new netvogue.advancedsearch("brandId", "Calvin Klien", "Calvin Klien",
                            		"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui. Vivamus vulputate" +
                            		"ipsum vel enim.", "http://placehold.it/231x145"),
                            new netvogue.advancedsearch("brandId", "Calvin Klien", "Calvin Klien",
                            		"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui. Vivamus vulputate" +
                            		"ipsum vel enim.", "http://placehold.it/231x145"),
                            new netvogue.advancedsearch("brandId", "Calvin Klien", "Calvin Klien",
                            		"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui. Vivamus vulputate" +
                            		"ipsum vel enim.", "http://placehold.it/231x145"),
                            new netvogue.advancedsearch("brandId", "Calvin Klien", "Calvin Klien",
                            		"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui. Vivamus vulputate" +
                            		"ipsum vel enim.", "http://placehold.it/231x145"),
                            new netvogue.advancedsearch("brandId", "Calvin Klien", "Calvin Klien",
                            		"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui. Vivamus vulputate" +
                            		"ipsum vel enim.", "http://placehold.it/231x145"),
                            new netvogue.advancedsearch("brandId", "Calvin Klien", "Calvin Klien",
                            		"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui. Vivamus vulputate" +
                            		"ipsum vel enim.", "http://placehold.it/231x145"),
                            new netvogue.advancedsearch("brandId", "Calvin Klien", "Calvin Klien",
                            		"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui. Vivamus vulputate" +
                            		"ipsum vel enim.", "http://placehold.it/231x145"),
	                     ];
      return {
          getsearchresults: function () {
              return advancedsearch;
          }
      };
  });