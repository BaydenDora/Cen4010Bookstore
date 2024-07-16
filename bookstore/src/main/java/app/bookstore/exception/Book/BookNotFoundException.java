package app.bookstore.exception.Book;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Book not found: " + id);
    }

    public BookNotFoundException(String isbn) {
        super("Book not found: " + isbn);
    }
}
