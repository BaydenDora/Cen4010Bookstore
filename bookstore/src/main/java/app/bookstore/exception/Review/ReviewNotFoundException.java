package app.bookstore.exception.Review;

public class ReviewNotFoundException extends RuntimeException {
    
    public ReviewNotFoundException(int id) {
        super("Review with ID '" + id + "' not found");
    }
    
}
