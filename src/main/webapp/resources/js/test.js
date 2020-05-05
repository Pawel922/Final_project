document.addEventListener('DOMContentLoaded', function (listener) {

    var autocomplete, map, marker;


    function initAutocomplete() {
        var searchField = document.querySelector('#city');
        var defaultBounds = new google.maps.LatLngBounds(
            new google.maps.LatLng(-33.8902, 151.1759),
            new google.maps.LatLng(-33.8474, 151.2631));
        var options = {
            bounds: defaultBounds,
            types: ['address'],
            componentRestrictions: {country: 'pl'}
        };
        autocomplete = new google.maps.places.Autocomplete(searchField, options);
        autocomplete.setFields(['geometry', 'formatted_address']);
        autocomplete.addListener('place_changed',showOnTheMap);
        resetForm();
    }

    function showOnTheMap() {
        var place = autocomplete.getPlace();
        resetForm();
        if (place.geometry) {
            fillInForm(place);
            var lat = place.geometry.location.lat();
            var lng = place.geometry.location.lng();
            var LatLng = {lat, lng};
            map = new google.maps.Map(document.getElementById('map'), {
                zoom: 15,
                center: LatLng
            });
            marker = new google.maps.Marker({
                position: LatLng,
                map: map,
            });
        } else {
            document.querySelector('#city').placeholder = 'Enter a address';
        }
    }

    function initMap() {
        var myLatLng = {lat: 52.0460513, lng: 18.6635953};
        map = new google.maps.Map(document.querySelector('#map'), {
            zoom: 6.5,
            center: myLatLng
        });
        marker = new google.maps.Marker({
            position: myLatLng,
            map: map,
        });
        resetForm();
    }

    function fillInForm(place) {
        var splitAddress = place.formatted_address.split(", ");
        var cityName = splitAddress[1].split(" ")[1];
        var streetName = splitAddress[0].split(" ")[0];
        var houseNumber = splitAddress[0].split(" ")[1];
        document.querySelector("#city_name").value = cityName;
        document.querySelector("#street_name").value = streetName;
        document.querySelector("#house_number").value = typeof houseNumber === "undefined" ? "" : houseNumber;
    }

    function resetForm() {
        document.querySelector("#city_name").value = "";
        document.querySelector("#street_name").value = "";
        document.querySelector("#house_number").value = "";
    }

    initAutocomplete();
    initMap();

});