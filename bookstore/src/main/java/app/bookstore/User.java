package app.bookstore;

import java.util.List;

public class User 
{
    private String myUserID;
    private String myUsername;
    private String myPassword;
    private String myEmailAddress;
    private String myHomeAddress;
    private List<Book> myWishlist;
    private List<Book> myShoppingCart;
    private CreditCard myCreditCard;
    
    //*Constructors go here*

  	//*Constructors go here*
    
    // Getters
    public String getUserID() 
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
    public void setUserID(String userID) 
    {
        myUserID = UserID;
    }

    public void setUsername(String username) 
    {
        myUsername = username;
    }

    public void setPassword(String password) 
    {
        myPassword = password;
    }

    public void setEmailAddress(String emailAddress) 
    {
        myEmailAddress = emailAddress;
    }

    public void setHomeAddress(String myHomeAddress) 
    {
        myHomeAddress = myHomeAddress;
    }

    public void setWishlist(List<Book> wishlist) 
    {
        myWishlist = wishlist;
    }
    
    public void setShoppingCart(List<Book> shoppingCart) 
    {
        myShoppingCart = ShoppingCart;
    }

    public void setCreditCard(CreditCard creditCard) 
    {
        myCreditCard = creditCard;
    }
}
