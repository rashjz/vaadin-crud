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
import rashjz.info.domain.Accident;
import rashjz.info.domain.Citizen;
import rashjz.info.domain.Users;
import rashjz.info.domain.Vechile;
import rashjz.info.jpa.AccidentRepository;
import rashjz.info.jpa.UsersRepository;

import java.util.Date;
import java.util.HashSet;

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
    public CommandLineRunner loadData(AccidentRepository repository, UsersRepository usersRepository) {
        return (args) -> {
            Users users = new Users();
            users.setUsername("rashjz");
            users.setPassword("12");
//            usersRepository.save(users);

            Accident accident = new Accident();
            accident.setActionDate(new Date());
            accident.setInsertDate(new Date());
            accident.setLocation("test1");
            accident.setNote("note");
            //create citizen
            Citizen citizen = new Citizen();
            citizen.setName("Vusal");
            citizen.setSurname("Hasanli");
            citizen.setRegAddress("TESTS");
            citizen.setPatronymic("A");
            citizen.setIdNumber("12412423");
            citizen.setIdSeries("AS");
            citizen.setAccidentId(accident);
            //add citizen
            HashSet<Citizen> citizens = new HashSet<>();
            citizens.add(citizen);
            accident.setCitizens(citizens);
            //create  vechile
            Vechile vechile = new Vechile();
            vechile.setRegNumber("11AA111");
            vechile.setModel("S Class");
            vechile.setMarka("A250");
            vechile.setAccidentId(accident);
            //set vechiles
            HashSet<Vechile> vechiles = new HashSet<>();
            vechiles.add(vechile);
            accident.setVechiles(vechiles);

//            repository.save(accident);


            Accident accident1 = repository.findOne(1);
            System.out.println(accident1.getCitizens().size() + "   " + accident1.getVechiles().size());
        };
    }

}
