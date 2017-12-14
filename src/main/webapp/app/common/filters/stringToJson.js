/**
 * Created by e543571 on 5/27/14.
 */
define(["app"], function(app, moment) {
  app.register.filter("stringToJson", function() {
    return function(data, language) {
      if (data === undefined || data === null || data === "") {
        return data;
      } else {
        if(data.contains("en_US")){
          var json = JSON.parse(data);
          if(language === undefined || language === null || language === ""){
            return json["en_US"];
          }
          if(json[language] === undefined || json[language] === null || json[language] === ""){
            return json["zh_CN"];
          }
          return json[language];
        }else{
          return data;
        }
      }
    }
  });
})