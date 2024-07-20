package app.bookstore.controller;

import app.bookstore.domain.Book;
import app.bookstore.domain.ShoppingCart;
import app.bookstore.domain.User;
import app.bookstore.domain.Wishlist;
import app.bookstore.dto.BookDTO;
import app.bookstore.dto.WishlistDTO;
import app.bookstore.exception.Book.BookNotFoundException;
import app.bookstore.exception.User.UserNotFoundException;
import app.bookstore.exception.Wishlist.WishlistNotFoundException;
import app.bookstore.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wishlists")
public class WishlistController {

    @Autowired
    private WishlistRepo wishlistRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    // Method to create a new wishlist
    @PostMapping
    public ResponseEntity<WishlistDTO> createWishlist(@RequestBody WishlistDTO wishlistDTO) {
        Wishlist wishlist = verifyWishlist(wishlistDTO);
        Wishlist savedWishlist = wishlistRepo.save(wishlist);
        wishlistDTO.setWishlistID(savedWishlist.getWishlistID());
        return ResponseEntity.status(HttpStatus.CREATED).body(wishlistDTO);
    }

    // Method to get a wishlist by its ID
    @GetMapping("/{id}")
    public ResponseEntity<WishlistDTO> getWishlistById(@PathVariable int id) {
        Wishlist wishlist = verifyWishlist(id);
        WishlistDTO wishlistDTO = new WishlistDTO(wishlist);
        return ResponseEntity.ok(wishlistDTO);
    }

    // Method to get all wishlists
    @GetMapping
    public ResponseEntity<List<WishlistDTO>> getAllWishlists() {
        List<Wishlist> wishlists = wishlistRepo.findAll();

        List<WishlistDTO> wishlistDTOs = wishlists.stream()
                            .map(WishlistDTO::new)
                            .collect(Collectors.toList());

        return ResponseEntity.ok(wishlistDTOs);
    }

    // Method to get all books in a wishlist by wishlist ID
    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDTO>> getBooksInWishlist(@PathVariable int id) {
        Wishlist wishlist = verifyWishlist(id);

        List<BookDTO> booksInWishlist = wishlist.getBooksInWishlist().stream()
                            .map(BookDTO::new)
                            .collect(Collectors.toList());

        return ResponseEntity.ok(booksInWishlist);
    }

    // Method to add a book to a wishlist
    @PostMapping("/{wishlistId}/books/{isbn}")
    public ResponseEntity<Void> addBookToWishlist(@PathVariable int wishlistId, @PathVariable String isbn) {
        Wishlist wishlist = verifyWishlist(wishlistId);
        Book book = bookRepo.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));

        List<Book> books = wishlist.getBooksInWishlist();
        if (!books.contains(book)) {
            books.add(book);
            wishlist.setBooksInWishlist(books);
            wishlistRepo.save(wishlist);
        }

        return ResponseEntity.ok().build();
    }

    // Method to remove a book from a wishlist and add it to the shopping cart
    @DeleteMapping("/{wishlistId}/books/{isbn}")
    public ResponseEntity<Void> removeBookFromWishlist(@PathVariable int wishlistId, @PathVariable String isbn) {
        Wishlist wishlist = verifyWishlist(wishlistId);
        Book book = bookRepo.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));

        List<Book> books = wishlist.getBooksInWishlist();
        if (books.contains(book)) {
            books.remove(book);
            wishlist.setBooksInWishlist(books);
            wishlistRepo.save(wishlist);

            // Add book to the user's shopping cart
            ShoppingCart shoppingCart = shoppingCartRepo.findById(wishlist.getUser().getUserID())
                    .orElse(new ShoppingCart());
            shoppingCart.setUser(wishlist.getUser());

            // Check if the book is already in the shopping cart
            if (!shoppingCart.getBooksInCart().contains(book)) {
                shoppingCart.getBooksInCart().add(book);
                shoppingCartRepo.save(shoppingCart);
            }
        }

        return ResponseEntity.ok().build();
    }


    private Wishlist verifyWishlist(int id) throws WishlistNotFoundException {
        return wishlistRepo.findById(id)
                .orElseThrow(() -> new WishlistNotFoundException(id));
    }


    private Wishlist verifyWishlist(WishlistDTO wishlistDTO) throws UserNotFoundException {
        int userID = wishlistDTO.getUserID();
        User user = userRepo.findById(userID)
                    .orElseThrow(() -> new UserNotFoundException(userID));
        
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setWishlistName(wishlistDTO.getWishlistName());

        List<Book> books = wishlistDTO.getBookISBNs().stream()
                .map(isbn -> bookRepo.findByIsbn(isbn).orElse(null))
                .collect(Collectors.toList());
        wishlist.setBooksInWishlist(books);
        
        return wishlist;
    }

}