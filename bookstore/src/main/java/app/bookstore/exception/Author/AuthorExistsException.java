package app.bookstore.exception.Author;

public class AuthorExistsException extends RuntimeException {

    public AuthorExistsException(int id) {
        super("Author with ID '" + id + "' already exists");
    }

    public AuthorExistsException(String firstName, String lastName) {
        super("Author '" + firstName + " " + lastName + "' already exists");
    }

}
