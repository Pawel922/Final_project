package pl.coderslab.entity;

import pl.coderslab.validator.CorrectDate;
import pl.coderslab.validator.CorrectPlaces;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "delivery_plans")
public class DeliveryPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CorrectDate(message = "wrong date format")
    private String deliveryDate;

    @ManyToMany
    @CorrectPlaces(message = "wrong input data")
    @JoinTable(name = "plan_details",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id"))
    private List<Place> places = new ArrayList<>();

    private boolean calculationRequiredFlag = true;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public void addPlace(Place place) {
        if(place != null) {
            places.add(place);
        }
    }

    public Place getPlace(int index) {
        return places.get(index);
    }

    public boolean isCalculationRequiredFlag() {
        return calculationRequiredFlag;
    }

    public void setCalculationRequiredFlag(boolean calculationRequiredFlag) {
        this.calculationRequiredFlag = calculationRequiredFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryPlan that = (DeliveryPlan) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
