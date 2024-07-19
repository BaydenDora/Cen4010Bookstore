package app.bookstore.exception.CreditCard;

public class CreditCardNotFoundException extends RuntimeException {
    
    public CreditCardNotFoundException(long id) {
        super("Credit card with ID '" + id + "' not found");
    }

}
