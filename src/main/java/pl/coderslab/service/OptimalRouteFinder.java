package pl.coderslab.service;

import org.springframework.stereotype.Component;
import pl.coderslab.entity.Route;
import pl.coderslab.entity.SingleRoad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OptimalRouteFinder {

    private final String TEMP_HELPFUL_STR = "ABCDEF";
    private final String START_LETTER = "A";

    public Route findRoute(List<SingleRoad> roads) {
        Set<String> permutationSet = generatePerm(TEMP_HELPFUL_STR).stream()
                .filter(s -> s.startsWith(START_LETTER))
                .collect(Collectors.toSet());
        List<Route> routes = new ArrayList<>();
        for(String str : permutationSet) {
            Route route = new Route();
            double totalDistance = 0;
            for(int i = 0; i < str.length() - 1; i++) {
                char prev = str.charAt(i);
                char next = str.charAt(i + 1);
                SingleRoad singleRoad = roads.stream()
                        .filter(sr -> sr.getStartPlace().getCharRepresentation() == prev && sr.getEndPlace().getCharRepresentation() == next)
                        .findFirst()
                        .get();
                double distance = singleRoad.getDistance();
                totalDistance = totalDistance + distance;
                route.addRoad(singleRoad);
            }
            route.setTotalDistance(totalDistance);
            routes.add(route);
        }

        double minDistance = routes.stream().mapToDouble(Route::getTotalDistance).min().getAsDouble();
        return routes.stream()
                .filter(route -> route.getTotalDistance() == minDistance)
                .findFirst()
                .get();
    }

    private static Set<String> generatePerm(String input) {
        Set<String> set = new HashSet<>();
        if (input == "") {
            return set;
        }
        Character a = input.charAt(0);
        if (input.length() > 1) {
            input = input.substring(1);
            Set<String> permSet = generatePerm(input);
            for (String x : permSet) {
                for (int i = 0; i <= x.length(); i++) {
                    set.add(x.substring(0, i) + a + x.substring(i));
                }
            }
        }
        else {
            set.add(a + "");
        }
        return set;
    }
}
