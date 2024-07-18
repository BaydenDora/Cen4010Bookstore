package app.bookstore.exception;
import java.util.List;
import java.util.ArrayList;

import app.bookstore.exception.Author.AuthorExistsException;
import app.bookstore.exception.Author.AuthorNotFoundException;
import app.bookstore.exception.Book.BookExistsException;
import app.bookstore.exception.Book.BookNotFoundException;
import app.bookstore.exception.Genre.NoSuchGenreException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ErrorResponse> authorNotFoundHandler(AuthorNotFoundException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorExistsException.class)
    public ResponseEntity<ErrorResponse> authorExistsHandler(AuthorExistsException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> bookNotFoundHandler(BookNotFoundException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookExistsException.class)
    public ResponseEntity<ErrorResponse> bookNotFoundHandler(BookExistsException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoSuchGenreException.class)
    ResponseEntity<ErrorResponse> authorExistsHandler(NoSuchGenreException ex) {
        return globalHandler(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<ErrorResponse> globalHandler(String msg, HttpStatus status){
        var error = new ErrorResponse(status.toString(), new ArrayList<>(){{add(msg);}});
        try{
            return new ResponseEntity<>(error, status);
        } catch(Exception e) { 
            return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(),  
                    new ArrayList<>(){{add("Error converting to JSON");}}), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}