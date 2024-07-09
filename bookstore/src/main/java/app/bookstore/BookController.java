package app.bookstore;

import app.bookstore.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    
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
        bookDTO.setMyCurrentPrice(book.getSellingPrice()); // Set current price
        bookDTO.roundPrices(); // Round the prices

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
        bookDTO.setMyCurrentPrice(book.get().getSellingPrice());
        bookDTO.roundPrices(); // Round the prices

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
            bookDTO.setMyCurrentPrice(book.getSellingPrice());
            bookDTO.roundPrices(); // Round the prices
            return bookDTO;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOs);
    }
    
    @GetMapping("/getByGenre/{genre}")
    public ResponseEntity<List<BookDTO>> getBookByGenre(@PathVariable Genre genre) {
        logger.info("Finding books with genre: " + genre);
        List<Book> books = bookRepo.findByGenre(genre);

        if (!books.isEmpty()) {
            logger.info("Here is a list of our books with genre " + genre + ".");
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
                bookDTO.setMyCurrentPrice(book.getSellingPrice());
                bookDTO.roundPrices(); // Round the prices
                return bookDTO;
            }).collect(Collectors.toList());
            return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
        } else {
            logger.error("\"" + genre + "\" books not found. Please retry with a different genre");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    
    @GetMapping("/getByRating/{rating}")
    public ResponseEntity<List<BookDTO>> getBooksByRating(@PathVariable int rating) {
        logger.info("Finding books with rating: " + rating);
        List<Book> books = bookRepo.findByRating(rating);

        if (!books.isEmpty()) {
            logger.info("Here is a list of our books with rating " + rating + ".");
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
                bookDTO.setMyCurrentPrice(book.getSellingPrice());
                bookDTO.roundPrices(); // Round the prices
                return bookDTO;
            }).collect(Collectors.toList());
            return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
        } else {
            logger.error("\"" + rating + "\" books not found. Please retry with a different rating");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    
    @GetMapping("/getByCopiesSold")
    public ResponseEntity<List<BookDTO>> getBookByCopiesSold() {
        logger.info("Finding 10 best sellers: ");
        List<Book> books = bookRepo.find10BestSellers();

        if (!books.isEmpty()) {
            logger.info("Here is a list of our 10 best sellers.");
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
                bookDTO.setMyCurrentPrice(book.getSellingPrice());
                bookDTO.roundPrices(); // Round the prices
                return bookDTO;
            }).collect(Collectors.toList());
            return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
        } else {
            logger.error("Books not found. Please retry");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    
    @PatchMapping("/discount/{publisherName}/{discountPercent}")
    public ResponseEntity<List<BookDTO>> updateDiscountByPublisher(@PathVariable String publisherName, @PathVariable float discountPercent) {
        logger.info("Applying a discount of {}% for publisher {}", discountPercent, publisherName);
    
        // Fetch the books to apply the discount
        List<Book> books = bookRepo.findByPublisher(publisherName);
    
        if (!books.isEmpty()) {
            List<BookDTO> bookDTOs = books.stream().map(book -> {
                float originalPrice = book.getPrice();
                float discountedPrice = originalPrice * (1 - discountPercent / 100);
                book.setSellingPrice(discountedPrice);  // Set the discounted price
                bookRepo.save(book); // Save the updated book to the database
                
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
                bookDTO.setMyPrice(originalPrice); // Original Price
                bookDTO.setMyCurrentPrice(discountedPrice); // Discounted Price
                bookDTO.roundPrices(); // Round the prices
                logger.info("Original price: {}, Discounted price: {}", originalPrice, discountedPrice);
                return bookDTO;
            }).collect(Collectors.toList());
    
            logger.info("Here is a list of the discounted books.");
            return ResponseEntity.ok(bookDTOs);
        } else {
            logger.error("Books not found for publisher: {}", publisherName);
            return ResponseEntity.noContent().build();
        }
    }            
}