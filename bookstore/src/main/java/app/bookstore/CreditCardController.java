package app.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creditcards")
public class CreditCardController {

    @Autowired
    private CreditCardRepo creditCardRepo;

    @GetMapping
    public List<CreditCard> getAllCreditCards() {
        return creditCardRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCard> getCreditCardById(@PathVariable Long id) {
        return creditCardRepo.findById(id)
                .map(creditCard -> ResponseEntity.ok().body(creditCard))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CreditCard createCreditCard(@RequestBody CreditCard creditCard) {
        return creditCardRepo.save(creditCard);
    }

    // Additional methods for update and delete can be added here
}
