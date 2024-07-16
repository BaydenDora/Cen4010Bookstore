package app.bookstore.exception.Book;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestControllerAdvice
class BookNotFoundAdvice implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    @ExceptionHandler(BookNotFoundException.class)
    // @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<String> bookNotFoundHandler(BookNotFoundException e) {

        Map<String, String> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        response.put("error", e.getMessage());
        try{
            String jsonResponse = objectMapper.writeValueAsString(response);
            return new ResponseEntity<>(jsonResponse, HttpStatus.NOT_FOUND);
        } catch(Exception ex) { 
            return new ResponseEntity<>("Error converting to JSON", HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    
    
    }

}
