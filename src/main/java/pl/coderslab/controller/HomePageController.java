package pl.coderslab.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import pl.coderslab.entity.DeliveryPlan;
import pl.coderslab.entity.Place;
import pl.coderslab.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/home", produces = "text/plain; charset=utf-8")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@SessionAttributes("deliveryPlan")
public class HomePageController {

    private final static char[] CHAR_TRIAL_TABLE = {'A','B','C'};

    @GetMapping("")
    public String displayHomePage() {
        return "home";
    }

    @GetMapping("/about")
    public String displayAboutUsPage() {
        return "about";
    }

    @GetMapping("/contact")
    public String displayContactPage() {
        return "contact";
    }

    @GetMapping("/trial")
    public String displayTrialForm(Model model,
                                   HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("deliveryPlan") == null) {
            DeliveryPlan deliveryPlan = DeliveryPlanController.prepareNewDeliveryPlan(CHAR_TRIAL_TABLE);
            model.addAttribute("deliveryPlan", deliveryPlan);
            return "trial-form";
        } else {
            model.addAttribute("deliveryPlan", session.getAttribute("deliveryPlan"));
            return "trial-form";
        }
    }
}
