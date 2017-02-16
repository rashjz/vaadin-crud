package rashjz.info.jpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rashjz.info.domain.Accident;

import java.sql.Timestamp;
import java.util.List;

public interface AccidentRepository extends JpaRepository<Accident, Integer> {

     //IgnoreCase makes uppercase
    List<Accident> findByLocationLike(String location, Pageable pageable);

    long countByLocationLike(String location);
}
