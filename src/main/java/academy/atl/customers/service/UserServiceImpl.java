package academy.atl.customers.service;

import academy.atl.customers.dto.UserDto;
import academy.atl.customers.exception.UserNotFoundException;
import academy.atl.customers.model.User;
import academy.atl.customers.repository.UserDao;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private static final String SECRET_KEY = "3lB4ut4!";
    @Autowired
    private UserDao repository;

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Iterable<User> users = repository.findAll();
        for (User user:users){
            list.add(user);
        }
        return list;
    }

    public User getUserByID(Integer id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public List<User> searchUser(String email, String address) {
        return repository.findByEmailOrAddress(email, address);
    }

    public void addUser(UserDto userDto) {
        User user = new User(userDto);

        String hashPassword = Hashing.sha256()
                .hashString(userDto.getPassword() + SECRET_KEY, StandardCharsets.UTF_8)
                .toString();

        user.setPassword(hashPassword);
        repository.save(user);
    }

    public void updateUser(Integer id, UserDto userDto) throws UserNotFoundException{
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setAddress(userDto.getAddress());
            repository.save(user);
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    public void removeUser(Integer id) throws UserNotFoundException{
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        repository.delete(user);
    }

}
