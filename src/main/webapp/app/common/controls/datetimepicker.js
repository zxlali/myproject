/**
 * Created by e543571 on 5/12/14.
 */
define(["app","bootstrap-datetimepicker","common/services/utilService"], function (app) {
    app.register
        .directive("datetimepicker", function (utilService,datetimeFormat) {
            return {
                restrict: 'A',
                // Always use along with an ng-model
                require: 'ngModel',

                link: function(scope, element, attrs,ctrl) {
                	var x = $(element.closest(".date"));
                	x.datetimepicker();
                	
                	 ctrl.$formatters.push(function(value){
                     	if(value==undefined||value=="")
                     		return value;
                         return utilService.moment(value).format(datetimeFormat);
                     });
                     ctrl.$parsers.push(function(value){
                         if(value==undefined||value=="")
                             return value;
                         return utilService.moment(value).valueOf();
                     });
                     
                    //element.datetimepicker();
                   /* if (!ngModel) return;

                    ngModel.$render = function() { //This will update the view with your model in case your model is changed by another code.
                        element.data("DateTimePicker").setDate( ngModel.$viewValue || '');
                    };

                    element.datetimepicker({pickTime:false}).on("dp.change",function(event){
                        scope.$apply(function() {
                            ngModel.$setViewValue(event.date.toDate());//This will update the model property bound to your ng-model whenever the datepicker's date changes.
                        });
                    });*/
                }
              /*  //require:"ngModel",
                link:function(scope,element,attrs,ctrl){
                   *//* ctrl.$formatters.push(function(value){
                    	if(value==undefined||value=="")
                    		return value;
                        return utilService.moment(value).format(datetimeFormat);
                    });
                    ctrl.$parsers.push(function(value){
                        if(value==undefined||value=="")
                            return value;
                        return utilService.moment(value).valueOf();
                    });*//*
                    element.datetimepicker();
                }*/
            };
        });
	});