/**
 * Created by e543571 on 5/27/14.
 */
define(["app"], function(app, moment) {
  app.register.filter("complieSelectedName", function() {
    return function(data, condition) {
      var returnValue ="";
      if (data === undefined || data === null || data === "") {
        return data;
      } else {
        for(var i = 0; i< data.length; i++){
          returnValue += data[i].text+",";
        }
        return returnValue.substring(0,returnValue.length-1);;
      }
    }
  });
})