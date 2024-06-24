package com.GeekText.bookstore.repo;

import org.springframework.data.repository.CrudRepository;

import com.GeekText.bookstore.entities.ShoppingCart;

public interface ShopRepo extends CrudRepository<ShoppingCart, Integer>
{}