package ch.burak;

import ch.burak.Customer;
import ch.burak.CustomerRepository;
import ch.burak.CustomerService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Burak Kara
 */
public class CustomerServiceTest {
    @Test
    public void findByNames() throws Exception {

        CustomerRepository mockRepo = mock(CustomerRepository.class);
        Customer expectdCusomer = new Customer("Burak");
        when(mockRepo.findAll()).thenReturn(Arrays.asList(expectdCusomer, new Customer("Hasan")));

        CustomerService customerService = new CustomerService(mockRepo);
        List<Customer> acutalFilteredList = customerService.findByName("Burak");

        assertThat("Filtering by firstname", acutalFilteredList, is(Arrays.asList(expectdCusomer)));
    }

}
