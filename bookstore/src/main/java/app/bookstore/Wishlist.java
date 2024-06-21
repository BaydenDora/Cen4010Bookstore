package app.bookstore;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
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
	private int Wishlist_ID;
	
	@ManyToMany
    @JoinTable(
        name = "ISBN",
        joinColumns = @JoinColumn(name = "Wishlist_ID"),
        inverseJoinColumns = @JoinColumn(name = "ISBN")
    )
	private List<Book> myBooksWishlisted = new ArrayList<>();
	
    @ManyToOne
    @JoinColumn(name = "User_ID", nullable = false)
	private User myUserID;
    
    @Column(name = "WishlistName", length = 25)
    private String myWishlistName;
	
	// Getters
    public int getWishlistID() {
        return Wishlist_ID;
    }

    public List<Book> getBooksWishlisted() {
        return myBooksWishlisted;
    }

    public User getUser() {
        return myUserID;
    }
    
    public String getName() {
    	return myWishlistName;
    }

    // Setters
    public void setWishlistID(int wishlistID) {
    	Wishlist_ID = wishlistID;
    }

    public void setBooksWishlisted(List<Book> booksWishlisted) {
    	myBooksWishlisted = booksWishlisted;
    }

    public void setUser(User user) {
    	myUserID = user;
    }
    
    public void setName(String name) {
    	myWishlistName = name;
    }
	
}
