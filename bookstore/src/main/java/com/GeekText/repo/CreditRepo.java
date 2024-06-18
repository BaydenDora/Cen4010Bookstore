package com.GeekText.repo;

import org.springframework.data.repository.CrudRepository;

import com.GeekText.bookstore.CreditCard;

public interface CreditRepo extends CrudRepository<CreditCard, Long>
{}