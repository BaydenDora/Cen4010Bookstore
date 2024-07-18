// package app.bookstore.exception.Author;
// import java.io.Serializable;
// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice
// class AuthorExistsAdvice implements Serializable{
    
//     @ExceptionHandler(AuthorExistsException.class)
//     @ResponseStatus(HttpStatus.FORBIDDEN)
//     String authorExistsHandler(AuthorExistsException e) {
//       return e.getMessage();
//     }

// }
