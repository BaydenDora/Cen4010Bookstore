package com.GeekText.repo;

import org.springframework.data.repository.CrudRepository;

import com.GeekText.bookstore.Review;

public interface ReviewRepo extends CrudRepository<Review, Integer>
{}