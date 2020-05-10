package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.User;

@Controller
@RequestMapping(value = "/user", produces = "text/plain; charset=utf-8")
public class UserController {

    @GetMapping("/register")
    public String displayFormToRegisterNewUser(Model model) {
        model.addAttribute("user", new User());
        return "user-register";
    }
}
