package rashjz.info.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import rashjz.info.domain.Customer;

//@Service
@Transactional
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {


//    public List<Customer> getAllCustomers(String lastName);

//    public List<Customer> lazyloadCustomers(Pageable pageable);
}
