package app.bookstore;

public class publisher 
{
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
