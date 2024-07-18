package app.bookstore.exception.User;

public class UserExistsException extends RuntimeException {

    public UserExistsException(int id) {
        super("User already exists: " + id);
    }

    public UserExistsException(String username) {
        super("User already exists: " + username);
    }

}
