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
		scope.isEditMode = false;
		scope.$on('filesadded', function(e, files) {
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
		replace		: true,
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
}).value('ui.config', {
	   // The ui-jq directive namespace
	   jq: {
	      // The qtip namespace
	      /*qtip: {
	         // qTip options. This object will be used as the defaults
	         position: {
	            my: 'left center',
	            at:'right center'
	         }
	      }*/
	   }
}).directive('uiJq', ['ui.config', function(uiConfig) {
	return {
		restrict: 'A',
		compile: function(tElm, tAttrs) {  
			if (!angular.isFunction(tElm[tAttrs.uiJq])) {
				throw new Error('ui-jq: The "'+tAttrs.uiJq+'" function does not exist');
				return;
			}
			var options = uiConfig['jq'] && uiConfig['jq'][tAttrs.uiJq];
			return function (scope, elm, attrs) {
				var linkOptions = [], ngChange = 'change';

				if (attrs.uiOptions) {
					linkOptions = scope.$eval('['+attrs.uiOptions+']');
					if (angular.isObject(options) && angular.isObject(linkOptions[0])) {
						linkOptions[0] = angular.extend(options, linkOptions[0]);
					} 
				} else if (options) {
					linkOptions = [options]; 
				}
				if (attrs.ngModel && elm.is('select,input,textarea')) {
					if (linkOptions && angular.isObject(linkOptions[0]) && linkOptions[0].ngChange !== undefined) {
						ngChange = linkOptions[0].ngChange;
					}
					ngChange && elm.on(ngChange, function(){
						elm.trigger('input');
					});
				}
				elm[attrs.uiJq].apply(elm, linkOptions);
				scope.$watch('scope.brands', function() {
					elm[attrs.uiJq].apply(elm, linkOptions);
				});
			};
		}
	};
}]).directive('uiIf', [function() {
	  return {
		    transclude: 'element',
		    priority: 1000,
		    terminal: true,
		    restrict: 'A',
		    compile: function(element, attr, linker) {
		      return function(scope, iterStartElement, attr) {
		        iterStartElement[0].doNotMove = true;
		        var expression = attr.uiIf;
		        var lastElement;
		        var lastScope; 
		        scope.$watch(expression, function(newValue) {
		          if (lastElement) {
		            lastElement.remove();
		            lastElement = null;
		          }
		          lastScope && lastScope.$destroy();
		          if (newValue) {
		            lastScope = scope.$new();
		            linker(lastScope, function(clone) {
		              lastElement = clone;
		              iterStartElement.after(clone);
		            });
		          }
		          // Note: need to be parent() as jquery cannot trigger events on comments
		          // (angular creates a comment node when using transclusion, as ng-repeat does).
		          iterStartElement.parent().trigger("$childrenChanged");
		        });
		      };
		    }
		  };
}]).directive('uiDate', ['ui.config', function (uiConfig) {
	  'use strict';
	  var options;
	  options = {};
	  if (angular.isObject(uiConfig.date)) {
	    angular.extend(options, uiConfig.date);
	  }
	  return {
	    require:'?ngModel',
	    link:function (scope, element, attrs, controller) {
	      var getOptions = function () {
	        return angular.extend({}, uiConfig.date, scope.$eval(attrs.uiDate));
	      };
	      var initDateWidget = function () {
	        var opts = getOptions();

	        // If we have a controller (i.e. ngModelController) then wire it up
	        if (controller) {
	          var updateModel = function () {
	            scope.$apply(function () {
	              controller.$setViewValue(element.datepicker("getDate"));
	            });
	          };
	          if (opts.onSelect) {
	            // Caller has specified onSelect, so call this as well as updating the model
	            var userHandler = opts.onSelect;
	            opts.onSelect = function (value, picker) {
	              updateModel();
	              return userHandler(value, picker);
	            };
	          } else {
	            // No onSelect already specified so just update the model
	            opts.onSelect = updateModel;
	          }
	          // In case the user changes the text directly in the input box
	          element.bind('change', updateModel);

	          // Update the date picker when the model changes
	          controller.$render = function () {
	            var date = controller.$viewValue;
	            element.datepicker("setDate", date);
	            // Update the model if we received a string
	            if (angular.isString(date)) {
	              controller.$setViewValue(element.datepicker("getDate"));
	            }
	          };
	        }
	        // If we don't destroy the old one it doesn't update properly when the config changes
	        element.datepicker('destroy');
	        // Create the new datepicker widget
	        element.datepicker(opts);
	        // Force a render to override whatever is in the input text box
	        controller.$render();
	      };
	      // Watch for changes to the directives options
	      scope.$watch(getOptions, initDateWidget, true);
	    }
	  };
	}
]).directive('uiModal', ['$timeout', function($timeout) {
	  return {
		  restrict: 'EAC',
		  require: 'ngModel',
		  link: function(scope, elm, attrs, model) {
			  //helper so you don't have to type class="modal hide"
		      elm.addClass('modal hide');
		      scope.$watch(attrs.ngModel, function(value) {
		    	  elm.modal(value && 'show' || 'hide');
		      });
		      elm.on('show.ui', function() {
		    	  $timeout(function() {
		    		  model.$setViewValue(true);
		    	  });
		      });
		      elm.on('hide.ui', function() {
		    	  $timeout(function() {
		    		  model.$setViewValue(false);
		    	  });
		      });
		  }
	  };		  
}]);