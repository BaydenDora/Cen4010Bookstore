// package app.bookstore.exception;
// import java.io.Serializable;
// import java.time.LocalDateTime;
// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseStatus;
// // import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// import com.fasterxml.jackson.annotation.JsonFormat;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import app.bookstore.exception.Book.BookExistsException;
// import app.bookstore.exception.Book.BookNotFoundException;


// @RestControllerAdvice
// class GlobalExceptionHandler implements Serializable {

//     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
//     // private LocalDateTime timestamp;
//     private ObjectMapper objectMapper;

//     @ExceptionHandler(BookNotFoundException.class)
//     // @ResponseStatus(HttpStatus.NOT_FOUND)
//     ResponseEntity<String> bookNotFoundHandler(BookNotFoundException e) {

//         Map<String, String> response = new HashMap<>();
//         // objectMapper = new ObjectMapper();
//         response.put("Error", e.getMessage());
//         try{
//             String jsonResponse = objectMapper.writeValueAsString(response);
//             return new ResponseEntity<>(jsonResponse, HttpStatus.NOT_FOUND);
//         } catch(Exception ex) { 
//             return new ResponseEntity<>("Error converting to JSON", HttpStatus.INTERNAL_SERVER_ERROR); 
//         }
//     }


//     @ExceptionHandler(BookExistsException.class)
//     @ResponseStatus(HttpStatus.FORBIDDEN)
//     String bookNotFoundHandler(BookExistsException e) {
//       return e.getMessage();
//     }




// }