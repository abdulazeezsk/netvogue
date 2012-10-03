'use strict';

/* Directives */


angular.module('netVogue.directives', []).
  directive('appVersion', ['version', function(version) {
    return function(scope, elm, attrs) {
      elm.text(version);
    };
  }]).
  directive('checkboxTree', function() {
	    var linkFn;
	    linkFn = function(scope, element, attrs, $timeout) {
	    	/*angular.element(element).ready(function() {
	    		alert('azeez1');
		    	$('#category div').tree({
	                components: ['checkbox', 'collapse'], onCheck: { node: 'expand' }, onUncheck: {
	                    node: 'collapse'
	                }
	            });
	    	});*/
	    	scope.toggle = function(event) {
	    		angular.element(event.srcElement).parent().find(".togglecontainer").slideToggle("slow");
	    		
	    	};
	    	scope.selectChildFilters= function(mainfilter, index){
	    		angular.forEach(mainfilter.children, function(children) {
	    			children.checked = mainfilter.checked;
	              });
	    	};
	    	scope.selectParentFilters= function(mainfilter){
	    		var allChildren = true;
	    		angular.forEach(mainfilter.children, function(children) {
	    			if(children.checked == false) {
	    				allChildren = false;
	    			}
	              });
	    		mainfilter.checked = allChildren;
	    	};
	    };
	    return {
	    	templateUrl:'templates/checkboxTree.htm',
	    	replace: true,
	    	transclude: true,
	    	scope: {
	    		searchfilter: '=ngModel',
	    	},
	        restrict: 'A',
	        link: linkFn
	    };
 }).directive('editText', function () {        
	    return {
	        restrict:'A',
	        scope: {
	            data		: '=data',
	            isEditMode	: '=editMode',
	            updateData	: '&updateData'
	        },
	        templateUrl: 'templates/Edit_Text.htm',
	        link: function(scope, elm, attrs) {
	        	var label = "";
	        	var seasonname = "";
	            scope.isEditMode = false;

	            scope.switchToPreview = function (save) {
	                scope.isEditMode = false;
	                if(save == "save") {
	                	scope.updateData({label:scope.data.label, seasonname:scope.data.seasonname, photoid: scope.data.uniqueid});
	                } else {
	                	scope.data.label = label;
	                	scope.data.seasonname = seasonname;
		            }
	            };
	            scope.switchToEdit = function () {
	            	label = scope.data.label;
	            	seasonname = scope.data.seasonname;
	                scope.isEditMode = true;
	            };
	        }
	    };
}).directive('fileuploadPlugin', function() {
	var linkFn;
	linkFn = function(scope, element, attrs) {
		var addfiles = function(files) {
			scope.newfiles = [];
			for (var i = 0; i < files.length; i++) {
		    	var loadingImage = window.loadImage(
		    			files[i],
		    	        function (img) {
		    	            
		    	        },
		    	        {maxWidth: 600}
		    	    );
		    	if (!loadingImage) {
		    		loadingImage.src = "http://placehold.it/210x150";
		    	}
		    	scope.newfiles.push(new netvogue.photo(files[i].name, "UNTITLED", loadingImage.src));
		    }
		};
		scope.isEditMode = false;
		scope.$on('filesadded', function(e, files) {
			addfiles(files);
		});
		var galleryid = {
				"galleryid" : scope.galleryid
		};
		angular.element(element).ready(function() {
			jQuery('#fileupload').fileupload({
		        dataType: 'json',
		        singleFileUploads: false,
		        formData: galleryid,
		        progressall: function (e, data) {
		        	var progtemp = parseInt(data.loaded / data.total * 100, 10);
		        	scope.$apply(function(scope) {
		        		scope.progress = progtemp;
		        	});
		        },
		        drop: function(e, data) {
		        	scope.$apply(function(scope) {
		        		addfiles(data.files);
		        	});
		        },
		        paste: function(e, data) {
		        	scope.$apply(function(scope) {
		        		addfiles(data.files);
		        	});
		        },
		        done: function (e, data) {
		        	scope.$apply(function(scope) {
		        		if(data.result.status == true) {
		        			for(var i=0; i < data.result.filesuploaded.length; i++){
		        				scope.existingfiles.push(data.result.filesuploaded[i]);
		        			};
		        			scope.newfiles = [];
		        		} else {
		        			alert("error");
		        		}
		        	});
		        }
		    });
    	});
		scope.updatedataToParent = function(label, seasonname, photoid) {
			scope.updatedata({label:label, seasonname:seasonname, photoid:photoid});
		};
		scope.progressVisible = true;
		scope.progress = 0;
	};
	return {
		templateUrl	: 'templates/fileupload_plugin.htm',
		replace		: false,
		transclude	: true,
		restrict	: 'A',
		scope		: {
			newfiles		: '=newFiles',
			existingfiles	: '=ngModel',
			maxheight		: '=maxHeight',
			minheight		: '=minHeight',
			galleryid		: '=galleryId',
			updatedata		: '&updateData',
			deletedata		: '&deleteData'
		},
		link		: linkFn
	};	
}).directive('styleuploadPlugin', function() {
	var linkFn;
	linkFn = function(scope, element, attrs) {
		scope.newfiles = [];
		var addfiles = function(files) {
			scope.newfiles = [];
			for (var i = 0; i < files.length; i++) {
		    	var loadingImage = window.loadImage(
		    			files[i],
		    	        function (img) {
		    	            
		    	        },
		    	        {maxWidth: 600}
		    	    );
		    	if (!loadingImage) {
		    		loadingImage.src = "http://placehold.it/130X151";
		    	}
		    	scope.existingfiles.splice(0, 1);
		    	scope.newfiles.push(loadingImage.src);
		    	if(3 == i)
		    		break;
		    }
			checkremainingfiles();
		};
		
		var checkremainingfiles = function() {
			//Only four files can be added
			var remainingimages = 4 - (scope.existingfiles.length + scope.newfiles.length);
			if(remainingimages < 0)
				remainingimages = 0;
			for (var i = 0; i < remainingimages; i++) {
				scope.newfiles.push("http://placehold.it/130X151");
			}
		};
		checkremainingfiles();
		
		scope.isEditMode = false;
		scope.filesadded	= function(element) {
			scope.$apply(function(scope) {
				// Turn the FileList object into an Array
				addfiles(element.files);
			});
		};
		var stylesheetid = {
				"stylesheetid" : scope.stylesheetid
		};
		angular.element(element).ready(function() {
			jQuery('#styleupload').fileupload({
		        dataType: 'json',
		        singleFileUploads: false,
		        limitMultiFileUploads: 4,
		        formData: stylesheetid,
		        progressall: function (e, data) {
		        	var progtemp = parseInt(data.loaded / data.total * 100, 10);
		        	scope.$apply(function(scope) {
		        		scope.progress = progtemp;
		        	});
		        },
		        drop: function(e, data) {
		        	scope.$apply(function(scope) {
		        		addfiles(data.files);
		        	});
		        },
		        done: function (e, data) {
		        	scope.$apply(function(scope) {
		        		if(data.result.status == true) {
		        			for(var i=0; i < data.result.filesuploaded.length; i++){
		        				alert(data.result.filesuploaded[i]);
		        				scope.existingfiles.push(data.result.filesuploaded[i]);
		        				alert(scope.existingfiles.length);
		        			};
		        			scope.newfiles = [];
		        			checkremainingfiles();
		        		} else {
		        			alert("error");
		        		}
		        	});
		        }
		    });
    	});
		scope.updatedataToParent = function(label, seasonname, photoid) {
			//scope.updatedata({label:label, seasonname:seasonname, photoid:photoid});
		};
		scope.progressVisible = true;
		scope.progress = 0;
	};
	return {
		templateUrl	: 'templates/brand/Styleupload_Plugin.htm',
		replace		: false,
		transclude	: true,
		restrict	: 'A',
		scope		: {
			existingfiles	: '=ngModel',
			stylesheetid	: '=stylesheetId',
		},
		link		: linkFn
	};	
}).directive('smartWizard', function() { // This is used in initiate page
	    var linkFn;
	    linkFn = function(scope, element, attrs, $timeout) {
	    	angular.element(element).ready(function() {
	    		element.smartWizard({ transitionEffect: 'fade', onFinish: scope.callbackfn});
	    	});
	    };
	    return {
	    	scope: {
	    		callbackfn: '=callbackFn',
	    	},
	        restrict: 'A',
	        link: linkFn
	    };
}).directive('searchDropdown', function() { //This is used in search bar
	  var linkFn;
	  linkFn = function(scope, element, attrs) {
		  
	  };
	  return {
		  template: '<ul class="dropdown-menu span4"> ' +
          			'<li ng-repeat="item in searchResults | filter:searchText"><a ng-href="#employees/10"> '	+
          			'<img width="50" height="50" src="http://placehold.it/60x50" style="float: left;">' +
          			'<p class="list-item">{{item.name}}<br>{{item.location}}</p> </a></li></ul>',
          replace	: true,
		  transclude: true,
		  restrict	: 'A',
	      link		: linkFn
	  };
}).directive('focused', function($timeout) { ////This is used in search bar
	  return function(scope, element, attrs) {
		    element[0].focus();
		    element.bind('focus', function() {
		      scope.$apply(attrs.focused + '=true');
		    });
		    element.bind('blur', function() {
		      // have to use $timeout, so that we close the drop-down after the user clicks,
		      // otherwise when the user clicks we process the closing before we process the click.
		      $timeout(function() {
		        scope.$eval(attrs.focused + '=false');
		      });
		    });
		    scope.$eval(attrs.focused + '=true');
	};
}).directive('sameAs', function () {
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function (viewValue) {
            	if (viewValue === attrs.sameAs) {
                    ctrl.$setValidity('sameAs', true);
                    return viewValue;
                } else {
                    ctrl.$setValidity('sameAs', false);
                    return undefined;
                }
            });
        }
    };
});