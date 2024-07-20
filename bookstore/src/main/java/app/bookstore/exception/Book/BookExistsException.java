package app.bookstore.exception.Book;

public class BookExistsException extends RuntimeException {
    public BookExistsException(Long id) {
        super("Book with ID '" + id + "' already exists");
    }

    public BookExistsException(String isbn) {
        super("Book with ISBN '" + isbn + "' already exists");
    }

}
