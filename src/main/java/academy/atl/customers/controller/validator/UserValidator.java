package academy.atl.customers.controller.validator;

import academy.atl.customers.dto.UserDto;
import academy.atl.customers.exception.InvalidEmailException;
import academy.atl.customers.exception.MinimumAmountOfCharactersException;
import academy.atl.customers.exception.RequiredFieldException;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public void validate(UserDto userDto) throws RequiredFieldException, MinimumAmountOfCharactersException, InvalidEmailException {
        validateFirstName(userDto);
        validateLastName(userDto);
        validateEmail(userDto);
        validateAddress(userDto);
    }

    private void validateFirstName(UserDto userDto) throws RequiredFieldException, MinimumAmountOfCharactersException{
        String firstName = userDto.getFirstName();

        if (firstName == null || firstName.trim().isEmpty()) {
            throw new RequiredFieldException("Name is a required field");
        }

        if (firstName.length() < 3) {
            throw new MinimumAmountOfCharactersException("The address must be at least 3 characters long.");
        }
    }

    private void validateLastName(UserDto userDto) throws RequiredFieldException, MinimumAmountOfCharactersException {
        String lastName = userDto.getLastName();

        if (lastName == null || lastName.trim().isEmpty()) {
            throw new RequiredFieldException("Last name is a required field");
        }

        if (lastName.length() < 3) {
            throw new MinimumAmountOfCharactersException("The address must be at least 3 characters long.");
        }
    }

    private void validateEmail (UserDto userDto) throws RequiredFieldException, InvalidEmailException {
        boolean b = false;
        String email = userDto.getEmail();

        if (email == null || email.trim().isEmpty()) {
            throw new RequiredFieldException("Email is a required field");
        }

        for (char c : email.toCharArray()){
            if (c == '@') {
                b = true;
                break;
            }
        }

        if (b == false) {
            throw new InvalidEmailException("Please enter a valid email address");
        }
    }

    private void validateAddress(UserDto userDto) throws RequiredFieldException, MinimumAmountOfCharactersException {
        String address = userDto.getAddress();

        if (address == null || address.trim().isEmpty()) {
            throw new RequiredFieldException("Address is a required field");
        }

        if (address.length() < 5) {
            throw new MinimumAmountOfCharactersException("The address must be at least 5 characters long");
        }
    }

}
