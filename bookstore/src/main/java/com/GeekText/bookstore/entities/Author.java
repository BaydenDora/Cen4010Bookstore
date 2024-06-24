package com.GeekText.bookstore.entities;

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
	private int myAuthorID; // 9 digit number to differentiate different authors (Some authors may share names)
	
	@Column(name = "First Name", nullable = false, length = 100)
	private String myFirstName;
	
	@Column(name = "Last Name", nullable = false, length = 100)
	private String myLastName;
	
	@Column(name = "Biography", nullable = false, length = 1000)
	private String myBiography;
	
	@ManyToMany
	@JoinTable(
        name = "author_publisher", 
        joinColumns = @JoinColumn(name = "author_id"), 
        inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )

	private List<Publisher> myPublishers = new ArrayList<>();
	
	@OneToMany(mappedBy = "myAuthor")
    private List<Book> booksWritten = new ArrayList<>();
	

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
	
	public List<Publisher> getPublishers()
	{
		return myPublishers;
	}
	
	
	// Setters
	protected void setAuthorID (int ID)
	{
		myAuthorID = ID;
		if (myAuthorID < 0) myAuthorID = 0; 
	}
	
	protected void setFirstName (String firstName)
	{
		myFirstName = firstName;
	}
	
	protected void setLastName (String lastName)
	{
		myLastName = lastName;
	}
	
	protected void setName (String firstName, String lastName)
	{
		myFirstName = firstName;
		myLastName = lastName;
	}
	
	protected void setBiography (String bio)
	{
		myBiography = bio;
	}
	
	protected void setPublishers (List<Publisher> publishers)
	{
		myPublishers = publishers;
	}
}
