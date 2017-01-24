package ch.burak;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Burak Kara
 */
@Service
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<Customer> findByName(String searchName) {
        return repo.findAll()
                   .stream()
                   .filter(customer -> customer.getFirstName().toLowerCase().contains(searchName.toLowerCase()))
                   .collect(Collectors.toList());
    }

}
