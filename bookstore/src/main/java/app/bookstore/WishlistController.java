package app.bookstore;

import app.bookstore.dto.BookDTO;
import app.bookstore.dto.WishlistDTO;
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

    @PostMapping
    public ResponseEntity<WishlistDTO> createWishlist(@RequestBody WishlistDTO wishlistDTO) {
        Optional<User> user = userRepo.findById(wishlistDTO.getUserID());
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user.get());

        List<Book> books = wishlistDTO.getBookISBNs().stream()
                .map(isbn -> bookRepo.findByISBN(isbn).orElse(null))
                .collect(Collectors.toList());

        wishlist.setBooksInWishlist(books);

        Wishlist savedWishlist = wishlistRepo.save(wishlist);

        wishlistDTO.setWishlistID(savedWishlist.getWishlistID());
        return ResponseEntity.ok(wishlistDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishlistDTO> getWishlistById(@PathVariable int id) {
        Optional<Wishlist> wishlist = wishlistRepo.findById(id);
        if (!wishlist.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setWishlistID(wishlist.get().getWishlistID());
        wishlistDTO.setUserID(wishlist.get().getUser().getUserID());
        wishlistDTO.setBookISBNs(wishlist.get().getBooksInWishlist().stream()
                .map(Book::getISBN)
                .collect(Collectors.toList()));

        return ResponseEntity.ok(wishlistDTO);
    }

    @GetMapping
    public ResponseEntity<List<WishlistDTO>> getAllWishlists() {
        List<Wishlist> wishlists = wishlistRepo.findAll();

        List<WishlistDTO> wishlistDTOs = wishlists.stream().map(wishlist -> {
            WishlistDTO wishlistDTO = new WishlistDTO();
            wishlistDTO.setWishlistID(wishlist.getWishlistID());
            wishlistDTO.setUserID(wishlist.getUser().getUserID());
            wishlistDTO.setBookISBNs(wishlist.getBooksInWishlist().stream()
                    .map(Book::getISBN)
                    .collect(Collectors.toList()));
            return wishlistDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(wishlistDTOs);
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDTO>> getBooksInWishlist(@PathVariable int id) {
        Optional<Wishlist> wishlist = wishlistRepo.findById(id);
        if (!wishlist.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        List<BookDTO> booksInWishlist = wishlist.get().getBooksInWishlist().stream().map(book -> {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setISBN(book.getISBN());
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
}