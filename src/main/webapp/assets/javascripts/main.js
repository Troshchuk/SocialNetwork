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
			$.post('rest/user/login',{login:login,pass:pass},function(server_json){
				if(server_json.status==true) {
					window.history.pushState([], [], 'list.html');
					$("body").load('list.html');

					// $.getJSON('ajax/users.json', {}, function(json) {
					$.getJSON('rest/user2/users', {}, function(json) {
						var list = '<div class="user-list">';
						list += '<ul>';
						for (var i = 0; i < json.users.length; i++) {
							list += '<li class="user-entry">';
							// list += '<div class="image"><img src="images3.jpeg"></div>';
							list += '<div class="textual">';
							list += '<div class="user-name"><span>Name </span>' + json.users[i].name + '</div>';
							list += '<div class="user-surname"><span>Surname </span>' + json.users[i].surname + '</div>';
							list += '<div class="user-position"><span>Position </span>' + json.users[i].position + '</div>';
							// list += '<div class="user-department"><span>Department </span>' + json.users[i].department + '</div>';
							// list += '<div class="user-age"><span>Age </span>' + json.users[i].age + '</div>';
							list += '</li>';
						};
						list += '</ul>';
						list += '</div>';
						$('body').append( $(list) );
					});
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