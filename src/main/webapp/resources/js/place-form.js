document.addEventListener('DOMContentLoaded', function () {

    var map;
    var marker;

    function initMap() {
        var myLatLng = {lat: -25.363, lng: 131.044};

        map = new google.maps.Map(document.getElementById('map'), {
            zoom: 4,
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