package app.bookstore.exception;
import java.util.ArrayList;

import app.bookstore.exception.Author.AuthorExistsException;
import app.bookstore.exception.Author.AuthorNotFoundException;
import app.bookstore.exception.Book.BookExistsException;
import app.bookstore.exception.Book.BookNotFoundException;
import app.bookstore.exception.CreditCard.CreditCardExistsException;
import app.bookstore.exception.CreditCard.CreditCardNotFoundException;
import app.bookstore.exception.Genre.NoSuchGenreException;
import app.bookstore.exception.Publisher.PublisherExistsException;
import app.bookstore.exception.Publisher.PublisherNotFoundException;
import app.bookstore.exception.User.UserExistsException;
import app.bookstore.exception.User.UserNotFoundException;
import app.bookstore.exception.Wishlist.WishlistNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullValueException.class)
    public ResponseEntity<ErrorResponse> NullArgumentHandler(NullValueException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ErrorResponse> authorNotFoundHandler(AuthorNotFoundException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorExistsException.class)
    public ResponseEntity<ErrorResponse> authorExistsHandler(AuthorExistsException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> bookNotFoundHandler(BookNotFoundException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookExistsException.class)
    public ResponseEntity<ErrorResponse> bookNotFoundHandler(BookExistsException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CreditCardNotFoundException.class)
    public ResponseEntity<ErrorResponse> creditCardNotFoundHandler(CreditCardNotFoundException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CreditCardExistsException.class)
    public ResponseEntity<ErrorResponse> creditCardExistsHandler(CreditCardExistsException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchGenreException.class)
    ResponseEntity<ErrorResponse> noSuchGenreHandler(NoSuchGenreException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PublisherNotFoundException.class)
    public ResponseEntity<ErrorResponse> publisherNotFoundHandler(PublisherNotFoundException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PublisherExistsException.class)
    public ResponseEntity<ErrorResponse> publisherExistsHandler(PublisherExistsException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundHandler(UserNotFoundException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ErrorResponse> userExistsHandler(UserExistsException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WishlistNotFoundException.class)
    public ResponseEntity<ErrorResponse> wishlistNotFoundHandler(WishlistNotFoundException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    private ResponseEntity<ErrorResponse> globalHandler(String msg, HttpStatus status){
        var error = new ErrorResponse(status.getReasonPhrase(), status.value(), new ArrayList<>(){{add(msg);}});
        try{
            return new ResponseEntity<>(error, status);
        } catch(Exception e) {
            var newStatus = HttpStatus.INTERNAL_SERVER_ERROR; 
            error.setError(newStatus.getReasonPhrase());
            error.setStatus(newStatus.value());
            error.setDetails(new ArrayList<>(){{add("Error converting to JSON");}});
            return new ResponseEntity<>(error, newStatus);
        }
    }

}