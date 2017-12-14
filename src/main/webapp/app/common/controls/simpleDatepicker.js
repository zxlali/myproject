/**
 * Created by e543571 on 6/25/14.
 */
define(["app","bootstrap-datetimepicker","common/services/utilService"], function (app) {
    app.register
        .directive("simpleDatepicker", function (utilService,datetimeFormat) {
            return {
            	require:"ng-Model",
                link:function(scope, element,attrs,ctrl){
                	element.attr("placeholder","YYYY-MM-DD");

                   /* ctrl.$formatters.push(function(value){
                        if(value==undefined||value=="")
                            return value;
                        return utilService.moment(value).format("YYYY-MM-DD");
                    });
                    ctrl.$parsers.push(function(value){
                        if(value==undefined||value=="")
                            return value;
                        return utilService.moment(value).valueOf();
                    });*/
                }
            }
        })
});