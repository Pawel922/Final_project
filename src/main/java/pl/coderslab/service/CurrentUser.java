package pl.coderslab.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import pl.coderslab.entity.DeliveryPlan;
import pl.coderslab.repository.DeliveryPlanRepository;

import java.util.Collection;

public class CurrentUser extends User {

    private final pl.coderslab.entity.User user;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.entity.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public pl.coderslab.entity.User getUser() {return user;}

    public static boolean checkIfBelongToLoggedUser(CurrentUser currentUser,
                                             DeliveryPlanRepository deliveryPlanRepository,
                                             long deliveryPlanId) {
        DeliveryPlan deliveryPlan = deliveryPlanRepository.findById(deliveryPlanId);
        return currentUser.getUser().getId().equals(deliveryPlan.getId());
    }
}
