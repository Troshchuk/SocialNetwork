jQuery(function( $ ) {
	'use strict';

	$('#register-btn').click( function() {
		$('.login-form').hide();
		$('.register-form').fadeIn();
	});
	$('#back-btn').click( function() {
		$('.register-form').hide();
		$('.login-form').fadeIn();
	});


	$('#user-submit').click(function() {
		login = $('#user-login').val();
		pass = $('#user-password').val();
		if(login!="" && pass!="") {
			$.post('/sn/index/login',{login:login,pass:pass},function(server_json){
				if(server_json.status==true) {
					location.reload();
				}
				else {alert('Wrong login or password')}
			},'json')
		}
	});

//    @FormParam("name") String name,
//        @FormParam("surname") String surname,
//        @FormParam("email") String login,
//        @FormParam("password") String password,
//        @FormParam("invite") String invite) {
//
    $('#register-btn2').click(function() {
        name = $('#name').val();
        surname = $('#surname').val();
        email = $('#email').val();
        pass = $('#pass').val();
        invite = $('#invite').val();
        $.post('/sn/index/registration',{name:name,surname:surname,position:position,email:email,password:pass,invite:invite},function(server_json){
            if(server_json.status==true) {
                location.reload();
            }
            else {alert('Wrong login or password')}
        },'json');
    });


	$('#exit').click('click', function() {
		$.get('/sn/user' +getUserId()+ '/exit', {}, function(server_json){
			location.reload();
		},'json')
	});



	$('#wall-submit').on('click', function(event){
		event.preventDefault();
		var msg = $('#wall-message').val();
		$.post('/sn/user' +getUserId()+ '/createPost', {msg:msg}, function(){
			location.reload();
		});
	});







	function formatDate(msec) {
		var date = new Date(msec);
		// var year = date.getFullYear();
		// var month = date.getMonth()+1;
		// var date = date.getDate();
		var formated = 'at ' + date.getHours() + ':' + date.getMinutes() + ' on ' + date.getDate() + '.' + date.getMonth()+1 + '.' + date.getFullYear();
		return formated;
	};

	function getUserId() {
		var userId = $.cookie("userId");
		return userId;
	}





	function setupHomePage() {
		console.log('Setup Home');
		var fullname = '';
		$.getJSON('/sn/user'+ getUserId() +'/getUser', {}, function(json) {
			$('#user-name').text( json.name );
			$('#user-surname').text( json.surname );
			$('#user-position').text( json.position );
			fullname = json.name + ' ' + json.surname;
		});

		$.getJSON('/sn/user' +getUserId()+ '/interests', {}, function(json) {
			// console.log(json.interests.length);
			var str = [];
			for (var i = 0; i < json.interests.length; i++) {
				str.push( json.interests[i].interest );
			};
			str = str.join(', ');
			$('#user-hobbies').text( str );
		});

		$.getJSON('/sn/user' + getUserId()+ '/posts0' , {}, function(json) {
			for (var i = 0; i < json.posts.length; i++) {
				console.log(json.posts[i].post);
				// Things[i]
				var node = '<div class="post">';
					node += '<div class="post-photo">';
					node +=	'<a href="#">';
					node += '<img src="/assets/img/Mt-8_dgwlHM.jpg" alt="">';
					node += '</a>';
					node += '</div>';
					node += '<div class="post-message">';
					node += '<p class="post-name"><a href="">' + fullname + '</a></p>';
					node +=	'<p>' +json.posts[i].post+ '</p>';
					node += '<span class="post-meta">'+formatDate( json.posts[i].time )+'</span>';
					node += '</div>';
					node += '</div>';
					$('#wall').append( node );
			};
		});
	}

	function setupMessagesPage() {
		console.log('Setup Home');
		$.getJSON('ajax/messages.json', {}, function(json) {
		// $.getJSON('rest/', {}, function(json) {
			//CODE FOR HOME;
		});
	}

	function setupFriendsPage() {
		console.log('Setup Friends');
		// $.getJSON('ajax/users.json', {}, function(json) {
		$.getJSON('/sn/user/workers0', {}, function (json) {
			var list = '<ul>';
			for (var i = 0; i < json.users.length; i++) {
				list += '<li class="user-entry">';
				list += '<div class="textual">';
				list += '<div class="user-name"><span>Name </span>' + json.users[i].name + '</div>';
				list += '<div class="user-surname"><span>Surname </span>' + json.users[i].surname + '</div>';
				list += '<div class="user-position"><span>Position </span>' + json.users[i].position + '</div>';
				list += '</li>';
			};
			list += '</ul>';
			$('#listOfUsers').append( $(list) );
		});
	};

	function determinePage() {
		var pageId = $('body').attr('id');
		switch(pageId) {
			case 'home': 
				console.log('Home-page');
				setupHomePage();
				break;
			case 'friends':
				console.log('Friends-page');
				setupFriendsPage();
				break;
			case 'messages':
				console.log('Messages-page');
				setupMessagesPage();
				break;
			default:
				console.log('Custom-page');
		}
	}

	determinePage();





	function updateMessages() {
		// $.get('ajax/message.json', function(response){
		// 	if ( response.new == true ) {
		// 		console.log('new Message');
		// 	}
		// });
	}
	function updateFriends() {
		// $.get('ajax/friend.json', function(response){
		// 	if ( response.new == true ) {
		// 		console.log('new Friend');
		// 	} 
		// });
	}

	setInterval( function(){ updateMessages(); } , 5000);
	setInterval( function(){ updateFriends(); } , 10000);




/*	var $wrapper = $(".main-section"),
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
	});*/
});