package app.bookstore;

import org.springframework.data.repository.CrudRepository;

//import java.util.ArrayList;
import java.util.List;

public interface BookRepo extends CrudRepository<Book, Integer>, BookRepoCustom 
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

