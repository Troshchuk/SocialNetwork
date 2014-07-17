jQuery(function( $ ) {

	$('#register-btn').click( function() {
		$('.to-login').hide();
		$('.to-register').fadeIn();
	});

	$('#back-btn').click( function() {
		$('.to-login').fadeIn();
		$('.to-register').hide();
	});

	$('#login-btn').click(function() {
		login = $('#form-login [type=text]').val()
		pass = $('#form-login [type=password]').val()
		if(login!="" && pass!="") {
			$.post('rest/user/login',{login:login,pass:pass},function(server_json){
				if(server_json.status==true) {
					window.history.pushState([], [], 'list.html');
					$("body").load('list.html');

					$.getJSON('ajax/users.json', {}, function(json){
							// debugger
						for (var i = 0; i < json.persons.length; i++) {
							var list = '<div class="user-list">';
							list += '<ul>';
							list += '<li class="user-entry">';
							list += '<div class="image"><img src="images3.jpeg"></div>';
							list += '<div class="textual">';
							list += '<div class="user-name"><span>Name </span>' + json.persons[i].name + '</div>';



							$('.user-name').eq(i).text(json.persons[i].name);
							$('.user-department').eq(i).text(json.persons[i].department);
							$('.user-position').eq(i).text(json.persons[i].position);
							$('.user-age').eq(i).text(json.persons[i].age);
						};
					});
				}
				else {alert('-')}
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