'use strict';

/* Filters */

angular.module('netVogue.filters', []).
filter('interpolate', ['version', function(version) {
    return function(text) {
      return String(text).replace(/\%VERSION\%/mg, version);
    };
}]).
filter('startFrom', function() {
    return function(input, start) {
    	start = +start; //parse to int
        return input.slice(start);
    };
});