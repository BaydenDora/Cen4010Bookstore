package com.GeekText.repo;

import org.springframework.data.repository.CrudRepository;

import com.GeekText.bookstore.Author;

public interface AuthorRepo extends CrudRepository<Author, Integer>
{}