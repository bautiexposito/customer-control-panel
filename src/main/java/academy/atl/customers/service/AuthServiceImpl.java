package academy.atl.customers.service;

import academy.atl.customers.model.User;
import academy.atl.customers.repository.UserDao;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserDao userDao;
    private static final String SECRET_KEY = "3lB4ut4!";

    @Override
    public User login(String email, String password){
        String hashPassword = Hashing.sha256()
                .hashString(password + SECRET_KEY, StandardCharsets.UTF_8)
                .toString();

        List<User> result = userDao.findByEmailAndPassword(email, hashPassword);

        if(result.isEmpty()){
            return null;
        } else {
            return result.get(0);
        }
    }
}
