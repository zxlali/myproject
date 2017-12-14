/**
 * Created by e543571 on 5/12/14.
 */
define(["app"],function(app){
    app.register.directive("contentFormDialog",function(){
        return {
            restrict:"E",
            replace:true,
            transclude:true,
            link:function(scope, element, attrs){
                element.modal({keyboard: false, show: false});
                scope.entity = {}
                scope.titleofcontent = attrs["titleofcontent"];
                scope.closeContent = function(){
                    if(scope.beforeCloseContent){
                        scope.beforeCloseContent();
                    }
                    element.modal("hide");

                    element.find(".help-block").remove();
                    element.find(".form-group").removeClass("has-error");

                }
                scope.openContent = function(){
                    element.modal("show");
                }

                var form = element.find("form");

                var validator = form.validate(scope.validateOptionsOfContent);
                scope.isValidOfContent = $.proxy(form.valid,form);
            },
            templateUrl:"common/templates/i10nContentFormDialog.html?t="+new Date().getMilliseconds()
        }
    });
})