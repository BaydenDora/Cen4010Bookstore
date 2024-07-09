package app.bookstore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
// @Table(name = "creditcard")
public class CreditCard 
{
    // Changed entire class to fit SQL data. Team project doesn't ask specifcally for brand details and we can get the Card holder's name via the connection to User through user id
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Card_ID", nullable = false)
    private long Card_ID;
    
    @Column(name = "CardNumber", nullable = false, unique = true, columnDefinition = "CHAR(16)")
    private String CardNumber; // Changed to String to handle digits and match SQL schema
    
    @Column(name = "ExpirationDate", nullable = false, length = 5)
    private String ExpirationDate;
    
    @Column(name = "CVV", nullable = false, columnDefinition = "CHAR(3)")
    private String CVV; // Changed to String to handle digits and match SQL schema
    
    @ManyToOne
    @JoinColumn(name ="User_ID", nullable = false)
    private User myUserID;
    
    // No-Arg constructor
    public CreditCard() 
    {
        setCardID(0);
        setCardNumber("0000000000000000");
        setExpirationDate("00/00");
        setCVV("000");
    }
    
    // Constructor
    public CreditCard(long cardID, String cardNumber, String expirationDate, String cvv, User user) {
        setCardID(cardID);
        setCardNumber(cardNumber);
        setExpirationDate(expirationDate);
        setCVV(cvv);
        setUser(user);
    }
    
    // Copy constructor
    public CreditCard(CreditCard cloneCard) {
        setCardID(cloneCard.getCardID());
        setCardNumber(cloneCard.getCardNumber());
        setExpirationDate(cloneCard.getExpirationDate());
        setCVV(cloneCard.getCVV());
        setUser(cloneCard.getUser());
    }
    
    // Getters
    public long getCardID() {
        return Card_ID;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }
    
    public String getCVV() {
        return CVV;
    }

    public User getUser() {
        return myUserID;
    }
        
    // Setters
    protected void setCardID(long cardID)
    {
        Card_ID = cardID;
    }
    
    protected void setCardNumber(String cardNumber)
    {
        CardNumber = cardNumber;
    }
    
    protected void setExpirationDate (String expirationDate)
    {
        ExpirationDate = expirationDate;
    }
    
    protected void setCVV (String cvv)
    {
        CVV = cvv;
    }
    
    protected void setUser(User user) {
        myUserID = user;
    }
}
