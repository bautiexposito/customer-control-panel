package academy.atl.customers.controller;

import academy.atl.customers.dto.RequestLogin;
import academy.atl.customers.model.User;
import academy.atl.customers.service.AuthService;
import academy.atl.customers.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody RequestLogin request) {
        User user = authService.login(request.getEmail(), request.getPassword());
        String token = JWTUtil.generateToken(user);
        return token;
    }
}
