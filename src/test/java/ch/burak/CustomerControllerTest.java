package ch.burak;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;

/**
 * @author Burak Kara
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebClient client;

    @MockBean
    CustomerRepository repo;

    @MockBean
    CustomerService service;

    @Test
    public void getIndexTest() throws Exception {

        given(repo.findAll())
            .willReturn(Arrays.asList(new Customer("Burak"), new Customer("Hasan")));

        MvcResult mvcResult = this.mvc.perform(get("/index")
            .accept(MediaType.TEXT_PLAIN))
                                      .andExpect(status().isOk())
                                      .andReturn();

        String htmlContent = mvcResult.getResponse().getContentAsString();
        String expectedContains = "Burak";

        assertTrue("Index content doesn't match", htmlContent.contains(expectedContains));
    }

    @Test
    public void getIndexHtmlUnitTest() throws Exception {

        given(repo.findAll())
            .willReturn(Arrays.asList(new Customer("Burak"), new Customer("Hasan")));

        HtmlPage page = this.client.getPage("/index");
        DomElement customerTable = page.getElementById("customerTable");
        String tableContent = customerTable.getTextContent();

        assertTrue("Index content doesn't match", tableContent.contains("Burak"));
    }

    @Test
    public void createCustomerTest() throws Exception {
        Customer expected = new Customer("Burak");

        HtmlPage page = this.client.getPage("/customerForm");
        HtmlForm htmlForm = page.getForms().get(0);
        htmlForm.getInputByName("firstName").setValueAttribute(expected.getFirstName());
        HtmlButton submit = (HtmlButton) page.getElementById("input");
        submit.click();

        verify(this.repo, times(1)).save(expected);

    }
}
