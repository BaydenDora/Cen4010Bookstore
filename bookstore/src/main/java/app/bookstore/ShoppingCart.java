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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "shoppingcart")
public class ShoppingCart 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int myCartID;
	
    @ManyToMany
    @JoinTable(
        name = "cart_book",
        joinColumns = @JoinColumn(name = "cart_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> myBooksInCart = new ArrayList<>();
	
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User myUserID;
	
    // Getters
    public int getCartID() {
        return myCartID;
    }

    public List<Book> getBooksInCart() {
        return myBooksInCart;
    }

    public User getUser() {
        return myUserID;
    }

    // Setters
    public void setCartID(int cartID) {
        myCartID = cartID;
    }

    public void setBooksInCart(List<Book> booksInCart) {
        myBooksInCart = booksInCart;
    }

    public void setUser(User user) {
        myUserID = user;
    }
}
