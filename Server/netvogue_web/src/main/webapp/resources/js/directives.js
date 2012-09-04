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
	    	templateUrl:'templates/boutique/CheckboxTree.htm',
	    	replace: true,
	    	transclude: true,
	    	scope: {
	    		searchfilter: '=ngModel',
	    	},
	        restrict: 'A',
	        link: linkFn
	    };
  }).directive('smartWizard', function() {
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
  }).directive('searchDropdown', function() {
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
  }).directive('focused', function($timeout) {
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
}]);