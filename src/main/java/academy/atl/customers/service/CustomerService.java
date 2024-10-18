package academy.atl.customers.service;

import academy.atl.customers.model.Customer;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerByID(Integer id);

    List<Customer> searchCustomer(String email, String address);

    void addCustomer(Customer customer);

    void updateCustomer(Integer id, Customer updateCustomer);

    void removeCustomer(Integer id);

}
