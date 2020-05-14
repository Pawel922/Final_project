package pl.coderslab.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import pl.coderslab.entity.DeliveryPlan;
import pl.coderslab.entity.Place;
import pl.coderslab.entity.Route;
import pl.coderslab.entity.User;
import pl.coderslab.repository.*;
import pl.coderslab.service.CurrentUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/delivery", produces = "text/plain; charset=utf-8")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@SessionAttributes({"deliveryPlan", "planToEdit"})
public class DeliveryPlanController {

    private final static char[] CHAR_TABLE = {'A','B','C','D','E','F'};

    private final DeliveryPlanRepository deliveryPlanRepository;
    private final PlaceRepository placeRepository;
    private final RouteRepository routeRepository;
    private final SingleRoadRepository singleRoadRepository;
    private final UserRepository userRepository;

    public DeliveryPlanController(DeliveryPlanRepository deliveryPlanRepository,
                                  PlaceRepository placeRepository,
                                  RouteRepository routeRepository,
                                  SingleRoadRepository singleRoadRepository,
                                  UserRepository userRepository) {
        this.deliveryPlanRepository = deliveryPlanRepository;
        this.placeRepository = placeRepository;
        this.routeRepository = routeRepository;
        this.singleRoadRepository = singleRoadRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/add")
    public String displayForm(Model model,
                              HttpServletRequest request,
                              @AuthenticationPrincipal CurrentUser currentUser) {
        HttpSession session = request.getSession();
        if(session.getAttribute("deliveryPlan") == null) {
            DeliveryPlan deliveryPlan = new DeliveryPlan();
            for(char c : CHAR_TABLE) {
                Place place = new Place();
                place.setCity("---");
                place.setStreet("---");
                place.setCharRepresentation(c);
                deliveryPlan.addPlace(place);
            }
            User owner = userRepository.findByUsername(currentUser.getUsername());
            deliveryPlan.setOwner(owner);
            model.addAttribute("deliveryPlan", deliveryPlan);
            return "delivery-form";
        } else {
            model.addAttribute("deliveryPlan", session.getAttribute("deliveryPlan"));
            return "delivery-form";
        }
    }

    @PostMapping("/add")
    public String processForm(HttpServletRequest request,
                              @Valid DeliveryPlan deliveryPlan,
                              BindingResult result) {
        if(!result.hasErrors()) {
            HttpSession session = request.getSession();
            deliveryPlan.getPlaces()
                    .forEach(placeRepository::save);
            deliveryPlanRepository.save(deliveryPlan);
            session.removeAttribute("planDelivery");
            return "redirect:/delivery/list";
        } else {
            return "delivery-form";
        }
    }

    @GetMapping("/details/{deliveryPlanId}")
    public String displayEditForm(Model model,
                                  @PathVariable long deliveryPlanId) {
        DeliveryPlan deliveryPlan = deliveryPlanRepository.findById(deliveryPlanId);
        model.addAttribute("planToEdit", deliveryPlan);
        return "delivery-edit";
    }

    @PostMapping("/details/{deliveryPlanId}")
    public String processEditForm(HttpServletRequest request,
                                  @PathVariable long deliveryPlanId,
                                  @Valid @ModelAttribute("planToEdit") DeliveryPlan planToEdit,
                                  BindingResult result) {
        HttpSession session = request.getSession();
        if(!result.hasErrors()) {
            DeliveryPlan planToUpdate = deliveryPlanRepository.findById(deliveryPlanId);
            planToUpdate.setDeliveryDate(planToEdit.getDeliveryDate());
            planToUpdate.setPlaces(planToEdit.getPlaces());
            planToUpdate.setCalculationRequiredFlag(true);
            deliveryPlanRepository.save(planToUpdate);
            session.removeAttribute("planToEdit");
            return "redirect:/delivery/list";
        }
        return "delivery-edit";
    }

    @GetMapping("/list")
    public String displayAllDeliveryPlans(Model model) {
        List<DeliveryPlan> deliveryPlans = deliveryPlanRepository.findAllOrderByDeliveryDate();
        model.addAttribute("deliveryPlans", deliveryPlans);
        return "delivery-list";
    }

    @GetMapping("/delete/{deliveryPlanId}")
    public String removeDeliveryPlan(@PathVariable long deliveryPlanId) {
        DeliveryPlan deliveryPlan = deliveryPlanRepository.findById(deliveryPlanId);
        Route route = routeRepository.getByDeliveryPlanId(deliveryPlanId);
        routeRepository.delete(route);
        deliveryPlanRepository.delete(deliveryPlan);
        return "redirect:/delivery/list";
    }

}
