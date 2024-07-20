package app.bookstore.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity // This tells Hibernate to make a table out of this class
public class CreditCard 
{
    // Changed entire class to fit SQL data. Team project doesn't ask specifcally for brand details and we can get the Card holder's name via the connection to User through user id
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Card_ID", nullable = false)
    private long cardID;
    
    @Column(name = "CardNumber", nullable = false, unique = true, columnDefinition = "CHAR(16)")
    private String cardNumber; // Changed to String to handle digits and match SQL schema
    
    @Column(name = "ExpirationDate", nullable = false, length = 5)
    private String expirationDate;
    
    @Column(name = "CVV", nullable = false, columnDefinition = "CHAR(3)")
    private String cvv; // Changed to String to handle digits and match SQL schema
    
    @ManyToOne
    @JoinColumn(name ="User_ID", nullable = false)
    private User myUserID;
    
    // No-Arg constructor
    public CreditCard() 
    {
        setCardID(0);
        setCardNumber("0000000000000000");
        setExpirationDate("00/00");
        setCvv("000");
    }
    
    // Constructor
    public CreditCard(long cardID, String cardNumber, String expirationDate, String cvv, User user) {
        setCardID(cardID);
        setCardNumber(cardNumber);
        setExpirationDate(expirationDate);
        setCvv(cvv);
        setUser(user);
    }
    
    // Copy constructor
    public CreditCard(CreditCard cloneCard) {
        setCardID(cloneCard.getCardID());
        setCardNumber(cloneCard.getCardNumber());
        setExpirationDate(cloneCard.getExpirationDate());
        setCvv(cloneCard.getCvv());
        setUser(cloneCard.getUser());
    }
    
    // Getters
    public long getCardID() {
        return cardID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
    
    public String getCvv() {
        return cvv;
    }

    public User getUser() {
        return myUserID;
    }
        
    // Setters
    protected void setCardID(long cardID)
    {
        this.cardID = cardID;
    }
    
    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }
    
    public void setExpirationDate (String expirationDate)
    {
        this.expirationDate = expirationDate;
    }
    
    public void setCvv (String cvv)
    {
        this.cvv = cvv;
    }
    
    public void setUser(User user) {
        this.myUserID = user;
    }
}
