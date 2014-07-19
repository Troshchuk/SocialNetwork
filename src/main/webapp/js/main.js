jQuery(function( $ ) {

	$('#register-btn').click( function() {
		$('.login-form').hide();
		$('.register-form').fadeIn();
	});

	$('#back-btn').click( function() {
		$('.register-form').hide();
		$('.login-form').fadeIn();
	});



	$('#user-submit').click(function() {
		login = $('#user-login').val()
		pass = $('#user-password').val()
		if(login!="" && pass!="") {
//			$.post('ajax/index.php',{login:login,pass:pass},function(server_json){
			$.post('/rest/user/login',{login:login,pass:pass},function(server_json){
				if(server_json.status==true) {
					location.reload();
				}
				else {alert('Wrong login or password')}
			},'json')
		}
	})










	var $wrapper = $(".main-section"),
		$header = $(".main-header"),
		$footer = $(".main-footer");

	$(window).on("resize load", function(){
		var hh = $header.outerHeight(),
			fh = $footer.outerHeight(),
			wh = $(window).height();

		$wrapper.css({
			"min-height" : function(){
				return wh-fh-hh;
			}
		});
	});


});