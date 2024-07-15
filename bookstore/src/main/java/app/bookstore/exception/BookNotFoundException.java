package app.bookstore.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super(String.format("%s%d\n", "Book not found: ", id));
    }

    public BookNotFoundException(String ISBN) {
        super(String.format("%s%s\n", "Book not found: ", ISBN));
    }
}
