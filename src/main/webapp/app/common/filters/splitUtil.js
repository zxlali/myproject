/**
 * Created by e543571 on 5/27/14.
 */
define(["app"], function(app, moment) {
  app.register.filter("splitText", function() {
    return function(data, count) {
      if (data===undefined||data===null||data===""||data.length < count) {
        return data;
      } else {
        if(new RegExp("[A-Za-z]").test(data[count])){
          var copy=data.substring(0, count);
          return data.substring(0, copy.lastIndexOf(" "))+" ...";
        }else{
          haveNext=data.length>count?" ...":"";
          return data.substring(0, count)+haveNext;
        }
      }
    }
  });
})