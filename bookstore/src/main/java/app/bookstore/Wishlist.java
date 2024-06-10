package app.bookstore;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "wishlist")
public class Wishlist 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int myWishlistID;
	
	@Column(name = "Book", nullable = false, length = 100)
	@OneToMany(mappedBy = "myWishlist")
	private List<Book> myBooksWishlisted = new ArrayList<>();
	
    @ManyToOne
	@Column(name = "User", nullable = false, length = 100)
	private int myUserID;
	
	// Getters
    public int getWishlistID() {
        return myWishlistID;
    }

    public List<Book> getBooksWishlisted() {
        return myBooksWishlisted;
    }

    public int getUserID() {
        return myUserID;
    }

    // Setters
    public void setWishlistID(int wishlistID) {
    	myWishlistID = wishlistID;
    }

    public void setBooksWishlisted(List<Book> booksWishlisted) {
    	myBooksWishlisted = booksWishlisted;
    }

    public void setUserID(int userID) {
    	myUserID = userID;
    }
	
}
