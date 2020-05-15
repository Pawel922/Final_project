package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.DeliveryPlan;

import java.util.List;


@Repository
public interface DeliveryPlanRepository extends JpaRepository<DeliveryPlan, Long> {

    @Query("SELECT db FROM DeliveryPlan db WHERE db.owner.username = ?1 ORDER BY db.deliveryDate")
    List<DeliveryPlan> findAllOrderBelongToUser(String username);

    @Query("SELECT db FROM DeliveryPlan db JOIN FETCH db.places WHERE db.id = ?1")
    DeliveryPlan findById(long id);

}
