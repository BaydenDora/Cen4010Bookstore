package app.bookstore;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "shoppingcart")
public class ShoppingCart 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int myCartID;
	
	@ManyToMany(mappedBy = "myShoppingCart")
    private List<Book> myBooksInCart = new ArrayList<>();
	
	@OneToOne(mappedBy = "myShoppingCart")
	private int myUserID;
	
    // Getters
    public int getCartID() {
        return myCartID;
    }

    public List<Book> getBooksInCart() {
        return myBooksInCart;
    }

    public int getUserID() {
        return myUserID;
    }

    // Setters
    public void setCartID(int cartID) {
        myCartID = cartID;
    }

    public void setBooksInCart(List<Book> booksInCart) {
        myBooksInCart = booksInCart;
    }

    public void setUserID(int userID) {
        myUserID = userID;
    }
}
