/**
 * Created by e543571 on 5/12/14.
 */
define(["app"],function(app){
    app.register.directive("jobformDialog",function(){
        return {
            restrict:"E",
            replace:true,
            transclude:true,
            link:function(scope, element, attrs){
                scope.entity = {}

                scope.title = attrs["title"];
                scope.closeJob = function(){
                    if(scope.beforeClose){
                        scope.beforeClose();
                    }
                    element.modal("hide");

                    element.find(".help-block").remove();
                    element.find(".form-group").removeClass("has-error");

                }
                scope.openJob = function(){
                    element.modal("show");
                }

                var form = element.find("form");

                var validator = form.validate(scope.validateOptions);
                scope.isValid = $.proxy(form.valid,form);
            },
            templateUrl:"common/templates/jobformDialog.html?t="+new Date().getMilliseconds()
        }
    });
})