package app.bookstore;

import org.springframework.data.repository.CrudRepository;

public interface WishlistRepo extends CrudRepository<Wishlist, Long> {

}