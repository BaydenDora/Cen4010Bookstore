package app.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class ReviewDTO {

    @JsonProperty("reviewID")
    private int reviewID;

    @JsonProperty("ISBN")
    private String isbn;

    @JsonProperty("userID")
    private int userID;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("rating")
    private int rating;

    @JsonProperty("date")
    private Date date;

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