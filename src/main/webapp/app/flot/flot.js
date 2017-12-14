define(["app",
        "common/controls/select2",
        "common/controls/iCheck",
        "common/controls/flot",
        "common/controls/datetimepicker",
        "common/controls/pagination",
        "common/directives/formDialog",
        "common/directives/timestampFormatter",
        "common/filters/timestampFormatter",
        "moment"], function(app, moment){
  var model = {
          user: "Adam",
          
          };
  app.register.factory("todoService",function($resource, $http, baseUrl){
    return {
        enableDateSearch:function(callback){
          $http.get(baseUrl).success(function(data){
            callback(data);
          });
        }
    };
});
  app.register.controller("flotCtrl", function($scope, $resource, $filter, todoService) {
    $scope.userLoginLogExtraOptions = {
            getPointHoverData: function(event, pos, item) {
              return "Date: " + item.datapoint[0] + "<br/>Total: " + item.datapoint[1];
            }
         };
    $scope.options = {
            series: {
//              points: { show: true, radius: 2, fill: false},
              bars: {  
              show: true,
              align: "center",  
              barWidth: 0.5  
            },  
//              lines: { show: true, fill: false, fillColor: "rgba(255, 255, 255, 0.8)"},
//              shadowSize:5,
//              colors: ["#d18b2c", "#dba255", "#919733"]
            },
            xaxis:{
//              ticks: [[0,'a'],[1,'b'],[2,'c'],[3,'d']],  
              tickSize: 2,  
              axisLabelUseCanvas: true,  
              axisLabelFontSizePixels: 12,  
              axisLabelFontFamily: 'Verdana, Arial',  
              axisLabelPadding: 10  
//              mode: "time",
//              timezone: "browser",
//              minTickSize: [1, "hour"],
//              timeformat: "%H:%M"
            },
            yaxis: {
              minTickSize: 1,
              min: 0
            },
            grid: {
              hoverable: true,
              autoHighlight: true
            }
        };
    $scope.dataset=[{label:"lines", data: [[1,20],[10,2],[12,20],[15,30]]},
                    {label:"bars", data: [[2,20],[13,2],[22,20],[25,30]]}
                   ];
    
    todoService.enableDateSearch(function(data){
      model.items=data;
    });
    $scope.todo=model;
    $scope.convert=function(val){
      var isTrue=false;
      if(val>0) {
        isTrue=true;
      }
      return isTrue;
    }
    $scope.incompleteCount=function(){
      var count=0;
      angular.forEach($scope.todo.items, function(item){
        if(!item.age){
          count++;
        }
      });
      return count;
    }
    
    $scope.warningLevel=function(){
      return $scope.incompleteCount()<3 ? "label-success" : "label-warning";
    }
    
    $scope.addNewItem=function(actionText){
      $scope.todo.items.push({name:actionText, age:0});
    }
    
    $scope.delItem=function(){
      $scope.todo.items.pop();
    }
    
  });
  app.register.filter("checkedItems", function(){
    return function(items, showComplete){
      var resultArr=[];
      angular.forEach(items, function(item){
        if(item.age==0 || showComplete==true){
          resultArr.push(item);
        }
      });
      return resultArr;
    }
  });
});