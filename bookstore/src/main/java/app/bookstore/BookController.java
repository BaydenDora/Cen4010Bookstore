package app.bookstore;

import app.bookstore.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo AuthorRepo;

    @Autowired
    private PublisherRepo publisherRepo;

    /*  Book Details Feature Task #1:
        An administrator must be able to create a book with the book ISBN, book name, 
        book description, price, author, genre, publisher, year published and copies sold  */
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Optional<Author> author = AuthorRepo.findById(bookDTO.getMyAuthorId());
        if (!author.isPresent())
            throw new RuntimeException("Author not found");
        Optional<Publisher> publisher = publisherRepo.findById(bookDTO.getMyPublisherId());
        if (!publisher.isPresent()) 
            throw new RuntimeException("Publisher not found");

        Book book = new Book(
            bookDTO.getISBN(),
            bookDTO.getMyTitle(),
            bookDTO.getMyDescription(),
            bookDTO.getMyYearPublished(),
            author.get(),
            publisher.get(),
            Genre.valueOf(bookDTO.getMyGenre()),
            bookDTO.getMyCopiesSold(),
            bookDTO.getMyPrice()
        );
        Book savedBook = bookRepo.save(book);

        bookDTO.setId(savedBook.getId());
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<Book> bk = bookRepo.findById(id);
        if (!bk.isPresent()) 
            return ResponseEntity.notFound().build();
        var book = bk.get();
        BookDTO bookDTO = new BookDTO(
            book.getId(),
            book.getISBN(),
            book.getTitle(),
            book.getDescription(),
            book.getYearPublished(),
            book.getAuthor().getAuthorID(),
            book.getPublisher().getID(),
            book.getGenre().name(),
            book.getCopiesSold(),
            book.getPrice()
        );
        return ResponseEntity.ok(bookDTO);
    }

    /*  Book Details Feature Task #2:
        Must be able retrieve a book’s details by the ISBN  */
    @GetMapping("/ISBN/{ISBN}")
    public ResponseEntity<BookDTO> getBookByISBN(@PathVariable String ISBN) {
        Optional<Book> bk = bookRepo.findByISBN(ISBN);
        if (!bk.isPresent())
            return ResponseEntity.notFound().build();

        Book book = bk.get();
        BookDTO bookDTO = new BookDTO(
            book.getId(),
            book.getISBN(),
            book.getTitle(),
            book.getDescription(),
            book.getYearPublished(),
            book.getAuthor().getAuthorID(),
            book.getPublisher().getID(),
            book.getGenre().name(),
            book.getCopiesSold(),
            book.getPrice()
        );
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepo.findAll().forEach(books::add);  // Convert Iterable to List

        List<BookDTO> bookDTOs = books.stream().map(book -> {
            BookDTO bookDTO = new BookDTO(
                book.getId(),
                book.getISBN(),
                book.getTitle(),
                book.getDescription(),
                book.getYearPublished(),
                book.getAuthor().getAuthorID(),
                book.getPublisher().getID(),
                book.getGenre().name(),
                book.getCopiesSold(),
                book.getPrice() 
            );
            return bookDTO;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOs);
    }

}