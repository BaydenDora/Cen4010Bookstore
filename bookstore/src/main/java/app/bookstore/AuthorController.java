package app.bookstore;

import app.bookstore.dto.AuthorDTO;
import app.bookstore.dto.BookDTO;
import app.bookstore.repo.AuthorRepo;
import app.bookstore.repo.PublisherRepo;
import app.bookstore.exception.AuthorExistsException;
import app.bookstore.exception.AuthorNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    /**
     * Book Details Feature Task #3:
     * An administrator must be able to create an author 
     * with first name, last name, biography and publisher
     * @param authorDTO
     * @return ResponseEntity<AuthorDTO> containing HTTP status code 201 and the created AuthorDTO
     */
    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        System.out.println("Received AuthorDTO: " + authorDTO); // Add logging to debug
        if (authorDTO.getBiography() == null || authorDTO.getBiography().trim().isEmpty()) {
            System.out.println("Invalid biography: " + authorDTO.getBiography()); // Add logging to debug
            return ResponseEntity.badRequest().body(null);
        }
        Author author = verifyAuthor(authorDTO);

        // Fetch and set publishers
        List<Publisher> publishers = (List<Publisher>) publisherRepo.findAllById(authorDTO.getPublisherIds());
        author.setPublishers(publishers);

        Author savedAuthor = authorRepo.save(author);
        authorDTO.setAuthorID(savedAuthor.getAuthorID());
        return ResponseEntity.status(HttpStatus.CREATED).body(authorDTO);
    }


    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = (List<Author>) authorRepo.findAll();
        List<AuthorDTO> authorDTOs = authors.stream().map(AuthorDTO::new)
                                                    .collect(Collectors.toList());
        return ResponseEntity.ok(authorDTOs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable int id) {
        var author = verifyAuthor(id);
        AuthorDTO authorDTO = new AuthorDTO(author);
        return ResponseEntity.ok(authorDTO);
    }


    /**
     * Book Details Feature Task #4:
     * Must be able to retrieve a list of books associated with an author
     * @param id
     * @return ResponseEntity<BookDTO> containing HTTP status code 200 and the found BookDTO list
     */
    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDTO>> getBooksById(@PathVariable int id) {
        var author = verifyAuthor(id);
        List<BookDTO> bookDTOs = author.getBooksWritten().stream()
                        .map(book -> {
                            BookDTO bookDTO = new BookDTO(book);
                            return bookDTO;
                        }).collect(Collectors.toList());
                
        return ResponseEntity.ok(bookDTOs);
    }

    
    /**
     * Helper method to verify and return Author given an id.
     * @param id the Author identifier
     * @return the found Author
     * @throws AuthorNotFoundException if no Author is found
     */
    private Author verifyAuthor(int id) throws AuthorNotFoundException{
        return authorRepo.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }


    /**
     * Helper method to verify and return Author given an AuthorDTO.
     * @param autorDTO an AuthorDTO
     * @return a newly created Author from the AuthorDTO
     * @throws AuthorExistsException if the Author already exists in the database
     */
    private Author verifyAuthor(AuthorDTO authorDTO) throws AuthorExistsException{
        Optional.of(authorDTO.getAuthorID())
                            .ifPresent(id -> { authorRepo.findById(id)
                            .ifPresent(a -> { throw new AuthorExistsException(id); });});
        
        String firstName = authorDTO.getFirstName(), lastName = authorDTO.getLastName();
        authorRepo.findByFirstNameAndLastName(firstName, lastName)
                .ifPresent(a ->  { throw new AuthorExistsException(firstName, lastName); });

        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBiography(authorDTO.getBiography());
        return author;
    }


}