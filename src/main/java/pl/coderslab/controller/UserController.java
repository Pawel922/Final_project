package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserService;
import pl.coderslab.validator.UserValidator;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user", produces = "text/plain; charset=utf-8")
public class UserController {

    private final UserService userService;

    private final UserValidator userValidator;

    public UserController (UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/register")
    public String displayFormToRegisterNewUser(Model model) {
        model.addAttribute("user", new User());
        return "user-register";
    }

    @PostMapping("/register")
    public String processFormToRegisterNewUser(@ModelAttribute User user,
                                               BindingResult result) {
        userValidator.validate(user, result);
        if (!result.hasErrors()) {
            userService.saveUser(user);
            return "redirect:/user/login";
        } else {
            return "user-register";
        }
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model,
                                   String error,
                                   HttpSession session) {
        if (error != null) {
            model.addAttribute("error", "Your username or password is invalid");
        }
        if(session.getAttribute("deliveryPlan") != null) {
            session.removeAttribute("deliveryPlan");
        }
        return "user-login";
    }

}
