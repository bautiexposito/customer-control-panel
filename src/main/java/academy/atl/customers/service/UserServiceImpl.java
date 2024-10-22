package academy.atl.customers.service;

import academy.atl.customers.model.User;
import academy.atl.customers.persistence.UserDao;
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

    public void addUser(User user) {
        String hashPassword = Hashing.sha256()
                .hashString(user.getPassword() + SECRET_KEY, StandardCharsets.UTF_8)
                .toString();

        user.setPassword(hashPassword);
        repository.save(user);
    }

    public void updateUser(Integer id, User updateUser) {
        updateUser.setId(id);
        repository.save(updateUser);
    }

    public void removeUser(Integer id) {
        repository.deleteById(id);
    }

}
