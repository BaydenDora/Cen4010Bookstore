package app.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditCardDTO {

    @JsonProperty("cardID")
    private long cardID;

    @JsonProperty("cardNumber")
    private String cardNumber;

    @JsonProperty("expirationDate")
    private String expirationDate;

    @JsonProperty("cvv")
    private String cvv;

    @JsonProperty("userID")
    private int userID;

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