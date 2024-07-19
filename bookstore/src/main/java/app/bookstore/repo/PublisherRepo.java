package app.bookstore.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import app.bookstore.domain.Publisher;

public interface PublisherRepo extends CrudRepository<Publisher, Integer> {

    Optional<Publisher> findByPublisherName(String publisherName);

}
