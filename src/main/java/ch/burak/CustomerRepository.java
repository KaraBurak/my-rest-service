package ch.burak;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Burak Kara
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByFirstName(String firstName);

    Optional<Customer> findById(Long id);

}
