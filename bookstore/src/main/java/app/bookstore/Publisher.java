package app.bookstore;

public class Publisher 
{
	private String myName;
	
	// No-Arg constructor
	public Publisher()
	{
		setName("Publisher");
	}
	
	// Constructor
	public Publisher(String name)
	{
		setName(name);
	}
	
	//Copy constructor
	public Publisher(Publisher clonePublisher)
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
