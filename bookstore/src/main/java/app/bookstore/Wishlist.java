package app.bookstore;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "wishlist")
public class Wishlist 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int myWishlistID;
	
	@ManyToMany
    @JoinTable(
        name = "wishlist_book",
        joinColumns = @JoinColumn(name = "wishlist_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
	private List<Book> myBooksWishlisted = new ArrayList<>();
	
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	// Getters
    public int getWishlistID() {
        return myWishlistID;
    }

    public List<Book> getBooksWishlisted() {
        return myBooksWishlisted;
    }

    public User getUser() {
        return user;
    }

    // Setters
    public void setWishlistID(int wishlistID) {
    	myWishlistID = wishlistID;
    }

    public void setBooksWishlisted(List<Book> booksWishlisted) {
    	myBooksWishlisted = booksWishlisted;
    }

    public void setUser(User user) {
    	this.user = user;
    }
	
}
