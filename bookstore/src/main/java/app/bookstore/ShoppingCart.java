package app.bookstore;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "shoppingcart")
public class ShoppingCart 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int myCartID;
	
	@OneToMany(mappedBy = "myShoppingCart")
    private List<Book> myBooksInCart = new ArrayList<>();
	
	@ManyToOne
    @JoinColumn(name ="User_ID", nullable = false)
	private User myUserID;
	
    // Getters
    public int getCartID() {
        return myCartID;
    }

    public List<Book> getBooksInCart() {
        return myBooksInCart;
    }

    public User getUserID() {
        return myUserID;
    }

    // Setters
    public void setCartID(int cartID) {
        myCartID = cartID;
    }

    public void setBooksInCart(List<Book> booksInCart) {
        myBooksInCart = booksInCart;
    }

    public void setUserID(User userID) {
        myUserID = userID;
    }
}
