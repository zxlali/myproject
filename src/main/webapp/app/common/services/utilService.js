/**
 * Created by e543571 on 5/27/14.
 */
define(["app","moment"],function(app,moment){
    app.register
        .service("utilService",function(){
            return {
                moment:moment
            };
        })
})