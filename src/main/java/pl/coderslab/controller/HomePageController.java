package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home", produces = "text/plain; charset=utf-8")
public class HomePageController {

    @GetMapping("")
    public String displayHomePage() {
        return "home";
    }
}
