/**
 * Created by troshchuk on 23.07.14.
 */
jQuery(function( $ ) {
    $('#user-exit').click(function() {
            $.post('/sn/user/exit',{},function(server_json){
                location.reload();
            },'json')
    });
});