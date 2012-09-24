'use strict';

/* Add and Edit Controllers */

function MyCtrlAddCollections($scope, $routeParams, currentvisitedprofile) {

    $scope.backButton = currentvisitedprofile.getBackHistory();
    $scope.addDetails = true;
    $scope.newcollection = {};
    $scope.AddCollection = function (collection) {
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
    $scope.newfiles = [];
	$scope.existingfiles = [
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "img/donna_karan_adriana_lima_1.jpg"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300")
	                        ];
	$scope.filesadded	= function(element) {
		$scope.$apply(function($scope) {
			// Turn the FileList object into an Array
			$scope.$broadcast('filesadded', element.files);
		});
	};
}

function MyCtrlAddLinesheets($scope, $routeParams, currentvisitedprofile) {

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

function MyCtrlAddNewsletter($scope, $routeParams, currentvisitedprofile) {

    $scope.backButton = currentvisitedprofile.getBackHistory();
    $scope.addDetails = true;
    $scope.newnewsletter = {};
    $scope.AddNewsletter = function (newsletter) {
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
    
    $scope.newfiles = [];
	$scope.existingfiles = [
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "img/donna_karan_adriana_lima_1.jpg"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300")
	                        ];
	$scope.filesadded	= function(element) {
		$scope.$apply(function($scope) {
			// Turn the FileList object into an Array
			$scope.$broadcast('filesadded', element.files);
		});
	};
}

function MyCtrlAddPrintCampaign($scope, $routeParams, currentvisitedprofile) {

    $scope.backButton = currentvisitedprofile.getBackHistory();
    $scope.addDetails = true;
    $scope.newprintcampaign = {};
    $scope.AddPrintcampaign = function (printcampaign) {
        $scope.newPrintcampaign = angular.copy(printcampaign);
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
    
    $scope.newfiles = [];
	$scope.existingfiles = [
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "img/donna_karan_adriana_lima_1.jpg"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x300")
	                        ];
	$scope.filesadded	= function(element) {
		$scope.$apply(function($scope) {
			// Turn the FileList object into an Array
			$scope.$broadcast('filesadded', element.files);
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

function MyCtrlEditCollections($scope, $routeParams, currentvisitedprofile) {

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

function MyCtrlAddGallery($scope) {
	$scope.newfiles = [];
	$scope.existingfiles = [
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "img/donna_karan_adriana_lima_1.jpg"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x150"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x150"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x150"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x150"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x150"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x150"),
	                        	new netvogue.photo("Thumbnail Azeez", "Season Name", "http://placehold.it/210x150")
	                        ];
	$scope.filesadded	= function(element) {
		$scope.$apply(function($scope) {
			// Turn the FileList object into an Array
			$scope.$broadcast('filesadded', element.files);
		});
	};
}