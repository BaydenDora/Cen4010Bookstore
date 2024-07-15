package app.bookstore.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(int id) {
        super(String.format("%s%d\n", "Author not found: ", id));
    }
}
