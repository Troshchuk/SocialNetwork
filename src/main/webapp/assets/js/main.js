jQuery(function ($) {
    'use strict';

    $('#formlogin').validate({
        submitHandler: function (form) {
            var login = $('#userlogin').val();
            var pass = $('#userpassword').val();
            $.post('/sn/index/login', {login: login, pass: pass}, function (server_json) {
                if (server_json.status == true) {
                    location.reload();
                }
                // else {alert('Wrong login or password')}
            }, 'json')
        }
    });

    $('#formregister').validate({
        submitHandler: function (form) {
            var name = $('#name').val();
            var surname = $('#surname').val();
            var position = $('#position').val();
            var email = $('#email').val();
            var birthday = $('#birthday').val();
            var pass = $('#pass').val();
            var invite = $('#invite').val();
            $.post('/sn/index/registration', {name: name, surname: surname, position: position, birthday: birthday, email: email, password: pass, invite: invite}, function (response) {
                if (response.status == true) {
                    location.reload();
                    $('#register-error-msg').hide();
                } else if (response.status == false) {
                    var msg = 'Server responded with error';
                    $('#register-error-msg').text(msg).fadeIn(500);
                } else if (response.status == 'wrongInviteCode') {
                    var msg = 'Wrong invite code';
                    $('#register-error-msg').text(msg).fadeIn(500);
                } else if (response.status == 'wrongLoginPass') {
                    var msg = 'Wrong login or password';
                    $('#register-error-msg').text(msg).fadeIn(500);
                }
            }, 'json');
        },
        rules: {
            pass: {
                required: true,
                rangelength: [4, 16]
            },
            passagain: {
                equalTo: "#pass"
            }
        }
    });


    $('#register-btn').click(function () {
        $('.login-form').hide();
        $('.register-form').fadeIn();
    });
    $('#back-btn').click(function () {
        $('.register-form').hide();
        $('.login-form').fadeIn();
    });


    $('.messages-tab').click(function (event) {
        event.preventDefault();
        var tab = $(this);
        $('.messages-tab').removeClass('active')
        tab.addClass('active')

        var id = tab.attr('href');
        $('.messages-tab-content').hide();
        $(id).fadeIn();
    });

    $('#exit').click('click', function () {
        $.get('/sn/user' + getUserId() + '/exit', {}, function (response) {
            if (response.status == true) {
                location.reload();
            }
        }, 'json');
    });


    $('#wall-submit').on('click', function (event) {
        event.preventDefault();
        var msg = $('#wall-message').val();
        if (msg == '') return;
        $.post('/sn/user' + getUserId() + '/createPost', {msg: msg}, function (response) {
            if (response.status == true) {
                location.reload();
            }
        });
    });

    $('#wall-message').on('change keyup', function () {
        if ($('#wall-message').val() == '') {
            $('#wall-submit').addClass('inactive');
        } else {
            $('#wall-submit').removeClass('inactive');
        }
    });






// ============ HELPERS =========

    decorateFollow();

    function decorateFollow(){
        setTimeout(function(){
            $.get('/sn/user'+ getUserId() +'/isFollowing', function(response){
                if(response.isFollowing) {
                    $('.follow').text('Unfollow');
                } else{
                    $('.follow').text('Follow');
                }
            });
        }, 500);
    }

    function handleFollower() {
        var id = getUserId();
        $.get('/sn/user'+ getUserId() +'/isFollowing', function(response){
            logger.log( response.isFollowing );
            if ( response.isFollowing ) {
                logger.log('do delete');
                $.post('/sn/user'+ getUserId()+ '/delete', {}, function(response){
                    logger.log('do delete '+id);
                    if (response.status == true) {
                        // location.reload();
                        logger.log('done');
                    }
                });
            } else {
                logger.log('do add');
                $.post('/sn/user'+ getUserId()+ '/add', {}, function(response){
                    logger.log('do add '+id);
                    if (response.status == true) {
                        // location.reload();
                        logger.log('done');
                    }
                });
            }
        });
        decorateFollow();
    };




    function formatDate(msec) {
        var date = new Date(msec);
        var month = ( '0' + ( date.getMonth() + 1 ) ).slice(-2);
        var formated = 'at ' + date.getHours() + ':' + date.getMinutes() + ' on ' + date.getDate() + '.' + month + '.' + date.getFullYear();
        return formated;
    };

    function getUserId() {
        var url = window.location.href;
        var pos = url.indexOf('user');
        if (pos == -1) {
            var id = $.cookie("userId");
            return id;
        }
        var id = url.slice(pos + 4);
        return id;
    }

    function removePost(id) {
        $.post('/sn/user' + getUserId() + '/deletePost', {postId: id}, function (response) {
            if (response.status == true) {
                location.reload();
            }
        });
    };


    function sendPrivateMessage(msg) {
        var url = window.location.href;
        var pos = url.indexOf('user');
        var id = url.slice(pos + 4);
        $.post('/sn/pm/sendMessage', {to:id, msg:msg}, function (json) {

        });
    };
    // sendPrivateMessage();

    $('.submit-submit').click(function(event){
        event.preventDefault();
        var mesage = $('.message-message').val();
        $('.message-message').val('');
        sendPrivateMessage(mesage);
    });
















    function setupHomePage() {
        logger.log('Setup Home');
        var page = 0;
        var haveMorePages = true;
        var fullname = '';
        var position = '';
        var ava = new Image();

        $.getJSON('/sn/user' + getUserId() + '/getUser', {}, function (json) {
            if (json != 'undefined') {
                if (json.name != 'undefined') {
                    $('#user-name').text(json.name);
                    fullname += json.name;
                }
                if (json.surname != 'undefined') {
                    $('#user-surname').text(json.surname);
                    if (fullname.length > 0) fullname += ' ' + json.surname;
                }
                if (json.position != 'undefined') {
                    $('#user-position').text(json.position);
                }
            }
        });

        var str = '/sn/user' + getUserId() + '/getAvatar';
        document.getElementById("avatar").src = str;


        $.getJSON('/sn/user' + getUserId() + '/interests', {}, function (json) {
            if (json != 'undefined') {
                //TODO: check interests
                var str = [];
                for (var i = 0; i < json.interests.length; i++) {
                    str.push(json.interests[i].interest);
                };
                str = str.join(', ');
                $('#user-hobbies').text(str);
            }
        });


        function _loadWallPosts() {
            $.getJSON('/sn/user' + getUserId() + '/posts' + page, {}, function (json) {
                _makeupWallPosts(json);
            });
            page++;
        };

        function _makeupWallPosts(json) {
            for (var i = 0; i < json.posts.length; i++) {
                var node = '<div class="post" id="' + json.posts[i].postId + '">';
                node += '<div class="post-photo">';
                node += '<a href="#">';
                node += '<img id="avatar">';
                node += '</a>';
                node += '</div>';
                node += '<div class="post-message">';
                node += '<p class="post-name"><a href="">' + fullname + '</a></p>';
                node += '<span class="remove-post"></span>';
                node += '<p>' + json.posts[i].post + '</p>';
                node += '<span class="post-meta">' + formatDate(json.posts[i].time) + '</span>';
                node += '</div>';
                node += '</div>';
                $('#wall').append(node);
            }
            ;
            $('.remove-post').on('click', function () {
                var id = $(this).parents('.post').attr('id');
                removePost(id);
            });
        };

        _loadWallPosts();

        // setTimeout(function () {
        //     _loadWallPosts();
        // }, 5000);


        $('.follow').text( decorateFollow() );

        $('.follow').click(function(event){
            event.stopPropagation();
            handleFollower();


        });

    }


    function setupMessagesPage() {
        logger.log('Setup Messages');



        $.getJSON('/sn/pm/sent0', {}, function (json) {
            var node = '<ul>'
            for (var i = 0; i < json.privateMessages.length; i++) {
                var name = json.privateMessages[i].receiverUser.name + ' ' + json.privateMessages[i].receiverUser.surname;
                node += '<li id="'+ json.privateMessages[i].messageId +'" class="message ' + json.privateMessages[i].read + '">';
                node += '<p'+json.privateMessages[i].receiverUser.id + '" > Sent to <span class="name">' + name + '</span></p>';
                node += '<div class="message-container"></div>';
                node += '</li>';
            };
            node += '</ul>';
            $('#sent').append( $(node) );
            $('.message').on('click', function(event){
                event.stopPropagation();
                var thisEl = $(this);
                var message = thisEl.find('.message-container');
                if(thisEl.hasClass('cl')) return;
                thisEl.addClass('cl');
                var id = thisEl.attr('id');
                $.get('pm/getMessage'+id, {}, function (response) {
                    var msg = '<p class="message-text">' + response.message + '<span class="close"></span></p>';
                    $(message).append( msg );
                });
            });
        });

        $.getJSON('/sn/pm/received0', {}, function (json) {
            var node = '<ul>'
            for (var i = 0; i < json.privateMessages.length; i++) {
                var name = json.privateMessages[i].sentUser.name + ' ' + json.privateMessages[i].sentUser.surname;
                node += '<li id="'+ json.privateMessages[i].messageId +'" class="message ' + json.privateMessages[i].read + '">';
                node += '<p'+json.privateMessages[i].receiverUser.id + '" > From <span class="name">' + name + '</span></p>';
                node += '<div class="message-container"></div>';
                node += '</li>';
            };
            node += '</ul>';
            $('#inbox').append( $(node) );
            $('.message').on('click', function(event){
                event.stopPropagation();
                var thisEl = $(this);
                var message = thisEl.find('.message-container');
                if(thisEl.hasClass('cl')) return;
                thisEl.addClass('cl');
                var id = thisEl.attr('id');
                $.get('pm/getMessage'+id, {}, function (response) {
                    var msg = '<p class="message-text">' + response.message + '<span class="close"></span></p>';
                    $(message).append( msg );
                });
            });
        });
    }


    function setupColleaguesPage() {
        logger.log('Setup Colleagues');
        $.getJSON('/sn/workers/getWorkers0', {}, function (json) {
            var list = '<ul>';
            for (var i = 0; i < json.users.length; i++) {
                list += '<li class="user-entry">';
                list += '<div class="textual">';
                list += '<div class="user-name"><a href="/sn/user' + json.users[i].id + '"><span></span>' + json.users[i].name + ' ' + json.users[i].surname + '</a></div>';
                list += '<div class="user-position"><span>Position </span>' + json.users[i].position + '</div>';
                list += '</li>';
            }
            ;
            list += '</ul>';
            $('#listOfUsers').append($(list));
        });
    };


    function setupNewsPage() {
        logger.log('Setup News');
        $.getJSON('/sn/news/news0', {}, function (json) {
            for (var i = 0; i < json.posts.length; i++) {
                var node = '<div class="news" id="' + json.posts[i].postId + '">';
                // node += '<div class="post-photo">';
                // node +=	'<a href="#">';
                // node += '<img src="/assets/img/Mt-8_dgwlHM.jpg" alt="">';
                // node += '</a>';
                // node += '</div>';
                node += '<div class="news-message">';
                // node += '<p class="news-name"><a href="">' + fullname + '</a></p>';
                // node += '<span class="remove-post"></span>';
                node += '<p>' + json.posts[i].post + '</p>';
                node += '<span class="post-meta">' + formatDate(json.posts[i].time) + '</span>';
                node += '</div>';
                node += '</div>';
                $('#content').append(node);
            }
            ;
        });
    };


    function setupFollowingsPage() {
        logger.log('Setup Followings');
        $.getJSON('/sn/followings/getFollowings0', {}, function (json) {
        var list = '<ul>';
            for (var i = 0; i < json.followingUsers.length; i++) {
                list += '<li class="user-entry">';
                list += '<div class="textual">';
                list += '<div class="user-name"><a href="/sn/user' + json.followingUsers[i].id + '"><span></span>' + json.followingUsers[i].name + ' ' + json.followingUsers[i].surname + '</a></div>';
                list += '<div class="user-position"><span>Position </span>' + json.followingUsers[i].position + '</div>';
                list += '</li>';
            };
            list += '</ul>';
            $('#listOfUsers').append($(list));
        });
    };


    function setupGroupPage() {
        logger.log('Setup Group');
        $.getJSON('/sn/groups0', {}, function (json) {

        });
    };


    function setupUserEditPage() {
        logger.log('Setup Useredit');
        var name = $('#username');
        var surname = $('#usersurname');
        var position = $('#userposition');
        var interests = $('#userinterests');
        var date = $('#userdate');
        var month = $('#usermonth');
        var year = $('#useryear');
        var avatar = $('#useravatar');

        $.get('/sn/user' + getUserId() + '/getUser', {}, function (json) {
            name.val(json.name);
            surname.val(json.surname);
            position.val(json.position);
            var bday = json.birthday.split('-');
            year.val(bday[0]);
            month.val(bday[1]);
            date.val(bday[2]);
        });
        $.getJSON('/sn/user' + getUserId() + '/interests', {}, function (json) {
            var str = [];
            for (var i = 0; i < json.interests.length; i++) {
                str.push(json.interests[i].interest);
            }
            str = str.join(', ');

            $('#userinterests').val(str);
        });

        $('#usereditform').validate({
            submitHandler: function (form) {
                $.post('/sn/user0/edit', { name: name.val(), surname: surname.val(), position: position.val(), interests: interests.val(), date: date.val(), month: month.val(), year: year.val() }, function (json) {
                     if (json.status == true) {
                             window.location.href = '/';
                         }
                     });

            }
        });
    };




    function Logger(state) {
        this.output = state || false;
        this.log = function (msg) {
            if (this.output) {
                console.log(msg);
            }
        }
    };

    var logger = new Logger(true);




    function determinePage() {
        var pageId = $('body').attr('id');
        switch (pageId) {
            case 'home':
                logger.log('Home-page detected');
                setupHomePage();
                break;
            case 'colleagues':
                logger.log('Сolleagues-page detected');
                setupColleaguesPage();
                break;
            case 'messages':
                logger.log('Messages-page detected');
                setupMessagesPage();
                break;
            case 'news':
                logger.log('News-page detected');
                setupNewsPage();
                break;
            case 'followings':
                logger.log('Followings-page detected');
                setupFollowingsPage();
                break;
            case 'group':
                logger.log('Group-page detected');
                setupGroupPage();
                break;
            case 'useredit':
                logger.log('Useredit-page detected');
                setupUserEditPage();
                break;
            default:
                logger.log('Custom-page detected');
        }
    }

    determinePage();


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