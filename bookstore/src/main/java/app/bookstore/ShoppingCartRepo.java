package app.bookstore;

import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepo extends CrudRepository<ShoppingCart, Long> {

}