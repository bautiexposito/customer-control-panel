package academy.atl.customers.service;

import academy.atl.customers.model.Customer;
import academy.atl.customers.persistence.CustomerDao;
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

    public List<Customer> searchCustomer(String firstName, String lastName, String email, String address) {
        return null;
    }

    public void addCustomer(Customer customer) {
        repository.save(customer);
    }

    public void updateCustomer(Integer id, Customer updateCustomer) {
        updateCustomer.setId(id);
        repository.save(updateCustomer);
    }

    public void removeCustomer(Integer id) {
        repository.deleteById(id);
    }

}
