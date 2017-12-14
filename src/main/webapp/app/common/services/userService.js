define(["app"],function(app){
    app.register
        .service("userService",function($resource,apiBaseUrl){
        	
        	var resource = $resource(apiBaseUrl+"select2/users/:name",{name:"@name"});
           
        })
})