package app.bookstore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class publisher 
{	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int myPubID;
	private String myName;
	
	// No-Arg constructor
	public publisher()
	{
		setName("Publisher");
	}
	
	// Constructor
	public publisher(String name)
	{
		setName(name);
	}
	
	//Copy constructor
	public publisher(publisher clonePublisher)
	{
		setName(clonePublisher.getName());
	}
	
	// Getters
	public String getName ()
	{
		return myName;
	}
	
	// Setters
	public void setName (String name)
	{
		myName = name;
	}
}
