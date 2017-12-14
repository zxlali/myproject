/**
 * Created by e543571 on 5/12/14.
 */
define(["app"], function (app) {
    app.register
        .directive("myRequired", function () {
          return {
            restrict: 'AE',
            scope: {},
            require: 'ngModel',
            link: function(scope, iElement, iAttrs) {
                    if(iElement.val() == ""){
                        return false;
                    } else {
                      return true;
                    }
              }
          }
        })
});