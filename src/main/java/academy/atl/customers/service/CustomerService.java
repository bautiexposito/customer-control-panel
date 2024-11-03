package academy.atl.customers.service;

import academy.atl.customers.dto.CustomerDto;
import academy.atl.customers.model.Customer;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerByID(Integer id);

    List<Customer> searchCustomer(String email, String address);

    void addCustomer(CustomerDto customerDto);

    void updateCustomer(Integer id, CustomerDto customerDto);

    void removeCustomer(Integer id);

}
