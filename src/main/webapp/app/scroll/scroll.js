define(["app",
        "common/controls/select2",
        "common/controls/pie",
        "common/controls/datetimepicker",
        "common/controls/pagination",
        "common/directives/formDialog",
        "common/directives/timestampFormatter",
        "common/filters/timestampFormatter",
        "moment"],
  function (app, moment) {
    app.register.factory("scrollService", function($resource) {
    
    });
    
    app.register.controller("scrollCtrl", function($scope, $resource, $filter, scrollService){
      $scope.images = [1,2,3,4,5,6,7,8];

      $scope.loadMore = function() {
        var last = $scope.images[$scope.images.length - 1];
        for(var i = 1; i <= 8; i++) {
          $scope.images.push(last + i);
        }
      };
//      $scope.myInterval=5000;
//      var slides=[];
//      $scope.slides=slides;
//      slides.push({ image: '../img/1.jpg', text: 'img3' });
//      slides.push({ image: '../img/2.jpg', text: 'img4' });
    });
});