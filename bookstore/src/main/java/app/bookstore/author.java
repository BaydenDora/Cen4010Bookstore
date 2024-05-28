package app.bookstore;

public class author 
{
	private int myAuthorID; // 9 digit number to differentiate different authors (Some authors may share names)
	private String myFirstName;
	private String myLastName;
	private String myBiography;
	private publisher myPublisher;

	// No Arg Constructor
	public author ()
	{
		setAuthorID (000000000);
		setFirstName ("Jo");
		setLastName ("Doe");
		setBiography ("Biography");
		setPublisher (new publisher());
	}
	
	// Constructor
	public author (int ID, String firstName, String lastName, String bio, publisher publisher)
	{
		setAuthorID (ID);
		setFirstName (firstName);
		setLastName (lastName);
		setBiography (bio);
		setPublisher (publisher);
	}
	
	//Copy constructor
	public author(author cloneAuthor)
	{
		setAuthorID (cloneAuthor.getAuthorID());
		setFirstName (cloneAuthor.getFirstName());
		setLastName (cloneAuthor.getLastName());
		setBiography (cloneAuthor.getBiography());
		setPublisher (cloneAuthor.getPublisher());
	}
	
	// Getters
	public int getAuthorID ()
	{
		return myAuthorID;
	}
	
	public String getFirstName ()
	{
		return myFirstName;
	}
	
	public String getLastName ()
	{
		return myLastName;
	}
	
	public String getName ()
	{
		return myFirstName + " " + myLastName;
	}
	
	public String getBiography ()
	{
		return myBiography;
	}
	
	public publisher getPublisher ()
	{
		return myPublisher;
	}
	
	
	// Setters
	public void setAuthorID (int ID)
	{
		myAuthorID = ID;
		if (myAuthorID < 0) myAuthorID = 0; 
	}
	
	public void setFirstName (String firstName)
	{
		myFirstName = firstName;
	}
	
	public void setLastName (String lastName)
	{
		myLastName = lastName;
	}
	
	public void setName (String firstName, String lastName)
	{
		myFirstName = firstName;
		myLastName = lastName;
	}
	
	public void setBiography (String bio)
	{
		myBiography = bio;
	}
	
	public void setPublisher (publisher publisher)
	{
		myPublisher = publisher;
	}
}
