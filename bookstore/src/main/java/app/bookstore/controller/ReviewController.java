//ReviewController.java

package app.bookstore.controller;

import app.bookstore.domain.Book;
import app.bookstore.domain.Review;
import app.bookstore.domain.User;
import app.bookstore.dto.ReviewDTO;
import app.bookstore.exception.Book.BookNotFoundException;
import app.bookstore.exception.Review.ReviewExistsException;
import app.bookstore.exception.Review.ReviewNotFoundException;
import app.bookstore.exception.User.UserNotFoundException;
import app.bookstore.repo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        Review savedReview = reviewRepo.save(verifyReview(reviewDTO));
        reviewDTO.setReviewID(savedReview.getID());
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable int id) {
        return ResponseEntity.ok(new ReviewDTO(verifyReview(id)));
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviewDTOs = reviewRepo.findAll().stream()
                            .map(ReviewDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(reviewDTOs);
    }

    @GetMapping("/{isbn}/averageRating")
    public ResponseEntity<Double> getBookAverageRating (@PathVariable String isbn) {
        Book book = bookRepo.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));

        List<Review> reviews = reviewRepo.findByMyBook_Isbn(book.getIsbn());

        if(reviews.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        double averageRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        return ResponseEntity.ok(averageRating);
    }

    @GetMapping("/{isbn}/comments")
    public ResponseEntity<List<String>> getBookComments(@PathVariable String isbn) {
        Book book = bookRepo.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));

        List<Review> reviews = reviewRepo.findByMyBook_Isbn(book.getIsbn());

        if(reviews.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<String> comments = reviews.stream()
                .map(Review::getComment)
                .collect(Collectors.toList());

        return ResponseEntity.ok(comments);
    }


    private Review verifyReview(int id) throws ReviewNotFoundException{
        return reviewRepo.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }

    private Review verifyReview(ReviewDTO reviewDTO) throws ReviewExistsException{
        Optional.of(reviewDTO.getReviewID())
                    .ifPresent(reviewID -> { throw new ReviewExistsException(reviewID); });
        
        String isbn = reviewDTO.getIsbn();
        Book book = bookRepo.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
        int userID = reviewDTO.getUserID();
        User user = userRepo.findById(userID).orElseThrow(() -> new UserNotFoundException(userID));

        Review review = new Review();
        review.setIsbn(book);
        review.setUsername(user);
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        review.setDate(reviewDTO.getDate() != null ? reviewDTO.getDate() : new Date());
        return review;
    }

}