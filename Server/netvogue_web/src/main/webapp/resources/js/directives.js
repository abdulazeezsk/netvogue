'use strict';

/* Directives */


angular.module('netVogue.directives', []).
  directive('appVersion', ['version', function(version) {
    return function(scope, elm, attrs) {
      elm.text(version);
    };
 }]).directive('checkboxTree', function() {
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
 }).directive('masonry', function($timeout) {
	    var linkFn;
	    linkFn = function(scope, element, attrs) {
	    	scope.$watch('$parent.photogallery', function(newValue, oldValue) {
	    		if(angular.isUndefined(newValue)) {
    				return;
    			}
	    		//setTimeout(function() {
		    		var i=0;
	    			angular.forEach(angular.element(element).find('img'), function(imgElement) {
	    				angular.element(imgElement).attr('src', newValue[i++].thumbnail_url);
	    			});
	    			element.imagesLoaded(function() {
						scope.$apply( function() {
							element.masonry({
								itemSelector : '.box'
							});
						});
					});
	    		//});
				
				/*$container.infinitescroll({
				      navSelector  : '#page-nav',    // selector for the paged navigation 
				      nextSelector : '#page-nav a',  // selector for the NEXT link (to page 2)
				      itemSelector : '.box',     // selector for all items you'll retrieve
				      loading: {
				          finishedMsg: 'No more pages to load.',
				          img: 'http://i.imgur.com/6RMhx.gif'
				        }
				      },
				      // trigger Masonry as a callback
				      function( newElements ) {
				        // hide new items while they are loading
				        var $newElems = $( newElements ).css({ opacity: 0 });
				        // ensure that images load before adding to masonry layout
				        $newElems.imagesLoaded(function(){
				          // show elems now they're ready
				          $newElems.animate({ opacity: 1 });
				          $container.masonry( 'appended', $newElems, true ); 
				        });
				      }
				);*/
	    	angular.element(element).find("a#single_image").fancybox({
//						overlayOpacity: 0,
//						padding: 0,
//						showCloseButton: true,
//						autoScale: false,
//						hideOnContentClick: true,
//						margin: 50,
	    		openEffect	: 'none',
	    		closeEffect	: 'none',
						type:'image'
					});
	    	});
	    };
	    return {
	    	templateUrl:'templates/Photos_Masonry.htm',
	    	replace: false,
	    	transclude:true,
	    	scope: {
	    		photogallery: '=photoGallery',
	    		galleryname: '=galleryName'
	    	},
	        restrict: 'A',
	        link: linkFn
	    };	    
 }).directive('elastislide', function() {
	    var linkFn;
	    linkFn = function(scope, element, attrs) {
	    	scope.$watch('photogallery', function(newValue, oldValue) {
	    		if(angular.isUndefined(newValue)) {
    				return;
    			}
	    		angular.element(element).find('#lookbookS').elastislide({
					speed       : 750,						
					imageW 		: 320,
					margin		: 5,
					border		: 3,
					minItems    : 3,
				});
	    		var i=0;
    			angular.forEach(angular.element(element).find("a#single_image"), function(imgElement) {
    				angular.element(imgElement).attr('href', newValue[i++].piclink);
    			});
	    		angular.element(element).find("a#single_image").fancybox({
					overlayOpacity: 0,
					padding: 0,
					showCloseButton: true,
					autoScale: false,
					hideOnContentClick: true,
					margin: 50,
					type:'image'
				});
	    	});
	    };
	    return {
	        restrict: 'A',
	        link: linkFn
	    };	    
 }).directive('editText', function () {        
	    return {
	        restrict:'A',
	        scope: {
	            data			: '=data',
	            isEditMode		: '=editMode',
	            setprofilepic	: '&setProfilepic',
	            updateData		: '&updateData',
	            deletedata		: '&deleteData',
	        },
	        templateUrl: 'templates/Edit_Text.htm',
	        link: function(scope, elm, attrs) {
	        	var label = "";
	        	var seasonname = "";
	            scope.isEditMode 	= false;

	            scope.switchToPreview = function (save) {
	                scope.isEditMode = false;
	                if(save == "save") {
	                	scope.updateData({label:scope.data.label, seasonname:scope.data.seasonname, photoid: scope.data.uniqueid});
	                	updatetext();
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
	            var updatetext = function() {
		            if(scope.data.label == "UNTITLED" || scope.data.label == "" || scope.data.label == null) {
		            	scope.emptylabel = true;
		            } else {
		            	scope.emptylabel = false;
		            }
		            if(scope.data.seasonname == "UNTITLED SEASON" || scope.data.seasonname == "" || scope.data.seasonname == null) {
		            	scope.emptyseasonname = true;
		            } else {
		            	scope.emptyseasonname = false;
		            }
	            };
	            updatetext();
	        }
	    };
}).directive('fileuploadPlugin', function() {
	var linkFn;
	linkFn = function(scope, element, attrs) {
		var addfiles = function(files) {
			scope.newfiles = [];
			scope.newfiles.push("Uploading images");
			/*for (var i = 0; i < files.length; i++) {
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
		    }*/
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
		        done: function (e, data) {
		        	scope.$apply(function(scope) {
		        		if(data.result.status == true) {
		        			if(true == scope.filesadded) {
		        				//Worst logic ever -- revisit it once again -- Azeez
		        				var replyFromServer = [];
			        			for(var i=0; i < data.result.filesuploaded.length; i++){
			        				replyFromServer.push(data.result.filesuploaded[i]);
			        			};
			        			for(var i=0; i < scope.existingfiles.length; i++){
			        				replyFromServer.push(scope.existingfiles[i]);
			        			};
			        			scope.existingfiles = angular.copy(replyFromServer);
			        			scope.newfiles = [];
			        			scope.filesadded = false;
			        			//scope.$emit('filesuploaded');
		        			}
		        		} else {
		        			alert("error");
		        		}
		        	});
		        }
		    });
    	});
		scope.orderchangedNew = function() {
			console.log("order changed");
		};
		scope.setprofilepicToParent = function(uniqueid) {
			scope.setprofilepic({uniqueid:uniqueid});
		};
		scope.updatedataToParent = function(label, seasonname, photoid) {
			scope.updatedata({label:label, seasonname:seasonname, photoid:photoid});
		};
		scope.deletedataToParent = function(photoid) {
			scope.deletedata({photoid:photoid});
		};
		scope.progressVisible = true;
		scope.progress = 0;
	};
	return {
		templateUrl	: 'templates/fileupload_plugin.htm',
		restrict	: 'A',
		scope		: {
			newfiles		: '=newFiles',
			existingfiles	: '=ngModel',
			maxheight		: '=maxHeight',
			minheight		: '=minHeight',
			galleryid		: '=galleryId',
			setprofilepic	: '&setProfilepic',
			updatedata		: '&updateData',
			deletedata		: '&deleteData',
			filesadded		: '=filesAdded'
		},
		link		: linkFn
	};
}).directive('profileuploadPlugin', function() {
	var linkFn;
	linkFn = function(scope, element, attrs, ngModel) {
		angular.element(element).ready(function() {
			jQuery('#profileupload').fileupload({
		        dataType: 'json',
		        limitMultiFileUploads: 1,
		        done: function (e, data) {
		        	scope.$apply(function(scope) {
		        		if(data.result.status == true) {
		        			scope.profilepic = data.result.filesuploaded[0].thumbnail_url;
		        		} else {
		        			alert("error");
		        		}
		        	});
		        }
		    });
    	});
	};
	return {
		restrict	: 'A',
		link		: linkFn
	};		
}).directive('styleuploadPlugin', function() {
	var linkFn;
	linkFn = function(scope, element, attrs) {
		scope.newfiles = [];
		var stylesheetid = {
				"stylesheetid": scope.stylesheetid
		};
		angular.element(element).ready(function() {
			jQuery('#styleupload').fileupload({
		        dataType: 'json',
		        singleFileUploads: false,
		        limitMultiFileUploads: 9 ,
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
		        			if(true == scope.senttoserver) {
			        			for(var i=0; i < data.result.filesuploaded.length; i++){
			        				scope.newstyle.availableImages.push(data.result.filesuploaded[i]);
			        			};
			        			scope.mainimage = scope.newstyle.availableImages[0].thumbnail_url;
			        			scope.senttoserver = false;
			        			scope.newfiles = [];
		        			};
		        		} else {
		        			alert("error");
		        		}
		        	});
		        }
		    });
    	});
		scope.deletephoto = function(index) {
			scope.existingfiles.splice(index, 1);
			scope.newfiles = [];
			//checkremainingfiles();
		};
	};
	return {
		restrict	: 'A',
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
		      }, 100);
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