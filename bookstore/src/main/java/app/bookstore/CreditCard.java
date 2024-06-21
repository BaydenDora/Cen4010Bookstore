package app.bookstore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "creditcard")
public class CreditCard 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long Card_ID;
	
	@Column(name = "Brand", nullable = false, length = 100)
	private String myCardBrand; //Visa, Discover, MasterCard, etc
	
	@Column(name = "Holder", nullable = false, length = 100)
	private String myCardHolder; //Name of the card holder
	
	@Column(name = "CardNumber", nullable = false, length = 16)
	private long myCardNumber; //The digits in groups of 4
	
	@Column(name = "ExpirationDate", nullable = false, length = 5)
	private String myExpirationDate; 
	
	@Column(name = "CVC", nullable = false, length = 3)
	private int myCVC; // The three funny numbers on the back
	
	@ManyToOne
	@JoinColumn(name ="User_ID", nullable = false)
	private User myUserID;
	
	// No-Arg constructor
    public CreditCard() 
    {
        setCardID(0);
        setCardBrand("Brand");
        setCardHolder("John Doe");
        setCardNumber(0);
        setExpirationDate("00/00");
        setCVC(000);
    }
    
    // constructor
    public CreditCard(long cardID, String cardBrand, String cardHolder, long cardNumber, 
                      String expirationDate, int cvc, User user) {
        setCardID(cardID);
        setCardBrand(cardBrand);
        setCardHolder(cardHolder);
        setCardNumber(cardNumber);
        setExpirationDate(expirationDate);
        setCVC(cvc);
		setUser(user);
    }
    
    // Copy constructor
    public CreditCard(CreditCard cloneCard) {
        setCardID(cloneCard.getCardID());
        setCardBrand(cloneCard.getCardBrand());
        setCardHolder(cloneCard.getCardHolder());
        setCardNumber(cloneCard.getCardNumber());
        setExpirationDate(cloneCard.getExpirationDate());
        setCVC(cloneCard.getCVC());
		setUser(cloneCard.getUser());
    }
	
	// Getters
	public long getCardID() {
        return Card_ID;
    }

    public String getCardBrand() {
        return myCardBrand;
    }

    public String getCardHolder() {
        return myCardHolder;
    }

    public long getCardNumber() {
        return myCardNumber;
    }

    public String getExpirationDate() {
        return myExpirationDate;
    }
    
    public int getCVC() {
        return myCVC;
    }

    public User getUser() {
        return myUserID;
    }
    	
	// Setters
	protected void setCardID(long cardID)
	{
		Card_ID = cardID;
	}
	
	protected void setCardBrand(String cardBrand)
	{
		myCardBrand = cardBrand;
	}
	
	protected void setCardHolder(String cardHolder)
	{
		myCardHolder = cardHolder;
	}
	
	protected void setCardNumber (long cardNumber)
	{
		myCardNumber = cardNumber;
	}
	
	protected void setExpirationDate (String expirationDate)
	{
		myExpirationDate = expirationDate;
	}
	
	protected void setCVC (int cvc)
	{
		myCVC = cvc;
	}
	
    protected void setUser(User user) {
        myUserID = user;
    }
}

