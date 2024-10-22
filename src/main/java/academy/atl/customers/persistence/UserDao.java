package academy.atl.customers.persistence;

import academy.atl.customers.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    @Query("SELECT c FROM User c WHERE c.email LIKE %:email% OR c.address LIKE %:address%")
    List<User> findByEmailOrAddress(@Param("email") String email,
                                    @Param("address") String address);

    @Query("SELECT c FROM User c WHERE c.email = :email AND password = :password") //HQL
    List<User> findByEmailAndPassword(@Param("email") String email,
                                      @Param("password") String password);

}
