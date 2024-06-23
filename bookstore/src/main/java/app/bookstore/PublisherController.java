package app.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherRepo publisherRepo;

    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Integer id) {
        return publisherRepo.findById(id)
                .map(publisher -> ResponseEntity.ok().body(publisher))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Publisher createPublisher(@RequestBody Publisher publisher) {
        return publisherRepo.save(publisher);
    }

    // Additional methods for update and delete can be added here
}
