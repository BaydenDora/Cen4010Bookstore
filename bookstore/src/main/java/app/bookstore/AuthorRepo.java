package app.bookstore;

import org.springframework.data.repository.CrudRepository;
// import java.util.List;

public interface AuthorRepo extends CrudRepository<Author, Integer> {
    // List<Book> findByBooksWritten(int author_id);
    
}
