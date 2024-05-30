package app.bookstore;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<book, java.math.BigInteger> {
    
}

