package app.bookstore.repo;

import org.springframework.data.repository.CrudRepository;

import app.bookstore.domain.Publisher;

public interface PublisherRepo extends CrudRepository<Publisher, Integer> {
}
