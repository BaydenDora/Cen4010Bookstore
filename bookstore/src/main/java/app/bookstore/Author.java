package app.bookstore;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "author")
public class Author 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int Author_ID; // 9 digit number to differentiate different authors (Some authors may share names)
	
	@Column(name = "First Name", nullable = false, length = 100)
	private String FirstName;
	
	@Column(name = "Last Name", nullable = false, length = 100)
	private String LastName;
	
	@Column(name = "Biography", nullable = false, length = 1000)
	private String Biography;
	
	@ManyToMany
	@JoinTable(
        name = "Publisher_ID", 
        joinColumns = @JoinColumn(name = "Author_id"), 
        inverseJoinColumns = @JoinColumn(name = "Publisher_id")
    )
	private List<Publisher> Publisher_ID = new ArrayList<>();
	
	@OneToMany(mappedBy = "myAuthor")
    private List<Book> BooksWritten = new ArrayList<>();
	

	// No-Arg Constructor
	public Author ()
	{
		setAuthorID (000000000);
		setFirstName ("Jo");
		setLastName ("Doe");
		setBiography ("Biography");
		setPublishers (new ArrayList<>());
	}
	
	// Constructor
	public Author (int ID, String firstName, String lastName, String bio, List<Publisher> publishers)
	{
		setAuthorID (ID);
		setFirstName (firstName);
		setLastName (lastName);
		setBiography (bio);
		setPublishers (publishers);
	}
	
	//Copy constructor
	public Author(Author cloneAuthor)
	{
		setAuthorID (cloneAuthor.getAuthorID());
		setFirstName (cloneAuthor.getFirstName());
		setLastName (cloneAuthor.getLastName());
		setBiography (cloneAuthor.getBiography());
		setPublishers (cloneAuthor.getPublishers());
	}
	
	// Getters
	public int getAuthorID ()
	{
		return Author_ID;
	}
	
	public String getFirstName ()
	{
		return FirstName;
	}
	
	public String getLastName ()
	{
		return LastName;
	}
	
	public String getName ()
	{
		return FirstName + " " + LastName;
	}
	
	public String getBiography ()
	{
		return Biography;
	}
	
	public List<Publisher> getPublishers()
	{
		return Publisher_ID;
	}
	
	
	// Setters
	protected void setAuthorID (int ID)
	{
		Author_ID = ID;
		if (Author_ID < 0) Author_ID = 0; 
	}
	
	protected void setFirstName (String firstName)
	{
		FirstName = firstName;
	}
	
	protected void setLastName (String lastName)
	{
		LastName = lastName;
	}
	
	protected void setName (String firstName, String lastName)
	{
		FirstName = firstName;
		LastName = lastName;
	}
	
	protected void setBiography (String bio)
	{
		Biography = bio;
	}
	
	protected void setPublishers (List<Publisher> publishers)
	{
		Publisher_ID = publishers;
	}
}
