/**
 * Created by e543571 on 5/27/14.
 */
define(["app","moment"],function(app,moment){
    app.register
        .filter("millisToDate",function(dateFormat){
            return function(value){
            	if(value===undefined||value===null||value==="")
            		return "";
               return moment(value).format(dateFormat);
            }
        });
    app.register
	    .filter("millisToDateTime",function(datetimeFormat){
	        return function(value){
	        	if(value===undefined||value===null||value==="")
            		return "";
	           return moment(value).format(datetimeFormat);
	        }
	    });
    app.register
    .filter("DateTimeToDate",function(){
        return function(value, datetimeFormat){
          if(value===undefined||value===null||value==="")
              return "";
           return moment(value).format(datetimeFormat);
        }
    });
})