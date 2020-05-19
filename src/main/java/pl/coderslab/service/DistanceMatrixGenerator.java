package pl.coderslab.service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.Place;

import java.io.IOException;
import java.util.List;

@Component
public class DistanceMatrixGenerator {

    private static final String API_KEY = "AIzaSyD6SFC8ZOB4hKovBt8uUeBkN-VIPUGlCso";


    public static DistanceMatrix getGoogleAPIResponse(List<Place> placesToVisit) throws ApiException, InterruptedException, IOException {
        String[] addresses = prepareStrArray(placesToVisit);
        GeoApiContext geoApiContext = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();

        DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(geoApiContext);
        DistanceMatrix result = request.origins(addresses)
                .destinations(addresses)
                .mode(TravelMode.DRIVING)
                .language("pl-PL")
                .units(Unit.METRIC)
                .await();
        return result;
    }

    public static double[][] getDistanceMatrix(DistanceMatrix matrix) {
        double[][] resultArray = new double[matrix.rows.length][matrix.rows.length];
        for(int i = 0; i < resultArray.length; i++) {
            for(int j = 0; j < resultArray[i].length; j++) {
                resultArray[i][j] = matrix.rows[i].elements[j].distance.inMeters;
            }
        }
        return resultArray;
    }

    public static String[][] getDurationMatrix(DistanceMatrix matrix) {
        String[][] resultArray = new String[matrix.rows.length][matrix.rows.length];
        for(int i = 0; i < resultArray.length; i++) {
            for(int j = 0; j < resultArray[i].length; j++) {
                resultArray[i][j] = matrix.rows[i].elements[j].duration.humanReadable;
            }
        }
        return resultArray;
    }

    private static String[] prepareStrArray(List<Place> placeList) {
        String[] resultArray = new String[placeList.size()];
        for(int i = 0; i < resultArray.length; i++) {
            resultArray[i] = placeList.get(i).getShortcut();
        }
        return resultArray;
    }
}
