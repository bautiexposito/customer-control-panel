package academy.atl.customers.service;

import academy.atl.customers.dto.UserDto;
import academy.atl.customers.exception.UserNotFoundException;
import academy.atl.customers.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserByID(Integer id);

    List<User> searchUser(String email, String address);

    void addUser(UserDto userDto);

    void updateUser(Integer id, UserDto userDto) throws UserNotFoundException;

    void removeUser(Integer id) throws UserNotFoundException;

}
