package com.GeekText.bookstore;

// import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userID;
	@Column(nullable = false, length = 100)
    private String username;
	@Column(nullable = false, length = 100)
    private String password;
	@Column(nullable = false, length = 100)
    private String emailAddress;
	@Column(nullable = true, length = 100)
    private String homeAddress;
    
	// @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //check this one
    // private List<Book> myWishlist;
    
	// @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //check this one
    // private List<Book> myShoppingCart;
    
	// unsure how to address objects at the moment
    // private CreditCard myCreditCard;
    
    // No-Arg constructor
    public User() {
        username = "user1";
        password = "password";
        emailAddress = null;
        homeAddress = null;
        // setWishlist(null);
        // setShoppingCart(null);
        // setCreditCard(null);
    }

    // Constructor
    public User(String username, String password, String emailAddress, String homeAddress
            // , List<Book> wishlist, List<Book> shoppingCart, CreditCard creditCard
                ) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.homeAddress = homeAddress;
        // setWishlist(wishlist);
        // setShoppingCart(shoppingCart);
        // setCreditCard(creditCard);
    }

    //  NOT NULL parameters constructor
    public User(String username, String password, String emailAddress) {
        this(username, password, emailAddress, null);
    }

    // Copy Constructor
    // public User(User cloneUser) {
    //     setUserID(cloneUser.getUserID());
    //     setUsername(cloneUser.getUsername());
    //     setPassword(cloneUser.getPassword());
    //     setEmailAddress(cloneUser.getEmailAddress());
        // setHomeAddress(cloneUser.getHomeAddress());
        // setWishlist(cloneUser.getWishlist());
        // setShoppingCart(cloneUser.getShoppingCart());
        // setCreditCard(cloneUser.getCreditCard());
    // }
    
    // Getters
    public Long getUserID() { return userID; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmailAddress() { return emailAddress;}
    public String getHomeAddress() { return homeAddress; }
    // public List<Book> getWishlist() { return myWishlist; }
    // public List<Book> getShoppingCart() { return myShoppingCart; }
    // public CreditCard getCreditCard() { return myCreditCard; }
    
    // Setters
    protected void setUserID(Long userID) { this.userID = userID; }
    protected void setUsername(String username) { this.username = username; }
    protected void setPassword(String password) { this.password = password; }
    protected void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    protected void setHomeAddress(String homeAddress) { this.homeAddress = homeAddress; }
    // protected void setWishlist(List<Book> wishlist) { myWishlist = wishlist; }
    // protected void setShoppingCart(List<Book> shoppingCart) { myShoppingCart = shoppingCart; }
    // protected void setCreditCard(CreditCard creditCard) { myCreditCard = creditCard; }

}

