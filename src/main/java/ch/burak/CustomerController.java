package ch.burak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.*;

/**
 * This is a Spring MVC controller for Web-Pages.
 *
 * @author Burak Kara
 */
@Controller
public class CustomerController {

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerRepository repo;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerRepository repo, CustomerService customerService) {
        this.repo = repo;
        this.customerService = customerService;
    }

    @GetMapping("/index")
    String index(Model model) {
        model.addAttribute("customers", this.repo.findAll());
        return "customers";
    }

    @GetMapping("/customerForm")
    String customerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customerForm";
    }

    @PostMapping("/displayCustomer")
    String customerSubmit(@ModelAttribute Customer customer) {
        repo.save(customer);

        return "display";
    }

    @GetMapping("/about")
    String about(Model model) {
        model.addAttribute("developers", Arrays.asList("Hasan Kara", "Burak"));
        return "about";
    }

    @RequestMapping("/deleteCustomer/{id}")
    String customerDelete(@PathVariable("id") Long id) {
        Customer customer = repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        repo.delete(customer);

        logger.info("Deleted {}", customer.getId());

        return "redirect:/index";
    }

    @RequestMapping("/updateCustomer/{id}")
    String update(@PathVariable("id") Long id, Customer editedCustomer) {

        Customer customer = repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        customer.setFirstName(editedCustomer.getFirstName());

        repo.save(customer);
        return "redirect:/index";
    }

    @RequestMapping("/editCustomer/{id}")
    String customerEdit(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Customer customer = repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("customer", customer);
        redirectAttributes.addFlashAttribute("customer", customer);
        return "redirect:/editCustomer";
    }

    @RequestMapping("/editCustomer")
    String customerEditRedirect(Customer customer, Model model){
        model.addAttribute("customer",customer);
        return "editCustomer";
    }

    @RequestMapping("/findCustomer")
    String findCustomer(@RequestParam("searchName") String searchName, Model model) {

        model.addAttribute("customers", customerService.findByName(searchName));
        return "customers";
    }

}
