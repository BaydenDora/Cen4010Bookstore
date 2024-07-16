package app.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.bookstore.domain.Book;
import app.bookstore.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    Optional<Book> findByISBN(String ISBN); 
    
//    @Query (value = "SELECT b FROM Book b WHERE b.myRating >= ?1")
//    List<Book> findByRating(int rating); 
    
    @Query("SELECT b FROM Book b JOIN b.reviews r WHERE r.myRating >= :rating")
    List<Book> findByRating(int rating); 
    
    @Query(value = "SELECT b FROM Book b WHERE b.myGenre = ?1")
    List<Book> findByGenre(Genre genre);
    
    @Query(value = "SELECT * FROM Book ORDER BY CopiesSold DESC LIMIT 10", nativeQuery = true)
    List<Book> find10BestSellers(); 
    
    @Query("SELECT b FROM Book b WHERE b.myPublisher.publisherName = :publisherName")
    List<Book> findByPublisher(String publisherName);
    
    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.mySellingPrice = b.myPrice * (1 - :discountPercent / 100) WHERE b.myPublisher.publisherName = :publisherName")
    void applyDiscountByPublisher(@Param("publisherName") String publisherName, @Param("discountPercent") float discountPercent);    
}