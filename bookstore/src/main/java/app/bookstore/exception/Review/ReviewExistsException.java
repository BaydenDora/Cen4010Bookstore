package app.bookstore.exception.Review;

public class ReviewExistsException extends RuntimeException{
    
    public ReviewExistsException(int id) {
        super("Review with ID '" + id + "' already exists");
    }

}
