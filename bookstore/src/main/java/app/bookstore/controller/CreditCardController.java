package app.bookstore.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.bookstore.domain.CreditCard;
import app.bookstore.domain.User;
import app.bookstore.dto.CreditCardDTO;
import app.bookstore.exception.CreditCard.CreditCardExistsException;
import app.bookstore.exception.CreditCard.CreditCardNotFoundException;
import app.bookstore.exception.User.UserExistsException;
import app.bookstore.exception.User.UserNotFoundException;
import app.bookstore.repo.CreditCardRepo;
import app.bookstore.repo.UserRepo;

@RestController
@RequestMapping("/users/{username}/creditcards")
public class CreditCardController {

    @Autowired
    private CreditCardRepo creditCardRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public ResponseEntity<CreditCardDTO> createCreditCard(@RequestBody CreditCardDTO creditCardDTO) {
        System.out.println("Received CreditCardDTO: " + creditCardDTO); // Add logging to debug
        CreditCard savedCreditCard = creditCardRepo.save(verifyCreditCard(creditCardDTO));
        creditCardDTO.setCardID(savedCreditCard.getCardID());
        return ResponseEntity.status(HttpStatus.CREATED).body(creditCardDTO);
    }

    @GetMapping("/cardid/{id}")
    public ResponseEntity<CreditCardDTO> getCreditCardById(@PathVariable Long id) {
        return ResponseEntity.ok(new CreditCardDTO(verifyCreditCard(id)));
    }

    @GetMapping()
    public ResponseEntity<List<CreditCardDTO>> getAllCreditCardsForUser(@PathVariable String username) {
        List<CreditCard> creditCards = creditCardRepo.findByUsername(username);

        List<CreditCardDTO> creditCardDTOs = creditCards.stream()
                    .map(CreditCardDTO::new)
                    .collect(Collectors.toList());
        return ResponseEntity.ok(creditCardDTOs);
    }


     private CreditCard verifyCreditCard(long id) throws CreditCardNotFoundException{
        return creditCardRepo.findById(id)
                    .orElseThrow(() -> new CreditCardNotFoundException(id));
    }


    private CreditCard verifyCreditCard(CreditCardDTO creditCardDTO) throws UserExistsException{
        int userID = creditCardDTO.getUserID();
        User user = userRepo.findById(userID)
                        .orElseThrow(() -> new UserNotFoundException(userID));

        var cardNumber = creditCardDTO.getCardNumber();
        Optional.of(cardNumber)
                .ifPresent(cardNum -> { throw new CreditCardExistsException(cardNum); });
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(cardNumber);
        creditCard.setExpirationDate(creditCardDTO.getExpirationDate());
        creditCard.setCVV(creditCardDTO.getCvv());
        creditCard.setUser(user);
        return creditCard;
    }

}