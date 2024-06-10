package app.bookstore;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "isbn")
    private int isbn;

    @Id
    @Column(name = "username", length = 300)
    private String username;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comment", length = 300)
    private String comment;

    @Column(name = "time", length = 30)
    private String time;

    public Review() {}

    public Review(int isbn, String username, int rating, String comment, String time) {
        this.isbn = isbn;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.time = time;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}