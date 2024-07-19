package app.bookstore.exception.User;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id){
        super("User with ID '" + id + "' not found");
    }
}
