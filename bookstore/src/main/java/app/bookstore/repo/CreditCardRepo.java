package app.bookstore.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.bookstore.domain.CreditCard;

@Repository
public interface CreditCardRepo extends JpaRepository<CreditCard, Long> {

    @Query("SELECT c FROM CreditCard c WHERE c.myUserID.myUsername = :username")
    List<CreditCard> findByUsername(String username);
}
