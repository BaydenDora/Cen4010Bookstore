package app.bookstore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "book")
public class Book 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int myBookID; // ISBNs are 13-digit numbers
	
	@Column(name = "ISBN", nullable = false, length = 100)
	private long myISBN; // ISBNs are 13-digit numbers

	@Column(name = "BookName", nullable = false, length = 500)
	private String myTitle;
	
	@Column(name = "BookDescription", nullable = false, length = 1000)
	private String myDescription;
	

	@Column(name = "YearPublished", nullable = false, length = 4)
	private int myYearPublished;
	
	@ManyToOne
	@JoinColumn(name ="Author_ID", nullable = false)
	private Author myAuthor;
	
	@ManyToOne
	@JoinColumn(name ="Publisher_ID", nullable = false)
	private Publisher myPublisher;
	
	@Column(name = "Genre", nullable = false, length = 100)
	private String myGenre; //enum take examples from MySQL

	@Column(name = "CopiesSold", nullable = false, length = 1000)
	private int myCopiesSold;
	
	@Column(name = "Rating", nullable = false, length = 100)
	private int myRating; // Ratings are item based need class for reviews
	
	@Column(name = "Price", nullable = false, length = 100)
	private double myPrice;
	
	// No-Arg Constructor
	public Book()
	{
		setISBN (0000000000000L); // ISBNs are 13-digit numbers
		setTitle ("The Book");
		setDescription ("Book Description");
		setYearPublished (0000);
		setAuthor (new Author());
		setGenre ("Novel");
		setPublisher (myAuthor.getPublisher());
		setCopiesSold (0);
		setRating (0);
		setPrice (00.00);
	}
	
	
	
	// Constructor
	public Book (long ISBN, String title, String description, int yearPublished, Author author, Publisher publisher, String genre, int copiesSold, int rating, double price)
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
	public Book (Book cloneBook)
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
	
	public Author getAuthor()
	{
		return myAuthor;
	}
	
	public Publisher getPublisher()
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
	
	protected void setAuthor (Author author)
	{
		myAuthor = author;
	}
		
	protected void setPublisher (Publisher publisher)
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