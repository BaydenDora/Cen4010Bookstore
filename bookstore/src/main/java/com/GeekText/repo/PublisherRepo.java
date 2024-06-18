package com.GeekText.repo;

import org.springframework.data.repository.CrudRepository;

import com.GeekText.bookstore.Publisher;

public interface PublisherRepo extends CrudRepository<Publisher, Integer>
{}