package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Route;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    Optional<Route> findByDeliveryPlanId(long id);

    @Query("SELECT r FROM Route r JOIN FETCH r.roads WHERE r.deliveryPlan.id = ?1")
    Optional<Route> getByDeliveryPlanId(long id);
}
