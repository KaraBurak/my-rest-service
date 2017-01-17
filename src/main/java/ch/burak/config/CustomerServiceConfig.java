package ch.burak.config;

import ch.burak.CustomerRepository;
import ch.burak.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Burak Kara
 */
@Configuration
public class CustomerServiceConfig {

    @Autowired
    CustomerRepository repo;

    @Bean
    public CustomerService customerService(){
        return new CustomerService(repo);
    }

}
