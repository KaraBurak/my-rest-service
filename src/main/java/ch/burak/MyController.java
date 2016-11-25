package ch.burak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Burak Kara
 */
@RestController
public class MyController {

    private final CustomerRepository repo;



    @Autowired
    public MyController(CustomerRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/all")
    public Iterable<Customer> getAllUsers() {
        Iterable<Customer> all = repo.findAll();
        all.forEach(System.out::println);
        return all;
    }

    @GetMapping("/create")
    public Customer foo(@RequestParam(value="name") String name) {
        Customer c = new Customer(name);
        System.out.println(c);
        repo.save(c);
        System.out.println(c);
        return c;
    }

}
