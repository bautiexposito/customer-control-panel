package academy.atl.customers.service;

import academy.atl.customers.dto.CustomerDto;
import academy.atl.customers.exception.CustomerNotFoundException;
import academy.atl.customers.model.Customer;
import academy.atl.customers.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDao repository;

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        Iterable<Customer> customers = repository.findAll();
        for (Customer customer:customers){
            list.add(customer);
        }
        return list;
    }

    public Customer getCustomerByID(Integer id) {
        Optional<Customer> customer = repository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        return null;
    }

    public List<Customer> searchCustomer(String email, String address) {
        return repository.findByEmailOrAddress(email, address);
    }

    public void addCustomer(CustomerDto customerDto) {
        Customer customer = new Customer(customerDto);
        repository.save(customer);
    }

    public void updateCustomer(Integer id, CustomerDto customerDto) throws CustomerNotFoundException{
        Optional<Customer> optionalCustomer = repository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setEmail(customerDto.getEmail());
            customer.setAddress(customerDto.getAddress());
            repository.save(customer);
        } else {
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        }
    }

    public void removeCustomer(Integer id) throws CustomerNotFoundException{
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
            repository.delete(customer);
    }

}
