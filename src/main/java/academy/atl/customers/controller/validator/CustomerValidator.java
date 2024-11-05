package academy.atl.customers.controller.validator;

import academy.atl.customers.dto.CustomerDto;
import academy.atl.customers.exception.InvalidEmailException;
import academy.atl.customers.exception.MinimumAmountOfCharactersException;
import academy.atl.customers.exception.RequiredFieldException;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {

    public void validate(CustomerDto customerDto) throws RequiredFieldException, MinimumAmountOfCharactersException, InvalidEmailException {
        validateFirstName(customerDto);
        validateLastName(customerDto);
        validateEmail(customerDto);
        validateAddress(customerDto);
    }

    private void validateFirstName(CustomerDto customerDto) throws RequiredFieldException, MinimumAmountOfCharactersException{
        String firstName = customerDto.getFirstName();

        if (firstName == null || firstName.trim().isEmpty()) {
            throw new RequiredFieldException("Name is a required field");
        }

        if (firstName.length() < 3) {
            throw new MinimumAmountOfCharactersException("The address must be at least 3 characters long.");
        }
    }

    private void validateLastName(CustomerDto customerDto) throws RequiredFieldException, MinimumAmountOfCharactersException {
        String lastName = customerDto.getLastName();

        if (lastName == null || lastName.trim().isEmpty()) {
            throw new RequiredFieldException("Last name is a required field");
        }

        if (lastName.length() < 3) {
            throw new MinimumAmountOfCharactersException("The address must be at least 3 characters long.");
        }
    }

    private void validateEmail (CustomerDto customerDto) throws RequiredFieldException, InvalidEmailException {
        boolean b = false;
        String email = customerDto.getEmail();

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

    private void validateAddress(CustomerDto customerDto) throws RequiredFieldException, MinimumAmountOfCharactersException {
        String address = customerDto.getAddress();

        if (address == null || address.trim().isEmpty()) {
            throw new RequiredFieldException("Address is a required field");
        }

        if (address.length() < 5) {
            throw new MinimumAmountOfCharactersException("The address must be at least 5 characters long");
        }
    }
}
