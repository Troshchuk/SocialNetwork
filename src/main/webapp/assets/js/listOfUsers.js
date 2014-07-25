/**
 * Created by troshchuk on 20.07.14.
 */
jQuery(function( $ ) {
    $('listOfUsers').ready(function() {
        $.getJSON('/sn/workers/getWorkers0',function (json) {
            var list = '<div class="user-list">';
            list += '<ul>';
            for (var i = 0; i < json.users.length; i++) {
                list += '<li class="user-entry">';
                list += '<div class="textual">';
                list += '<div class="user-name"><span>Name: </span>' + json.users[i].name + '</div>';
                list += '<div class="user-surname"><span>Surname: </span>' + json.users[i].surname + '</div>';
                list += '<div class="user-position"><span>Position: </span>' + json.users[i].position + '</div>';
                list += '</li>';
            }
            list += '</ul>';
            list += '</div>';
            var listOfUsers = document.getElementById('listOfUsers');
            listOfUsers.innerHTML = list;
        });
    });
});