package com.GeekText.bookstore.repo;

import org.springframework.data.repository.CrudRepository;

import com.GeekText.bookstore.entities.CreditCard;

public interface CreditRepo extends CrudRepository<CreditCard, Long>
{}