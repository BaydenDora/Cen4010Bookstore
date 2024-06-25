package app.bookstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    Optional<Book> findByISBN(String ISBN); // Add this method
    
    @Query (value = "SELECT b FROM Book b WHERE b.rating >= ?1")
    List<Book> findByRating(int rating); 
    List<Book> findByGenre(Genre genre); 
}