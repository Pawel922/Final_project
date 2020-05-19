package pl.coderslab.entity;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@Table(name = "roads")
public class SingleRoad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "start_place_id")
    private Place startPlace;

    @OneToOne
    @JoinColumn(name = "end_place_id")
    private Place endPlace;

    private double distance;

    private String duration;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Place getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(Place startPlace) {
        this.startPlace = startPlace;
    }

    public Place getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(Place endPlace) {
        this.endPlace = endPlace;
    }

    public double getDistance() {
        return distance;
    }

    public String roundDistance() {
        DecimalFormat df = new DecimalFormat("###.#");
        return df.format(distance/1000);
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "SingleRoad{" +
                "id=" + id +
                ", startPlace=" + startPlace +
                ", endPlace=" + endPlace +
                ", distance=" + distance +
                '}';
    }
}
