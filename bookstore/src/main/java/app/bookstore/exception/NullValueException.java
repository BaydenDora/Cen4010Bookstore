package app.bookstore.exception;

public class NullValueException extends IllegalArgumentException {
    
    public NullValueException(String parameter) {
        super("Parameter '" + parameter + "' cannot be null");
    }

}
