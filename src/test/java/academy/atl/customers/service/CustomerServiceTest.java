package academy.atl.customers.service;

import academy.atl.customers.dto.CustomerDto;
import academy.atl.customers.exception.CustomerNotFoundException;
import academy.atl.customers.model.Customer;
import academy.atl.customers.repository.CustomerDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerDao repository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1);
        Customer customer2 = new Customer();
        customer2.setId(2);
        when(repository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> customers = customerService.getAllCustomers();

        assertNotNull(customers);
        assertEquals(2, customers.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetCustomerByID() {
        Customer customer = new Customer();
        customer.setId(1);
        when(repository.findById(1)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerByID(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(repository, times(1)).findById(1);
    }

    @Test
    void testGetCustomerByIDNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        Customer result = customerService.getCustomerByID(1);

        assertNull(result);
        verify(repository, times(1)).findById(1);
    }

    @Test
    void testSearchCustomer() {
        Customer customer = new Customer();
        customer.setEmail("test@example.com");
        customer.setAddress("Test Address");
        when(repository.findByEmailOrAddress("test@example.com", "Test Address"))
                .thenReturn(List.of(customer));

        List<Customer> results = customerService.searchCustomer("test@example.com", "Test Address");

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("test@example.com", results.get(0).getEmail());
        verify(repository, times(1))
                .findByEmailOrAddress("test@example.com", "Test Address");
    }

    @Test
    void testAddCustomer() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("John");
        customerDto.setLastName("Doe");
        customerDto.setEmail("john.doe@example.com");
        customerDto.setAddress("123 Street");
        Customer customer = new Customer(customerDto);
        when(repository.save(any(Customer.class))).thenReturn(customer);

        customerService.addCustomer(customerDto);

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(repository, times(1)).save(captor.capture());
        Customer savedCustomer = captor.getValue();
        assertEquals("John", savedCustomer.getFirstName());
        assertEquals("Doe", savedCustomer.getLastName());
    }

    @Test
    void testUpdateCustomer() throws CustomerNotFoundException {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("Jane");
        customerDto.setLastName("Doe");
        customerDto.setEmail("jane.doe@example.com");
        customerDto.setAddress("456 Street");

        Customer customer = new Customer();
        customer.setId(1);
        when(repository.findById(1)).thenReturn(Optional.of(customer));

        customerService.updateCustomer(1, customerDto);

        verify(repository, times(1)).save(customer);
        assertEquals("Jane", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
    }

    @Test
    void testUpdateCustomerNotFound() {
        CustomerDto customerDto = new CustomerDto();
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> customerService.updateCustomer(1, customerDto));
        verify(repository, never()).save(any(Customer.class));
    }

    @Test
    void testRemoveCustomer() throws CustomerNotFoundException {
        Customer customer = new Customer();
        customer.setId(1);
        when(repository.findById(1)).thenReturn(Optional.of(customer));

        customerService.removeCustomer(1);

        verify(repository, times(1)).delete(customer);
    }

    @Test
    void testRemoveCustomerNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> customerService.removeCustomer(1));
        verify(repository, never()).delete(any(Customer.class));
    }
}
