package academy.atl.customers.service;

import academy.atl.customers.model.User;

public interface AuthService {

    User login(String email, String password);
}
