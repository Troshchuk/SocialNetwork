jQuery(function( $ ) {

	$('<img class="ie-img" src="assets/images/bg.jpg" alt="">').appendTo( $('body') );

	$('div.employee:nth-child(2n+1)').addClass('ie-2n');
	$('div.output:nth-child(3n)').addClass('ie-3n');
	$('div.category:nth-child(4n)').addClass('ie-4n');

	$('input, textarea').placeholder();
});