package pl.coderslab.service;

import org.springframework.stereotype.Component;
import pl.coderslab.entity.DeliveryPlan;
import pl.coderslab.entity.Place;
import pl.coderslab.entity.SingleRoad;

import java.util.ArrayList;
import java.util.List;

@Component
public class SingleRoadManager {

    public List<SingleRoad> prepareRoadObjects(DeliveryPlan deliveryPlan) {
        List<SingleRoad> resultList = new ArrayList<>();
        List<Place> placeList = deliveryPlan.getPlaces();
        double[][] distances = new double[placeList.size()][placeList.size()];

        try {
            distances = DistanceMatrixGenerator.getDistanceMatrix(placeList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < distances.length; i++) {
            for(int j = 0; j < distances[i].length; j++) {
                SingleRoad singleRoad = new SingleRoad();
                singleRoad.setStartPlace(placeList.get(i));
                singleRoad.setEndPlace(placeList.get(j));
                singleRoad.setDistance(distances[i][j]);
                resultList.add(singleRoad);
            }
        }
        return resultList;
    }
}
