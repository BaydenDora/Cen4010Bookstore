package app.bookstore.exception.CreditCard;

public class CreditCardExistsException extends RuntimeException {
    
    public CreditCardExistsException(long id) {
        super("Credit card with ID '" + id + "' already exists");
    }

    public CreditCardExistsException(String cardNumber) {
        super("Credit card with card number '" + cardNumber + "' already exists");
    }

}
