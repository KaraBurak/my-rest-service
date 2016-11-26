package ch.burak;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Collection<Customer> getAllCustomers() {
        return repo.findAll();
    }

    @GetMapping("/create")
    public Customer createCustomer(@RequestParam(value = "name") String name) {
        Customer customer = new Customer(name);
        repo.save(customer);
        return customer;
    }
    @RequestMapping
    @GetMapping("/find")
    public Customer findCustomer(@RequestParam(value = "name") String name) {
        if(repo.findByFirstName(name) == null){
            throw new ResourceNotFoundException();
//            return new Customer("404");
        }
        return repo.findByFirstName(name);
    }

}
