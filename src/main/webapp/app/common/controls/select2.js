/**
 * Created by e543571 on 5/12/14.
 */
define(["app", "select2"], function (app) {
    app.register
        .directive("select2", function () {
            return {            
            	require: 'ngModel',
            	priority: 1,
            	link: function (scope, element, attrs,ngModel) {
	                var placeholder = attrs["placeholder"];
	                element.select2({
	                    placeholder: placeholder
	                });
	                
	                ngModel.$render = function() {
	                	element.select2("val",ngModel.$viewValue);            
                    };
	            }
            };
        })
});