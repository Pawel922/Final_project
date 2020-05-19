package pl.coderslab.entity;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    @JoinTable(name = "routes_details",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "road_id"))
    private List<SingleRoad> roads = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_plan_id")
    private DeliveryPlan deliveryPlan;

    private double totalDistance;

    private String totalDuration;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SingleRoad> getRoads() {
        return roads;
    }

    public void setRoads(List<SingleRoad> roads) {
        this.roads = roads;
    }

    public void addRoad(SingleRoad singleRoad) {
        if(singleRoad != null) {
            roads.add(singleRoad);
        }
    }

    public DeliveryPlan getDeliveryPlan() {
        return deliveryPlan;
    }

    public void setDeliveryPlan(DeliveryPlan deliveryPlan) {
        this.deliveryPlan = deliveryPlan;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public String roundTotalDistance() {
        DecimalFormat df = new DecimalFormat("###.#");
        return df.format(totalDistance/1000);
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getTotalDuration() {
        String[] array = new String[this.roads.size()];
        for(int i = 0; i < array.length; i++) {
            array[i] = this.roads.get(i).getDuration();
        }
        int hours = 0;
        int min  = 0;

        for(String str : array) {
            String[] tempArray = str.split(" ");
            if(tempArray.length == 2) {
                min = min + Integer.parseInt(tempArray[0]);
                if(min >= 60) {
                    hours ++;
                    min = min - 60;
                }
            } else {
                hours = hours + Integer.parseInt(tempArray[0]);
                min = min + Integer.parseInt(tempArray[2]);
                if(min > 60) {
                    hours ++;
                    min = min - 60;
                }
            }
        }

        if(hours == 0) {
            return String.format("%d mins", min);
        } else if (hours == 1) {
            return String.format("%d hour %d mins", hours, min);
        } else {
            return String.format("%d hours %d mins", hours, min);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id == route.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
