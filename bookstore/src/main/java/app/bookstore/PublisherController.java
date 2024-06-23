package app.bookstore;

import app.bookstore.dto.PublisherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherRepo publisherRepo;

    @PostMapping
    public ResponseEntity<PublisherDTO> createPublisher(@RequestBody PublisherDTO publisherDTO) {
        System.out.println("Received PublisherDTO: " + publisherDTO); 

        if (publisherDTO.getPublisherName() == null || publisherDTO.getPublisherName().isEmpty()) {
            System.out.println("Invalid publisher name"); 
            return ResponseEntity.badRequest().body(null); 
        }

        Publisher publisher = new Publisher();
        publisher.setName(publisherDTO.getPublisherName());

        Publisher savedPublisher = publisherRepo.save(publisher);

        publisherDTO.setPublisherID(savedPublisher.getID());
        return ResponseEntity.ok(publisherDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable int id) {
        Optional<Publisher> publisher = publisherRepo.findById(id);
        if (!publisher.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setPublisherID(publisher.get().getID());
        publisherDTO.setPublisherName(publisher.get().getName());

        List<Integer> booksPublishedIds = publisher.get().getBooks().stream()
                .map(book -> Math.toIntExact(book.getId()))
                .collect(Collectors.toList());
        publisherDTO.setBooksPublished(booksPublishedIds);

        return ResponseEntity.ok(publisherDTO);
    }

    @GetMapping
    public ResponseEntity<List<PublisherDTO>> getAllPublishers() {
        List<Publisher> publishers = (List<Publisher>) publisherRepo.findAll();

        List<PublisherDTO> publisherDTOs = publishers.stream().map(publisher -> {
            PublisherDTO publisherDTO = new PublisherDTO();
            publisherDTO.setPublisherID(publisher.getID());
            publisherDTO.setPublisherName(publisher.getName());

            List<Integer> booksPublishedIds = publisher.getBooks().stream()
                    .map(book -> Math.toIntExact(book.getId()))
                    .collect(Collectors.toList());
            publisherDTO.setBooksPublished(booksPublishedIds);

            return publisherDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(publisherDTOs);
    }
}