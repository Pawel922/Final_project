package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserService;

@Controller
@RequestMapping(value = "/user", produces = "text/plain; charset=utf-8")
public class UserController {

    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String displayFormToRegisterNewUser(Model model) {
        model.addAttribute("user", new User());
        return "user-register";
    }

    @PostMapping("/register")
    public String processFormToRegisterNewUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String displayLoginPage() {
        return "user-login";
    }
}
