package app.bookstore;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book, Long>
{
	Book findByISBN(long ISBN);
	
	void listBookDetails (long ISBN);
	
	List<Book> findByPublisher(Publisher publisher);
	
	List<Book> findByAuthor(Author author);
	
	List<Book> findByGenre(String genre);
	
	List<Book> findByRating(int rating);
	
	List<Book> findTopTenSellers();
	
	void discountByPublisher(Publisher publisher);
}





