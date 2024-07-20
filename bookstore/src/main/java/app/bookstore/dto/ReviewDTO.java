package app.bookstore.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import app.bookstore.domain.Review;


@JsonPropertyOrder({"Review ID", "ISBN", "User ID", "Comment", "Rating", "Date"}) 
public class ReviewDTO {

    @JsonProperty("Review ID")
    private int reviewID;

    @JsonProperty("ISBN")
    private String isbn;

    @JsonProperty("User ID")
    private int userID;

    @JsonProperty("Comment")
    private String comment;

    @JsonProperty("Rating")
    private int rating;

    @JsonProperty("Date")
    private Date date;


    public ReviewDTO(){};

    public ReviewDTO(Review review){
        this(
            review.getID(), 
            review.getBook().getIsbn(),
            review.getUser().getUserID(),
            review.getComment(),
            review.getRating(),
            review.getDate()
        );
    }

    public ReviewDTO(int reviewID, String isbn, int userID, String comment, int rating, Date date) {
        this.reviewID = reviewID;
        this.isbn = isbn;
        this.userID = userID;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
    }
    

    // Getters and Setters

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "reviewID=" + reviewID +
                ", isbn='" + isbn + '\'' +
                ", userID=" + userID +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", date=" + date +
                '}';
    }
}