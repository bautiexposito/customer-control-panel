package academy.atl.customers.controller;

import academy.atl.customers.controller.validator.CustomerValidator;
import academy.atl.customers.dto.CustomerDto;
import academy.atl.customers.exception.CustomerNotFoundException;
import academy.atl.customers.exception.InvalidEmailException;
import academy.atl.customers.exception.MinimumAmountOfCharactersException;
import academy.atl.customers.exception.RequiredFieldException;
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
    @Autowired
    private CustomerValidator customerValidator;

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
    public void addCustomer(@RequestBody CustomerDto customerDto) throws RequiredFieldException, MinimumAmountOfCharactersException, InvalidEmailException {
        customerValidator.validate(customerDto);
        customerService.addCustomer(customerDto);
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable Integer id, @RequestBody CustomerDto customerDto) throws RequiredFieldException, MinimumAmountOfCharactersException, InvalidEmailException, CustomerNotFoundException {
        customerValidator.validate(customerDto);
        customerService.updateCustomer(id, customerDto);
    }

    @DeleteMapping("/{id}")
    public void removeCustomer(@PathVariable Integer id) throws CustomerNotFoundException {
        customerService.removeCustomer(id);
    }

}
