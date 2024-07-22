package app.bookstore.exception.User;

public class UserNotFoundExceptionID extends RuntimeException {
    public UserNotFoundExceptionID(int id){
        super("User with ID '" + id + "' not found");
    }
}