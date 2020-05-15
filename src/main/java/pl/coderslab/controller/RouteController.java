package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.DeliveryPlan;
import pl.coderslab.entity.Route;
import pl.coderslab.entity.SingleRoad;
import pl.coderslab.repository.DeliveryPlanRepository;
import pl.coderslab.repository.RouteRepository;
import pl.coderslab.repository.SingleRoadRepository;
import pl.coderslab.service.OptimalRouteFinder;
import pl.coderslab.service.SingleRoadManager;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/route", produces = "text/plain; charset=utf-8")
public class RouteController {

    private final DeliveryPlanRepository deliveryPlanRepository;
    private final RouteRepository routeRepository;
    private final SingleRoadRepository singleRoadRepository;

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


    public RouteController(DeliveryPlanRepository deliveryPlanRepository,
                           RouteRepository routeRepository,
                           SingleRoadRepository singleRoadRepository) {
        this.deliveryPlanRepository = deliveryPlanRepository;
        this.routeRepository = routeRepository;
        this.singleRoadRepository = singleRoadRepository;
    }

    @GetMapping("/find/{deliveryPlanId}")
    public String findOptimalRoute(@PathVariable long deliveryPlanId) {
        Optional<Route> optionalRoute = routeRepository.findByDeliveryPlanId(deliveryPlanId);
        DeliveryPlan deliveryPlan = deliveryPlanRepository.findById(deliveryPlanId);
        if(!optionalRoute.isPresent()) {
            processCalculations(deliveryPlan);
        } else if (deliveryPlan.isCalculationRequiredFlag()) {
            prepareForNewCalculations(deliveryPlanId);
            processCalculations(deliveryPlan);
        }
        return "redirect:/route/details/" + deliveryPlanId;
    }

    @GetMapping("/details/{deliveryPlanId}")
    public String displayRouteDetails(Model model,
                                      @PathVariable long deliveryPlanId) {
        Optional<Route> optionalRoute = routeRepository.getByDeliveryPlanId(deliveryPlanId);
        optionalRoute.ifPresent(route -> model.addAttribute("route", route));
        return "route-details";
    }

    public void prepareForNewCalculations(long deliveryPlanId) {
        Optional<Route> optionalRoute = routeRepository.getByDeliveryPlanId(deliveryPlanId);
        optionalRoute.ifPresent(routeRepository::delete);
    }

    public void processCalculations(DeliveryPlan deliveryPlan) {
        List<SingleRoad> singleRoads = singleRoadManager.prepareRoadObjects(deliveryPlan);
        Route route = optimalRouteFinder.findRoute(singleRoads, false);
        route.setDeliveryPlan(deliveryPlan);
        route.getRoads().forEach(singleRoadRepository::save);
        routeRepository.save(route);
        deliveryPlan.setCalculationRequiredFlag(false);
        deliveryPlanRepository.save(deliveryPlan);
    }

    public static Route processTrialCalculations(DeliveryPlan deliveryPlan,
                                                 SingleRoadManager singleRoadManager,
                                                 OptimalRouteFinder optimalRouteFinder) {
        List<SingleRoad> singleRoads = singleRoadManager.prepareRoadObjects(deliveryPlan);
        Route route = optimalRouteFinder.findRoute(singleRoads, true);
        return route;
    }
}
