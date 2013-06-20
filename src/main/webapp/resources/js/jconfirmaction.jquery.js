/*
 * jQuery Plugin : jConfirmAction
 * 
 * by Hidayat Sagita
 * http://www.webstuffshare.com
 * Licensed Under GPL version 2 license.
 *
 */
(function($){
	
	
jQuery.fn.jLoadAction = function (options) {

	return this.each (function () {
		var thisClass = $(this).attr('class');
		var thisHref= $(this).attr('href');
		var thisName = $(this).attr('name');
		if(thisClass == 'ask' || thisHref=="" || thisName=="editLink"){
			return;
		}
			$(this).bind('click', function(e) {
				e.preventDefault();
				 $('.right_content').load(thisHref,function(response,status,xhr){
					 
//						alert($("#elementOnLoginPage").length);
//					 alert(response.indexOf("elementOnLoginPage"));
					// window.location.replace("login.jsp");
					 
					 /*alert(response);
					 alert(response.indexOf("elementOnLoginPage"));
					 if(response.indexOf("elementOnLoginPage")!=-1){
						 window.location.herf="/login.jsp";
					 }*/
					/*alert(status);
					alert(response);
					alert(xhr);*/
				 });
				
			});
			
		});
	};
	
	/*jquery.fn.jEditAction = function(options){
		return this.each(function(){
			var thisHref= $(this).attr('href');
			$(this).bind('click', function(e) {
				e.preventDefault();
				 $.getJSON(thisHref,function(data){  
					 data.
				 });
				 });
			});
			
		
		
	};
	*/

	jQuery.fn.jConfirmAction = function (options) {
		
		// Some jConfirmAction options (limited to customize language) :
		// question : a text for your question.
		// yesAnswer : a text for Yes answer.
		// cancelAnswer : a text for Cancel/No answer.
		var theOptions = jQuery.extend ({
			question: "Are You Sure ?",
			yesAnswer: "Yes",
			cancelAnswer: "Cancel"
		}, options);
		
		return this.each (function () {
			
			$(this).bind('click', function(e) {

				e.preventDefault();
				thisHref	= $(this).attr('href');
				
				if($(this).next('.question').length <= 0)
					$(this).after('<div class="question">'+theOptions.question+'<br/> <span class="yes">'+theOptions.yesAnswer+'</span><span class="cancel">'+theOptions.cancelAnswer+'</span></div>');
				
				$(this).next('.question').animate({opacity: 1}, 300);
				
				$('.yes').bind('click', function(){
					window.location = thisHref;
				});
		
				$('.cancel').bind('click', function(){
					$(this).parents('.question').fadeOut(300, function() {
						$(this).remove();
					});
				});
				
			});
			
		});
	};
	
jQuery.fn.jConfirmLoadAction = function (options) {
		
		// Some jConfirmAction options (limited to customize language) :
		// question : a text for your question.
		// yesAnswer : a text for Yes answer.
		// cancelAnswer : a text for Cancel/No answer.
		var theOptions = jQuery.extend ({
			question: "Are You Sure ?",
			yesAnswer: "Yes",
			cancelAnswer: "Cancel"
		}, options);
		
		return this.each (function () {
			
			$(this).bind('click', function(e) {

				e.preventDefault();
				thisHref	= $(this).attr('href');
				
				if($(this).next('.question').length <= 0)
					$(this).after('<div class="question">'+theOptions.question+'<br/> <span class="yes">'+theOptions.yesAnswer+'</span><span class="cancel">'+theOptions.cancelAnswer+'</span></div>');
				
				$(this).next('.question').animate({opacity: 1}, 300);
				
				$('.yes').bind('click', function(){
					$('.right_content').load(thisHref);
				});
		
				$('.cancel').bind('click', function(){
					$(this).parents('.question').fadeOut(300, function() {
						$(this).remove();
					});
				});
				
			});
			
		});
	};
	
})(jQuery);