package app.bookstore.exception.Genre;
import java.io.Serializable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class NoSuchGenreAdvice implements Serializable{
    
    @ExceptionHandler(NoSuchGenreException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String authorExistsHandler(NoSuchGenreException e) {
      return e.getMessage();
    }

}
