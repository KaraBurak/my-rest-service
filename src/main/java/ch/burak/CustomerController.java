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


import java.util.*;

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

    @GetMapping("/customerForm")
    String customerForm(Model model){
        model.addAttribute("customer", new Customer());
        return "customerForm";
    }

    @PostMapping("/displayCustomer")
    String customerSubmit(@ModelAttribute Customer customer) {
        repo.save(customer);
        System.out.println(customer.getFirstName());

        return "display";
    }

    @GetMapping("/about")
    String about(Model model) {
        model.addAttribute("developers", Arrays.asList("Hasan Kara", "Burak"));
        return "about";
    }

    @RequestMapping("/deleteCustomer/{id}")
    String customerDelete(@PathVariable("id") Long id){
        Customer customer = repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        repo.delete(customer);
        System.out.println("Deleted");
        return "redirect:/index";
    }

    @RequestMapping("/updateCustomer/{id}")
    String update(@PathVariable("id") Long id, Customer editedCustomer){

        Customer customer = repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        customer.setFirstName(editedCustomer.getFirstName());

        repo.save(customer);
        return "redirect:/index";
    }

    @RequestMapping("/editCustomer/{id}")
    String customerEdit(@PathVariable("id") Long id, Model model){
        Customer customer = repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("customer",customer);
        System.out.println(customer.getFirstName());
        System.out.println("edit");
        return "editCustomer";
    }

    @RequestMapping("/findCustomer")
    String findCustomer(@RequestParam("searchName") String searchName, Model model){
        List<Customer> customers = repo.findAllByFirstName(searchName);
        model.addAttribute("customers", customers);
        return "foundCustomer";
    }




}
