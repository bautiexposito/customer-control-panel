package academy.atl.customers.persistence;

import academy.atl.customers.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.email LIKE %:email% OR c.address LIKE %:address%") //HQL
    List<Customer> findByEmailOrAddress(@Param("email") String email, @Param("address") String address);

}
