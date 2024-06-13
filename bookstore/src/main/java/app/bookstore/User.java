package app.bookstore;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "user")
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long myUserID;
	
	@Column(name = "Username", nullable = false, length = 100)
    private String myUsername;
    
	@Column(name = "Password", nullable = false, length = 100)
    private String myPassword;
    
	@Column(name = "Email Address", nullable = false, length = 100)
    private String myEmailAddress;
    
	@Column(name = "Home Address", nullable = false, length = 100)
    private String myHomeAddress;
    
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //check this one
    private List<Wishlist> myWishlists;
    
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL) //check this one
    private ShoppingCart myShoppingCart;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //check this one
    private List<CreditCard> myCreditCards = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //check this one
    private List<Review> myReviews = new ArrayList<>();
    
    
    // No-Arg constructor
    public User() {
        setUserID(0);
        setUsername("user1");
        setPassword("password");
        setEmailAddress("None");
        setHomeAddress("None");
    }

    // Constructor
    public User(long userID, String username, String password, String emailAddress, String homeAddress) {
        setUserID(userID);
        setUsername(username);
        setPassword(password);
        setEmailAddress(emailAddress);
        setHomeAddress(homeAddress);
    }

    // Copy Constructor
    public User(User cloneUser) {
        setUserID(cloneUser.getUserID());
        setUsername(cloneUser.getUsername());
        setPassword(cloneUser.getPassword());
        setEmailAddress(cloneUser.getEmailAddress());
        setHomeAddress(cloneUser.getHomeAddress());
        setWishlists(cloneUser.getWishlists());
        setShoppingCart(cloneUser.getShoppingCart());
        setCreditCards(cloneUser.getCreditCards());
    }
    
    // Getters
    public long getUserID() 
    {
        return myUserID;
    }

    public String getUsername() 
    {
        return myUsername;
    }

    public String getPassword() 
    {
        return myPassword;
    }

    public String getEmailAddress() 
    {
        return myEmailAddress;
    }

    public String getHomeAddress() 
    {
        return myHomeAddress;
    }

    public List<Wishlist> getWishlists() 
    {
        return myWishlists;
    }
    
    public ShoppingCart getShoppingCart() 
    {
        return myShoppingCart;
    }

    public List<CreditCard> getCreditCards() 
    {
        return myCreditCards;
    }

    public List<Review> getReviews() {
        return myReviews;
    }
    
    // Setters
    protected void setUserID(long userID) 
    {
        myUserID = userID;
    }

    protected void setUsername(String username) 
    {
        myUsername = username;
    }

    protected void setPassword(String password) 
    {
        myPassword = password;
    }

    protected void setEmailAddress(String emailAddress) 
    {
        myEmailAddress = emailAddress;
    }

    protected void setHomeAddress(String homeAddress) 
    {
        myHomeAddress = homeAddress;
    }

    protected void setWishlists(List<Wishlist> wishlists) 
    {
        myWishlists = wishlists;
    }
    
    protected void setShoppingCart(ShoppingCart shoppingCart) 
    {
        myShoppingCart = shoppingCart;
    }

    protected void setCreditCards(List<CreditCard> creditCards) 
    {
        myCreditCards = creditCards;
    }
}
