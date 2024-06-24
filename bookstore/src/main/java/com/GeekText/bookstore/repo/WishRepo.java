package com.GeekText.bookstore.repo;

import org.springframework.data.repository.CrudRepository;

import com.GeekText.bookstore.entities.Wishlist;

public interface WishRepo extends CrudRepository<Wishlist, Integer>
{}