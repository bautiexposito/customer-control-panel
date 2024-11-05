package academy.atl.customers.exception;

public class RequiredFieldException extends Exception{

    public RequiredFieldException (String message) {
        super(message);
    }
}
