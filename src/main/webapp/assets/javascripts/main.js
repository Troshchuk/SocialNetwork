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
							list += '<div class="user-department"><span>Department </span>' + json.persons[i].department + '</div>';
							list += '<div class="user-position"><span>Position </span>' + '</div>'

							$('.user-name').eq(i).text(json.persons[i].name);
							$('.user-department').eq(i).text(json.persons[i].department);
							$('.user-position').eq(i).text(json.persons[i].position);
							$('.user-age').eq(i).text(json.persons[i].age);
						};
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