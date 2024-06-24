package com.GeekText.bookstore.repo;
import org.springframework.data.repository.CrudRepository;

import com.GeekText.bookstore.entities.User;

public interface UserRepo extends CrudRepository<User, Integer>
{}