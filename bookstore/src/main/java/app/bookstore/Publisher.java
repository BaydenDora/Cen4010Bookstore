package app.bookstore;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "publisher")
public class Publisher 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long myPublisherID;
	
	@Column(nullable = false, length = 100)
	private String myName;
	
	@ManyToMany(mappedBy = "myPublisher")
    private List<Author> authorsPublished = new ArrayList<>();
	
	@OneToMany(mappedBy = "myPublisher")
    private List<Book> booksPublished = new ArrayList<>();
	
	// No-Arg constructor
	public Publisher()
	{
		setID(0);
		setName("Publisher");
	}
	
	// Constructor
	public Publisher(long ID, String name)
	{
		setID(ID);
		setName(name);
	}
	
	//Copy constructor
	public Publisher(Publisher clonePublisher)
	{
		setID(clonePublisher.getID());
		setName(clonePublisher.getName());
	}
	
	// Getters
	public long getID ()
	{
		return myPublisherID;
	}
	
	public String getName ()
	{
		return myName;
	}
	
	// Setters
	protected void setID (long ID)
	{
		myPublisherID = ID;
	}
	
	protected void setName (String name)
	{
		myName = name;
	}
}
