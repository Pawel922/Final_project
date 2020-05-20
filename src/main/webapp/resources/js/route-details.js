document.addEventListener('DOMContentLoaded', function () {

    var map;
    var marker;

    function initMap() {
        var myLatLng = {lat: 51.884, lng: 18.223};

        map = new google.maps.Map(document.getElementById('map'), {
            zoom: 6.5,
            center: myLatLng
        });

        marker = new google.maps.Marker({
            position: myLatLng,
            map: map,
            title: 'Hello World!'
        });
    }

    initMap();

});