package ch.burak;

import ch.burak.Customer;
import ch.burak.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Burak Kara
 */
@Configuration
public class AppConfig {

    private final static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Profile("dev")
    @Bean
    CommandLineRunner initDb(CustomerRepository repo){
        return strings -> {

            logger.info("Dev profile active.");

            repo.save(new Customer("Burak"));
            repo.save(new Customer("Hasan"));
            repo.save(new Customer("Urs"));
        };
    }

    @Profile("prod")
    @Bean
    CommandLineRunner init(){
        return strings -> {
            logger.info("Prod profile active.");
        };
    }
}
