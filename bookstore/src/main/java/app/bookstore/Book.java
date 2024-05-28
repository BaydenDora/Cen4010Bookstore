package app.bookstore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Book 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private long myISBN; // ISBNs are 13-digit numbers
	private String myTitle;
	private String myDescription;
	private int myYearPublished;
	private author myAuthor;
	private publisher myPublisher;
	private String myGenre;
	private int myCopiesSold;
	private int myRating; // This assumes a rating system from 0 - 10
	private double myPrice;
	
	// No Arg Constructor
	public book()
	{
		setISBN (0000000000000L); // ISBNs are 13-digit numbers
		setTitle ("The Book");
		setDescription ("Book Description");
		setYearPublished (0000);
		setAuthor (new author());
		setGenre ("Novel");
		setPublisher (myAuthor.getPublisher());
		setCopiesSold (0);
		setRating (0);
		setPrice (00.00);
	}
	
	
	
	// Constructor
	public book (long ISBN, String title, String description, int yearPublished, author author, publisher publisher, String genre, int copiesSold, int rating, double price)
	{
		setISBN (ISBN);
		setTitle (title);
		setDescription (description);
		setYearPublished (yearPublished);
		setAuthor (author);
		setPublisher (publisher);
		setGenre (genre);
		setCopiesSold (copiesSold);
		setRating (rating);
		setPrice (price);
	}
	
	// Copy Constructor
	public book (book cloneBook)
	{
		setISBN (cloneBook.getISBN());
		setTitle (cloneBook.getTitle());
		setDescription (cloneBook.getDescription());
		setYearPublished (cloneBook.getYearPublished());
		setAuthor (cloneBook.getAuthor());
		setPublisher (cloneBook.getPublisher());
		setGenre (cloneBook.getGenre());
		setCopiesSold (cloneBook.getCopiesSold());
		setRating (cloneBook.getRating());
		setPrice (cloneBook.getPrice());
	}
	
	
	
	// Getters
	public long getISBN()
	{
		return myISBN;
	}
	
	public String getTitle()
	{
		return myTitle;
	}
	
	public String getDescription()
	{
		return myDescription;
	}
	
	public int getYearPublished()
	{
		return myYearPublished;
	}
	
	public author getAuthor()
	{
		return myAuthor;
	}
	
	public publisher getPublisher()
	{
		return myPublisher;
	}
	
	public String getGenre()
	{
		return myGenre;
	}
	
	public int getCopiesSold()
	{
		return myCopiesSold;
	}
	
	public int getRating()
	{
		return myRating;
	}
	
	public double getPrice()
	{
		return myPrice;
	}
	
	
	// Setters
	protected void setISBN(long ISBN)
	{
		myISBN = ISBN;
		if (myISBN > 9999999999999L) myISBN = 9999999999999L;
		if (myISBN < 0L) myISBN = 0L;
	}
	
	protected void setTitle(String title)
	{
		myTitle = title;
		if (myTitle.trim() == "" || myTitle == null) myTitle = "Title";
	}
	
	protected void setDescription(String description)
	{
		myDescription = description;
		if (myDescription.trim() == "" || myDescription == null) myDescription = "Description";
	}
	
	protected void setYearPublished(int yearPublished)
	{
		myYearPublished = yearPublished;
		if (myYearPublished < 0) myYearPublished = 0;
	}
	
	protected void setAuthor (author author)
	{
		myAuthor = author;
	}
		
	protected void setPublisher (publisher publisher)
	{
		myPublisher = publisher;
	}
	
	protected void setGenre (String genre)
	{
		myGenre = genre;
		if (myGenre.trim() == "" || myGenre == null) myGenre = "Genre";
	}
	
	protected void setCopiesSold (int copiesSold)
	{
		myCopiesSold = copiesSold;
		if (myCopiesSold < 0) myCopiesSold = 0;
	}
		
	protected void setRating (int rating)
	{
		myRating = rating;
		if (myRating > 10) myRating = 10;
		if (myRating < 0) myRating = 0;
	}
	
	protected void setPrice (double price)
	{
		myPrice = price;
		if (myPrice < 0) myPrice = 0;
	}
}