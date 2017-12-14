define(["angularAMD", 
        "angular-route",
        "angular-resource",
        "validateConfig",
        "bootstrap"],
  function (angularAMD) {
    var app = angular.module("app", ["ngRoute","ngResource"])
        .constant("dateFormat","MM/DD/YYYY")
        .constant("baseUrl", "../api/users")
        .constant("datetimeFormat","MM/DD/YYYY HH:mm A")
        .config(function ($routeProvider) {
            $routeProvider.when("/flot",angularAMD.route( {
                templateUrl: "app/flot/flot.html",
                controller:"flotCtrl"
            })).when("/pie",angularAMD.route( {
              templateUrl: "app/pie/pie.html",
              controller:"pieCtrl"
            })).when("/user",angularAMD.route( {
              templateUrl: "app/user/user.html",
              controller:"userCtrl"
            })).when("/scroll",angularAMD.route( {
              templateUrl: "app/scroll/scroll.html",
              controller:"scrollCtrl"
            })).otherwise(angularAMD.route( { 
                template:""
            }));
        }).controller("sidebarCtrl",function($scope,$location){
          
            $scope.currentUrl = "/scroll";
            $scope.redirectTo = function(url){
                $location.path(url);
                $scope.currentUrl = url;
            }
            $location.path($scope.currentUrl);
        });
    angularAMD.bootstrap(app);
    return app;
});
