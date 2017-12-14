/**
 * Created by e543571 on 5/29/14.
 */
define(["app","bootstrap-datetimepicker","common/services/utilService"], function (app) {
    app.register.directive("timestampFormatter",function(utilService,datetimeFormat){
        return {
            require:"ngModel",
            link:function(scope,element,attrs,ctrl){
                ctrl.$formatters.push(function(value){
                    if(value==undefined||value=="")
                        return value;
                    return utilService.moment(value).format(datetimeFormat);
                });
            }
        };
    });
});