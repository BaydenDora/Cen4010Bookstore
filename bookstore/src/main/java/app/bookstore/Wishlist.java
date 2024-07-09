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
import jakarta.persistence.Column;

@Entity // This tells Hibernate to make a table out of this class
public class Wishlist 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Wishlist_ID;

    @Column(name = "WishlistName", nullable = false)
    private String WishlistName;

    @ManyToMany
    @JoinTable(
        name = "wishlist_books",
        joinColumns = @JoinColumn(name = "Wishlist_ID"),
        inverseJoinColumns = @JoinColumn(name = "ISBN", referencedColumnName = "ISBN")
    )
    private List<Book> myBooksInWishlist = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "User_ID", nullable = false)
    private User myUserID;

    // Getters
    public int getWishlistID() {
        return Wishlist_ID;
    }

    public String getWishlistName() {
        return WishlistName;
    }

    public List<Book> getBooksInWishlist() {
        return myBooksInWishlist;
    }

    public User getUser() {
        return myUserID;
    }

    // Setters
    public void setWishlistID(int wishlistID) {
        Wishlist_ID = wishlistID;
    }

    public void setWishlistName(String wishlistName) {
        WishlistName = wishlistName;
    }

    public void setBooksInWishlist(List<Book> booksInWishlist) {
        myBooksInWishlist = booksInWishlist;
    }

    public void setUser(User user) {
        myUserID = user;
    }
}