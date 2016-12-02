package ch.burak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Burak Kara
 */
@RestController
public class CustomerResource {

    private final CustomerRepository repo;

    @Autowired
    public CustomerResource(CustomerRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Customer>> getAllCustomers() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestParam(value = "name") String name) {
        Customer customer = new Customer(name);
        repo.save(customer);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/find")
    public ResponseEntity<Customer> findCustomer(@RequestParam(value = "name") String name) {
        return repo.findByFirstName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

}
