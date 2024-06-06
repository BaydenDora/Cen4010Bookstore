package app.bookstore;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "user")
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long myUserID;
	
	@Column(nullable = false, length = 100)
    private String myUsername;
    
	@Column(nullable = false, length = 100)
    private String myPassword;
    
	@Column(nullable = false, length = 100)
    private String myEmailAddress;
    
	@Column(nullable = false, length = 100)
    private String myHomeAddress;
    
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //check this one
    private List<Wishlist> myWishlist;
    
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //check this one
    private List<ShoppingCart> myShoppingCart;
    
	// unsure how to address objects at the moment
    private CreditCard myCreditCard;
    
    // No-Arg constructor
    public User() {
        setUserID(0);
        setUsername("user1");
        setPassword("password");
        setEmailAddress("None");
        setHomeAddress("None");
        setWishlist(null);
        setShoppingCart(null);
        setCreditCard(null);
    }

    // Constructor
    public User(long userID, String username, String password, String emailAddress, String homeAddress, 
                List<Book> wishlist, List<Book> shoppingCart, CreditCard creditCard) {
        setUserID(userID);
        setUsername(username);
        setPassword(password);
        setEmailAddress(emailAddress);
        setHomeAddress(homeAddress);
        setWishlist(wishlist);
        setShoppingCart(shoppingCart);
        setCreditCard(creditCard);
    }

    // Copy Constructor
    public User(User cloneUser) {
        setUserID(cloneUser.getUserID());
        setUsername(cloneUser.getUsername());
        setPassword(cloneUser.getPassword());
        setEmailAddress(cloneUser.getEmailAddress());
        setHomeAddress(cloneUser.getHomeAddress());
        setWishlist(cloneUser.getWishlist());
        setShoppingCart(cloneUser.getShoppingCart());
        setCreditCard(cloneUser.getCreditCard());
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

    public List<Book> getWishlist() 
    {
        return myWishlist;
    }
    
    public List<Book> getShoppingCart() 
    {
        return myShoppingCart;
    }

    public CreditCard getCreditCard() 
    {
        return myCreditCard;
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

    protected void setHomeAddress(String myHomeAddress) 
    {
        myHomeAddress = myHomeAddress;
    }

    protected void setWishlist(List<Book> wishlist) 
    {
        myWishlist = wishlist;
    }
    
    protected void setShoppingCart(List<Book> shoppingCart) 
    {
        myShoppingCart = ShoppingCart;
    }

    protected void setCreditCard(CreditCard creditCard) 
    {
        myCreditCard = creditCard;
    }
}
