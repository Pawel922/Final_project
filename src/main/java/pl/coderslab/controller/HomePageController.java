package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import pl.coderslab.entity.DeliveryPlan;
import pl.coderslab.entity.Route;
import pl.coderslab.service.OptimalRouteFinder;
import pl.coderslab.service.SingleRoadManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(value = "/home", produces = "text/plain; charset=utf-8")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@SessionAttributes("deliveryPlan")
public class HomePageController {

    private SingleRoadManager singleRoadManager;
    private OptimalRouteFinder optimalRouteFinder;

    @Autowired
    public void setSingleRoadManager(SingleRoadManager singleRoadManager) {
        this.singleRoadManager = singleRoadManager;
    }

    @Autowired
    public void setOptimalRouteFinder(OptimalRouteFinder optimalRouteFinder) {
        this.optimalRouteFinder = optimalRouteFinder;
    }

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
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            deliveryPlan.setDeliveryDate(formatter.format(date));
            model.addAttribute("deliveryPlan", deliveryPlan);
            return "trial-form";
        } else {
            model.addAttribute("deliveryPlan", session.getAttribute("deliveryPlan"));
            return "trial-form";
        }
    }

    @PostMapping("/trial")
    public String processTrialForm(Model model,
                                   @Valid DeliveryPlan deliveryPlan,
                                   BindingResult result) {
        if(!result.hasErrors()) {
            Route route = RouteController.processTrialCalculations(deliveryPlan, singleRoadManager, optimalRouteFinder);
            model.addAttribute("route", route);
            return "route-details";
        } else {
            return "trial-form";
        }
    }
}
