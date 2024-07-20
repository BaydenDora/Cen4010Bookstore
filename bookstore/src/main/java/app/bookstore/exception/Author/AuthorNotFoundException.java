package app.bookstore.exception.Author;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(int id) {
        super("Author with ID '" + id + "' not found");
    }
}
