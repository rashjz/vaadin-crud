package rashjz.info;

import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import rashjz.info.jpa.CustomerRepository;
import rashjz.info.jpa.UsersRepository;

/**
 * Created by Mobby on 1/31/2017.
 */
@SpringBootApplication
@ComponentScan(basePackages = "rashjz.info")
@EnableSpringConfigured
@EnableJpaRepositories("rashjz.info.jpa")
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }


    @Bean
    public CommandLineRunner loadData(CustomerRepository repository, UsersRepository usersRepository) {
        return (args) -> {
            log.info(usersRepository.findByUsername("rashjz").getId() + " ********************************");
            log.info(usersRepository.findByUsernameAndPassword("rashjz","12").getId() + " ********************************");

//            // save a couple of customers
//            repository.save(new Customer("1", "Oruc", "USD", new Date()));
//            repository.save(new Customer("2", "Abdulla", "GBP", new Date()));
//            repository.save(new Customer("3", "Mamed", "USD", new Date()));
//            repository.save(new Customer("4", "Ekber", "EUR", new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01")));
//            repository.save(new Customer("5", "Ahmed", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("6", "Javad", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("7", "Hasanli", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("8", "Hasanli", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("9", "Hasanli", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("10", "Abdulla", "GBP", new Date()));
//            repository.save(new Customer("11", "Mamed", "USD", new Date()));
//            repository.save(new Customer("12", "Ekber", "EUR", new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01")));
//            repository.save(new Customer("13", "Ahmed", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("14", "Javad", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("15", "Hasanli", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("16", "Hasanli", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("17", "Hasanli", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("18", "Abdulla", "GBP", new Date()));
//            repository.save(new Customer("19", "Mamed", "USD", new Date()));
//            repository.save(new Customer("20", "Ekber", "EUR", new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01")));
//            repository.save(new Customer("21", "Ahmed", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("22", "Javad", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("23", "Hasanli", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("24", "Hasanli", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("25", "Hasanli", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("26", "Abdulla", "GBP", new Date()));
//            repository.save(new Customer("27", "Mamed", "USD", new Date()));
//            repository.save(new Customer("28", "Ekber", "EUR", new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01")));
//            repository.save(new Customer("29", "Ahmed", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("30", "Javad", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("31", "Hasanli", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("32", "Hasanli", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("33", "Hasanli", "USD", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
//            repository.save(new Customer("34", "Abdulla", "GBP", new Date()));
//            repository.save(new Customer("35", "Mamed", "USD", new Date()));
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            for (Customer customer : repository.findAll()) {
//                log.info(customer.toString());
//            }
//            log.info("");
//
//            // fetch an individual customer by ID
//            Customer customer = repository.findOne(1L);
//            log.info("Customer found with findOne(1L):");
//            log.info("--------------------------------");
//            log.info(customer.toString());
//            log.info("");
//
//            // fetch customers by last name
//            log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
//            log.info("--------------------------------------------");
////           List<Customer>  list= repository.findAll(new PageRequest(1, 25)).getContent();
////            System.out.println("list sizeeeeeeeeeeeeee "+list.size());
////            for (Customer c : list) {
////                log.info(c.toString());
//            }
//            log.info("");
        };
    }

}
