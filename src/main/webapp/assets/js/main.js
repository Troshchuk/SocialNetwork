jQuery(function ($) {
    'use strict';

    var interestPage = 0;
    var workersPage = 0;
    var followersPage = 0;
    var groupListPage = 0;
    var sentMessagesPage = 0;
    var inboxMessagesPage = 0;
    var newsPage = 0;


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


    $('.tab').click(function (event) {
        event.preventDefault();
        var tab = $(this);
        $('.tab').removeClass('active')
        tab.addClass('active')

        var id = tab.attr('href');
        $('.tab-content').hide();
        $(id).fadeIn();
    });

    $('#exit').click('click', function () {
        $.get('/sn/user' + getUserId() + '/exit', {}, function (response) {
            if (response.status == true) {
                location.reload();
            }
        }, 'json');
    });




    $('.wall-post-submit').on('click', function (event) {
        var thisEl = $(this);
        event.preventDefault();
        var msg = thisEl.parent('form').find('textarea').val();
        if (msg == '') return;
        $.post('/sn/user' + getUserId() + '/createPost', {msg: msg}, function (response) {
            if (response.status == true) {
                location.reload();
            }
        });
    });


    $('.group-message-submit').on('click', function (event) {
        var thisEl = $(this);
        event.preventDefault();
        var msg = thisEl.parent('form').find('textarea').val();
        if (msg == '') return;
        logger.log(msg);
        $.post('/sn/group' + getGroupId() + '/createPost', {msg: msg}, function (response) {
            if (response.status == true) {
                location.reload();
            }
        });
    });


    $('.private-message-submit').click(function(event){
        event.preventDefault();
        var thisEl = $(this);
        var btn = thisEl.parent('form').find('[type="submit"]');
        var msg = thisEl.parent('form').find('textarea');
        if( msg.val() != '') {
            sendPrivateMessage( msg.val() );
            msg.val('');
            btn.addClass('button-inactive');
        }
    });


    $('.form-text-area').on('change keyup', function () {
        var thisEl = $(this);
        var button = thisEl.parent('form').find('[type="submit"]');
        if ( thisEl.val() == '') {
            button.addClass('button-inactive');
        } else {
            button.removeClass('button-inactive');
        }
    });


    $('.change-user-pic').click(function(){
        $(this).hide();
        $('.upload-user-pic').show();
    });



    $('.expand-message-form p').click(function(){
        var thisEl = $(this);
        var form = thisEl.parent().find('form');
        var btn = thisEl.parent().find('button[type="submit"]');
        thisEl.addClass('unbutton');
        setTimeout(function(){
            form.fadeIn();
            setTimeout(function(){
                btn.fadeIn();
            },600);
        }, 400);
    });


    $('.subcsribe-group').click(function(event){
        event.stopPropagation();
        handleSubscriber();
    });







// ============ HELPERS =========


    function isPageEnd() {
        var offset = $('.main-footer').scrollTop();
        var wHeight = $(window).height();
        if( offset < wHeight ) return true;
        return false;
    };


    function rotateY(el) {
        el.addClass('rotateY add-transition');
        setTimeout(function(){
            el.removeClass('rotateY add-transition');
        },500);
    }


    function nameFollow(){
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
        $.get('/sn/user'+ id +'/isFollowing', function(response){
            if ( response.isFollowing ) {
                $.post('/sn/user'+ id + '/delete', {}, function(response){
                    if (response.status == true) {
                    }
                });
            } else {
                $.post('/sn/user'+ id + '/add', {}, function(response){
                    if (response.status == true) {
                    }
                });
            }
        });
        nameFollow();
        rotateY($('.follow'));
    };




    function nameSubscriber(){
        logger.log('dfgdg');
        setTimeout(function(){
            $.get('/sn/group'+ getGroupId() +'/isFollowing', function(response){
                if(response.isFollowing) {
                    $('.subcsribe-group').text('Unsubscribe');
                } else{
                    $('.subcsribe-group').text('Subscribe');
                }
            });
        }, 500);
    };


    function handleSubscriber() {
        var id = getGroupId();
        $.get('/sn/group'+ id +'/isFollowing', function(response){
            logger.log( response.isFollowing );
            if ( response.isFollowing ) {
                $.post('/sn/group'+ id + '/unfollow', {}, function(response){
                    if (response.status == true) {
                    }
                });
            } else {
                $.post('/sn/group'+ id + '/follow', {}, function(response){
                    if (response.status == true) {
                    }
                });
            }
        });
        nameSubscriber();
        rotateY($('.subcsribe-group'));
    };



    function formatDate(msec) {
        var date = new Date(msec);
        var month = ( '0' + ( date.getMonth() + 1 ) ).slice(-2);
        var formated = 'at ' + date.getHours() + ':' + date.getMinutes() + ' on ' + date.getDate() + '.' + month + '.' + date.getFullYear();
        return formated;
    };


    function isSelfPage() {
        var url = window.location.href;
        var pos = url.indexOf('user');
        if (pos == -1) return true;

        var urlId = url.slice(pos + 4);
        var cookieId = $.cookie("userId");
        if( urlId == cookieId ) return true;
        return false;

    };


    function getUserId(paramId) {
        if( paramId != 'undefined' && !isNaN(paramId) ) {
            return paramId;
        }
        var url = window.location.href;
        var pos = url.indexOf('user');
        if (pos == -1) {
            var id = $.cookie("userId");
            return id;
        }
        var id = url.slice(pos + 4);
        return id;
    }

    function getGroupId() {
        var url = window.location.href;
        var pos = url.indexOf('group');
        // if (pos == -1) {
            // var id = $.cookie("userId");
            // return id;
        // }
        var id = url.slice(pos + 5);
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




    function createGroup(name, descr, userId) {
        $.post('/sn/newGroup', {name:name, description:descr }, function (json) {

        });
    }





    function findByInterest(interest) {
        $.get('/sn/interest'+ interest + '/' + interestPage, function(){

        });
        // interestPage++;
    };






    function setupHomePage() {
        logger.log('Setup Home');
        var page = 0;
        var haveMorePages = true;
        var fullname = '';
        var position = '';
        var ava = new Image();

        if ( isSelfPage() ){
            $('.private-message-form').hide();
            $('.follow').hide();
        }

        if ( ! isSelfPage() ){
            $('.new-wall-post').hide();
            $('.change-user-pic').hide();
        }



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
                if (json.birthday != 'undefined') {
                    $('#user-birthday').text(json.birthday);
                    
                }
                if (json.position != 'undefined') {
                    $('#user-position').text(json.position);
                }
            }
        });

        var str = '/sn/user' + getUserId() + '/getAvatar';
        document.getElementById("avatar").src = str;


        $.getJSON('/sn/user' + getUserId() + '/interests', {}, function (json) {
            if (json != 'undefined' ) {
                var str = [];
                for (var i = 0; i < json.interests.length; i++) {
                    var line = '<a class="interest-url" data-interest-id="'+json.interests[i].interests_id+'" href="">' + json.interests[i].interest + '</a>'; 
                    str.push(line);
                };
                str = str.join(', ');
                $('#user-hobbies').html(str);
            }
            $('.interest-url').click(function(event){
                event.preventDefault();
                var interestId = $(this).data('interest-id');
                findByInterest(interestId);
            });
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
                node += '<img class="userPic">';
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
                if(json.posts.length < 10 ) {
                    $('.more-wall-posts').hide();
                }
            };
            $('.remove-post').on('click', function () {
                var id = $(this).parents('.post').attr('id');
                removePost(id);
            });
            var userPic = '/sn/user' + getUserId() + '/getAvatar';
            $('.userPic').attr("src", userPic);
        };

        _loadWallPosts();


        $('.more-wall-posts').click(function(){
            _loadWallPosts();
        });


        $('.follow').text( nameFollow() );

        $('.follow').click(function(event){
            event.stopPropagation();
            handleFollower();
        });

        nameFollow();
    }


    function setupMessagesPage() {
        logger.log('Setup Messages');


        function _getSentMessages() {
            $.getJSON('/sn/pm/sent' + sentMessagesPage, {}, function (json) {
                _makeupSentMessages(json);
            });
            sentMessagesPage++;
        };

        function _getInboxMessages() {
            $.getJSON('/sn/pm/received'+ inboxMessagesPage, {}, function (json) {
                _makeupInboxMessages(json);
            });
            inboxMessagesPage++;
        }


        function _makeupSentMessages(json){
            var node = '<ul>'
            for (var i = 0; i < json.privateMessages.length; i++) {
                var name = json.privateMessages[i].receiverUser.name + ' ' + json.privateMessages[i].receiverUser.surname;
                node += '<li id="'+ json.privateMessages[i].messageId +'" class="message ' + json.privateMessages[i].read + '">';
                node += '<p'+json.privateMessages[i].receiverUser.id + '" > Sent to <span class="name">' + name + '</span></p>';
                node += '<div class="message-container"></div>';
                node += '</li>';
                if ( json.privateMessages.length < 10 ) {
                    $('.more-sent-messages').hide();
                }
            };
            node += '</ul>';
            $('.sent-inner').append( $(node) );
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
        }


        function _makeupInboxMessages(json) {
            var node = '<ul>'
            for (var i = 0; i < json.privateMessages.length; i++) {
                var name = json.privateMessages[i].sentUser.name + ' ' + json.privateMessages[i].sentUser.surname;
                node += '<li id="'+ json.privateMessages[i].messageId +'" class="message ' + json.privateMessages[i].read + '">';
                node += '<p'+json.privateMessages[i].receiverUser.id + '" > From <span class="name">' + name + '</span></p>';
                node += '<div class="message-container"></div>';
                node += '</li>';
                if ( json.privateMessages.length < 10 ) {
                    $('.more-inbox-messages').hide();
                }
            };
            node += '</ul>';
            $('.inbox-inner').append( $(node) );
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
        };


        _getSentMessages();

        _getInboxMessages();

        $('.more-sent-messages').click(function(){
            _getSentMessages();
        });

        $('.more-inbox-messages').click(function(){
            _getInboxMessages();
        });
    }






    function setupColleaguesPage() {

        logger.log('Setup Colleagues');


        function _loadColleags() {
            $.getJSON('/sn/workers/getWorkers' + workersPage, {}, function (json) {
                _makeupColleags(json);
            });
            workersPage++;
        };


        function _makeupColleags(json) {
            var list = '<ul>';
            for (var i = 0; i < json.users.length; i++) {
                list += '<li class="user-entry clearfix">';
                list += '<div class="post-photo">';
                list += '<a href="/sn/user'+ json.users[i].id +'">';
                list += '<img src="/sn/user' + json.users[i].id + '/getAvatar" >';
                list += '</a>';
                list += '</div>';
                list += '<div class="textual">';
                list += '<div class="user-name"><a href="/sn/user' + json.users[i].id + '"><span></span>' + json.users[i].name + ' ' + json.users[i].surname + '</a></div>';
                list += '<div class="user-position"><span>Position </span>' + json.users[i].position + '</div>';
                list += '</li>';
                if( json.users.length < 10 ) {
                    $('.more-workers').hide();
                }
            };
            list += '</ul>';
            $('#listOfUsers').append($(list));
        };

        _loadColleags();


        $('.more-workers').click(function(){
            _loadColleags();
        });
    };


    function setupNewsPage() {
        logger.log('Setup News');

        function _getNews(){
            $.getJSON('/sn/news/news' + newsPage, {}, function (json) {
                _makeupNews(json);
            });
            newsPage++;
        };

        function _makeupNews(json){
            if (json.posts != null ) {
                for (var i = 0; i < json.posts.length; i++) {
                    var node = '<div class="news" id="' + json.posts[i].postId + '">';
                    node += '<div class="news-message">';
                    // node += '<p class="news-name"><a href="">' + fullname + '</a></p>';
                    // node += '<span class="remove-post"></span>';
                    node += '<p>' + json.posts[i].post + '</p>';
                    node += '<span class="post-meta">' + formatDate(json.posts[i].time) + '</span>';
                    node += '</div>';
                    node += '</div>';
                    $('.news-inner').append(node);
                    if( json.posts.length < 10 ) {
                        $('.more-news').hide();
                    }
                };
            }
        };

        _getNews();

        $('.more-news').click(function(){
            _getNews();
        });

    };




    function setupFollowingsPage() {
        logger.log('Setup Followings');


        function _loadFollowings() {
           $.getJSON('/sn/followings/getFollowings' + followersPage, {}, function (json) {
                _makeupFollowings(json);
           });
           followersPage++;
        };


        function _makeupFollowings(json) {
            var list = '<ul>';
            for (var i = 0; i < json.followingUsers.length; i++) {
                list += '<li class="user-entry clearfix">';
                list += '<div class="post-photo">';
                list += '<a href="/sn/user'+ json.followingUsers[i].id +'">';
                list += '<img src="/sn/user' + json.followingUsers[i].id + '/getAvatar" >';
                list += '</a>';
                list += '</div>';
                list += '<div class="textual">';
                list += '<div class="user-name"><a href="/sn/user' + json.followingUsers[i].id + '"><span></span>' + json.followingUsers[i].name + ' ' + json.followingUsers[i].surname + '</a></div>';
                list += '<div class="user-position"><span>Position </span>' + json.followingUsers[i].position + '</div>';
                list += '</li>';
                if( json.followingUsers.length < 10 ) {
                    $('.more-followings').hide();
                }
            };
            list += '</ul>';
            $('#listOfUsers').append($(list));
        }

        _loadFollowings();

        $('.more-followings').click(function(){
            _loadFollowings();
        });
    };



    function setupGroupListPage() {
        logger.log('Setup GroupList');


        function _getGroupList() {
            $.get('/sn/groups/' + groupListPage, {}, function (json) {
                _makeupGroupList(json);
            });
            groupListPage++;
        };


        function _makeupGroupList(json) {
            var list = '<ul>';
            for (var i = 0; i < json.groups.length; i++) {
                list += '<li class="group-entry clearfix">';
                list += '<div class="textual">';
                list += '<div class="group-name"><a href="/sn/group' + json.groups[i].groupId + '"><span></span>' + json.groups[i].name + '</a></div>';
                list += '<div class="group-description"><span></span>' + json.groups[i].description + '</div>';
                list += '<div class="group-created-by"><span>Created by </span> <a href="/sn/user'+json.groups[i].author.id+'">' + json.groups[i].author.name + ' ' + json.groups[i].author.surname + '</div>';
                list += '</li>';
                if( json.groups.length < 10 ) {
                    $('.more-group-list').hide();
                }
            };
            list += '</ul>';
            $('.group-inner').append($(list));
        };

        _getGroupList();

        $('.more-group-list').click(function(){
            _getGroupList();
        });


        $('.create-group').click(function(event){
            event.preventDefault();
            var thisEl = $(this);
            var id = getUserId();
            var name = thisEl.parent().find('#group-name');
            var descr = thisEl.parent().find('textarea');
            name.removeClass('input-error');
            descr.removeClass('input-error');
            if (name.val() == '') {
                name.addClass('input-error');
                return;
            }
            if (descr.val() == '') {
                descr.addClass('input-error');
                return;
            }

            createGroup(name.val(), descr.val(), id);
            name.val('');
            descr.val('');
        });
    };



    function setupGroupPage() {
        var haveMorePages = true;
        var page = 0;
        logger.log('Setup Group');

        $.get('/sn/group'+ getGroupId() +'/description', {}, function (json) {
            logger.log(json);
            if (json != 'undefined') {
                if (json.name != 'undefined') {
                    $('#group-name').text(json.name);
                }
                if (json.description != 'undefined') {
                    $('#group-description').text(json.description);
                }
                if( json.author != 'undefined' ) {
                    $('#created-by span').html('<a href="/sn/user'+json.author.id+'"">' + json.author.name + ' ' + json.author.surname +'</a>');
                }
            }
        });


        $.get('/sn/group'+ getGroupId() +'/members', {}, function (json) {
            $('.members-quantity span').text( json.members );
        });


        $('.subcsribe-group').text( nameSubscriber() );



        function _loadGroupPosts() {
            $.getJSON('/sn/group' + getGroupId() + '/posts' + page, {}, function (json) {
                _makeupGroupPosts(json);
            });
            page++;
        };


        function _makeupGroupPosts(json) {
            logger.log(json);
            for (var i = 0; i < json.groupPosts.length; i++) {
                // if()
                var node = '<div class="post clearfix" id="' + json.groupPosts[i].groupPostId + '">';
                node += '<div class="post-photo">';
                node += '<a href="/sn/user'+ json.groupPosts[i].user.id +'">';
                node += '<img class="userPic" src="/sn/user'+json.groupPosts[i].user.id+'/getAvatar">';
                node += '</a>';
                node += '</div>';
                node += '<div class="post-message">';
                node += '<p class="post-name"><a href="/sn/user'+ json.groupPosts[i].user.id +'">' + json.groupPosts[i].user.name + ' ' + json.groupPosts[i].user.surname + '</a></p>';
                node += '<span class="remove-post"></span>';
                node += '<p>' + json.groupPosts[i].post + '</p>';
                node += '<span class="post-meta">' + formatDate(json.groupPosts[i].time) + '</span>';
                node += '</div>';
                node += '</div>';
                $('#group-posts-wrap').append(node);
                

            };
            $('.remove-post').on('click', function () {
                var id = $(this).parents('.post').attr('id');
                removePost(id);
            });
        };

        _loadGroupPosts();

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
            case 'groups':
                logger.log('Groups-page detected');
                setupGroupListPage();
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

// 063 148 71 22



