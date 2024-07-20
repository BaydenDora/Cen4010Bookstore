package app.bookstore.exception.User;

public class UserExistsException extends RuntimeException {

    public UserExistsException(int id) {
        super("User with ID '" + id + "' already exists");
    }

    public UserExistsException(String username) {
        super("User with user name '" + username + "' already exists");
    }

}
