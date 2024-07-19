package app.bookstore.controller;

import app.bookstore.domain.Book;
import app.bookstore.domain.Publisher;
import app.bookstore.dto.PublisherDTO;
import app.bookstore.exception.NullValueException;
import app.bookstore.exception.Publisher.PublisherExistsException;
import app.bookstore.exception.Publisher.PublisherNotFoundException;
import app.bookstore.repo.PublisherRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherRepo publisherRepo;

    @PostMapping
    public ResponseEntity<PublisherDTO> createPublisher(@RequestBody PublisherDTO publisherDTO) {
        System.out.println("Received PublisherDTO: " + publisherDTO); 
        Publisher savedPublisher = publisherRepo.save(verifyPublisher(publisherDTO));
        publisherDTO.setPublisherID(savedPublisher.getID());
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable int id) {
        Publisher publisher = verifyPublisher(id);
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setPublisherID(publisher.getID());
        publisherDTO.setPublisherName(publisher.getName());

        List<Long> booksPublishedIds = publisher.getBooks().stream()
                .map(Book::getId)
                .collect(Collectors.toList());
        publisherDTO.setBooksPublished(booksPublishedIds);

        return ResponseEntity.ok(publisherDTO);
    }

    @GetMapping
    public ResponseEntity<List<PublisherDTO>> getAllPublishers() {
        List<PublisherDTO> publisherDTOs = ((List<Publisher>) publisherRepo.findAll()).stream()
                                .map(PublisherDTO::new)
                                .collect(Collectors.toList());
        return ResponseEntity.ok(publisherDTOs);
    }


    private Publisher verifyPublisher(int id) throws PublisherNotFoundException {
        return publisherRepo.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }


    private Publisher verifyPublisher(PublisherDTO publisherDTO) throws PublisherExistsException {
        String publisherName = publisherDTO.getPublisherName();
        if (publisherName == null || publisherName.isEmpty())
            throw new NullValueException("Publisher Name"); 
        
        publisherRepo.findByPublisherName(publisherName)
                .ifPresent(publisher ->  { throw new PublisherExistsException(publisherName); });

        Publisher publisher;
        int publisherID =  publisherDTO.getPublisherID();
        if(publisherID > 0){
            publisherRepo.findById(publisherID)
                .ifPresent(p -> { throw new PublisherExistsException(publisherID); });
            publisher = new Publisher(publisherID, publisherName);
        } 
        else {
            publisher = new Publisher();
            publisher.setName(publisherName);
        }
        return publisher;
    }

}