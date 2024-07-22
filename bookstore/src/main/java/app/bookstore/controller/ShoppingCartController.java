package app.bookstore.controller;

import java.util.List;
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

import app.bookstore.domain.Book;
import app.bookstore.domain.ShoppingCart;
import app.bookstore.domain.User;
import app.bookstore.dto.ShoppingCartDTO;
import app.bookstore.exception.ShoppingCart.ShoppingCartNotFoundException;
import app.bookstore.exception.User.UserNotFoundExceptionID;
import app.bookstore.repo.BookRepo;
import app.bookstore.repo.ShoppingCartRepo;
import app.bookstore.repo.UserRepo;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public ResponseEntity<ShoppingCartDTO> createShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart savedCart = shoppingCartRepo.save(verifyShoppingCart(shoppingCartDTO));
        shoppingCartDTO.setCartID(savedCart.getCartID());
        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCartDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> getShoppingCartById(@PathVariable int id) {
        return ResponseEntity.ok(new ShoppingCartDTO(verifyShoppingCart(id)));
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCartDTO>> getAllShoppingCarts() {
        List<ShoppingCartDTO> shoppingCartDTOs = shoppingCartRepo.findAll().stream()
                    .map(ShoppingCartDTO::new)
                    .collect(Collectors.toList());
        return ResponseEntity.ok(shoppingCartDTOs);
    }


    private ShoppingCart verifyShoppingCart(int id) throws ShoppingCartNotFoundException {
        return shoppingCartRepo.findById(id)
                .orElseThrow(() -> new ShoppingCartNotFoundException(id));
    }

    private ShoppingCart verifyShoppingCart(ShoppingCartDTO shoppingCartDTO) throws UserNotFoundExceptionID {
        User user = userRepo.findById(shoppingCartDTO.getUserID())
                    .orElseThrow(() -> new UserNotFoundExceptionID(shoppingCartDTO.getUserID()));

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        List<Book> books = shoppingCartDTO.getBookISBNs().stream()
                .map(isbn -> bookRepo.findByIsbn(isbn).orElse(null))
                .collect(Collectors.toList());
        shoppingCart.setBooksInCart(books);
        
        return shoppingCart;
    }
    
}