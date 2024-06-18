package com.GeekText.repo;

import org.springframework.data.repository.CrudRepository;

import com.GeekText.bookstore.ShoppingCart;

public interface ShopRepo extends CrudRepository<ShoppingCart, Integer>
{}