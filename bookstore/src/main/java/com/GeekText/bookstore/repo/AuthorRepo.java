package com.GeekText.bookstore.repo;

import org.springframework.data.repository.CrudRepository;

import com.GeekText.bookstore.entities.Author;

public interface AuthorRepo extends CrudRepository<Author, Integer>
{}