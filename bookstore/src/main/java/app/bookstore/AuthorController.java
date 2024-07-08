package app.bookstore;

import app.bookstore.dto.AuthorDTO;
import app.bookstore.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepo authorRepo;
    
    @Autowired
    private PublisherRepo publisherRepo;

    /*  Book Details Feature Task #3:
        An administrator must be able to create an author with 
        first name, last name, biography and publisher      */
    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        System.out.println("Received AuthorDTO: " + authorDTO); // Add logging to debug

        if (authorDTO.getBiography() == null || authorDTO.getBiography().trim().isEmpty()) {
            System.out.println("Invalid biography: " + authorDTO.getBiography()); // Add logging to debug
            return ResponseEntity.badRequest().body(null);
        }

        Author author = new Author();
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setBiography(authorDTO.getBiography());

        // Fetch and set publishers
        List<Publisher> publishers = (List<Publisher>) publisherRepo.findAllById(authorDTO.getPublisherIds());
        author.setPublishers(publishers);

        Author savedAuthor = authorRepo.save(author);

        authorDTO.setAuthorID(savedAuthor.getAuthorID());
        return ResponseEntity.ok(authorDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable int id) {
        Optional<Author> author = authorRepo.findById(id);
        if (!author.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorID(author.get().getAuthorID());
        authorDTO.setFirstName(author.get().getFirstName());
        authorDTO.setLastName(author.get().getLastName());
        authorDTO.setBiography(author.get().getBiography());

        List<Integer> publisherIds = author.get().getPublishers().stream()
                .map(Publisher::getID)
                .collect(Collectors.toList());
        authorDTO.setPublisherIds(publisherIds);

        return ResponseEntity.ok(authorDTO);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = (List<Author>) authorRepo.findAll();

        List<AuthorDTO> authorDTOs = authors.stream().map(author -> {
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setAuthorID(author.getAuthorID());
            authorDTO.setFirstName(author.getFirstName());
            authorDTO.setLastName(author.getLastName());
            authorDTO.setBiography(author.getBiography());

            List<Integer> publisherIds = author.getPublishers().stream()
                    .map(Publisher::getID)
                    .collect(Collectors.toList());
            authorDTO.setPublisherIds(publisherIds);

            return authorDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(authorDTOs);
    }

    /*  Book Details Feature Task #4:
        Must be able to retrieve a list of books associated with an author  */
    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDTO>> getBooksById(@PathVariable int id) {
        Optional<Author> author = authorRepo.findById(id);
        if (!author.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<Book> books = author.get().getBooksWritten();

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