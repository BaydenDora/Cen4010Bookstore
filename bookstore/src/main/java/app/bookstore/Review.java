package app.bookstore;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int reviewID;
    
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	private User myUserID;
    
    @Column(name = "comment", length = 300)
    private String myComment;

    @Column(name = "rating")
    private int myRating;

    @Column(name = "date", length = 30)
    private Date myDate;

   // Constructors
    public Review() {}

    public Review(int id, Book book, User user, int rating, String comment, Date date) {
    	this.reviewID = id;
    	this.book = book;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }
    
    //Getters and Setters
    public int getID() {
    	return reviewID;
    }
    
    public void setID(int id) {
    	this.reviewID = id;
    }
    
    public Book getIsbn() {
        return book;
    }

    public void setIsbn(Book book) {
        this.book = book;
    }

    public User getUsername() {
        return user;
    }

    public void setUsername(User user) {
        this.user = user;
    }

    public void setCommentText(String commentText) {
    	myComment = commentText;
    }

    public void setDatePosted(Date datePosted) {
    	myDate = datePosted;
    }

    public void setUserID(User userID) {
    	myUserID = userID;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return myDate;
    }

    public void setTime(Date date) {
        this.date = date;
    }
}
