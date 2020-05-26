package pl.coderslab.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserService;

@Component
public class UserValidator implements Validator {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "RegisterForm.empty");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 20) {
            errors.rejectValue("username", "RegisterForm.Username.size");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "RegisterForm.Username.notUnique");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "RegisterForm.empty");
        if (user.getPassword().length() < 5 || user.getPassword().length() > 8) {
            errors.rejectValue("password", "RegisterForm.Password.size");
        }
    }
}
