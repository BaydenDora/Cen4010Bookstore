package app.bookstore.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity // This tells Hibernate to make a table out of this class
public class ShoppingCart 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Cart_ID;
	
    @ManyToMany
    @JoinTable(
        name = "shoppingcart_books",
        joinColumns = @JoinColumn(name = "Cart_ID"),
        inverseJoinColumns = @JoinColumn(name = "ISBN", referencedColumnName = "ISBN")
    )
    private List<Book> myBooksInCart = new ArrayList<>();
	
    @OneToOne
    @JoinColumn(name = "User_ID", nullable = false)
    private User myUserID;
	
    // Getters
    public int getCartID() {
        return Cart_ID;
    }

    public List<Book> getBooksInCart() {
        return myBooksInCart;
    }

    public User getUser() {
        return myUserID;
    }

    // Setters
    protected void setCartID(int cartID) {
    	Cart_ID = cartID;
    }

    public void setBooksInCart(List<Book> booksInCart) {
        myBooksInCart = booksInCart;
    }

    public void setUser(User user) {
        myUserID = user;
    }
}