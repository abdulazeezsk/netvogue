'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('netVogue.services', []).
	value('version', '0.1').
	service('myprofile', function () {
	    var aboutus = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vivamus dui. Vivamus vulputate" +
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
	                        ];


	    return {
	        getaboutus: function () {
	            return aboutus;
	        },
	        updateaboutus: function (aboutme) {
	            aboutus = aboutme;

	        },
	        getcontactinfo: function () {
	            return contactinfo;
	        },
	        updatecontactinfo: function (contactinfotemp) {
	            angular.copy(contactinfo, contactinfotemp);
	        },
	        getproductline: function () {
	            return productline;
	        },
	        updateproductline: function (productlinetemp) {
	            angular.copy(productline, productlinetemp);
	        },
	        getbrandscarried: function () {
	            return brandscarried;
	        },
	        updatebrandscarried: function (brandscarriedtemp) {
	            angular.copy(brandscarried, brandscarriedtemp);
	        }
	    };
	}).
  service('srvprofile', function (myprofile, mynetwork, myprintcampaigns, myvideocampaigns, mynewsletters, mycollections, mylinesheets) {
      var profiles = [
	                  {
	                      "profileid": "profileid1",
	                      "aboutus": "aboutus1",
	                      "contactinfo": new netvogue.contactinfo(
	              		        "Boutiquename@gmail.com", "9949879098", "040-23371149", "040-23371149",
	            		        "Lorem ipsum dolor sit amet\, consectetuer adipiscing elit. Vivamus dui.",
	            		        "Hyderabad", "500009", "Andhra Pradesh", "India", "netvogue.org", "2012"
	            		    	),
	                      "productline": [ //	productlinename, productlinepic, selected
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
		        						  ],
	                      "brandscarried":
		   		            	   			[ //brandlistitem , brandprofilepic
		   		  		                     new netvogue.brandscarried("Calvin klein", "http://placehold.it/100x72"),
		   				                     new netvogue.brandscarried("Rebecca  Minkoff Minkoff", "http://placehold.it/100x72"),
		   				                     new netvogue.brandscarried("Donna Karan", "http://placehold.it/100x72"),
		   				                     new netvogue.brandscarried("Ritu Beri", "http://placehold.it/100x72"),
		   				                     new netvogue.brandscarried("Jason Myers", "http://placehold.it/100x72"),
		   				                     new netvogue.brandscarried("Maria will", "http://placehold.it/100x72")
		   		  		                     ]
	                  },
	                  {
	                      "profileid": "profileid2",
	                      "aboutus": "aboutus2"
	                  },
	                  {
	                      "profileid": "profileid3",
	                      "aboutus": "aboutus3"
	                  },
	                  { "profileid": "profileid4", "aboutus": "aboutus4" },
	                  { "profileid": "profileid5", "aboutus": "aboutus5" },
	                  { "profileid": "profileid6", "aboutus": "aboutus6" },
	                  { "profileid": "profileid7", "aboutus": "aboutus7" },
	                  { "profileid": "profileid8", "aboutus": "aboutus8" },
	                  { "profileid": "profileid9", "aboutus": "aboutus9" },
	                  { "profileid": "profileid10", "aboutus": "aboutus10" },
	                  ];
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
          profileinfo: function (routeparams, profile, updateserver) {
              var profileid = "";
              if (!angular.isUndefined(routeparams.profileid)) {
                  profileid = routeparams.profileid;
              }

              if (angular.isUndefined(profile)) {
                  var config = {
                      method: "GET",
                      url: "profile/" + profileid
                  };
                  return $http(config);
              }
              //Save it locally first
              if (angular.isUndefined(updateserver) || true == updateserver) {
                  var config = {
                      method: "POST",
                      data: profile,
                      url: "profile/" + profileid
                  };
                  return $http(config);
              }
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
  service('notifiaction', function () {
        var notification = [
  	                          new netvogue.notification("Id", "Calvin Klien", "Calvin Klien", "India", "Hyderabad", "August 2000", "http://placehold.it/231x145"),
                              new netvogue.notification("Id", "Calvin Klien", "Calvin Klien", "India", "Hyderabad", "August 2000", "http://placehold.it/231x145"),
                              new netvogue.notification("Id", "Calvin Klien", "Calvin Klien", "India", "Hyderabad", "August 2000", "http://placehold.it/231x145"),
                              new netvogue.notification("Id", "Calvin Klien", "Calvin Klien", "India", "Hyderabad", "August 2000", "http://placehold.it/231x145"),
	                     ];
      return {
          getnotification: function () {
                return notification;
          }
      };
  });