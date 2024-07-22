package app.bookstore.exception.User;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username){
        super("User with ID '" + username + "' not found");
    }
}
