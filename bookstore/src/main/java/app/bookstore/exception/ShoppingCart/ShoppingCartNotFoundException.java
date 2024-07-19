package app.bookstore.exception.ShoppingCart;

public class ShoppingCartNotFoundException extends RuntimeException {
    
    public ShoppingCartNotFoundException(int id){
        super("Shopping cart with ID '" + id + "' not found");
    }

}
