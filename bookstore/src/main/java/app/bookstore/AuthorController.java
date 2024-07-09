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
        Optional<Author> aut = authorRepo.findById(id);
        if (!aut.isPresent()) 
            return ResponseEntity.notFound().build();
        
        var author = aut.get();
        AuthorDTO authorDTO = new AuthorDTO(
            author.getAuthorID(),
            author.getFirstName(),
            author.getLastName(),
            author.getBiography(),
            author.getPublishers().stream()
                        .map(Publisher::getID)
                        .collect(Collectors.toList())
        );
        return ResponseEntity.ok(authorDTO);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = (List<Author>) authorRepo.findAll();

        List<AuthorDTO> authorDTOs = authors.stream().map(author -> {
            AuthorDTO authorDTO = new AuthorDTO(
                author.getAuthorID(),
                author.getFirstName(),
                author.getLastName(),
                author.getBiography(),
                author.getPublishers().stream()
                            .map(Publisher::getID)
                            .collect(Collectors.toList())
            );
            return authorDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(authorDTOs);
    }

    /*  Book Details Feature Task #4:
        Must be able to retrieve a list of books associated with an author  */
    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDTO>> getBooksById(@PathVariable int id) {
        Optional<Author> author = authorRepo.findById(id);
        if (!author.isPresent()) 
            return ResponseEntity.notFound().build();
        
        List<BookDTO> bookDTOs = author.get().getBooksWritten().stream()
                .map(book -> {
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