package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    Place findById(long id);
}
