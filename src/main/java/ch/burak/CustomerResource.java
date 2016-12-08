package ch.burak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * This is a Spring MVC REST-controller.
 *
 * REST ENDPOINT!!!! NOT A WEBSITE CONTROLLER!!!!! JUST FOR COMPUTERS!!!!!
 *
 * @author Burak Kara
 */
@RestController()
public class CustomerResource {

    private final CustomerRepository repo;

    @Autowired
    public CustomerResource(CustomerRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/api/customer")
    public ResponseEntity<Collection<Customer>> getAllCustomers() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping("/api/customer")
    public ResponseEntity<Customer> createCustomer(@RequestParam(value = "name") String name) {
        Customer customer = new Customer(name);
        repo.save(customer);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/api/customer/find")
    public ResponseEntity<Customer> findCustomer(@RequestParam(value = "name") String name) {
        return repo.findByFirstName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

}
