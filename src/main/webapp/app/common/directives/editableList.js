define(["app"], function(app) {
  app.register.directive("editableList", function() {
    return {
      restrict: "E",
      replace: true,
      templateUrl: "common/templates/editableList.html?t=" + new Date().getMilliseconds(),
      scope: {
        options: "=",
        dataset: "="
      },
      link: function(scope, element) {
        scope.options.searchValue = "";
        scope.options.currentItem = {};
        
        scope.onItemClick = function(e) {
          scope.options.currentItem = this.item;
          scope.options.onItemClick && scope.options.onItemClick(scope.options.currentItem );
        };
        
        scope.onItemNew = function() {
          scope.options.onItemNew && scope.options.onItemNew();
        };
        
        scope.onItemDelete = function(e) {
          e.stopPropagation();
          if(scope.options.onItemDelete && confirm("Confirm deletion?")) {
            scope.options.onItemDelete(this.item);
          }
        };
        
        scope.onItemEdit = function(e) {
          e.stopPropagation();
          scope.options.onItemEdit && scope.options.onItemEdit(this.item);
        }
        
        scope.onSearch = function() {
          scope.options.onSearch && scope.options.onSearch();
          scope.options.currentItem = {};
        };
        
        if(scope.options.fireSearchAfterInit === true) {
          scope.onSearch();
        }

      }
    }
  });
})