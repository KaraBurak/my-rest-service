package ch.burak;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Burak Kara
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByFirstName(String firstName);

}
