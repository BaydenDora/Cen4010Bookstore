package app.bookstore;

import org.springframework.data.repository.CrudRepository;

public interface ShopRepo extends CrudRepository<ShoppingCart, Integer>
{}