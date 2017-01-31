package rashjz.info.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import rashjz.info.domain.Customer;

import java.util.List;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    public List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}
