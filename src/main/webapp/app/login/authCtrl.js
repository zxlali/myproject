require(["angular"],function(angular){
    return angular.module("login")
        .constant("authUrl","http://localhost:5500/users")
        .controller("authCtrl",function($scope,$http,$location,authUrl){
            $scope.authenticate = function(name,pwd){
                $http.post(authUrl,{username:name,password:pwd},{withCredentitals:true})
                    .success(function(data){
                        $location.path("/main");
                    }) .error(function(error){
                        $scope.authenticationError = error;
                    });
            }
        })
})
