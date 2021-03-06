package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.SingleRoad;

@Repository
public interface SingleRoadRepository extends JpaRepository<SingleRoad, Long> {
}
