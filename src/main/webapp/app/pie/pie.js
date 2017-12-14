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
    app.register.factory("userService", function($resource) {
    
    });
    
    app.register.controller("pieCtrl", function($scope, $resource, $filter, userService){
      $scope.options = {
              series: {
                pie: {
                    innerRadius: 0.0,
                    show: true,
                    label: {
                        show:false,
                        radius: 0.8,
                        formatter: function (label, series) {
                            return '<div style="border:1px solid grey;font-size:8pt;text-align:center;padding:5px;color:white;">' +
                            label + ' : ' + '</br>'+
                            (series.data[0][1]) + '</br>'+
                            Math.round(series.percent * 100) / 100 +
                            '%</div>';
                        },
                        background: {
                            opacity: 0.8,
                            color: '#000'
                        },
                    }
                }
            },
            grid: {
              hoverable: true
            }
      };
      
      var data=[1,2,3,4];
      $scope.show = false;
      if(data[0] == 0 && data[1] == 0 && data[2] == 0 && data[3] == 0) {
        $scope.show = true;
        return;
      }
      $scope.dataset = [
                         {label: "模拟1", data: data[0]},
                         {label: "模拟2", data:data[2]},
                         {label: "模拟3", data: data[1]},
                         {label: "模拟4", data: data[3]}
                       ];
      
      $scope.userActiveExtraOptions = {
              getPointHoverData: function(event, pos, item) {
                return item.series.label + ": " + item.series.data[0][1] + ' , ' + Math.round(item.series.percent * 100) / 100 + '%';
              }
           };
    });
});