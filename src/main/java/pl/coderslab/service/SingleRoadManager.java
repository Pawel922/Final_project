package pl.coderslab.service;

import com.google.maps.model.DistanceMatrix;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.DeliveryPlan;
import pl.coderslab.entity.Place;
import pl.coderslab.entity.SingleRoad;

import java.util.ArrayList;
import java.util.List;

@Component
public class SingleRoadManager {

    private DistanceMatrix matrix;

    public List<SingleRoad> prepareRoadObjects(DeliveryPlan deliveryPlan) {
        List<SingleRoad> resultList = new ArrayList<>();
        List<Place> placeList = deliveryPlan.getPlaces();
        double[][] distances = new double[placeList.size()][placeList.size()];
        String[][] durations = new String[placeList.size()][placeList.size()];

        try {
            matrix = DistanceMatrixGenerator.getGoogleAPIResponse(placeList);
            distances = DistanceMatrixGenerator.getDistanceMatrix(matrix);
            durations = DistanceMatrixGenerator.getDurationMatrix(matrix);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < distances.length; i++) {
            for(int j = 0; j < distances[i].length; j++) {
                SingleRoad singleRoad = new SingleRoad();
                singleRoad.setStartPlace(placeList.get(i));
                singleRoad.setEndPlace(placeList.get(j));
                singleRoad.setDistance(distances[i][j]);
                singleRoad.setDuration(durations[i][j]);
                resultList.add(singleRoad);
            }
        }
        return resultList;
    }
}
