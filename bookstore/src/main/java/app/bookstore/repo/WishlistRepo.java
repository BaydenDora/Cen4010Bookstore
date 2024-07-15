package app.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.bookstore.Wishlist;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {
}
