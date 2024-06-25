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

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Optional<Author> author = AuthorRepo.findById(bookDTO.getMyAuthorId());
        if (!author.isPresent()) {
            throw new RuntimeException("Author not found");
        }
        Optional<Publisher> publisher = publisherRepo.findById(bookDTO.getMyPublisherId());
        if (!publisher.isPresent()) {
            throw new RuntimeException("Publisher not found");
        }

        Book book = new Book();
        book.setISBN(bookDTO.getISBN());
        book.setTitle(bookDTO.getMyTitle());
        book.setDescription(bookDTO.getMyDescription());
        book.setYearPublished(bookDTO.getMyYearPublished());
        book.setAuthor(author.get());
        book.setPublisher(publisher.get());
        book.setGenre(Genre.valueOf(bookDTO.getMyGenre()));
        book.setCopiesSold(bookDTO.getMyCopiesSold());
        book.setPrice(bookDTO.getMyPrice());

        Book savedBook = bookRepo.save(book);

        bookDTO.setId(savedBook.getId());
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepo.findById(id);
        if (!book.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.get().getId());
        bookDTO.setISBN(book.get().getISBN());
        bookDTO.setMyTitle(book.get().getTitle());
        bookDTO.setMyDescription(book.get().getDescription());
        bookDTO.setMyYearPublished(book.get().getYearPublished());
        bookDTO.setMyAuthorId(book.get().getAuthor().getAuthorID());
        bookDTO.setMyPublisherId(book.get().getPublisher().getID());
        bookDTO.setMyGenre(book.get().getGenre().name());
        bookDTO.setMyCopiesSold(book.get().getCopiesSold());
        bookDTO.setMyPrice(book.get().getPrice());
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepo.findAll().forEach(books::add);  // Convert Iterable to List

        List<BookDTO> bookDTOs = books.stream().map(book -> {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setISBN(book.getISBN());
            bookDTO.setMyTitle(book.getTitle());
            bookDTO.setMyDescription(book.getDescription());
            bookDTO.setMyYearPublished(book.getYearPublished());
            bookDTO.setMyAuthorId(book.getAuthor().getAuthorID());
            bookDTO.setMyPublisherId(book.getPublisher().getID());
            bookDTO.setMyGenre(book.getGenre().name());
            bookDTO.setMyCopiesSold(book.getCopiesSold());
            bookDTO.setMyPrice(book.getPrice());
            return bookDTO;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOs);
    }
}