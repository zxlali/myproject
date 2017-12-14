define(["app", 
        "common/controls/select2",
        "common/controls/iCheck",
        "common/controls/datetimepicker",
        "common/controls/pagination",
        "common/directives/formDialog",
        "common/directives/timestampFormatter",
        "common/filters/timestampFormatter",
        "moment"],
        function(app, moment) {
  var model = {};
  app.register.factory("userService", function($resource, $http, baseUrl) {
    var resource = $resource(baseUrl + "/:id", {
      id: "@id"
    }, {
      query: {
        isArray: false
      },
    });
    return {
      list: [],
      total: 0,
      search: function(param, callback) {
        resource.query(param).$promise.then($.proxy(function(pageList) {
          this.list = $.map(pageList.list, function(v) {
            return new resource(v);
          });
          this.total = pageList.page.total;
          callback(this.list, this.total);
        }, this));
      },
      add: function(user) {
        new resource(user).$save().then($.proxy(function(data) {
          this.list.push(data);
        }, this));
      },
      update: function(user) {
        user.$save();
      },
      refresh:function(user){
        user.$get();
      },
      listUsers: function(callback) {
        $http.get(baseUrl).success(function(data) {
          callback(data);
        });
      }
    };
  });
  app.register.controller("userCtrl", function($scope, $resource, $filter, userService) {
    $scope.p_index = 1;
    $scope.p_size = 15;
    $scope.p_total = 0;
    $scope.p_data = [];
//    $scope.current = {};
    $scope.searchParams = {
      index: $scope.p_index,
      size: $scope.p_size,
      userName: ""
    };
    
    $scope.search = function() {
      userService.search($scope.searchParams, function(data, total){
        $scope.p_data = data;
        $scope.p_total = total;
      });
    };

    $scope.$watch("p_index",function(newValue) {
      $scope.searchParams.index = newValue;
      $scope.search();
    }); 
    
//    userService.listUsers(function(data) {
//      model.users = data;
//    });
//    $scope.todo = model;

    $scope.count = function() {
      var count = 0;
      angular.forEach($scope.p_data, function(item) {
        if (item.isActive != 0) {
          count++;
        }
      });
      return count;
    }
//
//    $scope.incompleteCount = function() {
//      var count = 0;
//      angular.forEach($scope.todo.items, function(item) {
//        if (!item.age) {
//          count++;
//        }
//      });
//      return count;
//    }
//
//    $scope.warningLevel = function() {
//      return $scope.incompleteCount() < 3 ? "label-success" : "label-warning";
//    }
//
//    $scope.addNewItem = function(actionText) {
//      $scope.todo.items.push({
//        name: actionText,
//        age: 0
//      });
//    }
//
//    $scope.delItem = function() {
//      $scope.todo.items.pop();
//    }
//
  });
  app.register.filter("checkedItems", function() {
    return function(items, showComplete) {
      var resultArr = [];
      angular.forEach(items, function(item) {
        if (item.age == 0 || showComplete == true) {
          resultArr.push(item);
        }
      });
      return resultArr;
    }
  });
});