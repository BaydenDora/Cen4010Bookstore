package app.bookstore;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "User")
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int myUserID;
	
	@Column(name = "Username", nullable = false, length = 100)
    private String myUsername;
    
	@Column(name = "Password", nullable = false, length = 100)
    private String myPassword;
    
	@Column(name = "Email Address", nullable = false, length = 100)
    private String myEmailAddress;
    
	@Column(name = "Home Address", nullable = false, length = 100)
    private String myHomeAddress;
    
    @OneToMany(mappedBy = "myUserID", cascade = CascadeType.ALL) //check this one
    private List<Review> myReview;

	@OneToMany(mappedBy = "myUserID", cascade = CascadeType.ALL) //check this one
    private List<Wishlist> myWishlist;
    
	@OneToMany(mappedBy = "myUserID", cascade = CascadeType.ALL) //check this one
    private List<ShoppingCart> myShoppingCart;
	
	@OneToMany
	@JoinColumn(name ="CreditCard_ID", nullable = false)
    private List<CreditCard> creditCards = new ArrayList<>();
    
	// unsure how to address objects at the moment
    private CreditCard myCreditCard;
    
    // No-Arg constructor
    public User() {
        setUserID(0);
        setUsername("user1");
        setPassword("password");
        setEmailAddress("None");
        setHomeAddress("None");
        setReview(null);
        setWishlist(null);
        setShoppingCart(null);
        setCreditCard(null);
    }

    // Constructor
    public User(int userID, String username, String password, String emailAddress, String homeAddress, List<Review> review,
                List<Wishlist> wishlist, List<ShoppingCart> shoppingCart, CreditCard creditCard) {
        setUserID(userID);
        setUsername(username);
        setPassword(password);
        setEmailAddress(emailAddress);
        setHomeAddress(homeAddress);
        setReview(review);
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
    public int getUserID() 
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
    public List<Review> getReview() 
    {
        return myReview;
    }

    public List<Wishlist> getWishlist() 
    {
        return myWishlist;
    }
    
    public List<ShoppingCart> getShoppingCart() 
    {
        return myShoppingCart;
    }

    public CreditCard getCreditCard() 
    {
        return myCreditCard;
    }
    
    // Setters
    protected void setUserID(int userID) 
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

    protected void setReview(List<Review> review) 
    {
        myReview = review;
    }

    protected void setWishlist(List<Wishlist> wishlist) 
    {
        myWishlist = wishlist;
    }
    
    protected void setShoppingCart(List<ShoppingCart> shoppingCart) 
    {
        myShoppingCart = shoppingCart;
    }

    protected void setCreditCard(CreditCard creditCard) 
    {
        myCreditCard = creditCard;
    }
}
