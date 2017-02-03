package rashjz.info;

import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import rashjz.info.domain.Customer;
import rashjz.info.jpa.CustomerRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mobby on 1/31/2017.
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = Application.class)
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }


    @Bean
    public CommandLineRunner loadData(CustomerRepository repository) {
        return (args) -> {
            log.info(repository + " ********************************");
            // save a couple of customers
            repository.save(new Customer("Elshad", "Oruc", "USD", new Date()));
            repository.save(new Customer("Elchin", "Abdulla", "GBP", new Date()));
            repository.save(new Customer("Farid", "Mamed", "USD", new Date()));
            repository.save(new Customer("Azer", "Ekber", "EUR", new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01")));
            repository.save(new Customer("Mamed", "Ahmed", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
            repository.save(new Customer("Rashad", "Javad", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
            repository.save(new Customer("Vusal", "Hasanli", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
            log.info("--------------------------------------------");
            for (Customer bauer : repository
                    .findByLastNameStartsWithIgnoreCase("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }
}
