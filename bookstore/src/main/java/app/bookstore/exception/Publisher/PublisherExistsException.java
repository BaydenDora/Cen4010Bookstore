package app.bookstore.exception.Publisher;

public class PublisherExistsException extends RuntimeException {
    
    public PublisherExistsException(int id) {
        super("Publisher with ID '" + id + "' already exists");
    }

    public PublisherExistsException(String name) {
        super("The publisher '" + name + "' already exists");
    }

}
