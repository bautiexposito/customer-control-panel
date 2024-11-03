package academy.atl.customers.controller;

import academy.atl.customers.Connection;
import academy.atl.customers.dto.CustomerDto;
import academy.atl.customers.model.Customer;
import academy.atl.customers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/customer")
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
    public List<Customer> searchCustomer(@RequestParam(value = "email", required = false) String email,
                                         @RequestParam(value = "address", required = false) String address) {
        return customerService.searchCustomer(email, address);
    }

    @PostMapping
    public void addCustomer(@RequestBody CustomerDto customerDto) {
        customerService.addCustomer(customerDto);
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable Integer id, @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(id, customerDto);
    }

    @DeleteMapping("/{id}")
    public void removeCustomer(@PathVariable Integer id) {
        customerService.removeCustomer(id);
    }

}
