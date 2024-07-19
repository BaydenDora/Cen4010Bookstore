package app.bookstore.exception.Wishlist;

public class WishlistNotFoundException extends RuntimeException {
    
    public WishlistNotFoundException(int id) {
        super("Wishlist with ID '" + id + "' not found");
    }

}