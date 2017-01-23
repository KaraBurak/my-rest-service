package ch.burak;

import ch.burak.Customer;
import ch.burak.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Burak Kara
 */
@Configuration
public class AppConfig {

    @Profile("dev")
    @Bean
    CommandLineRunner initDb(CustomerRepository repo){
        return strings -> {

            System.out.println("in dev profile");

            repo.save(new Customer("Burak"));
            repo.save(new Customer("Hasan"));
            repo.save(new Customer("Urs"));
        };
    }

    @Profile("prod")
    @Bean
    CommandLineRunner init(){
        return strings -> {
            System.out.println("in prod profile");
        };
    }
}
