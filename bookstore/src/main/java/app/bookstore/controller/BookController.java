package app.bookstore.controller;

import app.bookstore.domain.Book;
import app.bookstore.domain.Genre;
import app.bookstore.dto.BookDTO;
import app.bookstore.exception.Author.AuthorNotFoundException;
import app.bookstore.exception.Book.BookExistsException;
import app.bookstore.exception.Book.BookNotFoundException;
import app.bookstore.repo.AuthorRepo;
import app.bookstore.repo.BookRepo;
import app.bookstore.repo.PublisherRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Sort;
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


    /**
     * Book Details Feature Task #1: 
     * An administrator must be able to create a book with the book ISBN, book name, 
     * book description, price, author, genre, publisher, year published and copies sold
     * @param bookDTO an input Book object's arguments in JSON format
     * @return ResponseEntity<BookDTO> containing HTTP status code 201 and the created BookDTO
     */
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Book book = verifyBook(bookDTO);
        Book savedBook = bookRepo.save(book);
        bookDTO.setId(savedBook.getId());
        bookDTO.setMyCurrentPrice(book.getSellingPrice()); // Set current price
        bookDTO.roundPrices(); // Round the prices
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Book book = verifyBook(id);
        BookDTO bookDTO = new BookDTO(book);
        return ResponseEntity.ok(bookDTO);
    }


    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepo.findAll(Sort.by(Sort.Direction.ASC, "id")).forEach(books::add);
        List<BookDTO> bookDTOs = books.stream()
                        .map(BookDTO::new)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOs);
    }

    /**
     * Book Details Feature Task #2:
     * Must be able retrieve a book’s details by the ISBN 
     * @param ISBN a Book's ISBN
     * @return ResponseEntity<BookDTO> containing HTTP status code 200 and the found Book as a BookDTO
     */
    @GetMapping("/ISBN/{ISBN}")
    public ResponseEntity<BookDTO> getBookByISBN(@PathVariable String ISBN) {
        return ResponseEntity.ok(new BookDTO(verifyBook(ISBN)));
    }

    
    @GetMapping("/getByGenre/{genre}")
    public ResponseEntity<List<BookDTO>> getBookByGenre(@PathVariable Genre genre) {
        logger.info("Finding books with genre: " + genre);
        List<Book> books = bookRepo.findByGenre(genre);

        if (!books.isEmpty()) {
            logger.info("Here is a list of our books with genre " + genre + ".");
            List<BookDTO> bookDTOs = books.stream()
                        .map(BookDTO::new)
                        .collect(Collectors.toList());
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
            List<BookDTO> bookDTOs = books.stream()
                        .map(BookDTO::new)
                        .collect(Collectors.toList());
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
            List<BookDTO> bookDTOs = books.stream()
                        .map(BookDTO::new)
                        .collect(Collectors.toList());
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
                BookDTO bookDTO = new BookDTO(book);
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


    /**
     * Helper method to verify and return Book given an id.
     * @param id the Book Identifier
     * @return the found Book
     * @throws BookNotFoundException if no Book is found
     */
    private Book verifyBook(Long id) throws BookNotFoundException{
        return bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    /**
     * Helper method to verify and return Book given an ISBN.
     * @param ISBN the Book ISBN
     * @return the found Book
     * @throws BookNotFoundException if no Book is found
     */
    private Book verifyBook(String ISBN) throws BookNotFoundException{
        return bookRepo.findByIsbn(ISBN)
                .orElseThrow(() -> new BookNotFoundException(ISBN));
    }
    
    /**
     * Helper method to verify and return Book given a BookDTO.
     * @param bookDTO a JSON-formatted BookDTO passed in the request
     * @return a newly created Book from the BookDTO
     * @throws BookExistsException if the Book already exists in the database
     */
    private Book verifyBook(BookDTO bookDTO) throws BookExistsException{
        int auhthorId = bookDTO.getMyAuthorId();
        var author = AuthorRepo.findById(auhthorId).orElseThrow(() -> new AuthorNotFoundException(auhthorId));
        var publisher = publisherRepo.findById(bookDTO.getMyPublisherId())
                    .orElseThrow(() -> new RuntimeException("Publisher not found"));
            
        Optional.of(bookDTO.getId())
                    .ifPresent(id -> { bookRepo.findById(id)
                    .ifPresent(book -> { throw new BookExistsException(id); });});
        
        var isbn = bookDTO.getIsbn();
        bookRepo.findByIsbn(isbn)
                    .ifPresent(book ->  { throw new BookExistsException(isbn); });
        
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(bookDTO.getMyTitle());
        book.setDescription(bookDTO.getMyDescription());
        book.setYearPublished(bookDTO.getMyYearPublished());
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setGenre(Genre.valueOf(bookDTO.getMyGenre()));
        book.setCopiesSold(bookDTO.getMyCopiesSold());
        book.setPrice(bookDTO.getMyPrice());
        return book;
    }

}