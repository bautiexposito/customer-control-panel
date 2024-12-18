package academy.atl.customers.controller;

import academy.atl.customers.controller.validator.UserValidator;
import academy.atl.customers.dto.UserDto;
import academy.atl.customers.exception.InvalidEmailException;
import academy.atl.customers.exception.MinimumAmountOfCharactersException;
import academy.atl.customers.exception.RequiredFieldException;
import academy.atl.customers.exception.UserNotFoundException;
import academy.atl.customers.model.User;
import academy.atl.customers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

    @GetMapping
    public List<User> getAllUsers(String token) {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable Integer id) {
        return userService.getUserByID(id);
    }

    @GetMapping("/search")
    public List<User> searchUser(@RequestParam(value = "email", required = false) String email,
                                         @RequestParam(value = "address", required = false) String address) {
        return userService.searchUser(email, address);
    }

    @PostMapping
    public void addUser(@RequestBody UserDto userDto) throws RequiredFieldException, MinimumAmountOfCharactersException, InvalidEmailException {
        userValidator.validate(userDto);
        userService.addUser(userDto);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) throws RequiredFieldException, MinimumAmountOfCharactersException, InvalidEmailException, UserNotFoundException {
        userValidator.validate(userDto);
        userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable Integer id) throws UserNotFoundException{
        userService.removeUser(id);
    }
}
