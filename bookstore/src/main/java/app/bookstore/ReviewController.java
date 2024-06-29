//ReviewController.java

package app.bookstore;

import app.bookstore.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO) {
        Optional<Book> book = bookRepo.findByISBN(reviewDTO.getISBN());
        if (!book.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<User> user = userRepo.findById(reviewDTO.getUserID());
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        Review review = new Review();
        review.setIsbn(book.get());
        review.setUsername(user.get());
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        review.setDate(reviewDTO.getDate() != null ? reviewDTO.getDate() : new Date());

        Review savedReview = reviewRepo.save(review);

        reviewDTO.setReviewID(savedReview.getID());
        return ResponseEntity.ok(reviewDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable int id) {
        Optional<Review> review = reviewRepo.findById(id);
        if (!review.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewID(review.get().getID());
        reviewDTO.setISBN(review.get().getIsbn().getISBN());
        reviewDTO.setUserID(review.get().getUsername().getUserID());
        reviewDTO.setComment(review.get().getComment());
        reviewDTO.setRating(review.get().getRating());
        reviewDTO.setDate(review.get().getDate());

        return ResponseEntity.ok(reviewDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<Review> reviews = reviewRepo.findAll();

        List<ReviewDTO> reviewDTOs = reviews.stream().map(review -> {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setReviewID(review.getID());
            reviewDTO.setISBN(review.getIsbn().getISBN());
            reviewDTO.setUserID(review.getUsername().getUserID());
            reviewDTO.setComment(review.getComment());
            reviewDTO.setRating(review.getRating());
            reviewDTO.setDate(review.getDate());
            return reviewDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(reviewDTOs);
    }
    @GetMapping("/{isbn}/averageRating")
    public ResponseEntity<Double> getBookAverageRating (@PathVariable String isbn)
    {
        Optional<Book> book = bookRepo.findByISBN(isbn);
        if (!book.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        List<Review> reviews = reviewRepo.findByIsbn(book.get());

        if(reviews.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        double averageRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        return ResponseEntity.ok(averageRating);
    }
}