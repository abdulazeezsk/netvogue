if (!netvogue )
	var netvogue = {};

if ( !window.netvogue ) {
	window.netvogue = netvogue;
}

netvogue.yettocontact  	= true;
netvogue.firsttimeLogin = false;
netvogue.isbrand		= false;
netvogue.entityname		= "Matches";
netvogue.initialize		= function() {
	var ele = jQuery('#firsttimelogin').text();
	if(null != ele && ele == "true") {
		netvogue.firsttimeLogin = true;
	}
	var ele1 = jQuery('#isbrand').text();
	if(null != ele1 && ele1 == "true") {
		netvogue.isbrand = true;
	}
	var ele2 = jQuery('#brandname').text();
	if(null != ele2) {
		netvogue.entityname = ele2;
	}
};

netvogue.hashtable = function(obj)
{
    this.length = 0;
    this.items = {};
    for (var p in obj) {
        if (obj.hasOwnProperty(p)) {
            this.items[p] = obj[p];
            this.length++;
        }
    }

    this.setItem = function(key, value)
    {
        var previous = undefined;
        if (this.hasItem(key)) {
            previous = this.items[key];
        }
        else {
            this.length++;
        }
        this.items[key] = value;
        return previous;
    };

    this.getItem = function(key) {
        return this.hasItem(key) ? this.items[key] : undefined;
    };

    this.hasItem = function(key)
    {
        return this.items.hasOwnProperty(key);
    };
   
    this.removeItem = function(key)
    {
        if (this.hasItem(key)) {
            previous = this.items[key];
            this.length--;
            delete this.items[key];
            return previous;
        }
        else {
            return undefined;
        }
    };

    this.keys = function()
    {
        var keys = [];
        for (var k in this.items) {
            if (this.hasItem(k)) {
                keys.push(k);
            }
        }
        return keys;
    };

    this.values = function()
    {
        var values = [];
        for (var k in this.items) {
            if (this.hasItem(k)) {
                values.push(this.items[k]);
            }
        }
        return values;
    };

    this.each = function(fn) {
        for (var k in this.items) {
            if (this.hasItem(k)) {
                fn(k, this.items[k]);
            }
        }
    };

    this.clear = function()
    {
        this.items = {};
        this.length = 0;
    };
};

netvogue.jsonrequest = function(id, value) {
	this.id 	= id;
	this.value 	= value;
};
/**********************************************************************/
/************************Start of**************************************/
/******************Profile Objects*************************************/
/**********************************************************************/
netvogue.contactinfo	= function(email, mobile, landline1, landline2, address, city, zip, state, country, website, yearest) {
	this.email 		= email;
    this.mobile 	= mobile;
    this.landline1	= landline1;
    this.landline2	= landline2;
    this.address	= address;
    this.city		= city;
    this.zip		= zip;
    this.state		= state;
    this.country	= country;
    this.website	= website;
    this.yearest	= yearest;
};

netvogue.productline 	= function(productlinename, category) {
	this.productlinename	= productlinename;
   	this.selected			= false;
   	this.id					= productlinename;
   	this.category			= category;
};

netvogue.brandscarried	= function(brandlistitem, brandusername, brandprofilepic) {
	this.brandname 		= brandlistitem;
	this.brandusername	= brandusername;
	this.brandprofilepic= brandprofilepic; 
};

netvogue.profile 		= function(name, profileid, aboutus, contactinfo) {
	this.name			= name;
	this.profileid		= profileid;
	this.networkstatus	= "";
	this.aboutus		= aboutus;
	this.contactinfo	= contactinfo; 		//netvogue.contactinfo
	this.productlines	= []; 	//netvogue.productline 
	this.brandscarried	= [];	//netvogue.brandscarried
  	    	               
};

netvogue.parentcategory = new function() {
	this.apparel = "APPAREL";
	this.shoes	 = "SHOES";
	this.handbags= "HANDBAGS";
	this.watches = "WATCHES";
	this.others  = "OTHERS";
};
//Default Productlines
netvogue.defaultproductlines = [
                                new netvogue.productline("Womens RTW", 	netvogue.parentcategory.apparel),
								new netvogue.productline("Denim", 		netvogue.parentcategory.apparel),
							   	new netvogue.productline("Outerwear", 	netvogue.parentcategory.apparel),
							   	new netvogue.productline("Activewear", 	netvogue.parentcategory.apparel),
							   	new netvogue.productline("Mens RTW", 	netvogue.parentcategory.apparel),
							   	new netvogue.productline("Lingerie", 	netvogue.parentcategory.apparel),
							   	new netvogue.productline("Swimwear", 	netvogue.parentcategory.apparel),
							   	new netvogue.productline("Kids", 		netvogue.parentcategory.apparel),
							   	new netvogue.productline("Mens Shoe", 	netvogue.parentcategory.shoes),
							   	new netvogue.productline("Womens Shoe", netvogue.parentcategory.shoes),
							   	new netvogue.productline("Mens Bags", 	netvogue.parentcategory.handbags),
							   	new netvogue.productline("Handbags", 	netvogue.parentcategory.handbags),
							   	new netvogue.productline("Watches", 	netvogue.parentcategory.watches),
							   	new netvogue.productline("Jewelry", 	netvogue.parentcategory.others),
							   	new netvogue.productline("Hats", 		netvogue.parentcategory.others),
							   	new netvogue.productline("Luggage", 	netvogue.parentcategory.others),
							   	new netvogue.productline("Gifts", 		netvogue.parentcategory.others),
							   	new netvogue.productline("Candles", 	netvogue.parentcategory.others)
                                ];

netvogue.getparentcategory = function(productlinename) {
	for(var i=0;i < netvogue.defaultproductlines.length; i++) {
		if(netvogue.defaultproductlines[i].productlinename == productlinename) {
			return netvogue.defaultproductlines[i].category;
		} else if(netvogue.defaultproductlines[i].category == productlinename) {
			return netvogue.defaultproductlines[i].category;
		}
	}
};
/*************************************************************************/
/************************End of*******************************************/
/*********************Profile Objects*************************************/
/*************************************************************************/

/*************************************************************************/
/***********************Start of******************************************/
/*********************Network Objects*************************************/
/*************************************************************************/

netvogue.network = function(networklistitem, profilepiclink, networklistitemID) {
	this.networklistitem 	= networklistitem;
    this.profilepiclink 	= profilepiclink;
    this.networklistitemID 	= networklistitemID; 
};

/*************************************************************************/
/************************End of*******************************************/
/*********************Network Objects*************************************/
/*************************************************************************/

/*************************************************************************/
/***********************Start of******************************************/
/*********************Gallery Objects*************************************/
/*************************************************************************/

netvogue.gallery = function(galleryid, galleryname, gallerypic, gallerydesc, gallerydate) {
	this.galleryid		= galleryid;
	this.galleryname	= galleryname;
	this.gallerypic		= gallerypic;
	this.gallerydesc	= gallerydesc;
	this.gallerydate	= gallerydate;
};

netvogue.photo = function(label, seasonname, piclink, id) {
	this.uniqueid		= id;
	this.label 			= label;
    this.seasonname 	= seasonname;
    this.piclink		= piclink;
    this.thumbnail_url 	= piclink;
    this.left_url		= piclink;
};

/*************************************************************************/
/************************End of*******************************************/
/*********************Profile Objects*************************************/
/*************************************************************************/

/*************************************************************************/
/***********************Start of******************************************/
/*********************Campaign Objects************************************/
/*************************************************************************/

netvogue.campaign = function(campaignlistitemid, campaignlistitem, campaignname, campaigncoverpic, campaigndescription) {
	this.campaignlistitemid 	= campaignlistitemid;
    this.campaignlistitem 		= campaignlistitem;
    this.campaignname 			= campaignname;
    this.campaigncoverpic		= campaigncoverpic;
    this.campaigndescription	= campaigndescription;
};

netvogue.newsletter = function() {
	this.editorials = [];
};
netvogue.newsletter.prototype = new netvogue.campaign();

netvogue.campaignjsonrequest = function(name, desc, id) {
	this.id 	= id;
	this.name	= name;
	this.desc	= desc;
};
/*************************************************************************/
/************************End of*******************************************/
/*********************Campaign Objects************************************/
/*************************************************************************/

/*************************************************************************/
/***********************Start of******************************************/
/*******************Collection Objects************************************/
/*************************************************************************/

netvogue.collectionjsonrequest = function(seasonname, desc, category, id) {
	this.id 		= id;
	this.seasonname	= seasonname;
	this.desc		= desc;
	this.category 	= category;
};

/*************************************************************************/
/************************End of*******************************************/
/*******************Collection Objects************************************/
/*************************************************************************/

/*************************************************************************/
/***********************Start of******************************************/
/*******************Stylesheet Objects************************************/
/*************************************************************************/

netvogue.stylesheetjsonrequest = function(name, category, id) {
	this.id 		= id;
	this.name		= name;
	this.category 	= category;
};

netvogue.stylejsonrequest = function(stylesheetid) {
	this.stylesheetid	= stylesheetid;
	this.styleid;
	this.stylename 		= "isha"; 
	this.styleno		= "1234";
	this.description	= "First Style";
	this.fabrication	= "Cotton";
	this.price			= 10000;
	this.availableSizes = [];
	this.availableColors= [];
	this.availableImages= [];
	
	this.empty = function() {
		this.stylename = ""; 
		this.styleno	= ""; 
		this.description= "";
		this.fabrication= "";
		this.price		= "";
		this.availableSizes = [];
		this.availableColors= [];
		this.availableImages= [];
    };
    
    this.copy = function(existingstyle) {
		this.styleid		= existingstyle.styleid;
		this.stylename 		= existingstyle.stylename;
		this.styleno		= existingstyle.styleno;
		this.description	= existingstyle.description;
		this.fabrication	= existingstyle.fabrication;
		this.price			= existingstyle.price;
		this.availableSizes = existingstyle.availableSizes;
		this.availableColors= [];
		this.availableImages= []; //This part is done from existing files
    };
};

netvogue.stylesize = function(size) {
	this.size = size;
	this.available = false;
};

netvogue.defaultstylesizes = [
                              new netvogue.stylesize("SMALL"),
                              new netvogue.stylesize("MEDIUM"),
                              new netvogue.stylesize("LARGE")
                              ];
/*************************************************************************/
/************************End of*******************************************/
/*******************Stylesheet Objects************************************/
/*************************************************************************/

/*************************************************************************/
/***********************Start of******************************************/
/*******************Linesheet Objects*************************************/
/*************************************************************************/

netvogue.linesheet = function(linesheetlistitemid, linesheetbrandname, linesheetseason, linesheetdeliverydate, linesheetcoverpic,
		linesheetcategory, linesheetproductline, linesheetdescription, linesheetprivacy) {
	this.linesheetlistitemid 	= linesheetlistitemid;
    this.linesheetbrandname 	= linesheetbrandname; //Designer -- remove this
    this.linesheetseason 		= linesheetseason; 
    this.linesheetdeliverydate	= linesheetdeliverydate;
    this.linesheetcoverpic		= linesheetcoverpic;
    this.linesheetcategory		= linesheetcategory;
    this.linesheetproductline	= linesheetproductline;
    this.linesheetdescription	= linesheetdescription;
    this.linesheetprivacy		= linesheetprivacy;
};

netvogue.style	= function(styleno, stylename, description, price, size, colors, fabrication, privacy, coverpic, views) {
	this.styleno 		= styleno;
	this.stylename		= stylename;
	this.description	= description;
	this.price			= price;
	this.privacy		= privacy;
	this.size			= [];
	this.colors			= [];
	this.fabrication	= fabrication;
	this.coverpic		= coverpic; //Main image
	this.views			= views;
};

netvogue.linesheetjsonrequest = function(name, category, deliverydate, id) {
	this.id 			= id;
	this.name			= name;
	this.category 		= category;
	this.deliverydate 	= deliverydate;
};

/*************************************************************************/
/************************End of*******************************************/
/*******************Linesheet Objects*************************************/
/*************************************************************************/

/*************************************************************************/
/***********************Start of******************************************/
/*******************Advanced Search Objects*******************************/
/*************************************************************************/

netvogue.basicSearch = function(name, location) {
	this.name 		= name;
	this.location	= location;
};

netvogue.advancedsearch = function(brandlistitemid, brandlistitem, brandname, brandlocation, brandprofilepic) {
	this.brandlistitemid 	= brandlistitemid;
    this.brandlistitem 		= brandlistitem;
    this.brandname 			= brandname; 
    this.brandlocation		= brandlocation;
    this.brandprofilepic	= brandprofilepic;
};

netvogue.searchFilter 	= function() {
	function Filter(name) {
		  this.name = name;
		  this.checked = false;
		  this.children = [];
	}
	this.getCheckedFilters = function() {
		var result = "";
		for(var i=0;i<this.filters.length;i++) {
			for(var j=0;j<this.filters[i].children.length;j++) {
				if(true == this.filters[i].children[j].checked) {
					result += (this.filters[i].children[j].name) + ",";
				}
			}
		}
		return result;
	};
	this.filters = [
	                new Filter(netvogue.parentcategory.apparel),
	                new Filter(netvogue.parentcategory.shoes),
	                new Filter(netvogue.parentcategory.handbags),
	                new Filter(netvogue.parentcategory.watches)
	                ];
	
	
	for(var i=0;i<this.filters.length;i++) {
		for(var j=0;j<netvogue.defaultproductlines.length;j++) {
			if(this.filters[i].name == netvogue.defaultproductlines[j].category) {
				this.filters[i].children.push(new Filter(netvogue.defaultproductlines[j].productlinename));
			}
		}
	}
	/*this.filters[0].children = [
							  new Filter('WOMEN\'S RTW'),
							  new Filter('MEN\'S RTW'),
							  new Filter('OUTERWEAR'),
							  new Filter('DENIM'),
							  new Filter('ACTIVE WEAR'),
							  new Filter('LOUNGE WEAR'),
							  new Filter('YOGA'),
							  new Filter('SWIM WEAR')
	];
	
	this.filters[1].children = [
	                         new Filter('WOMEN\'S SHOES'),
	                         new Filter('MEN\'S SHOES'),
	                         ];
	
	this.filters[2].children = [
	                         new Filter('WOMEN\'S Handbags'),
	                         new Filter('MEN\'S bags'),
	                         ];
	
	this.filters[3].children = [
	                         new Filter('WOMEN\'S Watches'),
	                         new Filter('MEN\'S Watches'),
	                         ];*/
};

/*************************************************************************/
/************************End of*******************************************/
/*******************Advanced Search Objects*******************************/
/*************************************************************************/