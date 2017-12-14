/**
 * Created by e543571 on 5/12/14.
 */
define(["app", "iCheck"], function (app) {
    app.register
        .directive('icheck', function($timeout, $parse) {
        return {
            require: 'ngModel',
            link: function($scope, element, $attrs, ngModel) {
                return $timeout(function() {
                    var value;
                    value = $attrs['value'];

                    $scope.$watch($attrs['ngModel'], function(newValue){
                        $(element).iCheck('update');
                    })

                    return $(element).iCheck({
                        checkboxClass: 'icheckbox_square-blue',
                        radioClass: 'iradio_square-blue',
                        increaseArea: '20%'

                    }).on('ifChanged', function(event) {
                        if ($(element).attr('type') === 'checkbox' && $attrs['ngModel']) {
                            $scope.$apply(function() {
                                return ngModel.$setViewValue(event.target.checked);
                            });
                        }
                        if ($(element).attr('type') === 'radio' && $attrs['ngModel']) {
                            return $scope.$apply(function() {
                                return ngModel.$setViewValue(value);
                            });
                        }
                    });
                });
            }
        };
    });
})