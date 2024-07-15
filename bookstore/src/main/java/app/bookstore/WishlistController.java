package app.bookstore;

import app.bookstore.dto.BookDTO;
import app.bookstore.dto.WishlistDTO;
import app.bookstore.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
        Optional<User> user = userRepo.findById(wishlistDTO.getUserID());
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user.get());
        wishlist.setWishlistName(wishlistDTO.getWishlistName());

        List<Book> books = wishlistDTO.getBookISBNs().stream()
                .map(isbn -> bookRepo.findByISBN(isbn).orElse(null))
                .collect(Collectors.toList());

        wishlist.setBooksInWishlist(books);

        Wishlist savedWishlist = wishlistRepo.save(wishlist);

        wishlistDTO.setWishlistID(savedWishlist.getWishlistID());
        return ResponseEntity.ok(wishlistDTO);
    }

    // Method to get a wishlist by its ID
    @GetMapping("/{id}")
    public ResponseEntity<WishlistDTO> getWishlistById(@PathVariable int id) {
        Optional<Wishlist> wishlist = wishlistRepo.findById(id);
        if (!wishlist.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setWishlistID(wishlist.get().getWishlistID());
        wishlistDTO.setWishlistName(wishlist.get().getWishlistName());
        wishlistDTO.setUserID(wishlist.get().getUser().getUserID());
        wishlistDTO.setBookISBNs(wishlist.get().getBooksInWishlist().stream()
                .map(Book::getISBN)
                .collect(Collectors.toList()));

        return ResponseEntity.ok(wishlistDTO);
    }

    // Method to get all wishlists
    @GetMapping
    public ResponseEntity<List<WishlistDTO>> getAllWishlists() {
        List<Wishlist> wishlists = wishlistRepo.findAll();

        List<WishlistDTO> wishlistDTOs = wishlists.stream().map(wishlist -> {
            WishlistDTO wishlistDTO = new WishlistDTO();
            wishlistDTO.setWishlistID(wishlist.getWishlistID());
            wishlistDTO.setWishlistName(wishlist.getWishlistName());
            wishlistDTO.setUserID(wishlist.getUser().getUserID());
            wishlistDTO.setBookISBNs(wishlist.getBooksInWishlist().stream()
                    .map(Book::getISBN)
                    .collect(Collectors.toList()));
            return wishlistDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(wishlistDTOs);
    }

    // Method to get all books in a wishlist by wishlist ID
    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDTO>> getBooksInWishlist(@PathVariable int id) {
        Optional<Wishlist> wishlist = wishlistRepo.findById(id);
        if (!wishlist.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        List<BookDTO> booksInWishlist = wishlist.get().getBooksInWishlist().stream().map(book -> {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setIsbn(book.getISBN());
            bookDTO.setMyTitle(book.getTitle());
            bookDTO.setMyDescription(book.getDescription());
            bookDTO.setMyYearPublished(book.getYearPublished());
            bookDTO.setMyAuthorId(book.getAuthor().getAuthorID());
            bookDTO.setMyPublisherId(book.getPublisher().getID());
            bookDTO.setMyGenre(book.getGenre().name());
            bookDTO.setMyCopiesSold(book.getCopiesSold());
            bookDTO.setMyPrice(book.getPrice());
            return bookDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(booksInWishlist);
    }

    // Method to add a book to a wishlist
    @PostMapping("/{wishlistId}/books/{isbn}")
    public ResponseEntity<Void> addBookToWishlist(@PathVariable int wishlistId, @PathVariable String isbn) {
        Optional<Wishlist> wishlistOptional = wishlistRepo.findById(wishlistId);
        if (!wishlistOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Book> bookOptional = bookRepo.findByISBN(isbn);
        if (!bookOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Wishlist wishlist = wishlistOptional.get();
        Book book = bookOptional.get();

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
        Optional<Wishlist> wishlistOptional = wishlistRepo.findById(wishlistId);
        if (!wishlistOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Book> bookOptional = bookRepo.findByISBN(isbn);
        if (!bookOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Wishlist wishlist = wishlistOptional.get();
        Book book = bookOptional.get();

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
}