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
    private int Review_ID;
    
    @ManyToOne
    @JoinColumn(name = "ISBN", nullable = false)
    private Book myBook;

	@ManyToOne
    @JoinColumn(name = "User_ID", nullable = false)
	private User myUserID;
    
    @Column(name = "'Text'", length = 300)
    private String myComment;

    @Column(name = "Rating")
    private int myRating;

    @Column(name = "'Date'", length = 30)
    private Date myDate;

   // Constructors
    public Review() {}

    public Review(int id, Book book, User user, int rating, String comment, Date date) {
    	Review_ID = id;
    	myBook = book;
        myUserID = user;
        myRating = rating;
        myComment = comment;
        myDate = date;
    }
    
    //Getters and Setters
    public int getID() {
    	return Review_ID;
    }
    
    public void setID(int id) {
    	this.Review_ID = id;
    }
    
    public Book getIsbn() {
        return myBook;
    }

    public void setIsbn(Book book) {
        myBook = book;
    }

    public User getUsername() {
        return myUserID;
    }

    public void setUsername(User user) {
        myUserID = user;
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
        myComment = comment;
    }

    public Date getDate() {
        return myDate;
    }

    public void setTime(Date date) {
        myDate = date;
    }
}
