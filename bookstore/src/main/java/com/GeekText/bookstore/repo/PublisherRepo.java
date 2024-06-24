package com.GeekText.bookstore.repo;

import org.springframework.data.repository.CrudRepository;

import com.GeekText.bookstore.entities.Publisher;

public interface PublisherRepo extends CrudRepository<Publisher, Integer>
{}