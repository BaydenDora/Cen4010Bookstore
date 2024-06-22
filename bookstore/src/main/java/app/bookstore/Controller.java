package app.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/books")
public class Controller {

    @Autowired
    private BookRepo bookRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewBook(@RequestParam("isbn") String ISBN,
                                           @RequestParam("title") String title,
                                           @RequestParam("description") String description,
                                           @RequestParam("yearPublished") int yearPublished,
                                           @RequestParam("authorId") int authorId,
                                           @RequestParam("publisherId") int publisherId,
                                           @RequestParam("genre") String genre,
                                           @RequestParam("copiesSold") int copiesSold,
                                           @RequestParam("price") float price) {
        // Create a new book instance
        Book book = new Book();
        book.setISBN(ISBN);
        book.setTitle(title);
        book.setDescription(description);
        book.setYearPublished(yearPublished);

        // Assuming you have a method to find Author and Publisher by ID
        Author author = new Author();
        author.setAuthorID(authorId);
        book.setAuthor(author);

        Publisher publisher = new Publisher();
        publisher.setID(publisherId);
        book.setPublisher(publisher);

        book.setGenre(Genre.valueOf(genre.toUpperCase()));
        book.setCopiesSold(copiesSold);
        book.setPrice(price);

        // Save the book to the repository
        bookRepository.save(book);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
