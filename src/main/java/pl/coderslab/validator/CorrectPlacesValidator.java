package pl.coderslab.validator;

import pl.coderslab.entity.Place;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CorrectPlacesValidator implements ConstraintValidator<CorrectPlaces, List<Place>> {

    @Override
    public void initialize(CorrectPlaces constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<Place> places, ConstraintValidatorContext context) {
        for(Place place : places) {
            if(place.getShortcut().equals("--- , ---")){
                return false;
            }
        }
        return true;
    }
}
