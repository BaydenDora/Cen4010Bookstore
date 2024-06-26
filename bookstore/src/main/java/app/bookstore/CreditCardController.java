package app.bookstore;

import app.bookstore.dto.CreditCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/creditcards")
public class CreditCardController {

    @Autowired
    private CreditCardRepo creditCardRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public ResponseEntity<CreditCardDTO> createCreditCard(@RequestBody CreditCardDTO creditCardDTO) {
        System.out.println("Received CreditCardDTO: " + creditCardDTO); // Add logging to debug

        Optional<User> user = userRepo.findById(creditCardDTO.getUserID());
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(null); // Validate input
        }

        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(creditCardDTO.getCardNumber());
        creditCard.setExpirationDate(creditCardDTO.getExpirationDate());
        creditCard.setCVV(creditCardDTO.getCvv());
        creditCard.setUser(user.get());

        CreditCard savedCreditCard = creditCardRepo.save(creditCard);

        creditCardDTO.setCardID(savedCreditCard.getCardID());
        return ResponseEntity.ok(creditCardDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardDTO> getCreditCardById(@PathVariable Long id) {
        Optional<CreditCard> creditCard = creditCardRepo.findById(id);
        if (!creditCard.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setCardID(creditCard.get().getCardID());
        creditCardDTO.setCardNumber(creditCard.get().getCardNumber());
        creditCardDTO.setExpirationDate(creditCard.get().getExpirationDate());
        creditCardDTO.setCvv(creditCard.get().getCVV());
        creditCardDTO.setUserID(creditCard.get().getUser().getUserID());

        return ResponseEntity.ok(creditCardDTO);
    }

    @GetMapping
    public ResponseEntity<List<CreditCardDTO>> getAllCreditCards() {
        List<CreditCard> creditCards = creditCardRepo.findAll();

        List<CreditCardDTO> creditCardDTOs = creditCards.stream().map(creditCard -> {
            CreditCardDTO creditCardDTO = new CreditCardDTO();
            creditCardDTO.setCardID(creditCard.getCardID());
            creditCardDTO.setCardNumber(creditCard.getCardNumber());
            creditCardDTO.setExpirationDate(creditCard.getExpirationDate());
            creditCardDTO.setCvv(creditCard.getCVV());
            creditCardDTO.setUserID(creditCard.getUser().getUserID());

            return creditCardDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(creditCardDTOs);
    }
}