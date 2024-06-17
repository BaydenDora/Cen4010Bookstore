package app.bookstore;

import org.springframework.data.repository.CrudRepository;

public interface CreditRepo extends CrudRepository<CreditCard, Long>
{}