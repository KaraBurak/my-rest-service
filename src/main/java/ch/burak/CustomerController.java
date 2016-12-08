package ch.burak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Arrays;

/**
 * This is a Spring MVC controller for Web-Pages.
 *
 * @author Burak Kara
 */
@Controller
public class CustomerController {

    private final CustomerRepository repo;

    @Autowired
    public CustomerController(CustomerRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/index")
    String index(Model model) {
        model.addAttribute("customers", this.repo.findAll());

        return "customers";
    }

    @GetMapping("/customer")
    String customerForm(Model model){
        model.addAttribute("customer", new Customer());
        return "customer";
    }

    @PostMapping("/customer")
    String customerSubmit(@ModelAttribute Customer customer) {
        Customer c = repo.save(customer);
        return "display";
    }

    @GetMapping("/about")
    String about(Model model) {
        model.addAttribute("developers", Arrays.asList("Hasan Kara", "Burak"));
        return "about";
    }

}
