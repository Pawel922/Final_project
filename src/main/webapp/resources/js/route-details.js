document.addEventListener('DOMContentLoaded', function () {

    var map;
    var marker;
    var startPlaces = document.querySelectorAll(".start-place");

    function displayMarkers() {
        for(var i = 0; i < startPlaces.length; i++) {
            var index = ["1", "2", "3", "4", "5", "6"];
            var placeLat = parseFloat(startPlaces[i].getAttribute("data-lat"));
            var placeLng = parseFloat(startPlaces[i].getAttribute("data-lng"));
            var LatLng = {lat: placeLat, lng: placeLng };

            if(i === 0) {
                map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 10,
                    center: LatLng
                });
                setMarker(LatLng, index[i])
            } else {
                setMarker(LatLng, index[i])
            }
        }
    }

    function setMarker(location, ordinalNum) {
        marker = new google.maps.Marker({
            position: location,
            map: map,
            label: ordinalNum
        });
    }

    displayMarkers();

});