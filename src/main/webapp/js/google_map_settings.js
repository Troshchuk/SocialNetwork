function initialize() {
	var mapCanvas = document.getElementById('map_canvas');
	var mapOptions = {
		center: new google.maps.LatLng(59.214479, 10.941258),
		zoom: 16,
		scrollwheel: false,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	}
	var map = new google.maps.Map(mapCanvas, mapOptions);
}
google.maps.event.addDomListener(window, 'load', initialize);