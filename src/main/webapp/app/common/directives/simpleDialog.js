/**
 * Created by e543571 on 5/12/14.
 */
define(["app"],function(app){
    app.register.directive("simpleDialog",function(){
        return {
            restrict:"E",
            replace:true,
            transclude:true,
            link:function(scope, element){                             
                scope.close = function(){
                    if(scope.beforeClose){
                        scope.beforeClose();
                    }
                    element.modal("hide");
                }
                scope.open = function(){
                    element.modal("show");
                }                               
            },
            templateUrl:"common/templates/simpleDialog.html?t="+new Date().getMilliseconds()
        }
    });
})