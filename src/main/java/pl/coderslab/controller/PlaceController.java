package pl.coderslab.controller;

import com.google.maps.model.LatLng;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.DeliveryPlan;
import pl.coderslab.entity.Place;
import pl.coderslab.repository.PlaceRepository;
import pl.coderslab.service.CoordinatesManager;
import pl.coderslab.service.CurrentUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/place", produces = "text/plain; charset=utf-8")
public class PlaceController {

    private final PlaceRepository placeRepository;

    public PlaceController(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @GetMapping("/add/{charRep}")
    public String displayForm(Model model,
                              @SessionAttribute DeliveryPlan deliveryPlan,
                              @PathVariable char charRep) {
        Place placeToEdit = deliveryPlan.getPlaces().stream()
                .filter(place -> place.getCharRepresentation() == charRep)
                .findFirst()
                .get();
        model.addAttribute("place", placeToEdit);
        return "place-form";
    }

    @PostMapping("/add/{charRep}")
    public String processForm(@Valid Place place,
                              BindingResult result,
                              @SessionAttribute DeliveryPlan deliveryPlan,
                              @PathVariable char charRep,
                              @AuthenticationPrincipal CurrentUser currentUser) {
        if(!result.hasErrors()) {
            Place placeToEdit = deliveryPlan.getPlaces().stream()
                    .filter(p -> p.getCharRepresentation() == charRep)
                    .findFirst()
                    .get();
            placeToEdit.setCity(place.getCity());
            placeToEdit.setStreet(place.getStreet());
            placeToEdit.setHouseNumber(place.getHouseNumber());
            LatLng coordinates = CoordinatesManager.getCoordinates(placeToEdit.getShortcut());
            placeToEdit.setLat(coordinates.lat);
            placeToEdit.setLng(coordinates.lng);
            if(currentUser != null) {
                return "redirect:/delivery/add";
            } else {
                return "redirect:/home/trial";
            }
        } else {
            return "place-form";
        }
    }

    @GetMapping("/edit/{placeId}")
    public String displayEditForm(Model model,
                                  @PathVariable long placeId,
                                  DeliveryPlan planToEdit) {
        Place placeToEdit = placeRepository.findById(placeId);
        model.addAttribute("placeToEdit", placeToEdit);
        return "place-edit";
    }

    @PostMapping("/edit/{placeId}")
    public String processEditForm(HttpServletRequest request,
                                  @PathVariable long placeId,
                                  @Valid @ModelAttribute("placeToEdit") Place placeToEdit,
                                  BindingResult result) {
        HttpSession session = request.getSession();
        if(!result.hasErrors()) {
            Place placeToUpdate = placeRepository.findById(placeId);
            placeToUpdate.setCity(placeToEdit.getCity());
            placeToUpdate.setStreet(placeToEdit.getStreet());
            placeToUpdate.setHouseNumber(placeToEdit.getHouseNumber());
            placeToUpdate.setCharRepresentation(placeToEdit.getCharRepresentation());
            placeRepository.save(placeToUpdate);
            DeliveryPlan planToEdit = (DeliveryPlan) session.getAttribute("planToEdit");
            return "redirect:/delivery/details/" + planToEdit.getId();
        }
        return "place-edit";
    }
}
