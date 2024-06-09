package com.GeekText.bookstore;

// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.GeekText.bookstore.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

// public interface UserRepository extends CrudRepository<User, Integer> { }

public interface UserRepository extends JpaRepository<User, Long> { }