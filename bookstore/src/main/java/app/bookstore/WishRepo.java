package app.bookstore;

import org.springframework.data.repository.CrudRepository;

public interface WishRepo extends CrudRepository<Wishlist, Integer>
{}