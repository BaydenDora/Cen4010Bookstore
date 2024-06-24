package com.GeekText.bookstore.repo;

import org.springframework.data.repository.CrudRepository;

import com.GeekText.bookstore.entities.Review;

public interface ReviewRepo extends CrudRepository<Review, Integer>
{}