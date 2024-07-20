package app.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import app.bookstore.domain.CreditCard;

@JsonPropertyOrder({"Card ID", "Card Number", "Expiration Date", "CVV", "User ID"})
public class CreditCardDTO {

    @JsonProperty("Card ID")
    private long cardID;

    @JsonProperty("Card Number")
    private String cardNumber;

    @JsonProperty("Expiration Date")
    private String expirationDate;

    @JsonProperty("CVV")
    private String cvv;

    @JsonProperty("User ID")
    private int userID;

    public CreditCardDTO() {}

    public CreditCardDTO(CreditCard creditCard){
        this(creditCard.getCardID(), creditCard.getCardNumber(), creditCard.getExpirationDate(), 
        creditCard.getCvv(), creditCard.getUser().getUserID());
    }

    private CreditCardDTO(long cardID, String cardNumber, String expirationDate, String cvv, int userID) {
        this.cardID = cardID;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.userID = userID;
    }

    
    // Getters and setters

    public long getCardID() {
        return cardID;
    }

    public void setCardID(long cardID) {
        this.cardID = cardID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "CreditCardDTO{" +
                "cardID=" + cardID +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvv='" + cvv + '\'' +
                ", userID=" + userID +
                '}';
    }
}