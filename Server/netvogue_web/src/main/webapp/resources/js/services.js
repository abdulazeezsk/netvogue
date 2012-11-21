'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('netVogue.services', []).
	value('version', '0.1').
	service('myprofile', function ($http) {
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
	    	isbrand: function() {
	    		if(angular.isUndefined(profileinfo.isbrand))
	        		return false;
	    		return profileinfo.isbrand;
	    	},
	        getaboutus: function () {
	        	if(angular.isUndefined(profileinfo.aboutus))
	        		return "";
	            return profileinfo.aboutus;
	        },
	        setaboutus: function (aboutme) {
	        	profileinfo.aboutus = aboutme;

	        },
	        getprofilepic: function() {
	    		if(angular.isUndefined(profileinfo.profilepic))
	        		return "";
	    		return profileinfo.profilepic;
	    	},
	    	setprofilepic: function(profilepic) {
	    		profileinfo.profilepic = profilepic;
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
}).service('srvprofile', function ($http, myprofile, mynetwork) {
	var profileinfo = new netvogue.profile(); //This must be converted to hashtable if we want to store localdata
      
      /*********************************/
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
          setProfileLocally: function(profiledata, routeparams) {
        	  netvogue.yettocontact = false;
        	  if (angular.isUndefined(routeparams.profileid)) {
            	  myprofile.setprofileinfo(profiledata);
              } else {
            	  profileinfo = angular.copy(profiledata);
              }
          },
          getname: function(routeparams) {
              if (angular.isUndefined(routeparams.profileid)) {
                  return myprofile.getname();
              } else {
                  /*angular.forEach(profiles, function (profile) {
                      if (angular.equals(profile['profileid'], routeparams.profileid)) {
                          result = profile['aboutus'];
                      }
                  });*/
            	  if(angular.isUndefined(profileinfo.name))
  	        		return "";
            	  return profileinfo.name;
              }
          },
          isbrand: function(routeparams) {
        	  if (angular.isUndefined(routeparams.profileid)) {
                  return myprofile.isbrand();
              } else {
            	  if(angular.isUndefined(profileinfo.isbrand))
  	        		return false;
                  return profileinfo.isbrand;
              }
          },
          getaboutus: function (routeparams) {
              if (angular.isUndefined(routeparams.profileid)) {
                  return myprofile.getaboutus();
              } else {
            	  if(angular.isUndefined(profileinfo.aboutus))
  	        		return "";
                  return profileinfo.aboutus;
              }
          },
          getprofilepic: function(routeparams) {
              if (angular.isUndefined(routeparams.profileid)) {
                  return myprofile.getprofilepic();
              } else {
            	  if(angular.isUndefined(profileinfo.profilepic))
  	        		return "";
            	  return profileinfo.profilepic;
              }
          },
          getnetworkstatus: function(routeparams) {
        	  if(angular.isUndefined(profileinfo.networkstatus))
	        		return "";
              return profileinfo.networkstatus;
          },
          getcontactinfo: function (routeparams) {
              if (angular.isUndefined(routeparams.profileid)) {
                  return myprofile.getcontactinfo();
              } else {
            	  if(angular.isUndefined(profileinfo.contactinfo))
  	        		return {};
                  return profileinfo.contactinfo;
              }
          },
          getproductline: function (routeparams) {
              if (angular.isUndefined(routeparams.profileid)) {
                  return myprofile.getproductline();
              } else {
            	  if(angular.isUndefined(profileinfo.productlines))
  	        		return [];
                  return profileinfo.productlines;
              }
          },
          getbrandscarried: function (routeparams) {
              if (angular.isUndefined(routeparams.profileid)) {
                  return myprofile.getbrandscarried();
              } else {
            	  if(angular.isUndefined(profileinfo.brandscarried))
  	        		return [];
                  return profileinfo.brandscarried;
              }
          }
      };
}).service('currentvisitedprofile', function ($location) {
      var home = true;
      var historyURL = "";
      var currentURL = "";
      var myprofileID= "";
      var currentProfileID = "";
      return {
          isMyProfile: function () {
              return (currentProfileID == myprofileID || currentProfileID == "")? true : false;
          },
          isHomePage: function () {
              return home;
          },
          setmyprofileid: function(id) {
        	  myprofileID = id;
          },
          profilevisitChange: function (routeParams, hometemp) {
              if (angular.isUndefined(routeParams.profileid)) {
                  currentProfileID = "";
              } else if (!angular.equals(routeParams.profileid, currentProfileID)) {
                  currentProfileID = routeParams.profileid;
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
              if (!(currentProfileID == myprofileID || currentProfileID == "")) {
                  for (var link in leftpanellinks) {
                      leftpanellinks[link] = "#/" + currentProfileID + "/" + link;
                  }
              }
              return leftpanellinks;
          }
      };
}).service('mygallery', function ($http) {
		var name;
		var isbrand;
		var profilepic;
		var galleryname;
		var galleries = [];
		var photos    = [];
		return {
			getgalleries: function() {
	    		return galleries;
	    	},
	    	getname: function() {
	    		if(angular.isUndefined(name))
	        		return "";
	    		return name;
	    	},
	    	setname: function(name) {
	    		this.name = name;
	    	},
	    	isbrand: function() {
	    		if(angular.isUndefined(isbrand))
	        		return false;
	    		return isbrand;
	    	},
	    	getprofilepic: function() {
	    		if(angular.isUndefined(profilepic))
	        		return "";
	    		return profilepic;
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
	    	updategallery: function(ajaxrequestcall, datatosend) {
	    		var config = {
	    				method: "POST",
	    				data: datatosend,
	                    url: ajaxrequestcall + "/edit"
	                };
	            return $http(config);
	    	},
	    	updategallerylocally: function(galleryid, newname) {
	    		var index=0;
	    		for(index=0; index <  galleries.length; index++) {
	    			if(galleries[index].galleryid == galleryid) {
	    				galleries[index].galleryname = newname;
	    				break;
	    			}
	    		}
	    	},
	    	setprofilepic: function(ajaxrequestcall, datatosend) {
	    		var config = {
		    			method: "POST",
		    			data: datatosend,
		                url: ajaxrequestcall + "/setprofilepic"
		            };
		    	return $http(config);
	    	},
	    	sortphotos: function(ajaxrequestcall, datatosend) {
	    		var config = {
		    			method: "POST",
		    			data: datatosend,
		                url: ajaxrequestcall + "/sortphotos"
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
	    	deletephoto: function(ajaxrequestcall, photoid) {
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
	    	},
	    	galleries: function (ajaxrequestcall, routeparams, jsonrequest, pagenumber) {
		          var profileid = "";
		          if (!angular.isUndefined(routeparams.profileid)) {
		        	  profileid = routeparams.profileid;
		          }
		          if(angular.isUndefined(pagenumber))
		        	  pagenumber = 0;
		          var datatosend = {
		        		  	"pagenumber"  : pagenumber,
			  				"galleryname" : jsonrequest
			  	};
		          var config = {
		              method: "GET",
		              params: datatosend,
		              url: ajaxrequestcall + "/" + profileid
		          };
		          return $http(config);
		      },
		      photos: function (ajaxrequestcall, routeparams, galleryid, photoname, pagenumber) {
		          var profileid = "";
		          if (!angular.isUndefined(routeparams.profileid)) {
		        	  profileid = routeparams.profileid;
		          }
		          if(angular.isUndefined(pagenumber))
		        	  pagenumber = 0;
		          var datatosend = {
		  				"photoname" : photoname,
		  				"galleryid" : galleryid,
		  				"pagenumber": pagenumber
		  		};
		          var config = {
		              method: "GET",
		              params: datatosend,
		              url: ajaxrequestcall + "/getphotos/" + profileid
		          };
		          return $http(config);
		      },
		      setgallerylocally: function(galleriestemp, pagenumber) {
		    	  if(angular.isUndefined(pagenumber))
		        	  pagenumber = 0;
		    	  if(0 == pagenumber) {
		    		  name = galleriestemp.name;
			    	  isbrand = galleriestemp.isbrand;
			    	  profilepic = galleriestemp.profilepic;
		    		  angular.copy(galleriestemp.galleries, galleries);
		    	  } else {
		    		  for(var i=0; i < galleriestemp.galleries.length; i++) {
		    			  galleries.push(galleriestemp.galleries[i]);
		    		  }
		    	  } 
		      },
		      setphotoslocally: function(photostemp, pagenumber) {
		    	  if(angular.isUndefined(pagenumber))
		        	  pagenumber = 0;
		    	  if(0 == pagenumber) {
			    	  name = photostemp.name;
			    	  galleryname = photostemp.galleryname;
			    	  angular.copy(photostemp.photos, photos);
		    	  } else {
		    		  for(var i=0; i < photostemp.photos.length; i++) {
		    			  photos.push(photostemp.photos[i]);
		    		  }
		    	  } 
		      }
		};
 }).service('mycollection', function ($http) {
		var name;
		var profilepic;
		var galleryname;
		var brandname;
		var collections = [];
		var photos    = [];
		return {
			getcollections: function() {
	    		return collections;
	    	},
	    	getname: function() {
	    		if(angular.isUndefined(name))
	        		return "";
	    		return name;
	    	},
	    	setname: function(name) {
	    		this.name = name;
	    	},
	    	getprofilepic: function() {
	    		if(angular.isUndefined(profilepic))
	        		return "";
	    		return profilepic;
	    	},
	    	getgalleryname: function() {
	    		if(angular.isUndefined(galleryname))
	        		return "";
	    		return galleryname;
	    	},
	    	setgalleryname: function(name) {
	    		this.galleryname = name;
	    	},
	    	getbrandname: function() {
	    		if(angular.isUndefined(brandname))
	        		return "";
	    		return brandname;
	    	},
	    	createcollection: function(jsonrequest) {
	    		var config = {
  				method: "POST",
  				data: jsonrequest,
                url: "collection/create"
              };
              return $http(config);
	    	},
	    	updatecollection: function(datatosend) {
	    		var config = {
	    				method: "POST",
	    				data: datatosend,
	                    url: "collection/edit"
	                };
	            return $http(config);
	    	},
	    	updatecollectionlocally: function(id, name, desc) {
	    		var index=0;
	    		for(index=0; index <  collections.length; index++) {
	    			if(collections[index].galleryid == id) {
	    				collections[index].galleryname = name;
	    				collections[index].gallerydesc = desc;
	    				break;
	    			}
	    		}
	    	},
	    	setprofilepic: function(datatosend) {
	    		var config = {
		    			method: "POST",
		    			data: datatosend,
		                url: "collection/setprofilepic"
		            };
		    		return $http(config);
	    	},
	    	sortphotos: function(datatosend) {
	    		var config = {
		    			method: "POST",
		    			data: datatosend,
		                url: "collection/sortphotos"
		            };
		    	return $http(config);
	    	},
	    	deletecollection: function(galleryid) {
	    		var config = {
	    			method: "POST",
	    			data: galleryid,
	                url: "collection/delete"
	            };
	    		return $http(config);
	    	},
	    	deletecollectionlocally: function(galleryid) {
	    		var index=0;
	    		for(index=0; index <  collections.length; index++) {
	    			if(collections[index].galleryid == galleryid) {
	    				break;
	    			}
	    		}
	    		collections.splice(index, 1);
	    	},
	    	getphotos: function() {
	    		return photos;
	    	},
	    	savephotoinfo: function(label, seasonname, photoid) {
	    		var datatosend = {
	    				"photoname" : label,
	    				"seasonname": seasonname,
	    				"photoid"	: photoid
	    		};
	    		var config = {
	    				method: "POST",
	    				data: datatosend,
	                    url: "collection/editphotoinfo"
	                };
	            return $http(config);
	    	},
	    	deletephoto: function(photoid) {
	    		var config = {
	    			method: "POST",
	    			data: photoid,
	                url: "collection/deletephoto"
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
	    	},
	    	collections: function(routeparams, jsonrequest) {
		          var profileid = "";
		          if (!angular.isUndefined(routeparams.profileid)) {
		        	  profileid = routeparams.profileid;
		          }
		          var config = {
		              method: "GET",
		              params: jsonrequest,
		              url:   "getcollections/" + profileid
		          };
		          return $http(config);
		     },
		     photos: function (routeparams, galleryid, photoname, pagenumber) {
		          var profileid = "";
		          if (!angular.isUndefined(routeparams.profileid)) {
		        	  profileid = routeparams.profileid;
		          }
		          if(angular.isUndefined(pagenumber))
		        	  pagenumber = 0;
		          var datatosend = {
		  				"photoname" : photoname,
		  				"galleryid" : galleryid,
		  				"pagenumber": pagenumber
		  		  };
		          var config = {
		              method: "GET",
		              params: datatosend,
		              url: "collection/getphotos/" + profileid
		          };
		          return $http(config);
		     },
		     setcollectionlocally: function(temp, pagenumber) {
		    	 if(angular.isUndefined(pagenumber))
		        	  pagenumber = 0;
		    	 if(0 == pagenumber) {
			    	 name = temp.name;
			    	 profilepic = temp.profilepic;
			    	 angular.copy(temp.collections, collections);
		    	 } else {
		    		 for(var i=0; i < temp.collections.length; i++) {
		    			 collections.push(temp.collections[i]);
		    		  }
		    	 }
		     },
		     setphotoslocally: function(photostemp, pagenumber) {
		    	 if(angular.isUndefined(pagenumber))
		        	  pagenumber = 0;
		    	 if(0 == pagenumber) {
		    		 name = photostemp.name;
		    		 galleryname = photostemp.galleryname;
		    		 brandname = photostemp.brandname;
		    		 angular.copy(photostemp.photos, photos);
		    	 } else {
		    		  for(var i=0; i < photostemp.photos.length; i++) {
		    			  photos.push(photostemp.photos[i]);
		    		  }
		    	  } 
		     }
		};
}).service('mystylesheet', function ($http) {
	var name;
	var profilepic;
	var stylesheetname;
	var brandname;
	var stylesheets = [];
	var styles    = [];
	return {
		getstylesheets: function() {
    		return stylesheets;
    	},
    	getname: function() {
    		if(angular.isUndefined(name))
        		return "";
    		return name;
    	},
    	setname: function(name) {
    		this.name = name;
    	},
    	getprofilepic: function() {
    		if(angular.isUndefined(profilepic))
        		return "";
    		return profilepic;
    	},
    	getstylesheetname: function() {
    		if(angular.isUndefined(stylesheetname))
        		return "";
    		return stylesheetname;
    	},
    	setstylesheetname: function(name) {
    		this.stylesheetname = name;
    	},
    	getbrandname: function() {
    		if(angular.isUndefined(brandname))
        		return "";
    		return brandname;
    	},
    	createstylesheet: function(jsonrequest) {
    		var config = {
				method: "POST",
				data: jsonrequest,
            url: "stylesheet/create"
          };
          return $http(config);
    	},
    	updatestylesheet: function(datatosend) {
    		var config = {
    				method: "POST",
    				data: datatosend,
                    url: "stylesheet/edit"
                };
            return $http(config);
    	},
    	updatestylesheetlocally: function(data) {
    		var index=0;
    		for(index=0; index <  stylesheets.length; index++) {
    			if(stylesheets[index].galleryid == data.id) {
    				stylesheets[index].galleryname = data.name;
    				stylesheets[index].category	= data.category;
    				break;
    			}
    		}
    	},
    	deletestylesheet: function(galleryid) {
    		var config = {
    			method: "POST",
    			data: galleryid,
                url: "stylesheet/delete"
            };
    		return $http(config);
    	},
    	deletestylesheetlocally: function(galleryid) {
    		var index=0;
    		for(index=0; index <  stylesheets.length; index++) {
    			if(stylesheets[index].galleryid == galleryid) {
    				break;
    			}
    		}
    		stylesheets.splice(index, 1);
    	},
    	getstyles: function() {
    		return styles;
    	},
    	updatestyle: function(style, edit) { //Same for create style
    		var images = [];
    		for(var i=0;i < style.availableImages.length;i++) {
    			images.push(style.availableImages[i].uniqueid);
    		}
    		style.availableImages = images;
    		
    		var request = "";
    		if(edit)
    			request = "editstyle";
    		else
    			request = "createstyle";
    		var config = {
        			method: "POST",
        			data: style,
                    url: "stylesheet/" + request
                };
        		return $http(config);
    	},
    	updatestyleslocally: function(style) {
    		var index=0;
    		for(index=0; index <  styles.length; index++) {
    			if(styles[index].styleid == style.styleid) {
    				styles[index] = style;
    				break;
    			}
    		}
    		if(index == styles.length) {
    			styles.push(style);
    		}
    			
    	},
    	deletestyle: function(photoid) {
    		var config = {
    			method: "POST",
    			data: photoid,
                url: "stylesheet/deletestyle"
            };
    		return $http(config);
    	},
    	deletestyleslocally: function(id) {
    		var i=0;
    		for(i=0; i <  styles.length; i++) {
    			if(styles[i].styleid == id) {
    				break;
    			}
    		}
    		styles.splice(i, 1);
    	},
    	stylesheets: function(routeparams, jsonrequest) {
            var profileid = "";
            if (!angular.isUndefined(routeparams.profileid)) {
          	  profileid = routeparams.profileid;
            }
            var config = {
                method: "GET",
                params: jsonrequest,
                url:   "getstylesheets/" + profileid
            };
            return $http(config);
        },
        styles: function (routeparams, id, styleno, fabrication, fromprice, toprice, pagenumber) {
            var profileid = "";
            if (!angular.isUndefined(routeparams.profileid)) {
          	  profileid = routeparams.profileid;
            }
            if(angular.isUndefined(pagenumber))
	        	  pagenumber = 0;
            var datatosend = {
    				"stylesheetid" 	: id,
    				"styleno"		: styleno,
    				"fabrication"	: fabrication,
    				"fromprice"		: fromprice,
    				"toprice"		: toprice,
    				"pagenumber"	: pagenumber
    		};
            var config = {
                method: "GET",
                params: datatosend,
                url: "stylesheet/getstyles/" + profileid
            };
            stylesheets = [];
            return $http(config);
        },
        stylesbycategory: function (routeparams, category, name) {
            var profileid = "";
            if (!angular.isUndefined(routeparams.profileid)) {
          	  profileid = routeparams.profileid;
            }
            var datatosend = {
    				"stylename" : name,
    				"category" : category
    		};
            var config = {
                method: "GET",
                params: datatosend,
                url: "stylesheet/getstylesbycat/" + profileid
            };
            return $http(config);
        },
        setstylesheetlocally: function(temp, pagenumber) {
        	if(angular.isUndefined(pagenumber))
	        	  pagenumber = 0;
        	if(0 == pagenumber) {
	        	name = temp.name;
	    		profilepic = temp.profilepic;
	    		angular.copy(temp.stylesheets, stylesheets);
        	} else {
	    		 for(var i=0; i < temp.stylesheets.length; i++) {
	    			 stylesheets.push(temp.stylesheets[i]);
	    		  }
	    	 }
        },
        setstyleslocally: function(temp, pagenumber) {
        	if(angular.isUndefined(pagenumber))
	        	  pagenumber = 0;
        	if(0 == pagenumber) {
	        	name = temp.name;
	    		stylesheetname = temp.stylesheetname;
	    		brandname = temp.brandname;
	    		angular.copy(temp.styles, styles);
        	} else {
	    		 for(var i=0; i < temp.styles.length; i++) {
	    			 styles.push(temp.styles[i]);
	    		  }
	    	 }
        }
	};
}).service('mylinesheet', function ($http) {
	var name;
	var brandname;
	var profilepic;
	var linesheetname;
	var linesheets = [];
	var styles    = [];
	return {
		getlinesheets: function() {
    		return linesheets;
    	},
    	getname: function() {
    		if(angular.isUndefined(name))
        		return "";
    		return name;
    	},
    	setname: function(name) {
    		this.name = name;
    	},
    	getprofilepic: function() {
    		if(angular.isUndefined(profilepic))
        		return "";
    		return profilepic;
    	},
    	getlinesheetname: function() {
    		if(angular.isUndefined(linesheetname))
        		return "";
    		return linesheetname;
    	},
    	setlinesheetname: function(name) {
    		this.linesheetname = name;
    	},
    	getbrandname: function() {
    		if(angular.isUndefined(brandname))
        		return "";
    		return brandname;
    	},
    	createlinesheet: function(jsonrequest) {
    		var config = {
				method: "POST",
				data: jsonrequest,
            url: "linesheet/create"
          };
          return $http(config);
    	},
    	updatelinesheet: function(datatosend) {
    		var config = {
    				method: "POST",
    				data: datatosend,
                    url: "linesheet/edit"
                };
            return $http(config);
    	},
    	updatelinesheetlocally: function(data) {
    		var index=0;
    		for(index=0; index <  linesheets.length; index++) {
    			if(linesheets[index].galleryid == data.id) {
    				linesheets[index].galleryname = data.name;
    				linesheets[index].category	= data.category;
    				linesheets[index].deliverydate = data.deliverydate;
    				break;
    			}
    		}
    	},
    	deletelinesheet: function(galleryid) {
    		var config = {
    			method: "POST",
    			data: galleryid,
                url: "linesheet/delete"
            };
    		return $http(config);
    	},
    	deletelinesheetlocally: function(galleryid) {
    		var index=0;
    		for(index=0; index <  linesheets.length; index++) {
    			if(linesheets[index].galleryid == galleryid) {
    				break;
    			}
    		}
    		linesheets.splice(index, 1);
    	},
    	getstyles: function() {
    		return styles;
    	},
    	addstyle: function(styleid) { //Same for create style
    		var config = {
        			method: "POST",
        			data: styleid,
                    url: "linesheet/addstyle"
                };
        		return $http(config);
    	},
    	addstylelocally: function(style) {
    			styles.push(style);
    	},
    	deletestyle: function(photoid) {
    		var config = {
    			method: "POST",
    			data: photoid,
                url: "linesheet/deletestyle"
            };
    		return $http(config);
    	},
    	deletestyleslocally: function(id) {
    		var i=0;
    		for(i=0; i <  styles.length; i++) {
    			if(styles[i].styleid == id) {
    				break;
    			}
    		}
    		styles.splice(i, 1);
    	},
    	linesheets: function(routeparams, jsonrequest) {
            var profileid = "";
            if (!angular.isUndefined(routeparams.profileid)) {
          	  profileid = routeparams.profileid;
            }
            var config = {
                method: "GET",
                params: jsonrequest,
                url:   "getlinesheets/" + profileid
            };
            return $http(config);
        },
        styles: function (routeparams, id, styleno, fromprice, toprice, pagenumber) {
            var profileid = "";
            if (!angular.isUndefined(routeparams.profileid)) {
          	  profileid = routeparams.profileid;
            }
            if(angular.isUndefined(pagenumber))
	        	  pagenumber = 0;
            var datatosend = {
    				"linesheetid" 	: id,
    				"styleno"		: styleno,
    				"fromprice"		: fromprice,
    				"toprice"		: toprice,
    				"pagenumber"	: pagenumber
    		  };
            var config = {
                method: "GET",
                params: datatosend,
                url: "linesheet/getstyles/" + profileid
            };
            linesheets = [];
            return $http(config);
        },
        setlinesheetlocally: function(temp, pagenumber) {
        	if(angular.isUndefined(pagenumber))
	        	  pagenumber = 0;
        	if(0 == pagenumber) {
	        	name = temp.name;
	    		profilepic = temp.profilepic;
	    		angular.copy(temp.linesheets, linesheets);
        	} else {
	    		 for(var i=0; i < temp.linesheets.length; i++) {
	    			 linesheets.push(temp.linesheets[i]);
	    		  }
	    	 }
        },
        setstyleslocally: function(temp, pagenumber) {
        	if(angular.isUndefined(pagenumber))
	        	  pagenumber = 0;
        	if(0 == pagenumber) {
        		name = temp.name;
	    		linesheetname = temp.stylesheetname;//We are using same styles for both stylesheet and linesheet
	    		brandname = temp.brandname;
	    		angular.copy(temp.styles, styles);
        	} else {
	    		 for(var i=0; i < temp.styles.length; i++) {
	    			 styles.push(temp.styles[i]);
	    		  }
	    	}
        }
	};
}).service('trending', function () {
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

}).service('mynotifications', function ($http) {
	var notifications = [];
	var unreadnotifications = [];
	var numberofunreadnotifications = 0;
	var name;
	var profileid;
	var profilepic;
	return {
		getnotifications: function() {
    		return notifications;
    	},
    	getunreadnotifications: function() {
    		return unreadnotifications;
    	},
    	setnotifications: function(temp) {
    		name = temp.name;
    		profilepic = temp.profilepic;
    		profileid  = temp.profileid;	
    		angular.copy(temp.notifications, notifications);
    	},
    	setunreadnotifications: function(temp) {
    		name = temp.name;
    		profilepic = temp.profilepic;
    		profileid  = temp.profileid;
    		numberofunreadnotifications = temp.unreadnotifications;
    		angular.copy(temp.notifications, unreadnotifications);
    	},
    	getname: function() {
    		if(angular.isUndefined(name))
        		return "";
    		return name;
    	},
    	getprofileid: function() {
    		if(angular.isUndefined(profileid))
        		return "";
    		return profileid;
    	},
    	setname: function(name) {
    		this.name = name;
    	},
    	getprofilepic: function() {
    		if(angular.isUndefined(profilepic))
        		return "";
    		return profilepic;
    	},
    	getnumberofunreadnotifications: function() {
    		return numberofunreadnotifications;
    	},
    	notificationsunread: function() {
    		var config = {
  	              method: "GET",
  	              url: "notifications/unread"
  	          };
  	          return $http(config);
    	},
    	notifications: function() {
    		var config = {
  	              method: "GET",
  	              url: "notifications/get"
  	          };
  	          return $http(config);
    	},
    	discardnetwork: function(id) {
    		var config = {
    				method: "POST",
    				data: id,
    	            url: "notifications/discardnetwork"
    	          };
            return $http(config);
    	},
    	markread: function(id) {
    		var config = {
    				method: "POST",
    				data: profileid,
    	            url: "notifications/markread"
    	          };
            return $http(config);
    	},
    	markallread: function() {
    		var config = {
    				method: "POST",
    	            url: "notifications/markallread"
    	          };
            return $http(config);
    	},
    	markreadlocally: function(id) {
    		var index=0;
    		for(index=0; index <  notifications.length; index++) {
    			if(notifications[index].notificationid == id) {
    				notifications[index].isread = true;
    				break;
    			}
    		}
    	},
    	confirmnetworklocally: function(id) {
    		var index=0;
    		for(index=0; index <  notifications.length; index++) {
    			if(notifications[index].profileid == id) {
    				notifications[index].networkstatus = "CONFIRMED";
    				break;
    			}
    		}
    	},
    	discardnetworklocally: function(id) {
    		var index=0;
    		for(index=0; index <  notifications.length; index++) {
    			if(notifications[index].notificationid == id) {
    				notifications[index].networkstatus = "DISCARD";
    				break;
    			}
    		}
    	}
      };
}).service('mynetwork', function ($http) {
	var networks = [];
	var name;
	var isbrand;
	var profilepic;
	var contactinfo;
	return {
		getnetworks: function() {
    		return networks;
    	},
    	getname: function() {
    		if(angular.isUndefined(name))
        		return "";
    		return name;
    	},
    	setname: function(name) {
    		this.name = name;
    	},
    	isbrand: function() {
    		if(angular.isUndefined(isbrand))
        		return false;
    		return isbrand;
    	},
    	getprofilepic: function() {
    		if(angular.isUndefined(profilepic))
        		return "";
    		return profilepic;
    	},
    	getcontactinfo: function() {
    		if(angular.isUndefined(contactinfo))
        		return {};
    		return contactinfo;
    	},
    	createnetwork: function(profileid) {
    		var config = {
    				method: "POST",
    				data: profileid,
    				url: "network/createnetwork"
          };
          return $http(config);
    	},
    	confirmnetwork: function(profileid) {
    		var config = {
    				method: "POST",
    				data: profileid,
    	            url: "network/confirmnetwork"
    	          };
            return $http(config);
    	},
    	deletenetwork: function(profileid) {
    		var config = {
    				method: "POST",
    				data: profileid,
    	            url: "network/deletenetwork"
    	          };
            return $http(config);
    	},
    	blocknetwork: function(profileid) {
    		var config = {
    				method: "POST",
    				data: profileid,
    	            url: "network/blocknetwork"
    	          };
            return $http(config);
    	},
    	unblocknetwork: function(profileid) {
    		var config = {
    				method: "POST",
    				data: profileid,
    	            url: "network/unblocknetwork"
    	          };
            return $http(config);
    	},
    	confirmnetworklocally: function(profileid) {
    		var index=0;
    		for(index=0; index <  networks.length; index++) {
    			if(networks[index].profileid == profileid) {
    				networks[index].networkstatus = "CONFIRMED";
    				break;
    			}
    		}
    	},
    	blocknetworklocally: function(profileid) {
    		var index=0;
    		for(index=0; index <  networks.length; index++) {
    			if(networks[index].profileid == profileid) {
    				networks[index].networkstatus = "BLOCKED";
    				break;
    			}
    		}
    	},
    	deletenetworklocally: function(profileid) {
    		var index=0;
    		for(index=0; index <  networks.length; index++) {
    			if(networks[index].profileid == profileid) {
    				break;
    			}
    		}
    		networks.splice(index, 1);
    	},
    	networks: function (routeparams, onlyconfirmed, pagenumber) {
	          var profileid = "";
	          if (!angular.isUndefined(routeparams.profileid)) {
	        	  profileid = routeparams.profileid;
	          }
	          var datatosend = {
	        	  "pagenumber": pagenumber,
	        	  "onlyconfirmed" : onlyconfirmed
	          };
	          var config = {
	              method: "GET",
	              params: datatosend,
	              url: "network/getnetworks/" + profileid
	          };
	          return $http(config);
    	},
    	setnetworkslocally: function(temp, pagenumber) {
    		if(0 == pagenumber) {
	    		name = temp.name;
	    		isbrand = temp.isbrand;
	    		profilepic = temp.profilepic;
	    		contactinfo = angular.copy(temp.contactinfo);
	    		networks = angular.copy(temp.networks);
    		} else {
	    		 for(var i=0; i < temp.networks.length; i++) {
	    			 networks.push(temp.networks[i]);
	    		  }
	    	 }
    	}
	};
}).service('myreferences', function ($http) {
	var references = [];
	return {
		getreferences: function() {
    		return references;
    	},
    	references: function (pagenumber) {
    		var datatosend = {
    			"pagenumber": pagenumber
    		};
    		var config = {
    			method: "GET",
	             params: datatosend,
	             url: "getreferences"
	        };
	        return $http(config);
    	},
    	setreferenceslocally: function(temp) {
    		references = angular.copy(temp);
    	}
	};
}).service('mytimeline', function ($http) {
	var updates = [];
	var isbrand;
	var name;
	var profilepic;
	var contactinfo;
	return {
		getupdates: function() {
    		return updates;
    	},
    	getname: function() {
    		if(angular.isUndefined(name))
        		return "";
    		return name;
    	},
    	setname: function(name) {
    		this.name = name;
    	},
    	isbrand: function() {
    		if(angular.isUndefined(isbrand))
        		return false;
    		return isbrand;
    	},
    	getprofilepic: function() {
    		if(angular.isUndefined(profilepic))
        		return "";
    		return profilepic;
    	},
    	getcontactinfo: function() {
    		if(angular.isUndefined(contactinfo))
        		return {};
    		return contactinfo;
    	},
    	addupdate: function(update) {
    		var config = {
    				method: "POST",
    				data: update,
    				url: "statusupdate/add"
          };
          return $http(config);
    	},
    	editupdate: function(id, update) {
    		var request = netvogue.jsonrequest(id, update);
    		var config = {
    				method: "POST",
    				data: id,
    				url: "statusupdate/edit"
          };
          return $http(config);
    	},
    	deleteupdate: function(id) {
    		var config = {
    				method: "POST",
    				data: id,
    				url: "statusupdate/delete"
          };
          return $http(config);
    	},
    	addupdatelocally: function(data) {
    		var temp = [];
    		temp.push(data);
    		for(var i=0; i < updates.length; i++) {
    			temp.push(updates[i]);
    		}
    		updates = angular.copy(temp);
    	},
    	editupdatelocally: function(id, status) {
    		var index=0;
    		for(index=0; index <  updates.length; index++) {
    			if(updates[index].statusid == id) {
    				updates[index].status = status;
    				break;
    			}
    		}
    	},
    	deleteupdatelocally: function(id) {
    		var index=0;
    		for(index=0; index <  updates.length; index++) {
    			if(updates[index].statusid == id) {
    				break;
    			}
    		}
    		updates.splice(index, 1);
    	},
    	updates: function (routeparams, pagenumber) {
	          var profileid = "";
	          if (!angular.isUndefined(routeparams.profileid)) {
	        	  profileid = routeparams.profileid;
	          }
	          var datatosend = {
	        		  "pagenumber" : pagenumber
	          };
	          var config = {
	              method: "GET",
	              params: datatosend,
	              url: "getstatusupdates/" + profileid
	          };
	          return $http(config);
    	},
    	setupdateslocally: function(temp, pagenumber) {
    		if(0 == pagenumber) {
	    		name = temp.name;
	    		isbrand = temp.isbrand;
	    		profilepic = temp.profilepic;
	    		contactinfo = angular.copy(temp.contactinfo);
	    		updates = angular.copy(temp.updates);
    		} else {
	    		 for(var i=0; i < temp.updates.length; i++) {
	    			 updates.push(temp.updates[i]);
	    		  }
	    	 }
    	}
	};
}).service('search', function ($http) {
      return {
          getallusers: function(query) {
        	  var datatosend = {
        			  "query": query
        	  };
        	  var config = {
        			  method: "GET",
        			  params: datatosend,
                      url: "getallusers"
              };
        	  return $http(config);  
          },
          getadvancedsearchresults: function(name, location, categories, 
        		  usertype, userscarried, fromprice, toprice, pagenumber) {
        	  var datatosend = {
        			"name"			: name, 
        			"location"		: location,
					"categories"	: categories, 
					"usertype"		:	usertype,
					"userscarried"	: userscarried,
					"fromprice"		: fromprice,
					"toprice"		: toprice,
					"pagenumber"	: pagenumber
        	  };
        	  var config = {
                  method: "GET",
                  params: datatosend,
                  url: "advancedsearch"
              };
        	  return $http(config);
          },
          getbasicsearchresults: function(query) {
        	  var datatosend = {
        			  "query": query
        	  };
        	  var config = {
                  method: "GET",
                  params: datatosend,
                  url: "basicsearch"
              };
        	  return $http(config);
          }
      };
}).service('myaccount', function ($http) {
	    var accountinfo;

	    return {
	    	getaccountinfo: function() {
	    		return accountinfo;
	    	},
	    	setaccountinfo: function(account) {
	    		angular.copy(account, accountinfo);
	    	},
	    	setname: function (name) {
		       	accountinfo.name = name;
		    },
	    	getname: function() {
	    		return accountinfo.name;
	    	},
	    	isbrand: function() {
	    		return accountinfo.isbrand;
	    	},
	        getprofilepic: function() {
	    		return accountinfo.profilepic;
	    	},
	    	getemail: function() {
	    		return accountinfo.email;
	    	},
	    	setemail: function (email) {
		       	accountinfo.email = email;
		       },
	    	getemailnotifications: function () {
	        	if(angular.isUndefined(accountinfo.emailnotifications))
	        		return {};
	            return accountinfo.emailnotifications;
	        },
	        setemailnotifications: function (notifications) {
	        	accountinfo.emailnotifications = notifications;
	        },	       
	        posttoserver: function(datatosend, url) {	        	
	        	var config = {
	                      method: "POST",
	                      data: datatosend,
	                      url: "/account/" + url
	            };
	        	return $http(config);
	        },
	        accountinfo: function () {
	              var config = {
	                  method: "GET",
	                  url: "/getaccountdetails/"
	              };
	              return $http(config);
	        },
	        setAccountLocally: function(temp) {
	        	 accountinfo = angular.copy(temp);
	        },
	    };
});