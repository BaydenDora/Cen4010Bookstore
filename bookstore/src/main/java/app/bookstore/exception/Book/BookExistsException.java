package app.bookstore.exception.Book;

public class BookExistsException extends RuntimeException {
    public BookExistsException(Long id) {
        super(String.format("%s%d\n", "Book already exists: ", id));
    }

    public BookExistsException(String isbn) {
        super(String.format("%s%s\n", "Book already exists: ", isbn));
    }

}
