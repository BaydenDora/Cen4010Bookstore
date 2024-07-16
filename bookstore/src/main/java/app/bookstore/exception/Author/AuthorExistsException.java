package app.bookstore.exception.Author;

public class AuthorExistsException extends RuntimeException {

    public AuthorExistsException(int id) {
        super(String.format("%s%d\n", "Author already exists: ", id));
    }

    public AuthorExistsException(String firstName, String lastName) {
        super(String.format("%s%s %s\n", "Author already exists: ", firstName, lastName));
    }

}
