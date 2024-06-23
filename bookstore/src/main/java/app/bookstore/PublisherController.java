package app.bookstore;

import app.bookstore.dto.PublisherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherRepo publisherRepo;

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable Integer id) {
        Optional<Publisher> publisher = publisherRepo.findById(id);
        if (!publisher.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Publisher p = publisher.get();
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setPublisher_ID(p.getID());
        publisherDTO.setPublisherName(p.getName());
        // Add other necessary mappings if needed

        return ResponseEntity.ok(publisherDTO);
    }

    @GetMapping
    public Iterable<Publisher> getAllPublishers() {
        return publisherRepo.findAll();
    }
}