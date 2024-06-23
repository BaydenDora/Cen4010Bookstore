package app.bookstore;

import app.bookstore.dto.AuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepo authorRepo;

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Integer id) {
        Optional<Author> author = authorRepo.findById(id);
        if (!author.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Author a = author.get();
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthor_ID(a.getAuthorID());
        authorDTO.setFirstName(a.getFirstName());
        authorDTO.setLastName(a.getLastName());
        authorDTO.setBiography(a.getBiography());
        // Add other necessary mappings if needed

        return ResponseEntity.ok(authorDTO);
    }

    @GetMapping
    public Iterable<Author> getAllAuthors() {
        return authorRepo.findAll();
    }
}
