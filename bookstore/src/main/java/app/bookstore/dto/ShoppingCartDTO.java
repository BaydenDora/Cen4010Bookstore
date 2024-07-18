package app.bookstore.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import app.bookstore.domain.Author;
import app.bookstore.domain.Book;
import app.bookstore.domain.Publisher;
import app.bookstore.domain.ShoppingCart;

@JsonPropertyOrder({"Author ID", "First Name", "Last Name", "Biography", "Publisher IDs"})
public class ShoppingCartDTO {
    
    @JsonProperty("Cart ID")
    private int cartID;

    @JsonProperty("User ID")
    private int userID;

    @JsonProperty("Book ISBNs")
    private List<String> bookISBNs;

     public ShoppingCartDTO() {}

    public ShoppingCartDTO(ShoppingCart shoppingCart){
        this(
            shoppingCart.getCartID(), 
            shoppingCart.getUser().getUserID(), 
            shoppingCart.getBooksInCart().stream()
                        .map(Book::getIsbn)
                        .collect(Collectors.toList())
        );
    }

    private ShoppingCartDTO(int cartID, int userID, List<String> bookISBNs) {
        this.cartID = cartID;
        this.userID = userID;
        this.bookISBNs = bookISBNs;
    }


    // Getters and setters...

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<String> getBookISBNs() {
        return bookISBNs;
    }

    public void setBookISBNs(List<String> bookISBNs) {
        this.bookISBNs = bookISBNs;
    }
}