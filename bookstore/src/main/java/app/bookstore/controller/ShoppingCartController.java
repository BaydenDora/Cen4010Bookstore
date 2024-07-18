package app.bookstore.controller;

import app.bookstore.domain.Book;
import app.bookstore.domain.ShoppingCart;
import app.bookstore.domain.User;
import app.bookstore.dto.ShoppingCartDTO;
import app.bookstore.repo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<User> user = userRepo.findById(shoppingCartDTO.getUserID());
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user.get());

        List<Book> books = shoppingCartDTO.getBookISBNs().stream()
                .map(isbn -> bookRepo.findByIsbn(isbn).orElse(null))
                .collect(Collectors.toList());

        shoppingCart.setBooksInCart(books);

        ShoppingCart savedCart = shoppingCartRepo.save(shoppingCart);

        shoppingCartDTO.setCartID(savedCart.getCartID());
        return ResponseEntity.ok(shoppingCartDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> getShoppingCartById(@PathVariable int id) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepo.findById(id);
        if (!shoppingCart.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setCartID(shoppingCart.get().getCartID());
        shoppingCartDTO.setUserID(shoppingCart.get().getUser().getUserID());
        shoppingCartDTO.setBookISBNs(shoppingCart.get().getBooksInCart().stream()
                .map(Book::getIsbn)
                .collect(Collectors.toList()));

        return ResponseEntity.ok(shoppingCartDTO);
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCartDTO>> getAllShoppingCarts() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepo.findAll();

        List<ShoppingCartDTO> shoppingCartDTOs = shoppingCarts.stream().map(shoppingCart -> {
            ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
            shoppingCartDTO.setCartID(shoppingCart.getCartID());
            shoppingCartDTO.setUserID(shoppingCart.getUser().getUserID());
            shoppingCartDTO.setBookISBNs(shoppingCart.getBooksInCart().stream()
                    .map(Book::getIsbn)
                    .collect(Collectors.toList()));
            return shoppingCartDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(shoppingCartDTOs);
    }
}