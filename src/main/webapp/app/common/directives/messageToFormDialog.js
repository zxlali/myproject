/**
 * Created by e543571 on 5/12/14.
 */
define(["app"],function(app){
    app.register.directive("messageToFormDialog",function(){
        return {
            restrict:"E",
            replace:true,
            transclude:true,
            link:function(scope, element, attrs){
                element.modal({keyboard: false, show: false});
                scope.entity = {}
                scope.titleofmessageto = attrs["titleofmessageto"];
                scope.closeMessageTo = function(){
                    if(scope.beforeClose){
                        scope.beforeClose();
                    }
                    element.modal("hide");

                    element.find(".help-block").remove();
                    element.find(".form-group").removeClass("has-error");

                }
                scope.openMessageTo = function(){
                    element.modal("show");
                }

                var form = element.find("form");

                var validator = form.validate(scope.validateOptions);
                scope.isValid = $.proxy(form.valid,form);
            },
            templateUrl:"common/templates/messageToFormDialog.html?t="+new Date().getMilliseconds()
        }
    });
})