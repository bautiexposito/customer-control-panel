package academy.atl.customers.controller;

import academy.atl.customers.Connection;
import academy.atl.customers.model.Customer;
import academy.atl.customers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerByID(@PathVariable Integer id) {
        return customerService.getCustomerByID(id);
    }

    @GetMapping("/search")
    public List<Customer> searchCustomer(@RequestParam(value = "firstName", required = false) String firstName,
                                                 @RequestParam(value = "lastName", required = false) String lastName,
                                                 @RequestParam(value = "email", required = false) String email,
                                                 @RequestParam(value = "address", required = false) String address) {
        return customerService.searchCustomer(firstName, lastName, email, address);
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable Integer id, @RequestBody Customer updateCustomer) {
        customerService.updateCustomer(id, updateCustomer);
    }

    @DeleteMapping("/{id}")
    public void removeCustomer(@PathVariable Integer id) {
        customerService.removeCustomer(id);
    }

}
