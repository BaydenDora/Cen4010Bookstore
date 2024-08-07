package app.bookstore.domain;

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

@Entity // This tells Hibernate to make a table out of this class
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int User_ID;
	
	@Column(name = "Username", nullable = false, length = 50)
    private String myUsername;

    @Column(name = "FullName", nullable = true, length = 50)
    private String myName;
    
	@Column(name = "Pass", nullable = false, length = 50)
    private String myPassword;
    
	@Column(name = "Email", nullable = false, length = 50)
    private String myEmailAddress;
    
	@Column(name = "HomeAddress", nullable = true, length = 100)
    private String myHomeAddress;


	@OneToMany(mappedBy = "myUserID", cascade = CascadeType.ALL) //check this one
    private List<Wishlist> myWishlists;
    
	@OneToOne(mappedBy = "myUserID", cascade = CascadeType.ALL) //check this one
    private ShoppingCart myShoppingCart;
	
	@OneToMany(mappedBy = "myUserID", cascade = CascadeType.ALL) //check this one
    private List<CreditCard> myCreditCards = new ArrayList<>();
	
	@OneToMany(mappedBy = "myUserID", cascade = CascadeType.ALL) //check this one
    private List<Review> myReview = new ArrayList<>();
    
    
    // No-Arg constructor
    public User() {
        setUserID(0);
        setUsername("user1");
        setName("name1");
        setPassword("password");
        setEmailAddress("None");
        setHomeAddress("None");
    }

    // Constructor
    public User(int userID, String username, String name, String password, String emailAddress, String homeAddress) {
        setUserID(userID);
        setUsername(username);
        setName(name);
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
    public int getUserID() 
    {
        return User_ID;
    }

    public String getUsername() 
    {
        return myUsername;
    }

    public String getName() 
    {
        return myName;
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
        return myReview;
    }
    
    // Setters
    protected void setUserID(int userID) 
    {
    	User_ID = userID;
    }

    public void setUsername(String username) 
    {
        myUsername = username;
    }

    public void setName(String name) 
    {
        myName = name;
    }

    public void setPassword(String password) 
    {
        myPassword = password;
    }

    public void setEmailAddress(String emailAddress) 
    {
        myEmailAddress = emailAddress;
    }

    public void setHomeAddress(String homeAddress) 
    {
        myHomeAddress = homeAddress;
    }

    public void setWishlists(List<Wishlist> wishlists) 
    {
        myWishlists = wishlists;
    }
    
    public void setShoppingCart(ShoppingCart shoppingCart) 
    {
        myShoppingCart = shoppingCart;
    }

    public void setCreditCards(List<CreditCard> creditCards) 
    {
        myCreditCards = creditCards;
    }
}
