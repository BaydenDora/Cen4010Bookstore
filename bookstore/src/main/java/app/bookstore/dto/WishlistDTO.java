package app.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import app.bookstore.domain.Author;
import app.bookstore.domain.Book;
import app.bookstore.domain.Publisher;
import app.bookstore.domain.Wishlist;

import java.util.List;
import java.util.stream.Collectors;

@JsonPropertyOrder({"Wishlist ID", "Wishlist Name", "User ID", "Book ISBNS"})
public class WishlistDTO {

    @JsonProperty("Wishlist ID")
    private int wishlistID;

    @JsonProperty("Wishlist Name")
    private String wishlistName;

    @JsonProperty("User ID")
    private int userID;

    @JsonProperty("Book ISBNS")
    private List<String> bookISBNs;

    public WishlistDTO(){};

    public WishlistDTO(Wishlist wishlist){
        this(
            wishlist.getWishlistID(), 
            wishlist.getWishlistName(),
            wishlist.getUser().getUserID(),
            wishlist.getBooksInWishlist().stream()
                        .map(Book::getIsbn)
                        .collect(Collectors.toList())
        );
    }
    

    private WishlistDTO(int wishlistID, String wishlistName, int userID, List<String> bookISBNs) {
        this.wishlistID = wishlistID;
        this.wishlistName = wishlistName;
        this.userID = userID;
        this.bookISBNs = bookISBNs;
    }

    // Getters and Setters
    public int getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }

    public String getWishlistName() {
        return wishlistName;
    }

    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
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