package app.bookstore.repo;

import org.springframework.data.repository.CrudRepository;
import app.bookstore.domain.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorRepo extends CrudRepository<Author, Integer> {
    Optional<Author> findByFirstNameAndLastName(String FirstName, String LastName);

    public List<Author> findAllByOrderByAuthorIDAsc();

}
