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
	private int Publisher_ID;
	
	@Column(nullable = false, length = 50)
	private String PublisherName;
	
	@ManyToMany(mappedBy = "myPublishers")
    private List<Author> AuthorsPublished = new ArrayList<>();
	
	@OneToMany(mappedBy = "myPublisher")
    private List<Book> BooksPublished = new ArrayList<>();
	
	// No-Arg constructor
	public Publisher()
	{
		setID(0);
		setName("Publisher");
	}
	
	// Constructor
	public Publisher(int ID, String name)
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
	public int getID ()
	{
		return Publisher_ID;
	}
	
	public String getName ()
	{
		return PublisherName;
	}
	
	public List<Author> getAuthors ()
	{
		return AuthorsPublished;
	}
	
	public List<Book> getBooks ()
	{
		return BooksPublished;
	}
	
	// Setters
	protected void setID (int ID)
	{
		Publisher_ID = ID;
	}
	
	protected void setName (String name)
	{
		PublisherName = name;
	}
	
	protected void setAuthors (List<Author> authors)
	{
		AuthorsPublished = authors;
	}
	
	protected void setBooks (List<Book> books)
	{
		BooksPublished = books;
	}
}
