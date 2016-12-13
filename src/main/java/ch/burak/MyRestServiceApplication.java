package ch.burak;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class MyRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRestServiceApplication.class, args);
	}

	@Bean
    CommandLineRunner initDb(CustomerRepository repo) {
	    return strings -> {
            repo.save(new Customer("Burak", new Date()));
            repo.save(new Customer("Hasan", new Date()));
            repo.save(new Customer("Urs", new Date()));
        };
    }
}
