package app.bookstore;

import org.springframework.data.repository.CrudRepository;

//import java.util.ArrayList;
import java.util.List;

public interface BookRepo extends CrudRepository<book, Integer>, BookRepoCustom 
{
	book findByISBN(long ISBN);
	
	void listBookDetails (long ISBN);
	
	List<book> findByPublisher(publisher publisher);
	
	List<book> findByAuthor(author author);
	
	List<book> findByGenre(String genre);
	
	List<book> findByRating(int rating);
	
	List<book> findTopTenSellers();
	
	void discountByPublisher(publisher publisher);
}

