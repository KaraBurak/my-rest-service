package ch.burak;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Burak Kara
 */
@Service
@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Long> {


}
