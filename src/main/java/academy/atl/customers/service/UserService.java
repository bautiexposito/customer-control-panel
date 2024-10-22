package academy.atl.customers.service;

import academy.atl.customers.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserByID(Integer id);

    List<User> searchUser(String email, String address);

    void addUser(User user);

    void updateUser(Integer id, User updateUser);

    void removeUser(Integer id);

}
