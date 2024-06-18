package com.GeekText.repo;

import org.springframework.data.repository.CrudRepository;

import com.GeekText.bookstore.Wishlist;

public interface WishRepo extends CrudRepository<Wishlist, Integer>
{}