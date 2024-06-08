package com.GeekText.bookstore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class CreditCard 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long myCardID;
	
	@Column(nullable = false, length = 100)
	String myCardBrand; //Visa, Discover, MasterCard, etc
	
	@Column(nullable = false, length = 100)
	String myCardHolder; //Name of the card holder
	
	@Column(nullable = false, length = 100)
	long myCardNumber; //The digits in groups of 4
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	int myExpirationMonth; // 01 - 12
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	int myExpirationYear; // Two digit year (24 for 2024, 25 for 2025, etc)
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	int myCVC; // The three funny numbers on the back
	
	// No-Arg constructor
    public CreditCard() 
    {
        setCardID(0);
        setCardBrand("Brand");
        setCardHolder("John Doe");
        setCardNumber(0);
        setExpirationMonth(0);
        setExpirationYear(0);
        setCVC(000);
    }
    
    // constructor
    public CreditCard(long cardID, String cardBrand, String cardHolder, long cardNumber, 
                      int expirationMonth, int expirationYear, int cvc) {
        setCardID(cardID);
        setCardBrand(cardBrand);
        setCardHolder(cardHolder);
        setCardNumber(cardNumber);
        setExpirationMonth(expirationMonth);
        setExpirationYear(expirationYear);
        setCVC(cvc);
    }
    
    // Copy constructor
    public CreditCard(CreditCard cloneCard) {
        setCardID(cloneCard.getCardID());
        setCardBrand(cloneCard.getCardBrand());
        setCardHolder(cloneCard.getCardHolder());
        setCardNumber(cloneCard.getCardNumber());
        setExpirationMonth(cloneCard.getExpirationMonth());
        setExpirationYear(cloneCard.getExpirationYear());
        setCVC(cloneCard.getCVC());
    }
	
	// Getters
    long getCardID()
    {
    	return myCardID;
    }
	String getCardBrand()
	{
		return myCardBrand;
	}
	
	String getCardHolder()
	{
		return myCardHolder;
	}
	
	long getCardNumber()
	{
		return myCardNumber;
	}
	
	int getExpirationMonth()
	{
		return myExpirationMonth;
	}
	
	int getExpirationYear()
	{
		return myExpirationYear;
	}
	
	int getCVC()
	{
		return myCVC;
	}
	
	// Setters
	protected void setCardID(long cardID)
	{
		myCardID = cardID;
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
	
	protected void setExpirationMonth (int expirationMonth)
	{
		myExpirationMonth = expirationMonth;
	}
	
	protected void setExpirationYear (int expirationYear)
	{
		myExpirationYear = expirationYear;
	}
	
	protected void setCVC (int cvc)
	{
		myCVC = cvc;
	}
}

