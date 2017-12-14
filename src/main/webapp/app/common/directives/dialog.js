define(["app"], function(app) {
  app.register.directive("dialog", function() {
    return {
      restrict: "E",
      replace: true,
      transclude: true,
      templateUrl: "common/templates/dialog.html?t=" + new Date().getMilliseconds(),
      scope: {
        options: "=",
        api: "="
      },
      link: function(scope, element, attrs) {
        element.modal({keyboard: false, show: false});       
        
        if (scope.options.validateOptions) {
          var dialogForm = element.find("#dialogForm");
          dialogForm.validate(scope.options.validateOptions);
          scope.api.isValid = $.proxy(dialogForm.valid, dialogForm);
        }
        
        scope.api.open = function() {
          element.modal("show");
        };
        
        scope.api.save = function() {
          if (scope.options.onSave) {
            scope.options.onSave();
          } else {
            closeDialog();
          }
        }
        
        scope.api.close = function() {
          scope.options.beforeClose && scope.options.beforeClose();
          closeDialog();
        };
        
        function closeDialog() {
          element.modal("hide");
          element.find(".help-block").remove();
          element.find(".form-group").removeClass("has-error");
        }
        
      }
    }
  });
});