package com.GeekText.bookstore;

// import java.util.List;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity // This tells Hibernate to make a table out of this class
@Getter @Setter(AccessLevel.PROTECTED) @NoArgsConstructor 
@AllArgsConstructor @ToString
public class User 
{
	@Id
    @Column(name="USER_ID", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userID;
	@Column(name="USER_NAME", nullable=false, length=100)
    private String username;
	@Column(name="PASSWORD", nullable=false, length=100)
    private String password;
	@Column(name="EMAIL", nullable=false, length=100)
    private String emailAddress;
	@Column(name="HOMEADDRESS", nullable=true, length=100)
    private String homeAddress;
 
	// @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //check this one
    // private List<Book> myWishlist;
    
	// @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //check this one
    // private List<Book> myShoppingCart;
    
	// unsure how to address objects at the moment
    // private CreditCard myCreditCard;


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
    // public int getUserID() { return userID; }
    // public String getUsername() { return username; }
    // public String getPassword() { return password; }
    // public String getEmailAddress() { return emailAddress;}
    // public String getHomeAddress() { return homeAddress; }
    // public List<Book> getWishlist() { return myWishlist; }
    // public List<Book> getShoppingCart() { return myShoppingCart; }
    // public CreditCard getCreditCard() { return myCreditCard; }
    
    // Setters
    // protected void setUserID(int userID) { this.userID = userID; }
    // protected void setUsername(String username) { this.username = username; }
    // protected void setPassword(String password) { this.password = password; }
    // protected void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    // protected void setHomeAddress(String homeAddress) { this.homeAddress = homeAddress; }
    // protected void setWishlist(List<Book> wishlist) { myWishlist = wishlist; }
    // protected void setShoppingCart(List<Book> shoppingCart) { myShoppingCart = shoppingCart; }
    // protected void setCreditCard(CreditCard creditCard) { myCreditCard = creditCard; }

}

