/**
 * Created by e543571 on 5/12/14.
 */
define(["app"],function(app){
    app.register.directive("titleFormDialog",function(){
        return {
            restrict:"E",
            replace:true,
            transclude:true,
            link:function(scope, element, attrs){
                element.modal({keyboard: false, show: false});
                scope.entity = {}
                scope.titleoftitle = attrs["titleoftitle"];
                scope.closeTitle = function(){
                    if(scope.beforeCloseTitle){
                        scope.beforeCloseTitle();
                    }
                    element.modal("hide");

                    element.find(".help-block").remove();
                    element.find(".form-group").removeClass("has-error");

                }
                scope.openTitle = function(){
                    element.modal("show");
                }

                var form = element.find("form");

                var validator = form.validate(scope.validateOptionsOfTitle);
                scope.isValidOfTitle = $.proxy(form.valid,form);
            },
            templateUrl:"common/templates/i10nTitleFormDialog.html?t="+new Date().getMilliseconds()
        }
    });
})