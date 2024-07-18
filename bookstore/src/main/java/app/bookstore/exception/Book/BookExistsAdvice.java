// package app.bookstore.exception.Book;
// import java.io.Serializable;
// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice
// class BookExistsAdvice implements Serializable {

//     @ExceptionHandler(BookExistsException.class)
//     @ResponseStatus(HttpStatus.FORBIDDEN)
//     String bookNotFoundHandler(BookExistsException e) {
//       return e.getMessage();
//     }

// }
