package app.bookstore;

import app.bookstore.dto.AuthorDTO;
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

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        if (authorDTO.getBiography() == null || authorDTO.getBiography().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        
        Author author = new Author();
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setBiography(authorDTO.getBiography());

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
        // Set publisherIds if necessary
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
            // Set publisherIds if necessary
            return authorDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(authorDTOs);
    }
}