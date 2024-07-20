package app.bookstore.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.bookstore.domain.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByMyUsername(String username);

    @Query("SELECT u FROM User u WHERE u.myUsername = :username")
    // List<User> findByUsername(String username);
    List<User> findByUsernameList(String username);

}
