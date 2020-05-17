package pl.coderslab.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isUnique = true;
        Pattern compiledPattern = Pattern.compile("[a-zA-Z0-9]{4,15}");
        Matcher matcher = compiledPattern.matcher(value);
        if(matcher.matches()) {
            Optional<User> optionalUser = userRepository.getByUsername(value);
            if(optionalUser.isPresent()) {
                isUnique = false;
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate( "username already exists" )
                        .addConstraintViolation();
            }
        } else {
            isUnique = false;
        }
        return isUnique;
    }
}
