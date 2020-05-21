package pl.coderslab.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Component;

@Component
public class CoordinatesManager {

    private static final String API_KEY = "";

    public static LatLng getCoordinates(String address) {
        LatLng result = new LatLng(0,0);
        GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();

        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
            result = results[0].geometry.location;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
