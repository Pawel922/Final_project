document.addEventListener('DOMContentLoaded', function (listener) {

    var autocomplete;

    function initAutocomplete() {
        var searchField = document.querySelector('#start');
        var defaultBounds = new google.maps.LatLngBounds(
            new google.maps.LatLng(-33.8902, 151.1759),
            new google.maps.LatLng(-33.8474, 151.2631));
        var options = {
            bounds: defaultBounds,
            types: ['address'],
            componentRestrictions: {country: 'pl'}
        };
        autocomplete = new google.maps.places.Autocomplete(searchField, options);
        autocomplete.setFields(['geometry']);
        autocomplete.addListener('place_changed',getCoordinates);
    }

    function getCoordinates() {
        var place = autocomplete.getPlace();
        var lat = place.geometry.location.lat();
        var lng = place.geometry.location.lng();
        document.querySelector("#longitude").value = lat;
        document.querySelector("#latitude").value = lng;
        console.log(lat);
        console.log(lng);
    }

    initAutocomplete();

    // var testButton = document.getElementById("testButton");
    // testButton.addEventListener("click", sendResponse);
    //
    // function sendResponse() {
    //     var xhttp = new XMLHttpRequest();
    //     xhttp.onreadystatechange = function() {
    //         if (this.readyState === 4 && this.status === 200) {
    //             document.getElementById("demo").innerHTML = this.responseText;
    //         }
    //     };
    //     xhttp.open("GET", "http://localhost:8080/home", true);
    //     xhttp.send();
    //     console.log(xhttp);
    // }
});