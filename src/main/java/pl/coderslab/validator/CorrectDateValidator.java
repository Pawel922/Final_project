package pl.coderslab.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CorrectDateValidator implements ConstraintValidator<CorrectDate, String> {
    @Override
    public void initialize(CorrectDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(String textDate, ConstraintValidatorContext context) {
        boolean isCorrect = true;
        Pattern compiledPattern = Pattern.compile("[2][0-9]{3}-([0][1-9]|[1][0-2])-([0][1-9]|[1-2][0-9]|[3][0-1])");
        Matcher matcher = compiledPattern.matcher(textDate);
        if(matcher.matches()) {
            String[] tempArray = textDate.split("-");
            int year = Integer.parseInt(tempArray[0]);
            int month = Integer.parseInt(tempArray[1]);
            int day = Integer.parseInt(tempArray[2]);
            try {
                LocalDate date = LocalDate.of(year, month, day);
                if(date.isBefore(LocalDate.now())) {
                    isCorrect = false;
                }
            } catch (DateTimeException e) {
                isCorrect = false;
            }
        } else {
            isCorrect = false;
        }
        return isCorrect;
    }
}
