package pl.coderslab.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private char charRepresentation;

    @Pattern(regexp = "([A-ZĄĘŁŃÓŚŹŻ][a-ząęłńóśćżź]+)\\s?([A-ZĄĘŁŃÓŚŹŻ][a-ząęłńóśćżź]+)?",
            message = "must start with a big letter and contain only letters")
    private String city;

    @Pattern(regexp = "([A-ZĄĘŁŃÓŚŹŻ][a-ząęłńóśćżź]+)\\s?([A-ZĄĘŁŃÓŚŹŻ][a-ząęłńóśćżź]+)?",
            message = "must start with a big letter and contain only letters")
    private String street;

    @Pattern(regexp = "[1-9][0-9]*[a-zA-Z]?",
            message = "invalid value")
    private String houseNumber;

    private double lat;

    private double lng;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public char getCharRepresentation() {
        return charRepresentation;
    }

    public void setCharRepresentation(char charRepresentation) {
        this.charRepresentation = charRepresentation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getShortcut() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(street)
                .append(houseNumber == null ? " " : " " + houseNumber)
                .append(", ")
                .append(city);
        return stringBuilder.toString();
    }

}
