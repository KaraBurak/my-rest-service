package ch.burak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @GetMapping("api/search")
    public ResponseEntity<List<Customer>> searchCustomer(@RequestParam(value = "content") String name){

        List<Customer> allCustomers = repo.findAll();
        List<Customer> allContains = new ArrayList<>();

        for (Customer customer : allCustomers){
            if(customer.getFirstName().contains(name)){
                allContains.add(customer);
                System.out.println(customer.getFirstName());
            }
        }

        return ResponseEntity.ok(allContains);

    }


}
