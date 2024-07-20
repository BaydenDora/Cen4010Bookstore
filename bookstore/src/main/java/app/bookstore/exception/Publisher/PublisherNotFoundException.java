package app.bookstore.exception.Publisher;

public class PublisherNotFoundException extends RuntimeException {
    
    public PublisherNotFoundException(int id) {
        super("Publisher with ID '" + id + "' not found");
    }

}
