package ch.burak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/index")
    String index(Model model) {
        model.addAttribute("customers", this.repo.findAll());

        return "customers";
    }

    @RequestMapping("/about")
    String about(Model model) {
        model.addAttribute("developers", Arrays.asList("Hasan Kara", "Burak"));
        return "about";
    }

}
