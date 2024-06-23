package app.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlists")
public class WishlistController {

    @Autowired
    private WishlistRepo wishlistRepo;

    @GetMapping
    public List<Wishlist> getAllWishlists() {
        return wishlistRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getWishlistById(@PathVariable Integer id) {
        return wishlistRepo.findById(id)
                .map(wishlist -> ResponseEntity.ok().body(wishlist))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Wishlist createWishlist(@RequestBody Wishlist wishlist) {
        return wishlistRepo.save(wishlist);
    }

    // Additional methods for update and delete can be added here
}
