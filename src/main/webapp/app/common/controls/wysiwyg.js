/**
 * Created by e543571 on 7/27/14.
 */
define(["app","bootstrap-wysiwyg","jquery.hotkeys"], function (app) {
    app.register
        .directive("wysiwyg", function () {
        	
        	function initToolbarBootstrapBindings() {
        	      var fonts = ['Serif', 'Sans', 'Arial', 'Arial Black', 'Courier', 
        	            'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
        	            'Times New Roman', 'Verdana'],
        	            fontTarget = $('[title=Font]').siblings('.dropdown-menu');
        	      $.each(fonts, function (idx, fontName) {
        	          fontTarget.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'+fontName + '</a></li>'));
        	      });
        	      $('a[title]').tooltip({container:'body'});
        	    	$('.dropdown-menu input').click(function() {return false;})
        			    .change(function () {$(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');})
        	        .keydown('esc', function () {this.value='';$(this).change();});

        	      $('[data-role=magic-overlay]').each(function () { 
        	        var overlay = $(this), target = $(overlay.data('target')); 
        	        overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
        	      });
        	      if ("onwebkitspeechchange"  in document.createElement("input")) {
        	        var editorOffset = $('#editor').offset();
        	        $('#voiceBtn').css('position','absolute').offset({top: editorOffset.top, left: editorOffset.left+$('#editor').innerWidth()-35});
        	      } else {
        	        $('#voiceBtn').hide();
        	      }
        		};
            return {
                restrict: 'E',                
                replace:true,
                transclude:true,
                link: function(scope, element) {
                	initToolbarBootstrapBindings();
                	var editorContent = element.find("#editorContent");
                	element.find("#wysiwyg").wysiwyg().keyup(function(e) {
                	  editorContent.val($(this).text());
                	});                	
                },
                templateUrl:"common/templates/wysiwyg.html?t="+new Date().getMilliseconds()
            };
        });
});