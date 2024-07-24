package app.bookstore.controller;

import app.bookstore.domain.Author;
import app.bookstore.domain.Publisher;
import app.bookstore.dto.AuthorDTO;
import app.bookstore.dto.BookDTO;
import app.bookstore.exception.NullValueException;
import app.bookstore.exception.Author.AuthorExistsException;
import app.bookstore.exception.Author.AuthorNotFoundException;
import app.bookstore.repo.AuthorRepo;
import app.bookstore.repo.PublisherRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
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
     * @param authorDTO a JSON-formatted AuthorDTO passed in the request
     * @return ResponseEntity<AuthorDTO> containing HTTP status code 201 and the created AuthorDTO
     */
    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        Author savedAuthor = authorRepo.save(verifyAuthor(authorDTO));
        authorDTO.setAuthorID(savedAuthor.getAuthorID());
        return ResponseEntity.status(HttpStatus.CREATED).body(authorDTO);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = (List<Author>) authorRepo.findAll();
        List<AuthorDTO> authorDTOs = authors.stream()
                            .map(AuthorDTO::new)
                            .collect(Collectors.toList());
        return ResponseEntity.ok(authorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable int id) {
        return ResponseEntity.ok(new AuthorDTO(verifyAuthor(id)));
    }

    /**
     * Book Details Feature Task #4:
     * Must be able to retrieve a list of books associated with an author
     * @param id a unique Book identifier 
     * @return ResponseEntity<BookDTO> containing HTTP status code 200 and the found BookDTO list
     */
    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDTO>> getBooksById(@PathVariable int id) {
        List<BookDTO> bookDTOs = verifyAuthor(id).getBooksWritten().stream()
                            .map(BookDTO::new)
                            .collect(Collectors.toList());
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
     * @param autorDTO
     * @return a newly created Author from the AuthorDTO
     * @throws AuthorExistsException if the Author already exists in the database
     */
    private Author verifyAuthor(AuthorDTO authorDTO) throws AuthorExistsException{
        Optional.of(authorDTO.getAuthorID()).ifPresent(id -> { 
            authorRepo.findById(id).ifPresent(author -> { 
                throw new AuthorExistsException(id); });});
        
        String firstName = authorDTO.getFirstName(), lastName = authorDTO.getLastName();
        authorRepo.findByFirstNameAndLastName(firstName, lastName)
                .ifPresent(author ->  { throw new AuthorExistsException(firstName, lastName); });

        String biography = authorDTO.getBiography();
        if (biography == null || biography.trim().isEmpty()) {
            System.out.println("Invalid biography: " + authorDTO.getBiography()); // Add logging to debug
            throw new NullValueException("Biography");
        }

        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBiography(authorDTO.getBiography());

        // Fetch and set publishers
        List<Publisher> publishers = Streamable.of(publisherRepo.findAllById(authorDTO.getPublisherIDs())).toList();
        author.setPublishers(publishers);
        
        return author;
    }

}