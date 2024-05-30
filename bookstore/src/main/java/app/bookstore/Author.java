package app.bookstore;

public class Author 
{
	private int myAuthorID; // 9 digit number to differentiate different authors (Some authors may share names)
	private String myFirstName;
	private String myLastName;
	private String myBiography;
	private Publisher myPublisher;

	// No Arg Constructor
	public Author ()
	{
		setAuthorID (000000000);
		setFirstName ("Jo");
		setLastName ("Doe");
		setBiography ("Biography");
		setPublisher (new Publisher());
	}
	
	// Constructor
	public Author (int ID, String firstName, String lastName, String bio, Publisher publisher)
	{
		setAuthorID (ID);
		setFirstName (firstName);
		setLastName (lastName);
		setBiography (bio);
		setPublisher (publisher);
	}
	
	//Copy constructor
	public Author(Author cloneAuthor)
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
	
	public Publisher getPublisher ()
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
	
	public void setPublisher (Publisher publisher)
	{
		myPublisher = publisher;
	}
}
