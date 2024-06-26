package app.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WishlistDTO {

    @JsonProperty("wishlistID")
    private int wishlistID;

    @JsonProperty("userID")
    private int userID;

    @JsonProperty("bookISBNs")
    private List<String> bookISBNs;

    // Getters and Setters
    public int getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
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