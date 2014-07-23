jQuery(function( $ ) {

	$('#register-btn').click( function() {
		$('.login-form').hide();
		$('.register-form').fadeIn();
	});

	$('#back-btn').click( function() {
		$('.register-form').hide();
		$('.login-form').fadeIn();
	});


	$('#exit').on('click', function(){
		console.log('exit');
		$.get('/sn/user/exit', function(){
		});
		location.reload();
	});


	$('#user-submit').click(function() {
		login = $('#user-login').val();
		pass = $('#user-password').val();
		if(login!="" && pass!="") {
//			$.post('ajax/index.php',{login:login,pass:pass},function(server_json){
			$.post('/sn/user/login',{login:login,pass:pass},function(server_json){
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
//        if(login!="" && pass!="") {
////			$.post('ajax/index.php',{login:login,pass:pass},function(server_json){
            $.post('/sn/user/registration',{name:name,surname:surname,email:email,password:pass,invite:invite},function(server_json){
                if(server_json.status==true) {
                    location.reload();
                }
                else {alert('Wrong login or password')}
            },'json');
//        }
    });




























	function setupHomePage() {
		console.log('Setup Home');
		$.getJSON('user/id85', {}, function(json) {
		// $.getJSON('rest/', {}, function(json) {
			var node = '<div>';
			node += '<p>' + json.name + '</p>';
			node += '<p>' + json.surname + '</p>';
			node += '<p>' + json.position + '</p>';
			node += '</div>';
			$('#content').append( $(node) );
		});
	};

	function setupMessagesPage() {
		console.log('Setup Home');
		$.getJSON('ajax/messages.json', {}, function(json) {
		// $.getJSON('rest/', {}, function(json) {
			//CODE FOR HOME;
		});
	};

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
	};

	determinePage();





	function updateMessages() {
		// $.get('ajax/message.json', function(response){
		// 	if ( response.new == true ) {
		// 		console.log('new Message');
		// 	}
		// });
	};
	function updateFriends() {
		// $.get('ajax/friend.json', function(response){
		// 	if ( response.new == true ) {
		// 		console.log('new Friend');
		// 	} 
		// });
	};

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