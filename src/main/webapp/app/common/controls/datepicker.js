/**
 * Created by e543571 on 7/27/14.
 */
define(["app", "bootstrap-datepicker"], function(app) {
  app.register.directive("datepicker", function() {
    return {
      restrict: 'EA',
      require: 'ngModel',
      scope:{
        enableoptions:'=',
      },
      link: function(scope, element) {
        var end, start, init, onDateChanged;
        init = function() {
          if (scope.enableoptions) {
            element.datepicker('remove');
            start = scope.enableoptions.selectEnableStartDate;
            end = scope.enableoptions.selectEnableEndDate;
            if(start&&end){
              element.datepicker({
                "format": "yyyy-mm-dd",
                "autoclose": true,
                "todayBtn": "linked",
                "todayHighlight": true,
                "startDate": start,
                "endDate": end
              });
              return;
            }
            if(start){
              element.datepicker({
                "format": "yyyy-mm-dd",
                "autoclose": true,
                "todayBtn": "linked",
                "todayHighlight": true,
                "startDate": start
              });
              return ;
            }else{
              element.datepicker({
                "format": "yyyy-mm-dd",
                "autoclose": true,
                "todayBtn": "linked",
                "todayHighlight": true,
                "endDate": end
              });
            }
           
          } else {
            element.datepicker({
              "format": "yyyy-mm-dd",
              "autoclose": true,
              "todayBtn": "linked",
              "todayHighlight": true,
            });
          }
        }
        onDateChanged=function(){
          init();
        };
        scope.$watch('enableoptions', onDateChanged, true);
        init();
      }
    };
  });
});