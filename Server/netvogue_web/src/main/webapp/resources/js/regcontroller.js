if (!netvogue )
	var netvogue = {};

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

netvogue.entity 		= function() {
	this.email 			= "";
	this.password		= "";
	this.username		= "";
	this.name			= "";
	this.primarycontact = "";
	this.mobile			= "";
};
/* Add and Edit Controllers */
var app = angular.module('netVogue', []);

function MyCtrlRegistration($scope, $http, $timeout, $location) {
	$scope.entity 		= new netvogue.entity();
	
	$scope.emailchanged = function(email, ctrl, key) {
		if("" == email) {
			return;
		}
		var datatosend = {
				"email" : email
		};
		var config = {
                method: "GET",
                params: datatosend,
                url: entity + "/emailavailability"
            };
        $http(config).success(function(data) {
            if(data == "false") {
            	ctrl.$setValidity(key, false);
            } else {
            	ctrl.$setValidity(key, true);
            }
        }).error(function(data) {
        	ctrl.$setValidity(key, false);
        });
	};
	
	$scope.usernamechanged = function(username, ctrl, key) {
		if("" == username){
			return
		}
		var datatosend = {
				"username" : username
		};
		var config = {
                method: "GET",
                params: datatosend,
                url: entity + "/usernameavailability"
            };
        $http(config).success(function(data) {
            if(data == "false") {
            	ctrl.$setValidity(key, false);
            } else {
            	ctrl.$setValidity(key, true);
            }
        }).error(function(data) {
        	ctrl.$setValidity(key, false);
        });
	};
	
	$scope.addEntity	= function(event) {
		angular.element(event.srcElement).button('loading');
		
		var config = {
                method: "POST",
                data: $scope.entity,
                url: entity + "/doregistration"
            };
        $http(config).success(function(data) {
            if(data.status == true) {
            	$('#registration').html("<span style='display:block;height:200px;padding-top:150px;'>You have been registered successfully.Our team will contact you shortly</span>");
            	$timeout(function() {
            		window.location.href = "Netvogue.html";
    		      }, 1000);
            	
            } else {
            	angular.element(event.srcElement).button('reset');
            	alert('Registration is unsuccessful due to:' + data.error);
            	$scope.entity.productlines.splice(0, $scope.entity.productlines.length);
            }
        }).error(function(data) {
        	angular.element(event.srcElement).button('reset');
        	alert('Registration is unsuccessful due to bad request:' + data);
        	$scope.entity.productlines.splice(0, $scope.entity.productlines.length);
        });
	};
}

var INTEGER_REGEXP = /^\-?\d*$/;
app.directive('uiValidate', function () {

	  return {
	    restrict:'A',
	    require:'ngModel',
	    link: function(scope, elm, attrs, ctrl) {
	    	
	    	var validateFn, validateExpr = attrs.uiValidate;
	    	validateExpr = scope.$eval(validateExpr);
	        if (!validateExpr) {
	          return;
	        }
	        
	        if (angular.isFunction(validateExpr)) {
	          validateExpr = { validator: validateExpr };
	        }
	        
	        angular.forEach(validateExpr, function(validatorFn, key) {
	        	validateFn = function (valueToValidate) {
	        		if(!ctrl.$error.pattern && "" != valueToValidate) {
		        		validatorFn(valueToValidate, ctrl, key);
		        		return valueToValidate;
	        		} else {
	        			ctrl.$setValidity(key, true);
	        		}
	        	};
	        	ctrl.$formatters.push(validateFn);
	        	ctrl.$parsers.push(validateFn);
	        });	        
	    }
	};
});
app.directive('sameAs', function () {
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
app.value('ui.config', {
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
});
app.directive('uiJq', ['ui.config', function(uiConfig) {
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
}]);