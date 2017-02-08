package rashjz.info.jpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rashjz.info.domain.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

     //IgnoreCase makes uppercase
    List<Customer> findByFirstNameLike(String nameFilter, Pageable pageable);

    long countByFirstNameLike(String nameFilter);
}
