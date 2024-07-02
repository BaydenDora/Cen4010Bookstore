package app.bookstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    Optional<Book> findByISBN(String ISBN); // Add this method
    
    @Query (value = "SELECT b FROM Book b WHERE b.myRating >= ?1")
    List<Book> findByRating(int rating); 
    
    @Query(value = "SELECT b FROM Book b WHERE b.myGenre = ?1")
    List<Book> findByGenre(Genre genre);
    
    @Query(value = "SELECT * FROM books ORDER BY copies_sold DESC LIMIT 10", nativeQuery = true)
    List<Book> find10BestSellers(); 
    
    //void discountByPublisher(String publisherName, double discountPercent);
}