package app.bookstore;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "comment")
public class Review 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int myCommentID;
	
	@Column(name = "comment", nullable = false, length = 100)
	private String myCommentText;
	
//	@Column(name = "Rating", nullable = false, length = 100)
//	private int rating;
	
	@Column(name = "datePosted", nullable = false, length = 100)
	private Date myDatePosted;
	
	@Column(name = "User", nullable = false, length = 100)
	@ManyToMany(mappedBy = "myComments")
	private int myUserID;
	
	// Getters
    public int getCommentID() {
        return myCommentID;
    }

    public String getCommentText() {
        return myCommentText;
    }

    public Date getDatePosted() {
        return myDatePosted;
    }

    public int getUserID() {
        return myUserID;
    }

    // Setters
    public void setCommentID(int commentID) {
        myCommentID = commentID;
    }

    public void setCommentText(String commentText) {
    	myCommentText = commentText;
    }

    public void setDatePosted(Date datePosted) {
    	myDatePosted = datePosted;
    }

    public void setUserID(int userID) {
    	myUserID = userID;
    }

}
