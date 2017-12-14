/**
 * Created by e543571 on 5/27/14.
 */
define(["app"], function(app, moment) {
  app.register.filter("getObjectById", function() {
    return function(data,id) {
      if (data===undefined||data===null||!angular.isArray(data)) {
        return "";
      } else {
        var i=0;
        for(i=0;i<data.length;i++){
          if(data[i].id===id){
            return data[i];
          }
        }
      }
    }
  });
})